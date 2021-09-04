package com.gaming.worspace.services.mappers;

import com.gaming.worspace.models.Inbox;
import com.gaming.worspace.models.Messages;
import com.gaming.worspace.models.User;
import com.gaming.worspace.models.dto.request.UserRequestDTO;
import com.gaming.worspace.models.dto.response.InboxResponse;
import com.gaming.worspace.models.dto.response.MessagesResponse;
import org.aspectj.bridge.Message;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InboxMapper {

    InboxMapper INSTANCE = Mappers.getMapper(InboxMapper.class);

    List<InboxResponse> toInboxResponse(List<Inbox> inboxes);


}
