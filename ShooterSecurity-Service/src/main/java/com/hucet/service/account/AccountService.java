package com.hucet.service.account;

import com.hucet.domain.Account;
import com.hucet.dto.AccountDto;
import com.hucet.repository.AccountDao;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by taesu on 2017-01-22.
 */
public interface AccountService {

    Account newUser(AccountDto.ApplicationRequest applicationRequest);

    @Service
    @Transactional
    @Slf4j
    class AccountServiceImpl implements AccountService {
        @Autowired
        AccountDao accountDao;

        @Autowired
        ModelMapper modelMapper;

        @Override
        public Account newUser(AccountDto.ApplicationRequest applicationRequest) {
            boolean exist = accountDao.findByUserName(applicationRequest.getUserEmail())
                    .isPresent();
            if (exist) {
                // TODO EXCEPTION
                throw new RuntimeException("Duplicated Object");
            }
            Account account = modelMapper.map(applicationRequest, Account.class);
            account = accountDao.save(account);
            return account;
        }
    }

}
