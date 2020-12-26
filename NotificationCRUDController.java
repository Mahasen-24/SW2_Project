package com.example.demo.API;

import com.example.demo.infrastructure.INotification_T_DataAccess;
import com.example.demo.infrastructure.MemoryNotification_T_DataAccess;
import com.example.demo.model.notification;
import com.example.demo.model.response;
import com.example.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RequestMapping("API/notification")
@RestController
public class NotificationCRUDController {

    private final NotificationService service;

    @Autowired
    public NotificationCRUDController(NotificationService service) {
        this.service=service;
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

}
