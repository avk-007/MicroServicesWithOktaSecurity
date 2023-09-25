package com.micro.rating.ratingService.entity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//table and entity not going to use for nosql we will @Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("ratings")
public class Rating {
    @Id
//rating created in user use from that
    private String ratingId;
    private String userId;
    private String hotelId;
    private int    rating;
    private String feedback;
}
