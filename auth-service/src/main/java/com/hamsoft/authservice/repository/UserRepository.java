package com.hamsoft.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import  com.hamsoft.authservice.domain.User;

import java.util.Optional;

/**
 * Created By kabiruahmed on May 2019
 */

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);

}
