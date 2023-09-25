package com.micro.rating.ratingService.Controller;
import com.micro.rating.ratingService.Service.RatingService;
import com.micro.rating.ratingService.entity.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;
    //create
    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
    }
    @GetMapping
    //getALl of hotels
     public ResponseEntity<List<Rating>>getAllRating() {
         return ResponseEntity.ok(ratingService.getRatings());
     }
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
     }

  //http://localhost:8083/ratings/users/a9984258-cebd-40ea-8ec8-bd7d3e7c569c  to check use this
     //ratingByuserId
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));

}}
