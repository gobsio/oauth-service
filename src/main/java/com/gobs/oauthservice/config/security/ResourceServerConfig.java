package com.gobs.oauthservice.config.security;

import java.util.Arrays;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("RESOURCE_ID").stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        // http
        //     // .antMatcher("/api/**")
        //     .authorizeRequests()
        //         .antMatchers("/api/organizations*").authenticated();
        
        // http
        //     // .antMatcher("/oauth/**")
        //     .authorizeRequests()
        //         .antMatchers("/oauth/revoke_token*").authenticated();
        //         // .antMatchers("/user/password_reset*", "/user/password_recovery*").permitAll()

        // http
        //     .antMatcher("/user/**")
        //         .authorizeRequests()
        //             .antMatchers("/user/info", "/user/revoke_token").authenticated()
        //             .antMatchers("/user/password_reset*", "/user/password_recovery*").permitAll()
        //         .anyRequest().authenticated();

        String[] authenticated = Arrays.asList(
            "/api/**", "/oauth/userinfo", "/oauth/tokeninfo", "/oauth/revoke_token"
        ).toArray(new String[]{});

        http
            //     .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            // .and()
                .requestMatchers()
                    .antMatchers(authenticated)
            .and()
                .authorizeRequests()
                    .antMatchers(authenticated).authenticated()
                    .antMatchers("/**").permitAll()
                .anyRequest().authenticated();
		// @formatter:on
    }
}
