package com.gaming.worspace.services;


import com.gaming.worspace.dao.RoleRepository;
import com.gaming.worspace.exceptions.NotFoundException;
import com.gaming.worspace.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private RoleRepository roleRepository;


    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
//    Get Role By RoleName

    public Role getRoleByRoleName(String roleName){
        return roleRepository.findByRole(roleName)
                .orElseThrow(()->new NotFoundException("Role Not Found"));
    }


//    Get Role By ID
    public Role getRoleById(long id){
        return roleRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Role Not Found"));
    }


    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
