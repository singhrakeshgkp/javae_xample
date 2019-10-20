package com.tech.rest.webservices;


import java.rmi.StubNotFoundException;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.tech.rest.webservices.model.User;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes=RestfulWebServicesApplication.class, webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles(value="integration")
public class UserJpaResourceTest {

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(9998);
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	
	@Before
	public void setUp() {
		
		mockRemoteService();
	}

	@Test
	public void testRetrieveAllUsers() {
		
		ResponseEntity<List<User>> responseEntity = testRestTemplate.exchange("http://localhost:8093/all", HttpMethod.GET,null,
                new ParameterizedTypeReference<List<User>>(){}
        );
		
		List<User> users = responseEntity.getBody();
		System.out.println("User List "+users.size());
	}

	private void mockRemoteService() {
		
		stubFor(get(urlEqualTo("/all-students"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("response.json")
                ));
	}
	
}
