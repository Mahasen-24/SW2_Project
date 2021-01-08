package com.example.demo.Application;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static boolean randomSend() {
        Random rd = new Random(); // creating Random object
        return (rd.nextBoolean());
    }

    public static void DummyGetWay(){
        int choose, sMs_id, eMail_id, decision1, decision2;
        boolean choise = false;
        String url = "jdbc:mysql://localhost:3306/notificationproject";
        String user = "root";
        String password = "admin";

        while (!choise) {
            System.out.println("------------ Menu ------------");
            System.out.println("(1) Show SMS Que");
            System.out.println("(2) Show email Que");
            System.out.println("(3) Send One SMS Notification");
            System.out.println("(4) Send One email Notification");
            System.out.println("(5) Send all SMS Notifications");
            System.out.println("(6) Send all email Notifications");
            System.out.println("(7) exit");
            Scanner input = new Scanner (System.in);
            System.out.print("please enter your choose: ");
            choose = input.nextInt();

            if(choose<=0||choose>7)
            {
                System.out.println("please enter a valid Number");
                continue;
            }
            else if(choose==1) {
                try {
                    Connection connection = DriverManager.getConnection(url, user, password);
                    System.out.println("Connected to DataBase");

                    String sql = "SELECT * FROM smss";
                    Statement stat = connection.createStatement();
                    ResultSet result = stat.executeQuery(sql);
                    //int count = 0;
                    System.out.printf(" ------------------------------------------------------------------------------ \n");
                    System.out.printf("|SMS ID|Templete ID|Subject   |Content                               |Phone    |\n");
                    System.out.printf("|------|-----------|----------|--------------------------------------|---------|\n");
                    while (result.next()) {
                        //count++;
                        int sms_id = result.getInt("sms_id");
                        int templete_id = result.getInt("templete_id");
                        String subject = result.getString("subject");
                        String content = result.getString("content");
                        String phone = result.getString("phone");
                        //System.out.printf("SMS ID %d: %d - %s - %s - %s\n", sms_id, templete_id, subject, content, phone);
                        System.out.printf("|%-6d|%-11d|%-10s|%-38s|%-9s|\n", sms_id, templete_id, subject, content, phone);
                    }
                    System.out.printf(" ------------------------------------------------------------------------------ \n");
                    connection.close();
                } catch ( SQLException e) {
                    System.out.println("Oops! there is an error!");
                    e.printStackTrace();
                }
            }
            else if(choose==2)
            {
                try {
                    Connection connection = DriverManager.getConnection(url, user, password);
                    System.out.println("Connected to DataBase");

                    String sql = "SELECT * FROM emails";
                    Statement stat = connection.createStatement();
                    ResultSet result = stat.executeQuery(sql);
                    //int count = 0;
                    System.out.printf(" -------------------------------------------------------------------------------------- \n");
                    System.out.printf("|email ID|Templete ID|Subject   |Content                               |email          |\n");
                    System.out.printf("|--------|-----------|----------|--------------------------------------|---------------|\n");
                    while (result.next()) {
                        //count++;
                        int email_id = result.getInt("email_id");
                        int templete_id = result.getInt("templete_id");
                        String subject = result.getString("subject");
                        String content = result.getString("content");
                        String email = result.getString("email");
                        System.out.printf("|%-8d|%-11d|%-10s|%-38s|%-15s|\n", email_id, templete_id, subject, content, email);
                    }
                    System.out.printf(" -------------------------------------------------------------------------------------- \n");
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Oops! there is an error!");
                    e.printStackTrace();
                }
            }
            else if(choose==3) {
                try {
                    Connection connection = DriverManager.getConnection(url, user, password);
                    System.out.println("Connected to DataBase");
                    System.out.print("please enter sms id: ");
                    sMs_id = input.nextInt();

                    String sql1 = "SELECT* FROM smss WHERE sms_id=" + sMs_id;
                    Statement stat1 = connection.createStatement();
                    ResultSet result1 = stat1.executeQuery(sql1);
                    if(result1.next()) {
                        boolean check = randomSend();
                        if (check) {
                            String sql = "DELETE FROM smss WHERE sms_id=" + sMs_id;
                            Statement stat = connection.createStatement();
                            int result = stat.executeUpdate(sql);
                            System.out.print("sms was sent successfully\n");

                        } else {
                            System.out.print("failure\n");
                        }
                    }else
                    {
                        System.out.print("this id is not found\n");
                    }

                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Oops! there is an error!");

                }
            }
            else if(choose==4) {
                try {
                    Connection connection = DriverManager.getConnection(url, user, password);
                    System.out.println("Connected to DataBase");
                    System.out.print("please enter email id: ");
                    eMail_id = input.nextInt();

                    String sql1 = "SELECT* FROM emails WHERE email_id=" + eMail_id;
                    Statement stat1 = connection.createStatement();
                    ResultSet result1 = stat1.executeQuery(sql1);
                    if(result1.next()) {
                        boolean check = randomSend();
                        if (check) {
                            String sql = "DELETE FROM emails WHERE email_id=" + eMail_id;
                            Statement stat = connection.createStatement();
                            int result = stat.executeUpdate(sql);
                            System.out.print("email was sent successfully\n");

                        } else {
                            System.out.print("email was sent failure\n");
                        }
                    }else
                    {
                        System.out.print("this id is not found\n");
                    }
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Oops! there is an error!");

                }
            }
            else if(choose==5) {

                try {
                    Connection connection1 = DriverManager.getConnection(url, user, password);
                    Connection connection2 = DriverManager.getConnection(url, user, password);
                    System.out.println("Connected to DataBase");
                    System.out.print("Are you Sure you want to Send all sms Notifications? (YES: 1 - NO: 0): ");
                    decision1 = input.nextInt();
                    int id;
                    if (decision1== 1)
                    {
                        int count=0;

                        String sql = "SELECT * FROM smss";
                        Statement stat = connection1.createStatement();
                        ResultSet result = stat.executeQuery(sql);
                        Vector<Integer>to_delete=new Vector<>();


                        while (result.next())
                        {
                            count++;

                            id=result.getInt("sms_id");
                            boolean check=randomSend();
                            if(check)
                            {

                                to_delete.add(id);
                                System.out.println("sms with id="+id+" has sent successfully");
                            }
                            else
                            {
                                System.out.println("sms with id="+id+" failed");
                                continue;
                            }
                        }
                        for(int i=0;i<to_delete.size();i++)
                        {
                            String sql2 = "DELETE FROM smss where sms_id="+to_delete.get(i);
                            Statement stat2 = connection2.createStatement();
                            int result2 = stat.executeUpdate(sql2);
                        }

                        if(count==0)
                        {
                            System.out.println(" queue is empty ");
                            continue;
                        }

                        connection1.close();
                        connection2.close();
                    } else if (decision1 == 0) {
                        continue;
                    }
                } catch (SQLException e) {
                    System.out.println("Oops! there is an error!");

                }
            }
            else if(choose==6) {
                try {
                    Connection connection1 = DriverManager.getConnection(url, user, password);
                    Connection connection2 = DriverManager.getConnection(url, user, password);
                    System.out.println("Connected to DataBase");
                    System.out.print("Are you Sure you want to Send all email Notifications? (YES: 1 - NO: 0): ");
                    decision1 = input.nextInt();
                    int id;
                    if (decision1== 1)
                    {
                        int count=0;

                        String sql = "SELECT * FROM emails";
                        Statement stat = connection1.createStatement();
                        ResultSet result = stat.executeQuery(sql);
                        Vector<Integer>to_delete=new Vector<>();


                        while (result.next())
                        {
                            count++;
                            id=result.getInt("email_id");
                            boolean check=randomSend();
                            if(check)
                            {

                                to_delete.add(id);
                                System.out.println("emails with id="+id+" has sent successfully");
                            }
                            else
                            {
                                System.out.println("email with id="+id+" failed");
                                continue;
                            }
                        }
                        for(int i=0;i<to_delete.size();i++)
                        {
                            String sql2 = "DELETE FROM emails where email_id="+to_delete.get(i);
                            Statement stat2 = connection2.createStatement();
                            int result2 = stat.executeUpdate(sql2);
                        }

                        if(count==0)
                        {
                            System.out.println(" queue is empty ");
                            continue;
                        }


                        connection1.close();
                        connection2.close();
                    } else if (decision1 == 0) {
                        continue;
                    }
                } catch (SQLException e) {
                    System.out.println("Oops! there is an error!");

                }
            }
            else if (choose==7)
            {
                choise=true;
            }
        }

    }

    public static void main (String[] args)
    {
        DummyGetWay();
    }

}