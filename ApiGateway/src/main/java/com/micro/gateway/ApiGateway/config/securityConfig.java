package com.micro.gateway.ApiGateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class securityConfig {
    //create bean of SecurityWeb filter chain
    //reference: https://docs.spring.io/spring-security/reference/reactive/configuration/webflux.html
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity){
        //methods of oauth security start with httpSecurity.

      httpSecurity.authorizeExchange()
              .anyExchange()
              .authenticated()
              .and()
              .oauth2Client()
              .and()
              .oauth2ResourceServer()
              .jwt();
        return httpSecurity.build();
    }
}
