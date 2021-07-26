package com.gaming.worspace.services;


import com.gaming.worspace.dao.RoleRepository;
import com.gaming.worspace.dao.UserRepository;
import com.gaming.worspace.models.User;
import com.gaming.worspace.models.dto.request.UserRequestDTO;
import com.gaming.worspace.services.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {



    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserMapper userMapper;

    @Autowired
    public UserServices(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    //    CREATE ITEM
    public User createUser(UserRequestDTO userRequestDTO){
        User user = userMapper.toUser(userRequestDTO);




      return null;
    }



    //    READ ITEM BY ID
    //    READ ALL ITEMS
    //    UPDATE ITEM
    //    DELETE ITEM




}
