package com.gaming.worspace.services;


import com.gaming.worspace.dao.TrackingRepository;
import com.gaming.worspace.exceptions.NotFoundException;
import com.gaming.worspace.models.Tracking;
import com.gaming.worspace.models.User;
import com.gaming.worspace.models.dto.request.TrackingRequest;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class TrackingService {

    private UserServices userServices;
    private TrackingRepository trackingRepository;


    public TrackingService(UserServices userServices, TrackingRepository trackingRepository) {
        this.userServices = userServices;
        this.trackingRepository = trackingRepository;
    }



    // Generate Tracking code;
    public Tracking generateTrackingCode(TrackingRequest trackingRequest){
        Tracking tracking = trackingRepository.findByUserEmail(trackingRequest.getUserEmail());
        if(tracking!=null){
            tracking= updateTrackingLocation(trackingRequest);
        }else{
            User user = userServices.getUserByEmail(trackingRequest.getUserEmail());
            tracking = new Tracking();
            tracking.setActive(true);
            tracking.setUuid(generateRandomCode());
            tracking.setLongitude(trackingRequest.getLongitude());
            tracking.setLatitude(trackingRequest.getLatitude());
            tracking.setUser(user);
            tracking = trackingRepository.save(tracking);
        }

        return tracking;
    }



    //Get Location By UUID
    public Tracking getTrackingByUUID(String UUID){
        return trackingRepository.findByUuid(UUID)
                .orElseThrow(()-> new NotFoundException("User Not Found"));
    }


    // Updating Location
    public Tracking updateTrackingLocation(TrackingRequest trackingRequest){
            Tracking tracking= trackingRepository.findByUserEmail(trackingRequest.getUserEmail());
        tracking.setLongitude(trackingRequest.getLongitude());
        tracking.setLatitude(trackingRequest.getLatitude());
            tracking.setActive(true);
            tracking = trackingRepository.save(tracking);
        return tracking;
    }



    // Updating UUID
    public Tracking updateTrackingUUID(TrackingRequest trackingRequest){
        Tracking tracking= trackingRepository.findByUserEmail(trackingRequest.getUserEmail());
//        tracking.setLongitude(trackingRequest.getLongitude());
//        tracking.setLatitude(trackingRequest.getLatitude());
        tracking.setUuid(generateRandomCode());
        tracking = trackingRepository.save(tracking);
        return tracking;
    }


    //generate RANDOM CODE
    public String generateRandomCode(){
        UUID corrId = UUID.randomUUID();
        return corrId.toString();
    }

}
