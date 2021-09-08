package com.gaming.worspace.services;


import com.gaming.worspace.dao.NotificationRepository;
import com.gaming.worspace.exceptions.NotFoundException;
import com.gaming.worspace.models.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private NotificationRepository notificationRepository;
    private UserServices userServices;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, UserServices userServices, UserServices userServices1) {
        this.notificationRepository = notificationRepository;
        this.userServices = userServices1;
    }



    //create Notification
    public Notification createNotification(Notification notification){
        return notificationRepository.save(notification);
    }



    public List<Notification> getListOfNotifications(String email){
        return notificationRepository.findAllByUserEmail(email)
                .orElseThrow(()-> new NotFoundException("User Not Found"));

    }


}
