package com.app.RESTdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final JWTAuthFilter _authFilter;
    private final AuthenticationProvider _authProvider;

    @Autowired
    public SecurityConfiguration(JWTAuthFilter authFilter,AuthenticationProvider authProvider){
        this._authFilter = authFilter;
        this._authProvider = authProvider;
    }
    @Bean
    public DefaultSecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf( crf -> crf.disable() )
                .authorizeHttpRequests( request ->{
                        request.requestMatchers("/api/v1/auth/**","/api/v1/student/all" ).permitAll();
                        request.anyRequest().authenticated();
                        }
                );
                http.sessionManagement(session->{
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                        });

                http
                .authenticationProvider(_authProvider)
                .addFilterBefore(_authFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
}
