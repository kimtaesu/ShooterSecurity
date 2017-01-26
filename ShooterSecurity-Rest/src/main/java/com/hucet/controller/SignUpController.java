package com.hucet.controller;

import com.hucet.domain.Account;
import com.hucet.dto.AccountDto;
import com.hucet.service.EmailService;
import com.hucet.service.account.AccountService;
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
    AccountService userService;

    @Autowired
    EmailService emailService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Integer signup(@RequestBody @Valid AccountDto.ApplicationRequest user, BindingResult result) {
        if (result.hasErrors()) {
            // TODO Exception
            throw new ValidationException(result.toString());
        }

        Account savedAccount = userService.newUser(user);

        // TODO CREATE OAUTH USER with RPC

        emailService.notifyEmailCert(user);


        return null;
    }
}
