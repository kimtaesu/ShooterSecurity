package com.hucet.service.user;

import com.hucet.domain.User;
import com.hucet.dto.UserDto;
import com.hucet.repository.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by taesu on 2017-01-22.
 */
public interface UserService {

    User newUser(UserDto.ApplicationRequest applicationRequest);

    @Service
    @Transactional
    @Slf4j
    class UserServiceImpl implements UserService {
        @Autowired
        UserDao userDao;

        @Autowired
        ModelMapper modelMapper;

        @Override
        public User newUser(UserDto.ApplicationRequest applicationRequest) {
            boolean exist = userDao.findByUserEmail(applicationRequest.getUserEmail())
                    .isPresent();
            if (exist) {
                // TODO EXCEPTION
                throw new RuntimeException("Duplicated Object");
            }
            User user = modelMapper.map(applicationRequest, User.class);
            user = userDao.save(user);
            return user;
        }
    }

}
