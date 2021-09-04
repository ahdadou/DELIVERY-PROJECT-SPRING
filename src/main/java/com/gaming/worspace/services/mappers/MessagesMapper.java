package com.gaming.worspace.services.mappers;

import com.gaming.worspace.models.Messages;
import com.gaming.worspace.models.dto.response.MessagesResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MessagesMapper {


    MessagesMapper INSTANCE = Mappers.getMapper(MessagesMapper.class);
    List<MessagesResponse> messagesToMessagesResponse(List<Messages> messages);
    MessagesResponse messageToMessagesResponse(Messages messages);

}
