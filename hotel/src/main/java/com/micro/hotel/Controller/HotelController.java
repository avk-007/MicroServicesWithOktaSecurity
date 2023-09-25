package com.micro.hotel.Controller;
import com.micro.hotel.entity.Hotel;
import com.micro.hotel.service.hotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private hotelService hotelService;
    //create
    //to give authority
   //@PreAuthorize("hasAuthority('admin')")
    @PostMapping
    public ResponseEntity<Hotel>createHotel(@RequestBody Hotel hotel){

        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }
    //getSingle hotel


    //@PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("{hotelId}")
    public ResponseEntity<Hotel>getHotel(@PathVariable String hotelId) {

        return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(hotelId));
    }
    //getAll
    //@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('admin')")
    @GetMapping
    public ResponseEntity<List<Hotel>>getAll(){
        return ResponseEntity.ok(hotelService.getAll());
  }}
