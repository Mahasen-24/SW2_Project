package com.example.demo.infrastructure;

import com.example.demo.model.notification;
import com.example.demo.model.response;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository("DataAccessEMAIL")
public class DataBase_MYSQL_email_T_DataAccess implements IChannel_T_DataAccess{

    private Connection my_con=null;
    public DataBase_MYSQL_email_T_DataAccess()
    {
        try
        {
            my_con = DriverManager.getConnection("jdbc:mysql://localhost:3306/NotificationProject","root","admin");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    /////////////////////////////////for placeholders//////////////////////////
    public static String add_place_holders(String str,ArrayList<String> placeHolders)
    {
        String myString = null;
        int index=0;

        for(char s : str.toCharArray())
        {
            if(s=='x')
            {
                String placeholder=placeHolders.get(index);
                for(char z : placeholder.toCharArray())
                {
                    myString +=z;
                }
                index++;
            }
            else
            {
                if(myString!=null)
                {
                    myString+=s;
                }
                else
                {
                    myString = myString.valueOf(s);
                }
            }
        }

        return myString;
    }

    @Override
    public response CreateNotification(int templete_id, String id, String final_reciever,ArrayList<String>placeHolders) {

        int email_id = Integer.parseInt(id);
        notification n=null;
        DataBase_MYSQL_Notification_T_DataAccess notification=new DataBase_MYSQL_Notification_T_DataAccess();
        n=notification.ReadTemplate(templete_id);
        response r=new response();
        String final_content=null;

        PreparedStatement ps = null;
        try
        {
            ps = my_con.prepareStatement("INSERT INTO emails (email_id,subject,content,email,templete_id)values (?,?,?,?,?)");
            ps.setInt(1,email_id);
            ps.setString(2,n.getSubject());
            final_content=add_place_holders(n.getcontent(),placeHolders);
            ps.setString(3,final_content);
            ps.setString(4,final_reciever);
            ps.setInt(5,templete_id);

            int result = 0;
            result = ps.executeUpdate();
            if(result==0)
            {
                r.setStatus(false);
            }
            else if(result==1)
            {
                r.setStatus(true);
            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


        return r;
    }

    @Override
    public response UpdateNotification(int email_id, String final_reciever, ArrayList<String>placeHolders) {

        response r=new response();
        notification n=null;
        String phone=final_reciever;
        n=this.ReadNotification(email_id);
        PreparedStatement ps = null;
        try
        {
            ps = my_con.prepareStatement("UPDATE emails set subject=?,content=?,email=? where email_id= "+email_id);
            ps.setString(1,n.getSubject());
            DataBase_MYSQL_Notification_T_DataAccess m=new DataBase_MYSQL_Notification_T_DataAccess();
            notification base_notification=new notification();
            base_notification=m.ReadTemplate(n.getId());
            String final_content=add_place_holders(base_notification.getcontent(),placeHolders);
            ps.setString(2,final_content);
            ps.setString(3,phone);
            int result = 0;
            result = ps.executeUpdate();
            if(result==0)
            {
                r.setStatus(false);
            }
            else if(result==1)
            {
                r.setStatus(true);
            }
        }
        catch (SQLException e1)
        {
            System.out.println(e1.getMessage());
            e1.printStackTrace();
        }

        return r;
    }

    @Override
    public response deleteNotification(int email_id) {
        int my_rs = 0;
        response r =new response();

        java.sql.Statement my_statement = null;
        try
        {
            my_statement = my_con.createStatement();
            String sql="Delete from emails where email_id="+email_id;
            my_rs =my_statement.executeUpdate(sql);
            if(my_rs==0)
            {
                r.setStatus(false);
            }
            else if(my_rs==1)
            {
                r.setStatus(true);
            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


        return r;
    }

    @Override
    public notification ReadNotification(int email_id) {
        notification email_to_read=new notification();
        Statement my_statement = null;
        try
        {
            my_statement = my_con.createStatement();
            String sql="select* from emails where email_id="+email_id;
            ResultSet my_rs = null;
            my_rs = my_statement.executeQuery(sql);
            String subject= null,content = null,reciever=null;
            int templete_id=0;
            while(my_rs.next())
            {
                subject=my_rs.getString("subject");
                content= my_rs.getString("content");
                reciever=my_rs.getString("email");
                templete_id=my_rs.getInt("templete_id");
                email_to_read.setId(email_id);
                email_to_read.setcontent(content);
                email_to_read.setSubject(subject);
                email_to_read.setReceiver(reciever);
                email_to_read.setId(templete_id);
            }
        }

        catch (SQLException e1)
        {
            System.out.println(e1.getMessage());
            e1.printStackTrace();
        }
        return email_to_read;

    }
}
