package com.gaming.worspace.services.mappers;


import com.gaming.worspace.models.User;
import com.gaming.worspace.models.dto.request.UserRequestDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring") //Creates a Spring Bean automatically
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    List<UserRequestDTO> toUserDTOs(List<User> users);


    @InheritInverseConfiguration(name = "toUserDTO")
    User toUser(UserRequestDTO userRequestDTbO);


    @Mapping(target = "firstname", source = "firstname")
    @Mapping(target = "lastname", source = "lastname")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "birthday", source = "birthday")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "image", source = "image")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "city", source = "city")
    UserRequestDTO toUserDTO(User user);



}
