package com.micro.user.service.controller;
import com.micro.user.service.entities.User;
import com.micro.user.service.service.userService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//localhost:8081/users/72264dd6-956b-4ec6-9ed6-cbf8e61eb732
//localhost:8081/users/a9984258-cebd-40ea-8ec8-bd7d3e7c569c
@RestController
@RequestMapping("/users")
public class userController {
//    @Autowired
//   private  userService userService;
//
//    private Logger logger = LoggerFactory.getLogger(userController.class);
//
//    @PostMapping
//    public ResponseEntity<User>createUser(@RequestBody User user){
//        User user1 = userService.saveUser(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
//    }
//
//    //localhost:8081/users/72264dd6-956b-4ec6-9ed6-cbf8e61eb732
////localhost:8081/users/a9984258-cebd-40ea-8ec8-bd7d3e7c569c
//    //rating service call karna hoga
//    //int retryCount=1;
//    @GetMapping("/{userId}")
//    //single user get
//   // @CircuitBreaker(name ="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
//    //@Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallback")
//    @RateLimiter(name="userRateLimiter",fallbackMethod = "ratingHotelFallback")
//    public ResponseEntity<User>getUser(@PathVariable String userId){
//        logger.info("get single user Handler: userController");
//    //    logger.info("Retry count: {}", retryCount);
////         retryCount++;
//    User user = userService.getUser(userId);
//    return ResponseEntity.ok(user);
//}
//
//    //create fallbackmethod  for circuit breaker in userController
//   public ResponseEntity<User> ratingHotelFallback(String userId,Exception exception){
//       //logger.info("fallback is executed because service is down :",exception.getMessage());
//    //added while testing
//    exception.printStackTrace();
//       User user = User.builder().email("dummy@gmail.com").name("Dummy").about("This user is created dummy because some service is down").userId("141234").build();
//       return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
//
//}
//
//    @GetMapping
//    public ResponseEntity<List<User>>getAllUsers(){
//    List<User> allUsers = userService.getAllUsers();
//    return ResponseEntity.ok(allUsers);
//
//}
@Autowired
private userService userService;

    private Logger logger = LoggerFactory.getLogger(userController.class);

    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    //single user get


    @GetMapping("/{userId}")
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//    @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
        logger.info("Get Single User Handler: UserController");
//        logger.info("Retry count: {}", retryCount);

        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    //creating fall back  method for circuitbreaker


    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
//        logger.info("Fallback is executed because service is down : ", ex.getMessage());

        ex.printStackTrace();

        User user = User.builder().email("dummy@gmail.com").name("Dummy").about("This user is created dummy because some service is down").userId("141234").build();
        return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
    }


    //all user get
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUser = userService.getAllUsers();
        return ResponseEntity.ok(allUser);
    }
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }

    }

