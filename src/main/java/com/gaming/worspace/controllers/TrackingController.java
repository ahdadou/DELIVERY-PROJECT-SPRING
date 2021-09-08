package com.gaming.worspace.controllers;


import com.gaming.worspace.models.Tracking;
import com.gaming.worspace.models.dto.request.TrackingRequest;
import com.gaming.worspace.services.TrackingService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/v1/track")
public class TrackingController {

   private TrackingService trackingService;
   private SimpMessagingTemplate simpMessagingTemplate;

    public TrackingController(TrackingService trackingService, SimpMessagingTemplate simpMessagingTemplate){
        this.trackingService = trackingService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }


    @PostMapping
    public ResponseEntity getTrackingCode(@RequestBody TrackingRequest trackingRequest){
        Tracking tracking = trackingService.generateTrackingCode(trackingRequest);
        return  ResponseEntity.ok(tracking);
    }




    @PostMapping("/updateCode")
    public ResponseEntity updateTrackingCode(@RequestBody TrackingRequest trackingRequest){
        Tracking tracking = trackingService.updateTrackingUUID(trackingRequest);
        return  ResponseEntity.ok(tracking);
    }

    @PostMapping("/updateLocation")
    public ResponseEntity updateTrackingLocation(@RequestBody TrackingRequest trackingRequest){
        System.out.println("------------------------------1>"+ trackingRequest.getUuid());

        Tracking tracking = trackingService.updateTrackingLocation(trackingRequest);
        System.out.println("------------------------------2>"+ tracking.getUuid());

        simpMessagingTemplate.convertAndSendToUser(
                tracking.getUuid(), "/queue/tracking",tracking);
        return  ResponseEntity.ok(tracking);
    }




    @GetMapping("/code/{code}")
    public ResponseEntity getTrackingLocation(@PathVariable String code){
        Tracking tracking = trackingService.getTrackingByUUID(code);
        return  ResponseEntity.ok(tracking);
    }







}
