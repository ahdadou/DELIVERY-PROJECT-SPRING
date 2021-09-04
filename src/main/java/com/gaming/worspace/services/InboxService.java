package com.gaming.worspace.services;

import com.gaming.worspace.dao.InboxRepository;
import com.gaming.worspace.dao.MessagesRepository;
import com.gaming.worspace.exceptions.NotFoundException;
import com.gaming.worspace.models.Inbox;
import com.gaming.worspace.models.Messages;
import com.gaming.worspace.models.User;
import com.gaming.worspace.models.dto.request.MessageRequest;
import com.gaming.worspace.models.dto.response.InboxResponse;
import com.gaming.worspace.models.dto.response.MessagesResponse;
import com.gaming.worspace.services.mappers.InboxMapper;
import com.gaming.worspace.services.mappers.MessagesMapper;
import com.gaming.worspace.services.mappers.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InboxService {

    private UserServices userServices;
    private MessagesRepository messagesRepository;
    private InboxRepository inboxRepository;
    private UserMapper userMapper;
    private InboxMapper inboxMapper;
    private MessagesMapper messagesMapper;


    public InboxService(UserServices userServices, MessagesRepository messagesRepository, InboxRepository inboxRepository, UserMapper userMapper, InboxMapper inboxMapper, MessagesMapper messagesMapper) {
        this.userServices = userServices;
        this.messagesRepository = messagesRepository;
        this.inboxRepository = inboxRepository;
        this.userMapper = userMapper;
        this.inboxMapper = inboxMapper;
        this.messagesMapper = messagesMapper;
    }




    //get Inbox by User Email
    public List<InboxResponse> getInboxByEmail(String email){
        List<Inbox>  inboxes= inboxRepository.findAllByUserEmail(email)
                .orElseThrow(()-> new NotFoundException("Inbox Not Found"));

        return inboxMapper.toInboxResponse(inboxes);
    }

    //get Message By Inbox id
    public List<MessagesResponse> getMessagesByInboxId(long id){
        List<Messages>messagesResponses= messagesRepository.findAllByInboxId(id)
                .orElseThrow(()-> new NotFoundException("Inbox Not Found"));

        return messagesMapper.messagesToMessagesResponse(messagesResponses);

    }

    //add message
    public MessagesResponse addInbox(MessageRequest messages){
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

        return messagesMapper.messageToMessagesResponse(messages1);

    }


    public Long getInboxIdByReceiverAndSenderEmail(String senderEmail, String receiverEmail) {



        Inbox senderInbox = inboxRepository.findByUserEmailAndConnectedUserEmail(senderEmail,receiverEmail);
        if(senderInbox==null){
            User receiverUser = userServices.getUserByEmail(receiverEmail);
            User senderUser = userServices.getUserByEmail(senderEmail);
            Inbox sender = new Inbox();
            sender.setUser(senderUser);
            sender.setConnectedUser(receiverUser);
            sender.setLastMessage("");
            senderInbox = inboxRepository.save(sender);

            Inbox receiverInbox = inboxRepository.findByUserEmailAndConnectedUserEmail(receiverEmail,senderEmail);
            if(receiverInbox==null){
                Inbox receiver = new Inbox();
                receiver.setUser(receiverUser);
                receiver.setConnectedUser(senderUser);
                receiver.setLastMessage("");
                receiverInbox = inboxRepository.save(receiver);
            }
        }

        return senderInbox.getId();
    }
}
