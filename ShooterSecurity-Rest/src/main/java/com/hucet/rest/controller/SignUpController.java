package com.hucet.rest.controller;

import com.hucet.domain.User;
import com.hucet.dto.UserDto;
import com.hucet.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequestMapping("/user")
@Slf4j
public class SignUpController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Integer signup(@RequestBody @Valid UserDto.ApplicationRequest user, BindingResult result) {
        if (result.hasErrors()) {
            // TODO Exception
            throw new ValidationException(result.toString());
        }
        User savedUser = userService.newUser(user);
        return savedUser.getId();
    }
}
