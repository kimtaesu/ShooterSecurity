package com.hucet.rest.controller;

import com.hucet.dto.UserDto;
import com.hucet.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class SignUpController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, String> signup(@RequestBody UserDto.ApplicationRequest user) {
        Long id = userService.newUser(user);
        return "test";
    }
}
