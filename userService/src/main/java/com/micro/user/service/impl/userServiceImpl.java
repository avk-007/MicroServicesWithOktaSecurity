package com.micro.user.service.impl;

import com.micro.user.service.External.Services.HotelService;
import com.micro.user.service.Repository.userRepository;
import com.micro.user.service.Exception.ResourceNotFoundException;

import com.micro.user.service.entities.Hotel;
import com.micro.user.service.entities.Rating;
import com.micro.user.service.entities.User;
import com.micro.user.service.service.userService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class userServiceImpl implements userService {

    @Autowired
    private RestTemplate restTemplate;
    private Logger logger = LoggerFactory.getLogger(userServiceImpl.class);
    @Autowired
    private userRepository userRepository;
    @Autowired
    private HotelService hotelService;


    @Override
    public User saveUser(User user) {
        //uuid creation for unique
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

//    @Override
//    public User getUser(String userId) {
////get sinle user from database with the help of user Repository
//        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException
//                ("user with given id not available !! :" + userId));
//
//
//        //fetch the rating of the above user from ratingService ,
//        // ratingService has to have Api to give the detail
//        //we will go the ratingService RatingController and use THE @getMapping(/users/{userid})
//        //we will restTemplate here now
////        ArrayList RatingforUser = restTemplate.getForObject
////                ("http://localhost:8083/ratings/users/a9984258-cebd-40ea-8ec8-bd7d3e7c569c",
////                        ArrayList.class);
//        //bypass user id means dynamic user
//        Rating[]  ratingofUser = restTemplate.getForObject
//                ("http://RATINGSERVICE/ratings/users/" + user.getUserId(), Rating[].class);
//        logger.info("{}",ratingofUser);
//        List<Rating> ratings = Arrays.stream(ratingofUser).toList();
//
//        //http://localhost:8081/users/72264dd6-956b-4ec6-9ed6-cbf8e61eb732 go and check through this
//        //now set ratings you can see in postman a user ratings done by them
//
//        //for hotel finding
//        List<Rating> ratinglist = ratings.stream().map(rating -> {
//            //api call to hotelService to get the hotel
//            //localhost:8082/hotels/b94b46c7-ce3f-453f-af69-fdd7895840d7
//            //localhost:8081/users/72264dd6-956b-4ec6-9ed6-cbf8e61eb732 to check rating with hotel
//            System.out.println(rating.getHotelId());
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(),
//                    Hotel.class);
//            //Hotel hotel = forEntity.getBody();
//            //we will not use the resttemplate now we will use the feign client
//            Hotel hotel = hotelService.getHotel(rating.getHotelId());
//            logger.info("response status code:{}",forEntity.getStatusCode());
//
//            //set the hotel to rating
//            rating.setHotel(hotel);
//            //return the rating
//            return rating;
//        }).collect(Collectors.toList());
//       //return ratingist with hotel
//        user.setRatings(ratinglist);
//        return user;
//    }
//
    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(String.valueOf(userId));
    }
    //get single user
    //get single user
    @Override
    public User getUser(String userId) {
        //get user from database with the help  of user repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));
        // fetch rating of the above  user from RATING SERVICE
        //http://localhost:8083/ratings/users/47e38dac-c7d0-4c40-8582-11d15f185fad

        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        logger.info("{} ", ratingsOfUser);
        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to hotel service to get the hotel
            //http://localhost:8082/hotels/1cbaf36d-0b28-4173-b5ea-f1cb0bc0a791
           // ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            // logger.info("response status code: {} ",forEntity.getStatusCode());
            //set the hotel to rating
            rating.setHotel(hotel);
            //return the rating
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;
    }


}
