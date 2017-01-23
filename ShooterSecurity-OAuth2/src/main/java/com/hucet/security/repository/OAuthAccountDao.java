package com.hucet.security.repository;

import com.hucet.security.domain.OAuthAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface OAuthAccountDao extends Repository<OAuthAccount, Long> {

    Optional<OAuthAccount> findByUsername(String username);

    OAuthAccount save(OAuthAccount oauthAccount);
}
