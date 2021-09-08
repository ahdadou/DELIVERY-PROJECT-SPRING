package com.gaming.worspace.services;


import com.gaming.worspace.dao.UserRepository;
import com.gaming.worspace.exceptions.BadRequestException;
import com.gaming.worspace.exceptions.NotFoundException;
import com.gaming.worspace.models.City;
import com.gaming.worspace.models.Role;
import com.gaming.worspace.models.Stype;
import com.gaming.worspace.models.User;
import com.gaming.worspace.models.dto.request.UserRequestDTO;
import com.gaming.worspace.models.dto.response.UserResponse;
import com.gaming.worspace.models.enumerated.Gender;
import com.gaming.worspace.models.enumerated.Type;
import com.gaming.worspace.services.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

        Stype stype = getService_type(false);
        user.setStype(stype);
        //todo: add CITY
        user.setCity(userRequestDTO.getCityName());
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

    //todo:     READ ITEM DTO BY EMAIL
    public UserResponse getUserDtoByEmail(String email){
        User user= userRepository.findByEmail(email)
                .orElseThrow(()-> new NotFoundException("User Not Found"));
        return userMapper.toUserResponse(user);
    }



    //todo:    READ ALL ITEMS
    public List<UserResponse> getAllUser(String email){
        List<User> user= userRepository.findAllWithoutThis(email);
        return userMapper.toUsersResponse(user);

    }

    //    DELETE ITEM
    public void deleteUserById(Long id){
        if(userRepository.existsById(id))
            throw  new NotFoundException("User with Id "+id+" does not exist");
        userRepository.deleteById(id);
    }


    public List<User> getAllUserDelevries() {
        Stype service_type = serviceTypeService.getServiceByType(Type.DELIVERYMAN);
        List<User> users = userRepository.findByStypeId(service_type.getId());



        return users;
    }

    public List<UserResponse> getAllUserDelevriesDto() {
        List<UserResponse> users = userMapper.toUsersResponse(getAllUserDelevries());
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

    private Stype getService_type(boolean isDelivery){
        Stype service;
        if(isDelivery)
            service = serviceTypeService.getServiceByType(Type.DELIVERYMAN);
        else
            service = serviceTypeService.getServiceByType(Type.CLIENT);
       return  service;
    }


    public List<UserResponse> getAllUserDelivriesByCity(String city) {
        Stype service_type = serviceTypeService.getServiceByType(Type.DELIVERYMAN);
        List<User> users = userRepository.findByCityNameAndStypeId(city,service_type.getId());
        List<UserResponse> usersDto = userMapper.toUsersResponse(users);
        return usersDto;
    }

    public List<UserResponse> findByCityNameAndRatingAverageQuery(String city,int rating) {
        System.out.println(city+"  ...  "+rating);
        Stype service_type = serviceTypeService.getServiceByType(Type.DELIVERYMAN);
        List<User> users = new ArrayList<>();
        if(city.isEmpty() && rating  == -1){
            System.out.println(city+"  .-------.  "+rating);

            return this.getAllUserDelevriesDto();
        }
        else if(rating  == -1){
            users=  userRepository.findByCityNameAndStypeId(city,service_type.id);

        }else if(city == null){
            users=  userRepository.findByRatingAverageAndStypeId(rating,service_type.id);
        }
        else{
            users=  userRepository.findByCityNameAndRatingAverageAndStypeId(city,rating,service_type.id);

        }
        List<UserResponse> usersDto = userMapper.toUsersResponse(users);
        return usersDto;
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


    public User save(User usuario) {
        return userRepository.save(usuario);
    }

    public UserResponse getUserDtoById(long id) {
        return userRepository.findById(id)
                .map(user -> userMapper.toUserResponse(user))
                .orElseThrow(()-> new NotFoundException("User Not Found"));
    }

    public User completeRegister(UserRequestDTO userRequestDTO) {
        User user = getUserByEmail(userRequestDTO.getEmail());
        user.setCity(userRequestDTO.getCityName());
        user.setFirstname(userRequestDTO.getFirstname());
        user.setLastname(userRequestDTO.getLastname());
        user.setCountry(userRequestDTO.getCountry());
        Stype stype = getService_type(userRequestDTO.isDelivery());
        user.setStype(stype);
            userRepository.save(user);
        return user;
    }

    public List<UserResponse> findByCityOrEmail(String param) {
        List<User> users =userRepository.findByCityOrEmail(param);
        return userMapper.toUsersResponse(users);
    }
}
