package com.hucet.service.user;

import com.hucet.dto.UserDto;
import com.hucet.repository.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by taesu on 2017-01-22.
 */
public interface UserService {

    Long newUser(UserDto.ApplicationRequest applicationRequest);

    @Service
    @Transactional
    @Slf4j
    class UserServiceImpl implements UserService {
        @Autowired
        UserDao userDao;

        @Override
        public Long newUser(UserDto.ApplicationRequest applicationRequest) {
            boolean exist = userDao.findByUserEmail(applicationRequest.getUserEmail())
                    .isPresent();
            if (exist)
            {
                // TODO EXCEPTION
                throw new RuntimeException("Duplicated Object");
            }
            userDao.save()

            return null;
        }
    }

}
