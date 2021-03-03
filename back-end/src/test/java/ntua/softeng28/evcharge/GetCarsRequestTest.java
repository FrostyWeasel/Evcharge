// package ntua.softeng28.evcharge;

// import org.junit.jupiter.api.Test;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
// import org.springframework.boot.test.web.client.TestRestTemplate;
// import org.springframework.boot.web.server.LocalServerPort;
// import org.springframework.http.HttpEntity;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;

// import java.util.Collection;
// import java.util.Collections;
// import java.util.HashMap;
// import java.util.Map;

// import org.assertj.core.util.Arrays;

// @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// public class GetCarsRequestTest {

// 	@LocalServerPort
// 	private int port;

// 	@Autowired
// 	private TestRestTemplate restTemplate;

// 	Logger logger = LoggerFactory.getLogger(GetCarsRequestTest.class);

// 	@Test
// 	public void greetingShouldReturnDefaultMessage() throws Exception {
// 		String loginURL = "http://localhost:8765/evcharge/api/login";
		
// 		HttpHeaders headers = new HttpHeaders();
// 		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
// 		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

// 		Map<String, Object> body = new HashMap<>();
// 		body.put("username", "theBilbs");
// 		body.put("password", "bilbo123");
		
// 		HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

		
// 		ResponseEntity loginResponse = this.restTemplate.postForEntity(loginURL, request, ResponseEntity.class);

// 		logger.info("greetingShouldReturnDefaultMessage status code: {}", loginResponse.getStatusCode());

//         //assertThat(this.restTemplate.postForEntity("http://localhost:8765/evcharge/api/login" ));
// 		// assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/evcharge/api/cars",
// 		// 		String.class)).contains("Hello, World");
// 	}
// }