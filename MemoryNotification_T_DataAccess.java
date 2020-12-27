package com.example.demo.infrastructure;

import com.example.demo.model.notification;
import com.example.demo.model.response;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("DataAccessM")
public class MemoryNotification_T_DataAccess implements INotification_T_DataAccess{
    private static List<notification> templetes=new ArrayList<>();

    public  List<notification> ReadALL(){
        return templetes;
    }

    @Override
    public notification ReadTemplate(int id) {
        for(int i=0;i<templetes.size();i++)
        {
            if(templetes.get(i).getId()==id)
            {
                return templetes.get(i);
            }

        }

        return null;
    }

    @Override
    public response CreateTemplate(notification n) {
        response r=new response();
        for(int i=0;i<templetes.size();i++)
        {
            if(templetes.get(i).getId()==n.getId())
            {
                r.setStatus(false);
                return r;
            }

        }
            templetes.add(n);
            r.setStatus(true);
            return r;
    }

    @Override
    public response UpdateTemplate(int id,notification n) {
        response r=new response();
        for(int i=0;i<templetes.size();i++)
        {
            if(templetes.get(i).getId()==id)
            {
                templetes.set(i,n);
                r.setStatus(true);
                return r;
            }

        }
            r.setStatus(false);
            return r;

    }

    @Override
    public response deleteTemplate(int id) {
        response r=new response();
        for(int i=0;i<templetes.size();i++)
        {
            if(templetes.get(i).getId()==id)
            {
                templetes.remove(i);
                r.setStatus(true);
                return r;
            }

        }
            r.setStatus(false);
            return r;
    }

}
