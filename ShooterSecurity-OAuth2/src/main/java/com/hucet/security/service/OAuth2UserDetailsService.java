package com.hucet.security.service;

import com.hucet.domain.Account;
import com.hucet.repository.AccountDao;
import com.hucet.security.domain.OAuthAccount;
import com.hucet.security.repository.OAuthAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

/**
 * Created by taesu on 2017-01-23.
 */
@Service
@Transactional
public class OAuth2UserDetailsService implements UserDetailsService {

    @Autowired
    private OAuthAccountDao oAuthAccountDao;

    @Autowired
    public OAuth2UserDetailsService(OAuthAccountDao oAuthAccountDao) {
        this.oAuthAccountDao = oAuthAccountDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<OAuthAccount> account = oAuthAccountDao.findByUsername(username);

        if (!account.isPresent()) {
            throw new UsernameNotFoundException(String.format("Account %s does not exist!", username));
        }
        OAuthAccount authAccount = account.get();
        return new User(authAccount.getUserName(),
                authAccount.getPassword(),
                authAccount.isEnabled(),
                authAccount.isAccountNonExpired(),
                authAccount.isCredentialsNonExpired(),
                authAccount.isAccountNonLocked(),
                authAccount.getRoles());
    }

    private final static class UserRepositoryUserDetails extends User implements UserDetails {

        private static final long serialVersionUID = 1L;

        public UserRepositoryUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
            super(username, password, authorities);
        }

    }

}
