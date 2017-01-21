package com.hucet.security.core.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.OAuth2AuthorizationServerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * Created by Administrator on 2016-04-18.
 */
@Configuration
@EnableAuthorizationServer
class AuthorizationConfig extends OAuth2AuthorizationServerConfiguration {

    @Autowired
    public AuthorizationConfig(BaseClientDetails details, AuthenticationManager authenticationManager, ObjectProvider<TokenStore> tokenStoreProvider, AuthorizationServerProperties properties) {
        super(details, authenticationManager, tokenStoreProvider, properties);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//         @formatter:off
        clients
                .inMemory()
                .withClient("aaa")
                .authorizedGrantTypes("password", "refresh_token")
                .authorities("USER")
                .scopes("READ", "WRITE")
                .resourceIds("test")
                .secret("aaa");
        // @formatter:on
    }

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    private TokenStore tokenStore = new InMemoryTokenStore();

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // @formatter:off
        endpoints
                .tokenStore(this.tokenStore)
                .authenticationManager(this.authenticationManager)
                .userDetailsService(userDetailsService);
        // @formatter:on
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("isAnonymous()")
                .checkTokenAccess("isAuthenticated()");
    }
}
