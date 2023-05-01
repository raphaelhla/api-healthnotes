package com.ufcg.apihealthnotes.repositories;

import com.ufcg.apihealthnotes.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByLogin(String login);

    boolean existsByLogin(String login);
}
