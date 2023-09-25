package com.micro.rating.ratingService.Repository;

import com.micro.rating.ratingService.entity.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating,String> {

    //create custom method for both the methods

    List<Rating>findByUserId(String userId);
    List<Rating>findByHotelId(String hotelId);
}
