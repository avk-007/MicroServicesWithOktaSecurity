package com.micro.user.service.entities;


import lombok.*;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
@Builder
public class Rating {

    private String ratingId;
    private String userId;
    private String hotelId;
    private int    rating;
    private String feedback;

//for hotel finding with user id
    private Hotel hotel;
}
