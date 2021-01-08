package com.example.demo.API;

import com.example.demo.infrastructure.INotification_T_DataAccess;
import com.example.demo.infrastructure.MemoryNotification_T_DataAccess;
import com.example.demo.model.notification;
import com.example.demo.model.response;
import com.example.demo.service.ChanalService;
import com.example.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("API/notification")
@RestController
public class NotificationCRUDController {

    private final NotificationService service;
    private final ChanalService service2;

    @Autowired
    public NotificationCRUDController(NotificationService service,ChanalService service2) {
        this.service=service;
        this.service2=service2;
    }

    @PostMapping
    public response Add(@RequestBody notification n)
    {
        return service.CreateTemplate(n);
    }

    @GetMapping
    public List<notification> GetALL(){
        return service.ReadALL();
    }
    @GetMapping(path = "{id}")
    public notification Get(@PathVariable("id") int id)
    {
        return service.ReadTemplate(id);
    }

    @PutMapping(path ="{id}")
    public response Update(@PathVariable("id")int id,@RequestBody notification n)
    {
        return service.UpdateTemplate(id, n);
    }

    @DeleteMapping(path ="{id}")
    public response Delete(@PathVariable("id")int id)
    {
        return service.deleteTemplate(id);
    }


    @PostMapping(path = "add/{id}/{id2}/{r}/{x}")
    public response AddChanal(@PathVariable("id")int templete_id,@PathVariable("id2") String sms_id,@PathVariable("r") String final_reciever,@PathVariable("x") String x)
    {
        ArrayList<String> placeHolders=new ArrayList<>();
        for(String str:x.split(" "))
        {
            placeHolders.add(str);
        }

        return service2.CreateNotification(templete_id,sms_id,final_reciever,placeHolders);
    }
    @DeleteMapping(path ="delete/sms/{id}")
    public response DeleteChanalS(@PathVariable("id")int id)
    {
        return service2.deleteNotificationSMS(id);
    }
    @DeleteMapping(path ="delete/email/{id}")
    public response DeleteChanalE(@PathVariable("id")int id)
    {
        return service2.deleteNotificationEMAIL(id);
    }
    @GetMapping(path = "read/sms/{id}")
    public notification GetChannalS(@PathVariable("id") int id)
    {
        return service2.ReadNotificationSMS(id);
    }
    @GetMapping(path = "read/email/{id}")
    public notification GetChannalE(@PathVariable("id") int id)
    {
        return service2.ReadNotificationEMAIL(id);
    }
    @PutMapping(path = "update/sms/{id2}/{r}/{x}")
    public response updateChanalS(@PathVariable("id2") int sms_id,@PathVariable("r") String final_reciever,@PathVariable("x") String x)

    {
        ArrayList<String> placeHolders=new ArrayList<>();
        for(String str:x.split(" "))
        {
            placeHolders.add(str);
        }
        return service2.UpdateNotificationSMS(sms_id,final_reciever,placeHolders);
    }
    @PutMapping(path = "update/email/{id2}/{r}/{x}")
    public response updateChanalE(@PathVariable("id2") int sms_id,@PathVariable("r") String final_reciever,@PathVariable("x") String x)

    {
        ArrayList<String> placeHolders=new ArrayList<>();
        for(String str:x.split(" "))
        {
            placeHolders.add(str);
        }
        return service2.UpdateNotificationEMAIL(sms_id,final_reciever,placeHolders);
    }

}
