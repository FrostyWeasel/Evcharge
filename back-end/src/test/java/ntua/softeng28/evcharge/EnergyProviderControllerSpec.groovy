package ntua.softeng28.evcharge

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT

import org.springframework.beans.factory.annotation.Autowired

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovyx.net.http.RESTClient

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.http.MediaType

import ntua.softeng28.evcharge.energy_provider.EnergyProvider
import spock.lang.Specification
import spock.lang.Stepwise

@SpringBootTest(webEnvironment=DEFINED_PORT)
@TestPropertySource(locations="classpath:application-test.properties")
@Stepwise
class EnergyProviderControllerSpec extends Specification {
	
	private final static int EXPECTED_PORT = 8765
	
	def baseurl = "http://localhost:8765/evcharge/api/"
	
	def "check a basic crud functionality"() {
		given:
		    def client = new RESTClient(baseurl)

			Map<String, Object> user = new HashMap<>();
			user.put("username", "admin");
			user.put("password", "petrol4ever");
            
            def provider = new EnergyProvider("Kobe Bryant",100,200,300,150,250)
			def providerInJsonFormat = JsonOutput.toJson(provider)
			
			def updatedProvider = new EnergyProvider("Kobe Bryant",50,100,150,75,125)
			def updatedProviderInJson = JsonOutput.toJson(updatedProvider)
						
		when:
		    def loginResponse = client.post(path:"login",
		                               requestContentType: MediaType.APPLICATION_FORM_URLENCODED_VALUE,
									   contentType: MediaType.APPLICATION_JSON,
		                               body: user)
			
			def token = loginResponse.getData().toString()
			    token = token.substring(7,token.length()-1)
				
			def header=["X-OBSERVATORY-AUTH":token]
			
			def postResponse = client.post(path:"admin/energyproviders",
				                           requestContentType: MediaType.APPLICATION_JSON,
									       contentType: MediaType.APPLICATION_JSON,
										   headers: header,
		                                   body: providerInJsonFormat)	
			
			def getResponse = client.get(path:"energyproviders",
								         requestContentType: MediaType.APPLICATION_JSON,
									     contentType: MediaType.APPLICATION_JSON,
										 headers: header)
			
			def getByidResponse = client.get(path:"energyproviders/2",
                                         requestContentType: MediaType.APPLICATION_JSON,
									     contentType: MediaType.APPLICATION_JSON,
										 headers: header)
			
			def putResponse = client.put(path:"admin/payments/2",
				                         requestContentType: MediaType.APPLICATION_JSON,
				                         contentType: MediaType.APPLICATION_JSON,
				                         headers: header,
				                         body:updatedProviderInJson)

			def deleteResponse = client.delete(path:"admin/energyproviders/2",
				                               requestContentType: MediaType.APPLICATION_JSON,
				                               contentType: MediaType.APPLICATION_JSON,
				                               headers: header)

		then:
		    postResponse.status == 200
			getResponse.status == 200
			getByidResponse.status == 200
			putResponse.status == 200
			deleteResponse.status == 200
	}	
}
