package ntua.softeng28.evcharge.IntegrationTests

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.TestPropertySource

import spock.lang.Specification
import spock.lang.Stepwise

@SpringBootTest(webEnvironment=RANDOM_PORT)
@TestPropertySource(locations="classpath:application-test.properties")
@Stepwise
class EnergyProviderControllerSpec extends Specification {
	@Autowired TestRestTemplate client
	
	def "return a not allowed response"() {
		when:
		def entity=client.getForEntity("/evcharge/api/energyproviders", List)
		
		then:
		entity.statusCodeValue == 200 //HttpStatus.Ok
	}
}
