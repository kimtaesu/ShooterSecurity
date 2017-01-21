package com.hucet.repository;

import com.hucet.domain.User;
import org.springframework.data.repository.Repository;

/**
 * Created by taesu on 2017-01-21.
 */
public interface UserDao extends Repository<User, Long> {
}
