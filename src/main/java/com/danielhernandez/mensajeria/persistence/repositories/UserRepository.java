package com.danielhernandez.mensajeria.persistence.repositories;

import com.danielhernandez.mensajeria.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String username);
}
