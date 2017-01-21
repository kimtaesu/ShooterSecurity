package com.hucet.security.core.repository;


import com.hucet.security.core.domain.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin(String login);
}
