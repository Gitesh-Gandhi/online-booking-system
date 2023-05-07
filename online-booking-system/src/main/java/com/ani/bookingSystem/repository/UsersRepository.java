package com.ani.bookingSystem.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ani.bookingSystem.domain.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

    
    Optional<Users> findByEmailAndPassword(String email, String password);

    Optional<Users> findByEmail(String email);

    Users findByUserName(String userName);

     List<Users> findAllByUserName(String userName);

    
}
