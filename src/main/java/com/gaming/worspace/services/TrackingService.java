package com.gaming.worspace.services;


import com.gaming.worspace.dao.TrackingRepository;
import org.springframework.stereotype.Service;

@Service
public class TrackingService {


    private TrackingRepository trackingRepository;


    public TrackingService(TrackingRepository trackingRepository) {
        this.trackingRepository = trackingRepository;
    }




}
