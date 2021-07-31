package com.gaming.worspace.services;

import com.gaming.worspace.dao.CityRepository;
import com.gaming.worspace.exceptions.NotFoundException;
import com.gaming.worspace.models.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {


    private CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    public City getCityByName(String name){
        return cityRepository.findByName(name)
                .orElseThrow(()->new NotFoundException("City Not Found"));

    }


}
