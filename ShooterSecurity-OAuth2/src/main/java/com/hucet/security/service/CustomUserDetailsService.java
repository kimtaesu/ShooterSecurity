package com.hucet.security.service;

import com.hucet.domain.Account;
import com.hucet.repository.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountDao userRepository;

    @Autowired
    public CustomUserDetailsService(AccountDao userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = userRepository.findByUserName(username);

        if (!account.isPresent()) {
            throw new UsernameNotFoundException(String.format("Account %s does not exist!", username));
        }
        return new User(account.get);
    }

    private final static class UserRepositoryUserDetails extends User implements UserDetails {

        private static final long serialVersionUID = 1L;

        public UserRepositoryUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
            super(username, password, authorities);
        }

    }

}
