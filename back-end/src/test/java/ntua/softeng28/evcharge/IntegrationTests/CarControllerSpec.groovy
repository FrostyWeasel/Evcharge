package ntua.softeng28.evcharge.IntegrationTests

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

import groovy.json.JsonOutput
import groovyx.net.http.RESTClient
import ntua.softeng28.evcharge.car.AcCharger
import ntua.softeng28.evcharge.car.Brand
import ntua.softeng28.evcharge.car.Car
import ntua.softeng28.evcharge.car.ChargingCurvePoint
import ntua.softeng28.evcharge.car.DcCharger
import ntua.softeng28.evcharge.car.EnergyConsumption
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
@SpringBootTest(webEnvironment=DEFINED_PORT)
@TestPropertySource(locations="classpath:application-test.properties")
class CarControllerSpec extends Specification {
	private final static int EXPECTED_PORT = 8765

	def baseurl = "http://localhost:8765/evcharge/api/"

	def "checking a basic CRUD functionality"() {
		given:
		def client = new RESTClient(baseurl)

		def acCharger = new AcCharger(1,1,1,1)

		def brand = new Brand("Fiat")

		def chargingCurvePoint_1 = new ChargingCurvePoint(50,100)
		def chargingCurvePoint_2 = new ChargingCurvePoint(60,120)

		def setOfCurvePoints = new HashSet()

		setOfCurvePoints.add(chargingCurvePoint_1)
		setOfCurvePoints.add(chargingCurvePoint_2)

		def ports = ["Blue", "Yellow", "Green"] as String

		def dcCharger = new DcCharger(ports,1000,setOfCurvePoints,true)

		def energyConsumption = new EnergyConsumption(100)

		def car = new Car("electro",brand,"panda",2015,"something",12,acCharger,dcCharger,energyConsumption)


		Map<String, Object> user = new HashMap<>();
		user.put("username", "admin");
		user.put("password", "petrol4ever");

		def carInJson = JsonOutput.toJson(car)

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
		body: carInJson)
	}
}
