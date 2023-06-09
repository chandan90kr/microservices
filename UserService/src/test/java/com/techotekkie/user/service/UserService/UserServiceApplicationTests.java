package com.techotekkie.user.service.UserService;

import com.techotekkie.user.service.UserService.entities.Rating;
import com.techotekkie.user.service.UserService.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private RatingService ratingService;
	@Test
	void contextLoads() {
	}

	@Test
	void createRating(){
		Rating rating = Rating.builder().rating(10).userId("").hotelId("").feedback("This is create by freign").build();
		ratingService.createRating(rating);
		System.out.println("new rating created");
	}

}
