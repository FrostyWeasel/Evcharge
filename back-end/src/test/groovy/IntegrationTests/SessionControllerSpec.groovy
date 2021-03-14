package IntegrationTests

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT

import java.sql.Timestamp

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource

import groovy.json.JsonOutput
import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import ntua.softeng28.evcharge.session.SessionDataRequest
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
@SpringBootTest(webEnvironment=DEFINED_PORT)
@TestPropertySource(locations="classpath:application-test.properties")
class SessionControllerSpec extends Specification {

	private final static int EXPECTED_PORT = 8765

	def baseurl = "https://localhost:8765/evcharge/api/"

	def "a get all sessions request should return 200 status code"(){
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

		def getResponse = client.get(path:"sessions",
		requestContentType: MediaType.APPLICATION_JSON,
		contentType: MediaType.APPLICATION_JSON,
		headers: header)

		then:
		getResponse.status == 200
	}

	def "checking if a get request with wrong id returns bad request exception"(){
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

		def getResponse = client.get(path:"sessions/24",
		requestContentType: MediaType.APPLICATION_JSON,
		contentType: MediaType.APPLICATION_JSON,
		headers: header)

		then:
		HttpResponseException e = thrown()
		e.statusCode == 400
	}

	def "a post request with wrong parameters should return a bad request exception"(){
		given:
		def client = new RESTClient(baseurl)

		Map<String, Object> user = new HashMap<>();
		user.put("username", "admin");
		user.put("password", "petrol4ever");

		def timestamp = new Timestamp(System.currentTimeMillis())
		def date = new Date()

		def session = new SessionDataRequest(timestamp,new Timestamp(date.getTime()),"hybrid","card",85.76F,143.78F,"23",5L,12L,"John Cena")

		def sessionInJson = JsonOutput.toJson(session)

		when:
		def loginResponse = client.post(path:"login",
		requestContentType: MediaType.APPLICATION_FORM_URLENCODED_VALUE,
		contentType: MediaType.APPLICATION_JSON,
		body: user)

		def token = loginResponse.getData().toString()
		token = token.substring(7,token.length()-1)

		def header=["X-OBSERVATORY-AUTH":token]

		def postResponse = client.post(path:"sessions",
		requestContentType: MediaType.APPLICATION_JSON,
		contentType: MediaType.APPLICATION_JSON,
		headers: header,
		body: sessionInJson)

		then:
		HttpResponseException e = thrown()
		e.statusCode == 400
	}

	def "a delete request with wrong id should throw a bad request exception"(){
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

		def deleteResponse = client.delete(path:"admin/sessions/134",
		requestContentType: MediaType.APPLICATION_JSON,
		contentType: MediaType.APPLICATION_JSON,
		headers: header)

		then:
		HttpResponseException e = thrown()
		e.statusCode == 400
	}
}
