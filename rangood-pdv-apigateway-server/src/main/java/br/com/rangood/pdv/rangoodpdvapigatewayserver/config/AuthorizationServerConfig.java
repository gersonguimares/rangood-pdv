package br.com.rangood.pdv.rangoodpdvapigatewayserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class AuthorizationServerConfig extends ResourceServerConfigurerAdapter {

    private final JwtTokenStore jwtTokenStore;

    private static final String[] PUBLIC_ROUTES = {"/rangood-pdv-oauth/oauth/token"};
    private static final String[] MANAGER_ROUTES = {"/**"};
    private static final String[] CLIENT_ROUTES = {"/rangood-pdv-customer/customer"};


    @Autowired
    public AuthorizationServerConfig(JwtTokenStore jwtTokenStore) {
        this.jwtTokenStore = jwtTokenStore;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(jwtTokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(PUBLIC_ROUTES).permitAll()
                .antMatchers(MANAGER_ROUTES).hasRole("MANAGER")
                .antMatchers(CLIENT_ROUTES).hasAnyRole("MANAGER", "CLIENT")
                .anyRequest().authenticated();
    }
}
