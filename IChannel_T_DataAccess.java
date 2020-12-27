package com.example.demo.infrastructure;

import com.example.demo.model.notification;
import com.example.demo.model.response;

import java.util.ArrayList;

public interface IChannel_T_DataAccess {

    public response CreateNotification(int templete_id, String id, String final_reciever,ArrayList<String>plaseholder);

    public response UpdateNotification(int sms_id,String final_reciever,ArrayList<String>plaseholder);

    public response deleteNotification(int id);

    public notification ReadNotification(int id);
}
