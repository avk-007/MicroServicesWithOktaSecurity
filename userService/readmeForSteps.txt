note:: ---MongoDb--if mongo stops working go to services and enable the mongo server
       --mongodb has its own mongoRepository<>
       it is no sql
       no jpa required,we can use id ,,for entity and table we will use @Document("anyname")
       //for rating--localhost:8083/ratings ,, localhost:8083/ratings/hotels/

note to start the project:
start with serviceRegistry,configServer,ApiGateway,UserService,ratingService,HotelService

class 1 :why microservices

class 2:first created user.
        created in entity --> private List<Rating> ratings; then make class.
        PostgreSql is same as mysql
        point to be Remember we can create any custom method handler in  like for userid&hotelid to get the details

        //localhost:8081/users/72264dd6-956b-4ec6-9ed6-cbf8e61eb732
        //localhost:8081/users/a9984258-cebd-40ea-8ec8-bd7d3e7c569c
        http://localhost:8761/eureka/
        http://192.168.0.105:8083/ratings to check after starting eureka server

        ---MongoDb--if mongo stops working go to services and enable the mongo server
        --mongodb has its own mongoRepository<>
        it is no sql
        no jpa required,we can use id ,,for entity and table we will use @Document("anyname")

        // for rating--localhost:8083/ratings ,, localhost:8083/ratings/hotels/

        &&&& to work serviceRegistry in userService we copy
        <spring-cloud.version>2021.0.8</spring-cloud.version>
        <dependencies>
            <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter</artifactId>
            </dependency>
            <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
            </dependency>

            <dependencyManagement>
                    <groupId>org.springframework.cloud</groupId>
                    <artifactId>spring-cloud-dependencies</artifactId>
              </dependencyManagement>
        -----------------------------------------& do yml config
            eureka:
              instance:
                prefer-ip-address: true
                client:
                  fetch-registry: true
                  register-with-eureka: true
                  service-url:
                    defaultZone: http://localhost:8761/eureka/
        add @EnableEurekaClient and
        ----------------------------------------------------------------------------
 class 3:  What we are going to build to Master Microservices
 class 4: Lets Start Creating User Microservice
 class 5:Complete User Microservice in one video with mysql db(create project)
 class 6: Creating Full Hotel Microservices with PostgreSQL (create project)
 class 7:Complete Rating Microservice with MongoDB in one shot(create project)
 class 8:create Service Registry using Eureka SERVER(create new project)
 with dependency
        step 1:@SpringBootApplication
               @EnableEurekaServer
               public class ServiceRegistryApplication {

               	public static void main(String[] args) {
               		SpringApplication.run(ServiceRegistryApplication.class, args);
          step:// for rating--localhost:8083/ratings ,, localhost:8083/ratings/hotels/

                       &&&& to work serviceRegistry in userService we copy
                       <spring-cloud.version>2021.0.8</spring-cloud.version>
                       <dependencies>
                           <dependency>
                             <groupId>org.springframework.cloud</groupId>
                             <artifactId>spring-cloud-starter</artifactId>
                           </dependency>
                           <dependency>
                             <groupId>org.springframework.cloud</groupId>
                             <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
                           </dependency>

                           <dependencyManagement>
                                   <groupId>org.springframework.cloud</groupId>
                                   <artifactId>spring-cloud-dependencies</artifactId>
                             </dependencyManagement>
                       -----------------------------------------& do yml config
                           eureka:
                             instance:
                               prefer-ip-address: true
                               client:
                                 fetch-registry: true
                                 register-with-eureka: true
                                 service-url:
                                   defaultZone: http://localhost:8761/eureka/
                       add @EnableEurekaClient and
       ---------------------------------------------------



 class 9:Implementing Service Discovery Client
 step 1: dependency- eureka server(spring cloud discovery),cloud bootstrap(spring cloud)
 step 2:.yml
  &&&& to work serviceRegistry in userService we copy
                        <spring-cloud.version>2021.0.8</spring-cloud.version>

                        <dependencies>
                            <dependency>
                              <groupId>org.springframework.cloud</groupId>
                              <artifactId>spring-cloud-starter</artifactId>
                            </dependency>
                            <dependency>
                              <groupId>org.springframework.cloud</groupId>
                              <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
                            </dependency>

                            <dependencyManagement>
                                    <groupId>org.springframework.cloud</groupId>
                                    <artifactId>spring-cloud-dependencies</artifactId>
                              </dependencyManagement>
                        -----------------------------------------& do yml config
                            eureka:
                              instance:
                                prefer-ip-address: true
                                client:
                                  fetch-registry: true
                                  register-with-eureka: true
                                  service-url:
                                    defaultZone: http://localhost:8761/eureka/
                        add @EnableEurekaClient in the main class of userService,RatingService and HotelService

 class 10:Implementing Service Discovery Client in Hotel Microservice

 same process for both the hotel and rating
 class 11:lass 10:Implementing Service Discovery Client in rating Microservice

        -----------------------------------------------------------------------------------------------------
class 12:Microservices Communication | How USER SERVICE Communicate to RATING SERVICE

steps:in userserviceimpl ::@Autowired
          private RestTemplate restTemplate;

          private Logger logger = LoggerFactory.getLogger(userServiceImpl.class);

          @Override
              public User getUser(String userId) {
          //get sinle user from database with the help of user Repository
                  User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException
                          ("user with given id not available !! :" + userId));


                  //fetch the rating of the above user from ratingService ,
                  // ratingService has to have Api to give the detail
                  //we will go the ratingService RatingController and use THE @getMapping(/users/{userid})
                  //we will restTemplate here now
          //        ArrayList RatingforUser = restTemplate.getForObject
             ("http://localhost:8083/ratings/users/a9984258-cebd-40ea-8ec8-bd7d3e7c569c",ArrayList.class);
                  //bypass user id means dynamic user
                  ArrayList RatingforUser = restTemplate.getForObject
                          ("http://localhost:8083/ratings/users/"+user.getUserId(), ArrayList.class);

                  logger.info("{}",RatingforUser);

                  //http://localhost:8081/users/72264dd6-956b-4ec6-9ed6-cbf8e61eb732 go and check through this
                  //now set ratings you can see in postman a user ratings done by them
                  user.setRatings(RatingforUser);
                  return user;

                  } and
                  $create bean at myconfig class. then we will further add hotel service in userservice
-----------------------------------------------------------------------------------------
 class 13:  calling 2 microservices together

//ab rating se hotel nikalana hai ab
create in entity of userservice ->Hotel entity banado aur ek method likhodo ratingEntity me like
 private String ratingId;
 private String userId;
  private String hotelId;
   private int    rating;
    private String feedback;
  //this add   private Hotel hotel;


//bypass user id means dynamic user
        Rating[]  ratingofUser = restTemplate.getForObject
                ("http://localhost:8083/ratings/users/" + user.getUserId(), Rating[].class);
        logger.info("{}",ratingofUser);
        List<Rating> ratings = Arrays.stream(ratingofUser).toList();

        //http://localhost:8081/users/72264dd6-956b-4ec6-9ed6-cbf8e61eb732 go and check through this
        //now set ratings you can see in postman a user ratings done by them

        //for hotel finding
        List<Rating> ratinglist = ratings.stream().map(rating -> {
            //api call to hotelService to get the hotel
            //localhost:8082/hotels/b94b46c7-ce3f-453f-af69-fdd7895840d7
            System.out.println(rating.getHotelId());
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://localhost:8082/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();
             logger.info("response status code:{}",forEntity.getStatusCode());

            //set the hotel to rating
            rating.setHotel(hotel);
            //return the rating
            return rating;
        }).collect(Collectors.toList());
       //return ratingist with hotel
        user.setRatings(ratinglist);
        return user;
    }
-------------------------------------------------------------------------------------------------
class 14:Removing Host(localhost) and Port(8083) of Microservices | Microservices

ex: "http://localhost:8083/ratings/users/  to
("http://RATINGSERVICE/ratings/users/" + user.getUserId(), Rating[].class);
"http://HOTELSERVICE/hotels/"+rating.getHotelId(),

add  @LoadBalanced in myconfig with @bean
    @Bean
    @LoadBalanced
    //when multiple instance we use loadbalance,to reduce load
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//port no rating is changed anytime ex:9069,port change hone pe bhi fark ni padega kyuki @loadbalanced used h.
agar ratingService ka port change bhi krdoge to kuch fark ni padega http://RATINGSERVICE/ratings/users/
------------------------------------------------------------------------------------------------------------
class 15 :Using Feign Client | Microservices

the feign is a declartive Http web client developed by netflix (httprequest and resttemplate)
to use feign,create an interface and annotate it.

 **steps to create & use feign:

 1.add dependency:OpenFeign (SPRING CLOUD ROUTING) paste in userService pom.xml
 use:Declarative REST Client. OpenFeign creates a dynamic implementation of
 an interface decorated with JAX-RS or Spring MVC annotations.
 2.@EnableFeignClients add in main class of userService
 3.create ExternalServices (package)& create interface HotelService in that annotate with @FeignClient(name = "")
 4.inject @Autowired private HotelService hotelService; in userServiceImpl
 5.we will not use the resttemplate now we will use the feign client

------------------------------------------------------------------------------------------
class 16 :SourceCode
-----------------------------------------------------------------------------------------
class 17: FeignClient Post,Put,Delete http calls complete
usecase scenario refer page 10 of ppt to get & post rating
steps: create ratingService in externalservices then
@Service
@FeignClient(name="RATINGSERVICE")
public interface RatingService {
    //get

    //post
    @PostMapping("/ratings")
    //to pass rating data you can use map as (map<String,objects> values);
    public Rating createRating(Rating values);


    //put
    @PutMapping("/ratings/{ratingId}")
    public Rating updateRating(@PathVariable("ratingId") String ratingId,Rating rating);

    //delete
    @DeleteMapping("/ratings/{ratingId}")
    public void deleteRating(@PathVariable String ratingId);
}

-----> then
@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	// to test ratingService ratingService in externalservices

	@Autowired
	private RatingService ratingService;
		@Test
	void createRating(){
		//complete this method using builder define @builder in Rating entity to use across the project
		Rating rating=Rating.builder().rating(10).userId("").hotelId("").feedback("this is created using feign cleint").build();
		Rating saveRating = ratingService.createRating(rating);
		System.out.println("new rating created");
	}
}
run the test now

op: new rating created
-------------------------------------------------------------------------------------------------------
class 18:API GATEWAY with Example

Client—>api gateway→ services→db
Api gateway is also a microservice
--------------------------------------------------------------------------
class 19: Implementing API GATEWAY in Simple ways
https://spring.io/projects/spring-cloud-gateway#learn

*Spring Cloud Gateway is built on Spring Boot, Spring WebFlux, and Project Reactor.

 As a consequence, many of the familiar synchronous libraries (Spring Data and Spring Security, for example)
 and patterns you know may not apply when you use Spring Cloud Gateway. If you are unfamiliar with these projects,
 we suggest you begin by reading their documentation to familiarize yourself with some new
 concepts before working with Spring Cloud Gateway.
*Spring Cloud Gateway requires the Netty runtime provided by Spring Boot and Spring Webflux.
 It does not work in a traditional Servlet Container or when built as a WAR.

 steps :apiGateway springProject created with dependency
 cloud bootStrap,gateway(cloud routing),lombok,reactive web,eureka discovery client(spring cloud discovery)
 application.yml:
 server:
   port: 8084

 spring:
   application:
     name: APIGATEWAY


   cloud:
     gateway:
       routes:
         - id: USERSERVICE
           uri: lb://USERSERVICE
           predicates:
             - Path=/users/**

         - id: HOTELSERVICE
           uri: lb://HOTELSERVICE
           predicates:
             - Path=/hotels/**,/staffs/**

         - id: RATINGSERVICE
           uri: lb://RATINGSERVICE
           predicates:
             - Path=/ratings/**
 eureka:
         instance:
           prefer-ip-address: true
           client:
             fetch-registry: true
             register-with-eureka: true
             service-url:
               defaultZone: http://localhost:8761/eureka/

--------------------------------------------------------------------------
class 20:API Gateway configuring multiple url of microservice

to check this we added staffcontroller:
@RequestMapping("/staffs")
public class StaffController {
    @GetMapping
    public ResponseEntity <List<String>>getStaffs(){
        List<String> list = Arrays.asList("ram", "shyam", "ganshyam", "abhishek", "sumit");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    && add Application.yml
                 predicates:
                   - Path=/hotels/**,/staffs/**
--------------------------------------------------------------------------
class 21:Config server in Microservices

refer page 12 of microservices.ppt

it provide a client server architechture provide karta hai jiise hmlog configuration
externalize kar skate hai distributed system mai.

--------------------------------------------------------------------------
class 22:Implementing Config Server in Microservices

& class 23:Reading config from github

create a config server project from spring initializr.

http://192.168.0.105:8085/actuator/default to check actuator
http://192.168.0.105:8085/actuator/dev
http://192.168.0.105:8085/actuator/prod

git->config->client(user,hotel,rating)
step1: create spring project "configServer"
step 2:add dependency--> config server(git svn &hash v) &&
       spring-cloud-starter-netflix-eureka-client
step 3:applicatin.yml
                       server:
                        port: 8085

                      spring:
                        application:
                          name: CONFIGSERVER

                        cloud:
                          config:
                            server:
                              git:
                                uri: https://github.com/avk-007/microService-ConfigServer.git
                                clone-on-start: true
step 4:add @EnableConfigServer annotation
step 5:app git config git configs
step 6: create git repo https://github.com/avk-007/microService-ConfigServer.git

step 7: add dependency config client(spring cloud config)
<dependency><groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-config</artifactId></dependency>
			 to make UserService use config server through application
**Config Client SPRING CLOUD CONFIG
  Client that connects to a Spring Cloud Config Server to fetch the application's configuration.

step 8:update application.yml in UserService add this
           config:
             import: optional:configserver:http://localhost:8085
step 9:
check error in casing spacing and mind it to give always accurate styling in yml file in ide and repo.

step 10
remove spring:application:name from repo only eureka configs will be in git repo

step 11: yml
profiles:
  active:

step 12:
follow same to configure configserver with rating and hotel service



--------------------------------------------------------------------------
class 24: How to handle if microservice is faulty ? | Fault Tolerance and Circuit Breaker

refer :: https://github.com/resilience4j/resilience4j-spring-boot2-demo
         https://resilience4j.readme.io/
refer page 13 of ppt
----------------------------------------------------------------------------
class 25: Implementing Circuit Breaker using Resilience4J in one shot

like we are making UserService as for fault tolerance

steps 1 :go to userService pom.xml
          add dependency:
<!--		for resilience circut breaker fault tolerance dependencies needed-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-aop</artifactId>
		</dependency>
		<!-- https://mvnrepository.com/artifact/io.github.resilience4j/resilience4j-spring-boot2 -->
		<dependency>
			<groupId>io.github.resilience4j</groupId>
			<artifactId>resilience4j-spring-boot2</artifactId>
		</dependency>

step2:go to usercontroller now ; use @GetMapping("/{userId}") //single user get
we will use this method to implement circuit breaker

step 3:add private Logger logger = LoggerFactory.getLogger(userController.class);

step 4: @GetMapping("/{userId}")
            //single user get
            @CircuitBreaker(name ="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
            public ResponseEntity<User>getUser(@PathVariable String userId){
            //added
                logger.info("get single user Handler: userController");
            User user = userService.getUser(userId);
            return ResponseEntity.ok(user);
        } //add this is important
            //create fallbackmethod  for circuit breaker in userController
        public ResponseEntity<User> ratingHotelFallback(String userId,Exception exception){
               logger.info("fallback is executed because service is down :",exception.getMessage());
            User user = User.builder().email("dummy@gmail.com").name("dummy").
                    about("this user is created dummy beacuse some service is down").userId("11224").build();
            return new ResponseEntity<>(user,HttpStatus.OK); }

steps 5: resilience4j config in application.yml

management:
  health:
    circuitbreakers:
      enabled: true
  endpoint:
    health:
      show-details: always

resilience4j:
  circuitbreaker:
    instances:
      ratingHotelBreaker:
        registerHealthIndicator: true
        eventConsumeBufferSize: 10
        failureRateThresold: 50
        minimumNumberOfCalls: 5
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 6s
        permittedNumberOfCallsInHalfOpenState: 3
        slidingWindowSize: 10
        slidingWindowType: COUNT_BASED

#configuring all this setting from https://resilience4j.readme.io/docs/circuitbreaker#count-based-sliding-window

 step 6:check http://192.168.0.125:8081/users/a9984258-cebd-40ea-8ec8-bd7d3e7c569c

 step 7: stop rating  service (ckt breaker) means during fetch from users ratings will not going to show
 dummy details will show
 o/p fallback is executed because service is down :in userService

 step 8: to check in health :: health http://192.168.0.125:8081/actuator/health

 note --> where and when to use circuitBreaker:
 use wherever you want implement circuitBreaker pattern you can implement in same way,and you can implement in
 different methods after implementing do configuration in application.yml,,like in apigateway rating,hotelSrvice etc
 like you are dependent in service which calls different service ,you can do it by circuitBreaker.

 -----------------------------------------------------------------------------------
 class :Retry
 step 1: where we implement circuitBreaker ,in place of that we are going to use Retry.
 Step 2: int retryCount=1;
             @GetMapping("/{userId}")

             //single user get
            // @CircuitBreaker(name ="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")

             @Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallback")
             public ResponseEntity<User>getUser(@PathVariable String userId){
               //  logger.info("get single user Handler: userController");
                  logger.info("Retry count: {}", retryCount);
                  retryCount++;
             User user = userService.getUser(userId);
             return ResponseEntity.ok(user);}

    step 3:application.yml
                retry:
                  instances:
                    ratingHotelService:
                      max-attempts: 3
                      wait-duration: 5s
 ------------------------------------------------------------------------------------------
 class 26:rate limiter
 concept refer page 14

 class 27 & 28: implement rate limiter

 step 1: go to https://resilience4j.readme.io/docs/ratelimiter
 step 2:check dependency aop,resilience4j-spring-boot2,actuator
 step 3: @GetMapping("/{userId}")

                 //single user get
                // @CircuitBreaker(name ="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")

                 //@Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallback")
                 @RateLimiter(name="userRateLimiter",fallbackMethod = "ratingHotelFallback")
                 public ResponseEntity<User>getUser(@PathVariable String userId){...}

  step 4:application.yml   ratelimiter:
                             instances:
                               userRateLimiter:
                                 limitForPeriod: 2
                                 limitRefreshPeriod: 4s
                                 timeoutDuration: 2s

   step 5:download jmeter 5.6 open jmeter.bat
   step 6:steps to use jmeter
  open jmeter.bat
  testplan->add->threadUser->threadgroup->openid.continue
  no of threads->15 or any no
  ramp of period->1
  loop->1
  then..
  threadgroup->add->sampler->httprequest->webserver
  server name ip-localhost,port:8081

  then..
  httprequest->(right click)->add ->listener->viewResultTree
  click on save and start with green logo

   ------------------------------------------------------
   class 29:SpringSecurity with okta Auth
   refer ppt for spring security with okta
---------------------------------------------------------------
   class 30:configuartion in okta acpunt and creation of apllication

   id: https://dev-98806091-admin.okta.com/admin/dashboard
   always refer internet and documentation

   ---------------------------------------------------------------

   class 31:Implementing Spring Security at API GATEWAY using OKTA

   step 1:pom.xml springSecurity,okta
   step 2:edit application.yml
   okta:
     oauth2:
       issuer: https://dev-98806091.okta.com/oauth2/default
       audience: api://default
       client-id: 0oabcwigeq8s2DvYp5d7
       client-secret: QSNKyl2Oyg8pcxHtBU4P7xHO0aBs5llNh6yyH2XDxYKAxDoAFUz_n3QMJwSP4zrX
       scopes: openid, profile, email , offline_access

       //for scopes and infos:https://dev-98806091-admin.okta.com/admin/oauth2/as/ausbcn847kYurWPpa5d7#scopes


   step 3: in okta website in application(add scope and claim)

   add scope name-inernal & claim (name,token-accestoken,value-groups,filter-matches regex-.*,disable claim-x,
   include in-.anyScope) and create

   step 4:remember youre working gatewap api only.

   create config package in that create Security config class with annotation and bean creation.

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

step 5:   create controller package in that create AuthController for login usage
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

        return new ResponseEntity<>(authResponse, HttpStatus.OK);}}

step 6:  for AuthResponse create a model payload class
//this authResonse in nothing but used to call datas from controller to postman or in browser

@Data
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class AuthResponse {
    private String userId;
    private String accesToken;
    private String refreshToken;
    private long   expireAt;
    private Collection<String> authorities;
}

step 7:create methods in authController like authResponse.setUserId(user.getEmail());......

step 8: build and run api gateway

step 9:postman will show error with status 400
step 10: go to okta web app and change the redirect url
step 11: start again the gateway
step 12:vist: localhost:8084/auth/login and enter user & pass for okta and see the details with token in it.
mainly a token in that STring is going to genarate take that token
step 13:go to postman visit :
choose oauth2 ,request headers and given token with bearer and boom you fetch the details usng that.

-------------------------------------------------------------------
class 32:Major challenge while implement security in userservice
note:: pehle userSerice ko client banana hoga aur spring security bhi lagana hoga,
jab user se rating service karna hoga to bearer token bhejega user

step 1: add dependency okta,oauth2client,spring security
step 2: yml okta:
              oauth2:
                issuer: https://dev-98806091.okta.com/oauth2/default
                audience: api://default

               security:
                 oauth2:
                   resourceserver:
                      jwt:
                        issuer-uri: https://dev-98806091.okta.com/oauth2/default
                   client:
                     registration:
                       myInternalClient:
                         provider: okta
                         authorization-grant-type: client_credentials
                         scope: internal
                         client-id: 0oabcwigeq8s2DvYp5d7
                         client-secret: QSNKyl2Oyg8pcxHtBU4P7xHO0aBs5llNh6yyH2XDxYKAxDoAFUz_n3QMJwSP4zrX
                     provider:
                       okta:
                         issuerUri: https://dev-98806091.okta.com/oauth2/default



step 3:create class in config
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //imp to enable security in api we add this annotation
public class webSecurityConfig {
        //methods of oauth security start with httpSecurity.
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {

            security.authorizeHttpRequests()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .oauth2ResourceServer()
                    .jwt();
            return security.build();
    }}

step 4:important we create feignClient in external packages of userService
***Intercepter need for restTtemplate and feignCLient
(kyunki ye dono hi methods ko call karega aur token create karega)***

-----------------------------------------------------------------
class 33:Creating Feign Client Interceptor Step by Step

***Intercepter need for restTtemplate and feignCLient
(kyunki ye dono hi methods ko call karega aur token create karega

step 1:go to  package create a class under config>subpackage and create FeignClientInterceptor
https://developer.okta.com/blog/2019/05/22/java-microservices-spring-boot-spring-cloud

step 2:@Configuration
       @Component
       public class FeignClientInterceptor  implements RequestInterceptor {

           //to get the token with the help of this class
           private OAuth2AuthorizedClientManager manager;
           @Override
           public void apply(RequestTemplate requestTemplate) {
               //https://developer.okta.com/blog/2019/05/22/java-microservices-spring-boot-spring-cloud
               requestTemplate.header("Authorization","Bearer"+token);
           }
       }

 step 3: add   //to get the token with the help of this class
                         private OAuth2AuthorizedClientManager manager;
 step 4:public void apply(RequestTemplate requestTemplate) {
                //https://developer.okta.com/blog/2019/05/22/java-microservices-spring-boot-spring-cloud use this reference
                //now using OAuth2AuthorizedClientManager manager to get token
                //myInternalClient from yml file
                String token = manager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("myInternalClient")
                        .principal("Internal")
                        .build()).getAccessToken().getTokenValue();
                //use local variable to get the name token

                requestTemplate.header("Authorization","Bearer"+token);

step 4: create bean of OAuth2AuthorizedClientManager  DefaultOAuth2AuthorizedClientManager in myconfig
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



-----------------------------------------------------------------
class 34:Creating Rest Template Interceptor
edit the bean of RestTemplate with interceptors

step 1:@Bean
           @LoadBalanced
           //when multiple instance we use loadbalance,to reduce load
           public RestTemplate restTemplate() {
            RestTemplate restTemplate = new RestTemplate();
            List<ClientHttpRequestInterceptor> interceptorList=new ArrayList<>();
            interceptorList.add(new RestTemplateInterceptor());
                    //crate class for RestTemplateInterceptor in
               // com/micro/user/service/config/interceptor/RestTemplateInterceptor.java
            restTemplate.setInterceptors(interceptorList);
               return restTemplate;
           }

  step 2: create class   RestTemplateInterceptor in interceptor in config
  step 3:   work in RestTemplateInterceptor class now
  public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {
      //define manager
      private OAuth2AuthorizedClientManager manager;

      public RestTemplateInterceptor(OAuth2AuthorizedClientManager manager) {
          this.manager = manager;
      }

      //send mananger from restTemplate
      @Override
      public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
          //copied from FeignClientInterceptor
          String token = manager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("myInternalClient")
                  .principal("Internal")
                  .build()).getAccessToken().getTokenValue();
          //use local variable to get the name token

         request.getHeaders().add("Authorization","Bearer"+token);
        //use execution inplace of null

          return execution.execute(request,body);
      }
  }

  step 5 : to pass manager in  interceptorList.add(new RestTemplateInterceptor(manager(..here..));
  create @Autowired
              private ClientRegistrationRepository clientRegistrationRepository;
              @Autowired
              private OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository;

              done

-----------------------------------------------------------------

class 35:
pom.xml dependendency -springSecurity and okta only
step 2.yml
#for security
okta:
  oauth2:
    issuer: https://dev-98806091.okta.com/oauth2/default
    audience: api://default

step 3:create SecurityConfig class
       @Configuration
       @EnableWebSecurity
       @EnableGlobalMethodSecurity(prePostEnabled = true)
       public class SecurityConfig {

           @Bean
           public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {


               security
                       .authorizeHttpRequests()
                       .anyRequest()
                       .authenticated()
                       .and()
                       .oauth2ResourceServer()
                       .jwt();

               return security.build();
           }
       }

 step 4:same for hotelService repeat the same

 step 5:: most important
 for particular access we can implement with the help of @EnableGlobalMethodSecurity(prePostEnabled = true)
 eg in hotelController: @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")



-----------------------------------------------------------------

class 36:testing Secure microServices

go to userController in @ getMapping and // the logger.info and retry++

step 2: add in restTemplate interceptor
         private Logger logger= LoggerFactory.getLogger(RestTemplateInterceptor.class);
and
//added while testing
         logger.info("Rest Template interceptor: Token :  {} ",token);

         and do debugging add printStacktrace

         in console: org.springframework.security.oauth2.client.ClientAuthorizationException: [invalid_scope] One or more scopes are not configured for the authorization server resource.
                     	at org.springframework.security.oauth2.client.ClientCredentialsOAuth2AuthorizedClientProvider.getTokenRespon

                     	go to okta app and select client credentials

-----------------------------------------------------------------




























