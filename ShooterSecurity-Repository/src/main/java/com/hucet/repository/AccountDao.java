package com.hucet.repository;


import com.hucet.domain.Account;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * Created by taesu on 2017-01-21.
 */
public interface AccountDao extends Repository<Account, Long> {
    Account save(Account newAccount);

    Optional<Account> findByUserName(String userName);
}
