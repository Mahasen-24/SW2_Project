package com.example.demo.service;

import com.example.demo.infrastructure.DataBase_MYSQL_Notification_T_DataAccess;
import com.example.demo.infrastructure.IChannel_T_DataAccess;
import com.example.demo.model.notification;
import com.example.demo.model.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class ChanalService {

    private final IChannel_T_DataAccess DataAccess1;
    private final IChannel_T_DataAccess DataAccess2;

    @Autowired
    public ChanalService(@Qualifier("DataAccessSMS")IChannel_T_DataAccess dataAccess1,@Qualifier("DataAccessEMAIL")IChannel_T_DataAccess dataAccess2) {
        DataAccess1 = dataAccess1;
        DataAccess2 = dataAccess2;

    }

    public response CreateNotification(int templete_id, String sms_id,  String final_reciever,ArrayList<String> placeHolders)
    {
        DataBase_MYSQL_Notification_T_DataAccess notification=new DataBase_MYSQL_Notification_T_DataAccess();
        notification n=notification.ReadTemplate(templete_id);
        if(n.getChannel().equals("email"))
        {
            return DataAccess2.CreateNotification(templete_id,sms_id,final_reciever,placeHolders);
        }
        else if(n.getChannel().equals("sms")){
            return DataAccess1.CreateNotification(templete_id, sms_id, final_reciever, placeHolders);
        }
        else
        {
            response r=new response();
            r.setStatus(false);

            return r;
        }

    }
    public response UpdateNotificationSMS(int id,String final_reciever,ArrayList<String> placeHolders)
    {
            return DataAccess1.UpdateNotification(id, final_reciever, placeHolders);

    }
    public response UpdateNotificationEMAIL(int id,String final_reciever,ArrayList<String> placeHolders)
    {
        return DataAccess2.UpdateNotification(id, final_reciever, placeHolders);

    }
     public response deleteNotificationSMS(int id)
    {
        return DataAccess1.deleteNotification(id);
    }
    public response deleteNotificationEMAIL(int id)
    {
        return DataAccess2.deleteNotification(id);
    }
     public notification ReadNotificationSMS(int id)
    {
        return DataAccess1.ReadNotification(id);
    }
    public notification ReadNotificationEMAIL(int id)
    {
        return DataAccess2.ReadNotification(id);
    }
}


