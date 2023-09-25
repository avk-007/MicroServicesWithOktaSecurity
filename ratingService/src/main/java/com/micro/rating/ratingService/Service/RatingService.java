package com.micro.rating.ratingService.Service;
import com.micro.rating.ratingService.entity.Rating;
import java.util.List;

public interface RatingService {
    //create
    Rating create(Rating rating);
    //getAllRatings
    List<Rating> getRatings();
    //getall by Userid
    List<Rating>getRatingByUserId(String userId);
    //get All by hotel
    List<Rating>getRatingByHotelId(String hotelId);
}
