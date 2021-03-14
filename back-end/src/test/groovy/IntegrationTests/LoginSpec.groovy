package ntua.softeng28.evcharge.IntegrationTests

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource

import groovyx.net.http.*
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
@SpringBootTest(webEnvironment=DEFINED_PORT)
@TestPropertySource(locations="classpath:application-test.properties")
class LoginSpec extends Specification {

	private final static int EXPECTED_PORT = 8765;

	def baseurl = "https://localhost:8765/evcharge/api/"

	def "check simple login-logout functionality"(){
		given:
		def client = new RESTClient(baseurl)
		Map<String, Object> user = new HashMap<>();
		user.put("username", "admin");
		user.put("password", "petrol4ever");

		when:
		def loginResponse = client.post(path:"login",
		requestContentType: MediaType.APPLICATION_FORM_URLENCODED_VALUE,
		contentType: MediaType.APPLICATION_JSON,
		body: user)

		def token = loginResponse.getData().toString()
		token = token.substring(7,token.length()-1)

		def logoutResponse = client.post(path:"logout",
		headers:["X-OBSERVATORY-AUTH":token])
		then:
		loginResponse.status==200

		and:
		logoutResponse.status==200
	}

	def "trying to login with wrong credentils should throw exception"(){
		given:
		def client = new RESTClient(baseurl)
		Map<String, Object> user = new HashMap<>();
		user.put("username", "kostas");
		user.put("password", "prekas");

		when:
		def loginResponse = client.post(path:"login",
		requestContentType: MediaType.APPLICATION_FORM_URLENCODED_VALUE,
		contentType: MediaType.APPLICATION_JSON,
		body: user)

		then:
		HttpResponseException e = thrown()
		e.statusCode == 400
	}
}
