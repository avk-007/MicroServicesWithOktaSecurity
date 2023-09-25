package com.micro.gateway.ApiGateway.controller;
import com.micro.gateway.ApiGateway.Models.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,
            @AuthenticationPrincipal OidcUser user,
            Model model
    ) {


        logger.info("user email id : {} ", user.getEmail());

        //creating auth response object
        AuthResponse authResponse = new AuthResponse();

        //setting email to authresponse
        authResponse.setUserId(user.getEmail());

        //setting toke to auth response
        authResponse.setAccesToken(client.getAccessToken().getTokenValue());

        authResponse.setRefreshToken(client.getRefreshToken().getTokenValue());

        authResponse.setExpireAt(client.getAccessToken().getExpiresAt().getEpochSecond());

        List<String> authorities = user.getAuthorities().stream().map(grantedAuthority -> {
            return grantedAuthority.getAuthority();
        }).collect(Collectors.toList());


        authResponse.setAuthorities(authorities);

        return new ResponseEntity<>(authResponse, HttpStatus.OK);


    }

}
//http://localhost:8084/authorization-code/callback isko change kiye hai
// isme http://localhost:8084/login/oauth2/code/okta
//check spring security laga hai ki ni check kro
//127.0.0.1:8084/users/a9984258-cebd-40ea-8ec8-bd7d3e7c569c bad request 400 comes means right


//token eyJraWQiOiI2blI1LUJ1WFBQZW9jbWJFT2FtV0d5YVI1VDJIR1l1WVJ5SU9tYnBrTWtZIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULlFWNmE1RGVuV1RIMXVUSE8wVWhTc0tWRDNQQXJOSlEyRk5jclZ0ZjIxYnMub2FyMWJhZGxpdmJpQ3I5WUU1ZDciLCJpc3MiOiJodHRwczovL2Rldi05ODgwNjA5MS5va3RhLmNvbS9vYXV0aDIvZGVmYXVsdCIsImF1ZCI6ImFwaTovL2RlZmF1bHQiLCJpYXQiOjE2OTUxMjEyMzAsImV4cCI6MTY5NTEyNDgzMCwiY2lkIjoiMG9hYmN3aWdlcThzMkR2WXA1ZDciLCJ1aWQiOiIwMHViY25jY2Vkc1RVZGh1UjVkNyIsInNjcCI6WyJvZmZsaW5lX2FjY2VzcyIsIm9wZW5pZCIsImVtYWlsIiwicHJvZmlsZSJdLCJhdXRoX3RpbWUiOjE2OTUxMjEyMjYsInN1YiI6ImFiaGlrN2p1bHlAZ21haWwuY29tIn0.OCsYghWgXzn2eypRRYT4VsIdju_KJ7GuoXd7bXrLPkGva9bMGoVesBd4G4ScsZfJh_iD1Pio9IU4OHh-b4Wp_0RXXvUFIUU0gGGuHR6khiTfR6glrHAcgg_GMo2EkeyPD_bIcYRf8jePmP0VvSvokYdTaOj2QVFtQUSZoxVvbDRsSO2hxPezx0_0hVBdYjEdmpy6tl5AltVJ6ThzjTMtAiu6Cv9g5XExcXfhQhLkaLGxZSyDRmRBcbbjbJvocOMytj1rWX5tbGOCSJrq7iZ02Ya8eRF4pmxfHOE6N_owxLOGQHXJnS8vLTq_LATQEYIgZNOdKlqGlB58XE-Ppx3NQA