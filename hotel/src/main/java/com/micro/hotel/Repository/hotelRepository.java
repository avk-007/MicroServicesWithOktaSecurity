package com.micro.hotel.Repository;

import com.micro.hotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface hotelRepository extends JpaRepository<Hotel,String> {
}

