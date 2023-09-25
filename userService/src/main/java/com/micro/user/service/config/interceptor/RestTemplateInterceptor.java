package com.micro.user.service.config.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

import java.io.IOException;

public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {
    //define manager
    private OAuth2AuthorizedClientManager manager;
    //added while testing
    private Logger logger= LoggerFactory.getLogger(RestTemplateInterceptor.class);

    public RestTemplateInterceptor(OAuth2AuthorizedClientManager manager) {
        this.manager = manager;
    }

    //send mananger from restTemplate
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        //copied from FeignClientInterceptor
        String token = manager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("myInternalClient")
                .principal("internal")
                .build()).getAccessToken().getTokenValue();
        //use local variable to get the name token

        //added while testing
        logger.info("Rest Template interceptor: Token :  {} ",token);

       request.getHeaders().add("Authorization","Bearer"+token);
      //use execution inplace of null

        return execution.execute(request,body);
    }
}
