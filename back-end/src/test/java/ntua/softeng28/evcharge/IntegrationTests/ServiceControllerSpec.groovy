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
class ServiceControllerSpec extends Specification{

	private final static int EXPECTED_PORT = 8765

	def baseurl = "http://localhost:8765/evcharge/api/"

	def "a sessionsperpoint request to an empty DB should return 400 status code"(){
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

		def getResponse = client.get(path:"SessionsPerPoint/100/20200930/20210930",
		requestContentType: MediaType.APPLICATION_JSON,
		contentType: MediaType.APPLICATION_JSON,
		headers: header)

		then:
		HttpResponseException e = thrown()

		and:
		e.statusCode==400
	}

	def "a sessionsperstation request to an empty DB should return 400 status code"(){
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

		def getResponse = client.get(path:"SessionsPerStation/100/20200930/20210930",
		requestContentType: MediaType.APPLICATION_JSON,
		contentType: MediaType.APPLICATION_JSON,
		headers: header)

		then:
		HttpResponseException e = thrown()

		and:
		e.statusCode==400
	}

	def "a SessionsPerEv request to an empty DB should return 400 status code"(){
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

		def getResponse = client.get(path:"SessionsPerEV/100/20200930/20210930",
		requestContentType: MediaType.APPLICATION_JSON,
		contentType: MediaType.APPLICATION_JSON,
		headers: header)

		then:
		HttpResponseException e = thrown()

		and:
		e.statusCode==400
	}

	def "a sessionsPerProvider request to an empty DB should return 400 status code"(){
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

		def getResponse = client.get(path:"SessionsPerProvider/100/20200930/20210930",
		requestContentType: MediaType.APPLICATION_JSON,
		contentType: MediaType.APPLICATION_JSON,
		headers: header)

		then:
		HttpResponseException e = thrown()

		and:
		e.statusCode==400
	}

	def "a get all cars from brand request to an empty DB should return 400 status code"(){
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

		def getResponse = client.get(path:"CarsByBrand/125",
		requestContentType: MediaType.APPLICATION_JSON,
		contentType: MediaType.APPLICATION_JSON,
		headers: header)

		then:
		HttpResponseException e = thrown()

		and:
		e.statusCode==400
	}

	def "a get userreport request to an empty DB should return 400 status code"(){
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

		def getResponse = client.get(path:"UserReport/randomuser/20200909/20201010",
		requestContentType: MediaType.APPLICATION_JSON,
		contentType: MediaType.APPLICATION_JSON,
		headers: header)

		then:
		HttpResponseException e = thrown()

		and:
		e.statusCode==400
	}
}
