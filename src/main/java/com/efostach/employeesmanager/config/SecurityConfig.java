package com.efostach.employeesmanager.config;

import com.efostach.employeesmanager.security.jwt.JwtConfigurer;
import com.efostach.employeesmanager.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Security configuration class for JWT based Spring Security application.
 *
 * @author Helen Fostach
 * @version 1.0
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    private static final String AUTH_ENDPOINT = "/api/v1/auth/**";
    private static final String ADMIN_ENDPOINT = "/api/v1/admin/**";
    private static final String USER_ENDPOINT = "/api/v1/users/**";
    private static final String EMPLOYEE_ENDPOINT = "/api/v1/employees/**";
    private static final String DEPARTMENT_ENDPOINT = "/api/v1/departments/**";

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_ENDPOINT).permitAll()

                .antMatchers(USER_ENDPOINT).hasRole("ADMIN")
                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, EMPLOYEE_ENDPOINT).hasAnyRole("ADMIN", "MODERATOR", "USER")
                .antMatchers(HttpMethod.POST, EMPLOYEE_ENDPOINT).hasAnyRole("ADMIN", "MODERATOR")
                .antMatchers(HttpMethod.PUT, EMPLOYEE_ENDPOINT).hasAnyRole("ADMIN", "MODERATOR")
                .antMatchers(HttpMethod.DELETE, EMPLOYEE_ENDPOINT).hasAnyRole("ADMIN", "MODERATOR")

                .antMatchers(HttpMethod.GET, DEPARTMENT_ENDPOINT).hasAnyRole("ADMIN", "MODERATOR", "USER")
                .antMatchers(HttpMethod.POST, DEPARTMENT_ENDPOINT).hasAnyRole("ADMIN", "MODERATOR")
                .antMatchers(HttpMethod.PUT, DEPARTMENT_ENDPOINT).hasAnyRole("ADMIN", "MODERATOR")
                .antMatchers(HttpMethod.DELETE, DEPARTMENT_ENDPOINT).hasAnyRole("ADMIN", "MODERATOR")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}