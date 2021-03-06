package ntua.softeng28.evcharge.IntegrationTests

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.TestPropertySource

import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
@SpringBootTest(webEnvironment=RANDOM_PORT)
@TestPropertySource(locations="classpath:application-test.properties")
class CarControllerSpec extends Specification {
	
	@Autowired
	TestRestTemplate client
	
	def "check if unauthorized action returns 401"() {
		when:
		def entity=client.getForEntity("/evcharge/api/cars", List)
		
		then:
		entity.statusCodeValue==401 //unauthorized
	}
}
