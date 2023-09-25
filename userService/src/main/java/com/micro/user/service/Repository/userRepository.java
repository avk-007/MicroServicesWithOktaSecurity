package com.micro.user.service.Repository;

import com.micro.user.service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<User,String> {
}
