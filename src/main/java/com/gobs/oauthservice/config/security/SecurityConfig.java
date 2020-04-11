package com.gobs.oauthservice.config.security;

import java.util.Arrays;
import java.util.List;

import com.gobs.oauthservice.services.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity(debug = false)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${oauth2-config.default-success-redirect}")
    private String DEFAULT_SUCCESS_URL;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        String[] resources = Arrays.asList(new String[] {
                "/assets/**", "/css/**", "/js/**", "/images/**"
        }).toArray(new String[] {});
        
        String[] allowed = Arrays.asList(new String[] {
                "/password_recovery*", 
                "/password_reset*", 
                "/register*", 
                "/user/password_recovery*", 
                "/user/password_reset*",
                "/maintenance",
                "/tokens",
                "/auth/**"
                // "/oauth/userinfo*",
                // "/oauth/tokeninfo*"
        }).toArray(new String[] {});
        
        // http
        //     .authorizeRequests()
        //         .antMatchers("/api/**").authenticated();

        http
            .authorizeRequests()
                .antMatchers(resources).permitAll();

        http.authorizeRequests()
                .antMatchers(allowed).permitAll();

        // http.authorizeRequests()
        //         .antMatchers("/oauth/tokeninfo*").permitAll();

        String TOKENINFO_URL = "/oauth/tokeninfo";
        String USERINFO_URL = "/oauth/userinfo";


        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/user/**", "/api/**", USERINFO_URL, TOKENINFO_URL).permitAll() 
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/login")
                // .defaultSuccessUrl(DEFAULT_SUCCESS_URL, false)
                // .success
                .permitAll()
            .and()
                .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "SESSION")
                .clearAuthentication(true)
                //.logoutSuccessUrl("/exit")
                .permitAll();
        //@formatter:on
    }

}