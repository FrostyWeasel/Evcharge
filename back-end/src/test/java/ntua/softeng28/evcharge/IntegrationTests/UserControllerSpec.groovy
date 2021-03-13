package ntua.softeng28.evcharge.IntegrationTests

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource

import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
@SpringBootTest(webEnvironment=DEFINED_PORT)
@TestPropertySource(locations="classpath:application-test.properties")
class UserControllerSpec extends Specification{

	private final static int EXPECTED_PORT = 8765;

	def baseurl = "http://localhost:8765/evcharge/api/"

	def "a get all users request should return 200 status code"(){
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

		def header=["X-OBSERVATORY-AUTH":token]

		def getResponse = client.get(path:"users",
		requestContentType: MediaType.APPLICATION_JSON,
		contentType: MediaType.APPLICATION_JSON,
		headers: header)

		then:
		getResponse.status == 200
	}

	def "a get request with invalid username should throw an exception"(){
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

		def header=["X-OBSERVATORY-AUTH":token]

		def getResponse = client.get(path:"users/BlackMamba",
		requestContentType: MediaType.APPLICATION_JSON,
		contentType: MediaType.APPLICATION_JSON,
		headers: header)

		then:
		HttpResponseException e = thrown()
		e.statusCode == 400
	}

	def "checking creating a new user functionality"(){
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

		def header=["X-OBSERVATORY-AUTH":token]

		def postResponse = client.post(path:"admin/usermod/BlackMamba/24",
		requestContentType: MediaType.APPLICATION_JSON,
		contentType: MediaType.TEXT_PLAIN,
		headers: header)

		then:
		postResponse.status == 200
	}
}
