package com.example.demo.service;

import com.example.demo.infrastructure.INotification_T_DataAccess;
import com.example.demo.model.notification;
import com.example.demo.model.response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final INotification_T_DataAccess DataAccess;

    @Autowired
    public NotificationService(@Qualifier("DataAccessM") INotification_T_DataAccess DataAccess) {
        this.DataAccess=DataAccess;
    }


    public List<notification> ReadALL(){
        return DataAccess.ReadALL();
    }


    public notification ReadTemplate(int id)
    {
        return DataAccess.ReadTemplate(id);
    }

    public response CreateTemplate(notification n)
    {
        return DataAccess.CreateTemplate(n);
    }


    public response UpdateTemplate(int id,notification n)
    {
        return DataAccess.UpdateTemplate(id, n);
    }

    public response deleteTemplate(int id)
    {
        return DataAccess.deleteTemplate(id);
    }
}
