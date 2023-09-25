package com.micro.user.service.External.Services;

import com.micro.user.service.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "HOTELSERVICE")
public interface HotelService {
    //we will right a method for hotel
    @GetMapping("/hotels/{hotelId}")
    Hotel getHotel(@PathVariable("hotelId")String hotelId);
}
