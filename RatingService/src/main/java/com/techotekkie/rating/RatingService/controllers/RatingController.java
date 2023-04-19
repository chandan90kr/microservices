package com.techotekkie.rating.RatingService.controllers;

import com.techotekkie.rating.RatingService.entities.Rating;
import com.techotekkie.rating.RatingService.services.RatingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingServices ratingServices;


    @PostMapping
    //crate rating
    public ResponseEntity<Rating> create(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingServices.create(rating));
    }

    @GetMapping
    public  ResponseEntity<List<Rating>> getRating(){
        return ResponseEntity.ok(ratingServices.getRating());
    }
    @GetMapping("users/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
        return ResponseEntity.ok(ratingServices.getRatingByUserId(userId));
    }

    @GetMapping("hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
        return ResponseEntity.ok(ratingServices.getRatingByHotelId(hotelId));
    }

}
