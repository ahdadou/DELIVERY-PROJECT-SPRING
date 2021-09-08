package com.gaming.worspace.services.mappers;


import com.gaming.worspace.models.User;
import com.gaming.worspace.models.dto.request.UserRequestDTO;
import com.gaming.worspace.models.dto.response.UserResponse;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring") //Creates a Spring Bean automatically
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    List<UserRequestDTO> toUserDTOs(List<User> users);

    User toUser(UserRequestDTO userRequestDTbO);


    @InheritInverseConfiguration(name = "toUser")
    UserRequestDTO toUserDTO(User user);


    UserResponse toUserResponse(User user);
    List<UserResponse> toUsersResponse(List<User> user);

}
