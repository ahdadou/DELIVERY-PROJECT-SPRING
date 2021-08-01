package com.gaming.worspace.services;


import com.gaming.worspace.dao.NotificationRepository;
import com.gaming.worspace.models.Notification;
import com.gaming.worspace.models.User;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private NotificationRepository notificationRepository;
    private UserServices userServices;


    public NotificationService(NotificationRepository notificationRepository, UserServices userServices) {
        this.notificationRepository = notificationRepository;
        this.userServices = userServices;
    }



    //create Notification
    public Notification createNotification(Notification notification){
        return notificationRepository.save(notification);
    }





}
