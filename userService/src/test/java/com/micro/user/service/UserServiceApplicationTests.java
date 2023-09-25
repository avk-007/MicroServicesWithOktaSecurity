package com.micro.user.service;

import com.micro.user.service.External.Services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	// to test RatingService ratingService in external.services

	@Autowired
	private RatingService ratingService;
//	@Test
//	void createRating(){
//		//complete this method using builder define @builder in Rating entity to use across the project
//		Rating rating=Rating.builder().rating(10).userId("").hotelId("").feedback("this is created using feign cleint").build();
//		Rating saveRating = ratingService.createRating(rating);
//		System.out.println("new rating created");
//	}
//we use this for test only
}
