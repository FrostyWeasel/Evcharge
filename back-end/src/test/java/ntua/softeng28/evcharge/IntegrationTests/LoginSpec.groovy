package ntua.softeng28.evcharge.IntegrationTests

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.*
import org.springframework.test.context.TestPropertySource

import groovyx.net.http.*
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
@SpringBootTest(webEnvironment=DEFINED_PORT)
@TestPropertySource(locations="classpath:application-test.properties")
class LoginSpec extends Specification {

	private final static int EXPECTED_PORT = 8765;

	def baseurl = "http://localhost:8765/evcharge/api/"


	def "check simple login"(){
		given:
            def client = new RESTClient(baseurl)
		    Map<String, Object> user = new HashMap<>();
		    user.put("username", "theBilbs");
		    user.put("password", "bilbo123");

		when:
		    def response = client.post(path:"login",
		                               requestContentType: MediaType.APPLICATION_FORM_URLENCODED_VALUE,
									   contentType: MediaType.APPLICATION_JSON,
		                               body: user)
			
		then:
		    response.status==200
		    println(response.getData())
	}
}
