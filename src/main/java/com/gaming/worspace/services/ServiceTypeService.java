package com.gaming.worspace.services;
import com.gaming.worspace.dao.ServiceTypeRepository;
import com.gaming.worspace.exceptions.NotFoundException;
import com.gaming.worspace.models.Stype;
import com.gaming.worspace.models.enumerated.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceTypeService {


    private ServiceTypeRepository serviceRepository;

    @Autowired
    public ServiceTypeService(ServiceTypeRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }


    // Get Service type
    public Stype getServiceByType(Type type){
        return serviceRepository.findByType(type)
                .orElseThrow(()->new NotFoundException("Service Type Not Found"));
    }


}
