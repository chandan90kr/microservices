package com.techotekkie.rating.RatingService.services;

import com.techotekkie.rating.RatingService.entities.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingServices {

    //create

    Rating create(Rating rating);

    //get All ratings
    List<Rating> getRating();

    //get all by userid
    List<Rating>getRatingByUserId(String userId);


    //get all by hotel
    List<Rating> getRatingByHotelId(String hotelId);
}
