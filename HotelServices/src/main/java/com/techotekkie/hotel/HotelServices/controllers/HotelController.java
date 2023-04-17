package com.techotekkie.hotel.HotelServices.controllers;

import com.techotekkie.hotel.HotelServices.entities.Hotel;
import com.techotekkie.hotel.HotelServices.services.HotelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelServices hotelServices;
    //create

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
    {

       String hotelId = UUID.randomUUID().toString();
       hotel.setId(hotelId);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelServices.create(hotel));
    }
    //getsingle

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId)
    {

        return ResponseEntity.status(HttpStatus.OK).body(hotelServices.get(hotelId));
    }


    //getAll

    @GetMapping
    public ResponseEntity<List<Hotel>> getAll(){
        return ResponseEntity.ok(hotelServices.getAll());
    }
}
