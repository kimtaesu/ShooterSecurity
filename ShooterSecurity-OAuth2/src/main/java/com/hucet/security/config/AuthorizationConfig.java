package com.hucet.security.config;

import com.hucet.security.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * Created by Administrator on 2016-04-18.
 */
@Configuration
@EnableAuthorizationServer
class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Value(value = "${oauth2.config.client_id}")
    private String client_id;

    @Value(value = "${oauth2.config.client_id}")
    private String client_secret;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//         @formatter:off
        clients
                .inMemory()
                .withClient(client_id)
                .authorizedGrantTypes(GrantType.PASSWORD.getGrantType(),
                        GrantType.REFRESH_TOKEN.getGrantType())
                .authorities(Role.RoleType.USER.name())
                .scopes(ScopeEnum.TEST.getScope())
                .resourceIds(client_secret)
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
//        security
//                .tokenKeyAccess("isAnonymous()")
//                .checkTokenAccess("isAuthenticated()");
    }
}
