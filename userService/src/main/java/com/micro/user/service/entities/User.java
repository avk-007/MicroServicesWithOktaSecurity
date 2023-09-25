package com.micro.user.service.entities;

import lombok.Builder;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
@Builder
@Entity
@Table(name="micro_users")
public class User {
    @Id
    @Column(name = "Id")
    private String userId;
    @Column(name = "name",length=200)
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "about")
    private String about;

    //by using @Transient ,not going to save in db,we will check the rating in get method of postman
    @Transient
    private List<Rating> ratings=new ArrayList<>();

}
