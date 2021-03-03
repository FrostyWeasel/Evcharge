package ntua.softeng28.evcharge.IntegrationTests

import groovyx.net.http.RESTClient
import javax.transaction.Transactional

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.web.client.RestTemplate

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
class ChargingPointControllerSpec extends Specification {

	def client=new RESTClient("http://localhost:8080/evcharge/api/admin/",JSON)

	def "simple status checker"() {
		when:
		def response=client.post(path:"chargingPoints")

		then:
		response.status==200
	}
}