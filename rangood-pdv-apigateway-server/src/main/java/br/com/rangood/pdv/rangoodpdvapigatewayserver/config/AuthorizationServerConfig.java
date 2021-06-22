package br.com.rangood.pdv.rangoodpdvapigatewayserver.config;

import com.netflix.ribbon.proxy.annotation.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class AuthorizationServerConfig extends ResourceServerConfigurerAdapter {

    private JwtTokenStore jwtTokenStore;

    private static final String[] PUBLIC_ROUTES = {"/api-oauth/**"};
    private static final String[] MANAGER_ROUTES = {"/**"};
//    private static final String[] CLIENT_ROUTES = {"/api-product/**"};

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

                //PUBLIC SPECIFIC OPERATIONS
                .antMatchers("/api-customer/v1/customer").permitAll()

                //OPERATOR SPECIFIC OPERATIONS
                .antMatchers(HttpMethod.GET, "/api-product/v1/product/**").hasRole("OPERATOR")
                .antMatchers(HttpMethod.POST, "/api-order/v1/order").hasRole("OPERATOR")
                .antMatchers(HttpMethod.GET, "/api-order/v1/order/{order}").hasRole("OPERATOR")
                .antMatchers(HttpMethod.GET, "/api-order/v1/order/{order}/status").hasRole("OPERATOR")
                .antMatchers(HttpMethod.GET, "/api-order/v1/order/{order}/cancel").hasRole("OPERATOR")

                .antMatchers(MANAGER_ROUTES).hasRole("ADMIN")
                .anyRequest().authenticated();
    }
}
