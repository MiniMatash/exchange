package com.minimatash.exchangetest.repository;

import com.minimatash.exchangetest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByToken(String token);
}
