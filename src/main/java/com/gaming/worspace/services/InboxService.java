package com.gaming.worspace.services;

import antlr.debug.MessageAdapter;
import com.gaming.worspace.dao.InboxRepository;
import com.gaming.worspace.dao.MessagesRepository;
import com.gaming.worspace.exceptions.NotFoundException;
import com.gaming.worspace.models.Inbox;
import com.gaming.worspace.models.Messages;
import com.gaming.worspace.models.User;
import com.gaming.worspace.models.dto.request.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InboxService {

    private UserServices userServices;
    private MessagesRepository messagesRepository;
    private InboxRepository inboxRepository;

    public InboxService(UserServices userServices, MessagesRepository messagesRepository, InboxRepository inboxRepository) {
        this.userServices = userServices;
        this.messagesRepository = messagesRepository;
        this.inboxRepository = inboxRepository;
    }




    //get Inbox by User Email
    public List<Inbox> getInboxByEmail(String email){
        return inboxRepository.findAllByUserEmail(email)
                .orElseThrow(()-> new NotFoundException("Inbox Not Found"));
    }

    //get Message By Inbox id
    public List<Messages> getMessagesByInboxId(long id){
        return messagesRepository.findAllByInboxId(id)
                .orElseThrow(()-> new NotFoundException("Inbox Not Found"));

    }

    //add message
    public Messages addInbox(MessageRequest messages){
        //getUsers
        User senderUser = userServices.getUserByEmail(messages.getSender_email());
        User receiverUser = userServices.getUserByEmail(messages.getReceiver_email());

        //get Sender Inbox
        Inbox senderInbox = inboxRepository.findByUserEmailAndConnectedUserEmail(messages.getSender_email(),messages.getReceiver_email());
        if(senderInbox==null){
            Inbox sender = new Inbox();
            sender.setUser(senderUser);
            sender.setConnectedUser(receiverUser);
            sender.setLastMessage(messages.getMessage());
            senderInbox = inboxRepository.save(sender);
        }else{
            senderInbox.setLastMessage(messages.getMessage());
            senderInbox = inboxRepository.save(senderInbox);

        }


        //get Receiver Inbox
        Inbox receiverInbox = inboxRepository.findByUserEmailAndConnectedUserEmail(messages.getReceiver_email(),messages.getSender_email());
        if(receiverInbox==null){
            Inbox sender = new Inbox();
            sender.setUser(receiverUser);
            sender.setConnectedUser(senderUser);
            sender.setLastMessage(messages.getMessage());
            receiverInbox = inboxRepository.save(sender);
        }else{
            receiverInbox.setLastMessage(messages.getMessage());
            receiverInbox = inboxRepository.save(receiverInbox);
        }

        //save Message in sender Inbox
        Messages messages1 = new Messages();
        messages1.setInbox(senderInbox);
        messages1.setUser_sender(senderUser);
        messages1.setMsg(messages.getMessage());
        messages1 = messagesRepository.save(messages1);


        //save Message in receiver Inbox
        messages1 = new Messages();
        messages1.setUser_sender(senderUser);
        messages1.setMsg(messages.getMessage());
        messages1.setInbox(receiverInbox);
        messages1 = messagesRepository.save(messages1);

        return messages1;

    }








}
