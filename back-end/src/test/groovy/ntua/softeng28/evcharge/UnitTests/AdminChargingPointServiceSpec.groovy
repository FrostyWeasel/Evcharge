package ntua.softeng28.evcharge.UnitTests

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

import ntua.softeng28.evcharge.admin.ChargingPointData
import ntua.softeng28.evcharge.admin.ChargingPointDataRequest
import ntua.softeng28.evcharge.admin.ChargingPointService
import ntua.softeng28.evcharge.charging_point.ChargingPoint
import ntua.softeng28.evcharge.charging_point.ChargingPointRepository
import ntua.softeng28.evcharge.charging_station.Address
import ntua.softeng28.evcharge.charging_station.Country
import ntua.softeng28.evcharge.operator.Operator
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
@DataJpaTest
@TestPropertySource(locations="classpath:application-test.properties")
class AdminChargingPointServiceSpec extends Specification {
	
	@Autowired
	ChargingPointRepository pointrepo
	
	def "testing if ChargingPointService saves a charging point to DB"(){
		given:
		def chargingpointdata = new ChargingPointData()
		def operator = new Operator("some operator")
		def country = new Country()
		country.setContinentCode("random")
		country.setISOCode("random")
		country.setName("Greece")
		def address = new Address()
		address.setAddress("random")
		address.setCountry(country)
		address.setProvince("random")
		address.setTown("Athens")
		
		chargingpointdata.setAddressInfo(address)
		chargingpointdata.setOperator(operator)
		
		ChargingPointData[] request = [chargingpointdata]
		
		def chargingpointdatarequest=new ChargingPointDataRequest()
		chargingpointdatarequest.setPointData(request)
		
		def service = new ChargingPointService()
		
		when:
		service.saveChargingPointsToDB(chargingpointdatarequest) 
		
		def List<ChargingPoint> chargingpointsfromdb = pointrepo.findAll()
		
		then:
		chargingpointsfromdb.size()==1
	}
}
