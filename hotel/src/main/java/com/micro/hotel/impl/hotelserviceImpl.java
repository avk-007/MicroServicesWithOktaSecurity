package com.micro.hotel.impl;

import com.micro.hotel.Exception.ResourceNotFoundException;
import com.micro.hotel.Repository.hotelRepository;
import com.micro.hotel.entity.Hotel;
import com.micro.hotel.service.hotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class hotelserviceImpl implements hotelService {
    @Autowired
   private hotelRepository hotelRepository;


    @Override
    public Hotel create(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        hotelRepository.save(hotel);
        return null;
    }

    @Override
    public List<Hotel> getAll() {
       return  hotelRepository.findAll();
    }

    @Override
    public Hotel get(String id) {
        return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException
                ("user with given id not available !! :" + id));
    }
}
