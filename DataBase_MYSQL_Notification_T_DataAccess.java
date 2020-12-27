package com.example.demo.infrastructure;

import com.example.demo.model.notification;
import com.example.demo.model.response;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

@Repository("DataAccessMYSQL")
public class DataBase_MYSQL_Notification_T_DataAccess implements INotification_T_DataAccess{

    private Connection my_con;
    public DataBase_MYSQL_Notification_T_DataAccess()
    {
        try
        {
            my_con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/NotificationProject","root","admin");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
    public notification ReadTemplate(int id) {

        //create a statement
        java.sql.Statement my_statement = null;
        try
        {
            my_statement = my_con.createStatement();
        }
        catch (SQLException e1)
        {
            System.out.println(e1.getMessage());
            e1.printStackTrace();
        }
        //execute sql quary

        String sql="select* from notification_templetes where templete_id="+id;
        ResultSet my_rs = null;
        try
        {
            my_rs = my_statement.executeQuery(sql);
        }
        catch (SQLException e1)
        {
            System.out.println(e1.getMessage());
            e1.printStackTrace();
        }
        String subject= null,content = null,language= null,reciever=null,channel=null;
        int placeholders=0;
        try
        {
            while(my_rs.next())
            {
                subject=my_rs.getString("subject");
                content= my_rs.getString("content");
                language=my_rs.getString("language");
                placeholders=Integer.parseInt(my_rs.getString("num_placholders"));
                reciever=my_rs.getString("reciever");
                channel=my_rs.getString("channal");
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        notification notification_to_read = new notification(id,placeholders,content,subject,language,channel);
       if(notification_to_read.getcontent()==null)
           return null;
        return notification_to_read;

    }

    @Override
    public List<notification> ReadALL() {
        ArrayList<notification> all_notifications=new ArrayList<>();

        //create a statement
        Statement my_statement = null;
        try
        {
            my_statement = my_con.createStatement();
            String sql="select* FROM notification_templetes";
            ResultSet my_rs = null;
            my_rs = my_statement.executeQuery(sql);
            String subject= null,content = null,language= null,reciever=null;
            int placeholders=0,templete_id=0;
            try
            {
                while(my_rs.next())
                {
                    subject=my_rs.getString("subject");
                    content= my_rs.getString("content");
                    language=my_rs.getString("language");
                    placeholders=Integer.parseInt(my_rs.getString("num_placholders"));
                    reciever=my_rs.getString("reciever");
                    templete_id=my_rs.getInt("templete_id");
                    notification notification_to_read=new notification();
                    notification_to_read.setId(templete_id);
                    notification_to_read.setcontent(content);
                    notification_to_read.setSubject(subject);
                    notification_to_read.setLanguage(language);
                    notification_to_read.setNum_placeHolder(placeholders);
                    notification_to_read.setReceiver(reciever);

                    all_notifications.add(notification_to_read);

                }
            }
            catch (SQLException e)
            {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return all_notifications
                ;
    }

    @Override
    public response CreateTemplate(notification n) {
        response r=new response();

        PreparedStatement ps = null;
        try
        {
            ps = my_con.prepareStatement("INSERT INTO notification_templetes (templete_id,subject,content,language,num_placholders,reciever,channal)values (?,?,?,?,?,?,?)");

            ps.setInt(1, n.getId());
            ps.setString(2,n.getSubject());

            ps.setString(3,n.getcontent());

            ps.setString(4,n.getLanguage());

            ps.setInt(5,n.getNum_placeHolder());

            ps.setString(6,n.getReceiver());
            ps.setString(7,n.getChannel());
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        int result = 0;
        try
        {
            result = ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        if(result==0)
        {
            r.setStatus(false);
        }
        else if(result==1)
        {
            r.setStatus(true);
        }

        return r;

    }

    @Override
    public response UpdateTemplate(int id, notification n) {
        response r=new response();
        notification up=new notification(n.getId(),n.getNum_placeHolder(),n.getcontent()
        ,n.getSubject(),n.getLanguage(),n.getChannel());

        PreparedStatement ps = null;
        try
        {
            ps = my_con.prepareStatement("UPDATE notification_templetes set templete_id=?,subject=?,content=?,language=?,num_placholders=?,reciever=? ,channal=? where templete_id="+id);
            ps.setInt(1, up.getId());

            ps.setString(2,up.getSubject());

            ps.setString(3,up.getcontent());

            ps.setString(4,up.getLanguage());

            ps.setInt(5,up.getNum_placeHolder());
            ps.setString(6,up.getReceiver());
            ps.setString(7,up.getChannel());
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        int result = 0;
        try
        {
            result = ps.executeUpdate();
            ps.setString(5,n.getReceiver());
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        if(result==0)
        {
            r.setStatus(false);
        }
        else if(result==1)
        {
            r.setStatus(true);
        }

        return r;

    }

    @Override
    public response deleteTemplate(int id) {
        int my_rs = 0;
        response r =new response();

        java.sql.Statement my_statement = null;
        try
        {
            my_statement = my_con.createStatement();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        //execute sql quary

        String sql="Delete from notification_templetes where templete_id="+id;
        try
        {
            my_rs =my_statement.executeUpdate(sql);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        if(my_rs==0)
        {
            r.setStatus(false);
        }
        else if(my_rs==1)
        {
            r.setStatus(true);
        }

        return r;

    }
}
