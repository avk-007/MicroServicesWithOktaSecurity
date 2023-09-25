package com.micro.user.service.config.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class FeignClientInterceptor  implements RequestInterceptor {

    //to get the token with the help of this class
    @Autowired
    private OAuth2AuthorizedClientManager manager;
//    @Override
//    public void apply(RequestTemplate requestTemplate) {
//        //https://developer.okta.com/blog/2019/05/22/java-microservices-spring-boot-spring-cloud use this reference
//        //now using OAuth2AuthorizedClientManager manager to get token
//        //myInternalClient from yml file
//        String token = manager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("myInternalClient")
//                .principal("internal")
//                .build()).getAccessToken().getTokenValue();
//        //use local variable to get the name token
//
//        requestTemplate.header("Authorization","Bearer"+token);

    @Override
    public void apply(RequestTemplate template) {

        String token = manager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("myInternalClient").principal("internal").build()).getAccessToken().getTokenValue();
        template.header("Authorization", "Bearer " + token);


    }
}
