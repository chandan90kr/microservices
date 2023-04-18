package com.techotekkie.rating.RatingService;

import com.techotekkie.rating.RatingService.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating,String> {

    //custom finer methods
    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);
}
