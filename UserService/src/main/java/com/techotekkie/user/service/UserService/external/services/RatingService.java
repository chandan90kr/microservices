package com.techotekkie.user.service.UserService.external.services;

import com.techotekkie.user.service.UserService.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Map;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    //get

    //POST
    @PostMapping("/ratings")
    public Rating createRating(Rating values);


    //Put
    @PutMapping("/ratings/{ratingId}")
    public Rating updateRating(@PathVariable("ratingId")String ratingId, Rating rating);

    @DeleteMapping("/ratings/{ratingId}")
    public void deleteRating(@PathVariable String ratingId);



}
