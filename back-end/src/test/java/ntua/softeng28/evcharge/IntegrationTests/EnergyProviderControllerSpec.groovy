package ntua.softeng28.evcharge.IntegrationTests

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.test.context.TestPropertySource

import ntua.softeng28.evcharge.energy_provider.EnergyProvider
import spock.lang.Specification
import spock.lang.Stepwise

@SpringBootTest(webEnvironment=RANDOM_PORT)
@TestPropertySource(locations="classpath:application-test.properties")
@Stepwise
class EnergyProviderControllerSpec extends Specification {
	@Autowired TestRestTemplate client
	@Autowired EnergyProvider provider
	
	private provider=new EnergyProvider("Kobe Bryant",100,200,300,150,250)
	
	def baseurl="/evecharge/api/"	
	def loginurl="/evecharge/api/login"
	
	def "check if unauthorized action returns 401"() {
		when:
		def entity=client.getForEntity(baseurl+"energyproviders", ArrayList.class)
		
		then:
		entity.statusCodeValue == 401 //unauthorized
	}
	
	def "check if authorized action returns 200"(){
		when:		 
		def entity=client.withBasicAuth("theBilbs","bilbo123").getForEntity(baseurl+"energyproviders",ArrayList.class)
		
		then:
		entity.statusCodeValue == 200
	}
	
}
