package com.micro.hotel.service;

import com.micro.hotel.entity.Hotel;

import java.util.List;

public interface hotelService {
    //create
    Hotel create (Hotel hotel);
    //get ALl
    List<Hotel> getAll();
    //get single hotel
    Hotel get(String id);



}
