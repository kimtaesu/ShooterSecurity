package com.hucet.repository;


import com.hucet.domain.User;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * Created by taesu on 2017-01-21.
 */
public interface UserDao extends Repository<User, Long> {
    User save(User newUser);

    Optional<User> findByUserEmail(String userEmail);
}
