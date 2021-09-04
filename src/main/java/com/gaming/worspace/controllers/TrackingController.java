package com.gaming.worspace.controllers;


import com.gaming.worspace.services.TrackingService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrackingController {

   private TrackingService trackingService;

    public TrackingController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }



}
