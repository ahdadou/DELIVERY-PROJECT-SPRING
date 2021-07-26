package com.gaming.worspace.services;


import com.gaming.worspace.dao.RoleRepository;
import com.gaming.worspace.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepository;


    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
//    Get Role By RoleName

//    public Role getRoleByRoleName(String RoleName){
//        return roleRepository.findByRoleName(String roleName);
//    }







}
