package com.gaming.worspace.services;


import com.gaming.worspace.dao.UserRepository;
import com.gaming.worspace.exceptions.BadRequestException;
import com.gaming.worspace.exceptions.NotFoundException;
import com.gaming.worspace.models.City;
import com.gaming.worspace.models.Role;
import com.gaming.worspace.models.SERVICE_TYPE;
import com.gaming.worspace.models.User;
import com.gaming.worspace.models.dto.request.UserRequestDTO;
import com.gaming.worspace.models.enumerated.Gender;
import com.gaming.worspace.models.enumerated.Type;
import com.gaming.worspace.services.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServices {


    private CityService cityService;
    private UserRepository userRepository;
    private RoleService roleService;
    private ServiceTypeService serviceTypeService;
    private UserMapper userMapper;

    @Autowired
    public UserServices(CityService cityService, UserRepository userRepository, RoleService roleService, ServiceTypeService serviceTypeService, UserMapper userMapper) {
        this.cityService = cityService;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.serviceTypeService = serviceTypeService;
        this.userMapper = userMapper;
    }

    //todo:    CREATE ITEM
    public Optional<User> createUser(UserRequestDTO userRequestDTO){
        User user = userMapper.toUser(userRequestDTO);
        System.out.println(user);
        //todo: Email Exist Or Not

        if(userRepository.existsByEmail(userRequestDTO.getEmail()))
            throw new BadRequestException(
                    "Email " + userRequestDTO.getEmail() + " taken");


        //todo: add Roles To User
        user.setRoles(getRolesForNewUser(userRequestDTO.isToBeAdmin()));
        user.setActive(true);
        user.setEmailVerified(false);
        //todo: add service type

        SERVICE_TYPE service=getService_type(userRequestDTO.isDelivery());
        user.setService_type(service);
        //todo: add CITY
        City city = cityService.getCityByName(userRequestDTO.getCityName());
        user.setCity(city);


        //todo: add gender type
        if(userRequestDTO.isMan())
            user.setGender(Gender.MALE);
        else
            user.setGender(Gender.FEMALE);

        return Optional.ofNullable(userRepository.save(user));
    }


    //todo:     READ ITEM BY ID
    public User getUserById(long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("User Not Found"));
    }



    //todo:     READ ITEM BY EMAIL
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new NotFoundException("User Not Found"));
    }



    //todo:    READ ALL ITEMS
    public List<User> getAllUser(){
        return userRepository.findAll();

    }

    //    DELETE ITEM
    public void deleteUserById(Long id){
        if(userRepository.existsById(id))
            throw  new NotFoundException("User with Id "+id+" does not exist");
        userRepository.deleteById(id);
    }


    public List<User> getAllUserBySERVICETYPE(String servicetype) {
        SERVICE_TYPE service_type = serviceTypeService.getServiceByType(Type.DELIVERYMAN);
        List<User> users = userRepository.findByService_typeId(service_type.getID());
        return users;
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

    private SERVICE_TYPE getService_type(boolean isDelivery){
        SERVICE_TYPE service;
        if(isDelivery)
            service = serviceTypeService.getServiceByType(Type.DELIVERYMAN);
        else
            service = serviceTypeService.getServiceByType(Type.CLIENT);
       return  service;
    }



}
