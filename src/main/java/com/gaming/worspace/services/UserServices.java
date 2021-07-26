package com.gaming.worspace.services;


import com.gaming.worspace.dao.RoleRepository;
import com.gaming.worspace.dao.UserRepository;
import com.gaming.worspace.exceptions.BadRequestException;
import com.gaming.worspace.exceptions.NotFoundException;
import com.gaming.worspace.models.Role;
import com.gaming.worspace.models.User;
import com.gaming.worspace.models.dto.request.UserRequestDTO;
import com.gaming.worspace.models.enumerated.RoleName;
import com.gaming.worspace.services.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServices {



    private UserRepository userRepository;
    private RoleService roleService;
    private UserMapper userMapper;

    @Autowired
    public UserServices(UserRepository userRepository, RoleService roleService, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.userMapper = userMapper;
    }

    //todo:    CREATE ITEM
    public User createUser(UserRequestDTO userRequestDTO){
        User user = userMapper.toUser(userRequestDTO);
        //todo: Email Exist Or Not

        if(userRepository.existsByEmail(userRequestDTO.getEmail()))
            throw new BadRequestException(
                    "Email " + userRequestDTO.getEmail() + " taken");
        //todo: Username Exist Or Not
        if(userRepository.existsByUsername(userRequestDTO.getUsername()))
            throw new BadRequestException(
                    "Username " + userRequestDTO.getUsername() + " taken");

        //todo: add Roles To User
        user.setRoles(getRolesForNewUser(userRequestDTO.isToBeAdmin()));
        user.setActive(true);
        user.setEmailVerified(false);

      return userRepository.save(user);
    }


    //todo:     READ ITEM BY ID
    public User getUserById(long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("User Not Found"));
    }



    //todo:    READ ALL ITEMS
    public List<User> getAllUser(){
        return userRepository.findAll();

    }

    //    UPDATE ITEM
    //    DELETE ITEM
    public void deleteUser(Long id){
        if(userRepository.existsById(id))
            throw  new NotFoundException("User with Id "+id+" does not exist");
        userRepository.deleteById(id);
    }





    /**
     * Performs a quick check to see what roles the new user could be assigned to.
     *
     * @return list of roles for the new user
     */
    private Set<Role> getRolesForNewUser(Boolean isToBeMadeAdmin) {
        Set<Role> newUserRoles = new HashSet<>(roleService.findAll());
        if (!isToBeMadeAdmin) {
            newUserRoles.removeIf(Role::isAdminRole);
        }
        // logger.info("Setting user roles: " + newUserRoles);
        return newUserRoles;
    }


}
