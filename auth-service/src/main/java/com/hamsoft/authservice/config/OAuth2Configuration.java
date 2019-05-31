package com.hamsoft.authservice.config;

import com.hamsoft.authservice.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;


/**
 * Created By kabiruahmed on May 2019
 */

@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class OAuth2Configuration extends AuthorizationServerConfigurerAdapter {

    private final String NOOP_PASSWORD_ENCODE = "{noop}";

    final CustomUserDetailsService userDetailsService;

    private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 60*60;
    private static final int REFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;


    @Qualifier("authenticationManagerBean")
    final AuthenticationManager authenticationManager;

    final PasswordEncoder passwordEncoder;


    final TokenStore tokenStore;


    @Override
    public void configure (ClientDetailsServiceConfigurer clients) throws Exception {

            clients.inMemory ()
                    .withClient ("client")
                    .authorizedGrantTypes ("password", "authorization_code", "refresh_token", "implicit")
                    .authorities ("ROLE_CLIENT","ROLE_USER", "ROLE_TRUSTED_CLIENT", "ROLE_SUPER_ADMIN")
                    .scopes ("read", "write","trust")
                   // .secret("password")
                    .autoApprove (true)
                    .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS).
                    refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS)
                    .secret (passwordEncoder.encode ("password"));
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .passwordEncoder(passwordEncoder)
                .allowFormAuthenticationForClients();
               // .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }







}
