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
	
	HttpHeaders headers = new HttpHeaders()
	HttpEntity<EnergyProvider> request = new HttpEntity<>(provider,headers)			
	
	def "check if unauthorized action returns 401"() {
		when:
		def entity=client.getForEntity(baseurl+"energyproviders", EnergyProvider.class)
		
		then:
		entity.statusCodeValue == 401 //unauthorized
		entity.body == null
	}
	
	def "check if post request is done correctly"(){
		when:		 
		def entity=client.postForEntity(baseurl+"admin/energyproviders", request, EnergyProvider.class)
		
		then:
		entity.statusCodeValue == 401
	}
	
}
