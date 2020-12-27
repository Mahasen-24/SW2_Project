package com.example.demo.infrastructure;

import com.example.demo.model.notification;
import com.example.demo.model.response;

import java.util.List;
import java.util.Map;

public interface INotification_T_DataAccess {
    public notification ReadTemplate(int id);
    public List<notification> ReadALL();

    public response CreateTemplate(notification n);

    public response UpdateTemplate(int id,notification n);

    public response deleteTemplate(int id);
}
