package pl.io.springdata.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.POST, "/getToken", "/api/order")
                .antMatchers(HttpMethod.GET,
                        "/api/product", "/api/product/all", "/api/order", "/api/order/all" );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/customer/all", "/api/customer").authenticated()
                .anyRequest().hasRole("ADMIN")
                .and()
                .addFilter(new JwtFilter(authenticationManager()));

        http.csrf().disable();
        http.headers().frameOptions().disable();

    }
}
