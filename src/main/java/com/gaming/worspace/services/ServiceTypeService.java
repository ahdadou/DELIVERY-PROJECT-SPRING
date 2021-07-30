package com.gaming.worspace.services;


import com.gaming.worspace.dao.ServiceRepository;
import com.gaming.worspace.exceptions.NotFoundException;
import com.gaming.worspace.models.SERVICE_TYPE;
import com.gaming.worspace.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTypeService {


    private ServiceRepository serviceRepository;

    @Autowired
    public ServiceTypeService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    // Get Service type
    public SERVICE_TYPE getService(String type){
        return serviceRepository.findByType(type)
                .orElseThrow(()->new NotFoundException("Service Type Not Found"));
    }


}
