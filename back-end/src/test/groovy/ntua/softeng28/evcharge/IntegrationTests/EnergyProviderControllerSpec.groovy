package ntua.softeng28.evcharge.IntegrationTests

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT

import org.springframework.beans.factory.annotation.Autowired

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovyx.net.http.RESTClient
import groovyx.net.http.HttpResponseException

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
	
	def baseurl = "https://localhost:8765/evcharge/api/"
	
	def "testing a get all providers request"(){
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
			
		    def getResponse = client.get(path:"energyproviders",
								         requestContentType: MediaType.APPLICATION_JSON,
									     contentType: MediaType.APPLICATION_JSON,
										 headers: header)

		then:
            getResponse.status == 200
	}

	def "testing a post request"(){
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
			
		    def postResponse = client.post(path:"energyproviders",
								         requestContentType: MediaType.APPLICATION_JSON,
									     contentType: MediaType.APPLICATION_JSON,
										 headers: header,
										 body: providerInJsonFormat)
		then:
            postResponse.status == 200
	}

	def "testing a get by id request"(){
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
			
		    def getResponse = client.get(path:"energyproviders/1",
								         requestContentType: MediaType.APPLICATION_JSON,
									     contentType: MediaType.APPLICATION_JSON,
										 headers: header)

		then:
            HttpResponseException e = thrown()
			e.statusCode == 400
	}

	def "testing deletion operation"(){
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
			
		    def deleteResponse = client.delete(path:"admin/energyproviders/1",
								         requestContentType: MediaType.APPLICATION_JSON,
									     contentType: MediaType.APPLICATION_JSON,
										 headers: header)

		then:
            HttpResponseException e = thrown()
			e.statusCode == 400
	}

	def "testing update operation"(){
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
			
		    def putResponse = client.put(path:"admin/energyproviders/1",
								         requestContentType: MediaType.APPLICATION_JSON,
									     contentType: MediaType.APPLICATION_JSON,
										 headers: header)

		then:
            HttpResponseException e = thrown()
			e.statusCode == 400
	}		
}
