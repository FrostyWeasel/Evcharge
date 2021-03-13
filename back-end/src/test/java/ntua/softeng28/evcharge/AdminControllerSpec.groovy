package ntua.softeng28.evcharge

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource

import groovy.json.JsonOutput
import groovyx.net.http.RESTClient
import ntua.softeng28.evcharge.admin.BrandData
import ntua.softeng28.evcharge.admin.CarData
import ntua.softeng28.evcharge.admin.CarDataRequest
import ntua.softeng28.evcharge.admin.ChargingPointData
import ntua.softeng28.evcharge.admin.ChargingPointDataRequest
import ntua.softeng28.evcharge.admin.DcChargerRequest
import ntua.softeng28.evcharge.admin.MetaData
import ntua.softeng28.evcharge.admin.ProviderData
import ntua.softeng28.evcharge.admin.ProviderDataRequest
import ntua.softeng28.evcharge.car.AcCharger
import ntua.softeng28.evcharge.car.ChargingCurvePoint
import ntua.softeng28.evcharge.car.EnergyConsumption
import ntua.softeng28.evcharge.car.PowerPerChargingPoint
import ntua.softeng28.evcharge.charging_station.Address
import ntua.softeng28.evcharge.charging_station.Country
import ntua.softeng28.evcharge.operator.Operator
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
@SpringBootTest(webEnvironment=DEFINED_PORT)
@TestPropertySource(locations="classpath:application-test.properties")
class AdminControllerSpec extends Specification{

	private final static int EXPECTED_PORT = 8765

	def baseurl = "http://localhost:8765/evcharge/api/"

	def "testing a healthcheck request; should return 200 on success"(){
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

		def getResponse = client.get(path:"admin/healthcheck",
		requestContentType: MediaType.APPLICATION_JSON,
		contentType: MediaType.APPLICATION_JSON,
		headers: header)

		then:
		getResponse.status==200
	}

	def "testing a reset session request; Should return 200 on success"(){
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

		def postResponse = client.post(path:"admin/resetsessions",
		requestContentType: MediaType.APPLICATION_JSON,
		contentType: MediaType.APPLICATION_JSON,
		headers: header)

		then:
		postResponse.status==200
	}

	def "testing the creation of a charging point"(){
		given:
		def client = new RESTClient(baseurl)

		Map<String, Object> user = new HashMap<>();
		user.put("username", "admin");
		user.put("password", "petrol4ever");

		def operator = new Operator("Kobe Bryant")
		def country = new Country()
		country.setName("Greece")
		country.setContinentCode("Random")
		country.setISOCode("Unknown")
		def address = new Address()
		address.setAddress("Gkazi")
		address.setCountry(country)
		address.setTown("Athens")
		address.setProvince("I don't know")

		def pointdata = new ChargingPointData()
		pointdata.setAddressInfo(address)
		pointdata.setOperator(operator)

		ChargingPointData[] data = [pointdata]

		def request = new ChargingPointDataRequest()
		request.setPointData(data)

		def requestinjson=JsonOutput.toJson(request)

		when:
		def loginResponse = client.post(path:"login",
		requestContentType: MediaType.APPLICATION_FORM_URLENCODED_VALUE,
		contentType: MediaType.APPLICATION_JSON,
		body: user)

		def token = loginResponse.getData().toString()
		token = token.substring(7,token.length()-1)

		def header=["X-OBSERVATORY-AUTH":token]

		def postResponse = client.post(path:"admin/pointsupd",
		requestContentType: MediaType.APPLICATION_JSON,
		contentType: MediaType.APPLICATION_JSON,
		headers: header,
		body: requestinjson)

		then:
		postResponse.status == 200
	}

	def "testing the creation of a car"(){
		given:
		def client = new RESTClient(baseurl)

		Map<String, Object> user = new HashMap<>();
		user.put("username", "admin");
		user.put("password", "petrol4ever");

		def cardata = new CarData() 					//creating a car
		cardata.setId("1")
		cardata.setBrand("Volvo")
		cardata.setType("Electric")
		cardata.setBrand_id("1")
		cardata.setModel("Some Model")
		cardata.setRelease_year(2015)
		cardata.setUsable_battery_size(100)
		cardata.setVariant("Variant")
		
		def accharger = new AcCharger()					//creating an ac charger for the car
		def powerperpoint = new PowerPerChargingPoint(2.0,2.3,3.7,7.4,11.0,16.0,22.0,43.0) // every ac charger needs power per point
		String[] ports = ["1","2","3"]
		accharger.setMax_power(1000)
		accharger.setPorts(ports)
		accharger.setUsable_phases(3)
		accharger.setPower_per_charging_point(powerperpoint)
		
		cardata.setAc_charger(accharger)
		
		def dccharger=new DcChargerRequest()				//creating a dc charger
		def curvepoint = new ChargingCurvePoint(100,1000)   //it requires a curvepoint
		ChargingCurvePoint[] curvepoints = [curvepoint]
		dccharger.setCharging_curve(curvepoints)
		dccharger.setIs_default_charging_curve(true)
		dccharger.setMax_power(10000)
		dccharger.setPorts(ports)
		
		cardata.setDc_charger(dccharger)
		
		def consumption = new EnergyConsumption(87)
		
		cardata.setEnergyConsumption(consumption)
		
		CarData[] datacar = [cardata] //array of cardata for cardatarequest
		
		def branddata=new BrandData("1","Something")
		BrandData[] databrand= [branddata]				//array of branddata
		
		def metadata=new MetaData("something","Random")
		
		def request = new CarDataRequest(datacar,databrand,metadata)
		def requestinjson=JsonOutput.toJson(request)		

		when:
		def loginResponse = client.post(path:"login",
		requestContentType: MediaType.APPLICATION_FORM_URLENCODED_VALUE,
		contentType: MediaType.APPLICATION_JSON,
		body: user)

		def token = loginResponse.getData().toString()
		token = token.substring(7,token.length()-1)

		def header=["X-OBSERVATORY-AUTH":token]

		def postResponse = client.post(path:"admin/carsupd",
		requestContentType: MediaType.APPLICATION_JSON,
		contentType: MediaType.APPLICATION_JSON,
		headers: header,
		body: requestinjson)

		then:
		postResponse.status == 200
	}

	def "testing the creation of a provider"(){
		given:
		def client = new RESTClient(baseurl)

		Map<String, Object> user = new HashMap<>();
		user.put("username", "admin");
		user.put("password", "petrol4ever");

		def providerdata = new ProviderData("Energy",100,200,300,150,250)
		ProviderData[] data = [providerdata]
		def request = new ProviderDataRequest()
		request.setProviderData(data)

		def requestinjson=JsonOutput.toJson(request)

		when:
		def loginResponse = client.post(path:"login",
		requestContentType: MediaType.APPLICATION_FORM_URLENCODED_VALUE,
		contentType: MediaType.APPLICATION_JSON,
		body: user)

		def token = loginResponse.getData().toString()
		token = token.substring(7,token.length()-1)

		def header=["X-OBSERVATORY-AUTH":token]

		def postResponse = client.post(path:"admin/providersupd",
		requestContentType: MediaType.APPLICATION_JSON,
		contentType: MediaType.APPLICATION_JSON,
		headers: header,
		body: requestinjson)

		then:
		postResponse.status == 200
	}
}
