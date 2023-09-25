package com.micro.hotel.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/staffs")
public class StaffController {
    //127.0.0.1:8084/staffs
    @GetMapping
    public ResponseEntity <List<String>>getStaffs(){
        List<String> list = Arrays.asList("ram", "shyam", "ganshyam", "abhishek", "sumit");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
