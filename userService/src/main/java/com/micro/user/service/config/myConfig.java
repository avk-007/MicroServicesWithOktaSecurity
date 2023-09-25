package com.micro.user.service.config;

import com.micro.user.service.config.interceptor.RestTemplateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class myConfig {
    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;
    @Autowired
    private OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository;
    @Bean
    @LoadBalanced
    //when multiple instance we use loadbalance,to reduce load
    public RestTemplate restTemplate() {
     RestTemplate restTemplate = new RestTemplate();
     List<ClientHttpRequestInterceptor> interceptorList=new ArrayList<>();
     //send manager from here and pass karna hai by create injection
        // OAuth2AuthorizedClientRepository & ClientRegistrationRepository
        //pass both clientRegistrationRepository,oAuth2AuthorizedClientRepository
     interceptorList.add(new RestTemplateInterceptor(manager
             (clientRegistrationRepository,oAuth2AuthorizedClientRepository
     )));

             //crate class for RestTemplateInterceptor in
        // com/micro/user/service/config/interceptor/RestTemplateInterceptor.java
     restTemplate.setInterceptors(interceptorList);
        return restTemplate;
    }

    //create bean of OAuth2AuthorizedClientManager in myconfig
    @Bean
    public OAuth2AuthorizedClientManager manager(
                ClientRegistrationRepository clientRegistrationRepository,
                OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository ){

        //for provider at last we will use build

        OAuth2AuthorizedClientProvider provider= OAuth2AuthorizedClientProviderBuilder
                .builder().clientCredentials().build();

        DefaultOAuth2AuthorizedClientManager defaultOAuth2AuthorizedClientManager
                = new DefaultOAuth2AuthorizedClientManager
                (clientRegistrationRepository,auth2AuthorizedClientRepository);

        defaultOAuth2AuthorizedClientManager.setAuthorizedClientProvider(provider);
        //now search how to get provider create object for provider
        return defaultOAuth2AuthorizedClientManager;
    }

}
