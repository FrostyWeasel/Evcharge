package UnitTests

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

import ntua.softeng28.evcharge.charging_point.ChargingPoint
import ntua.softeng28.evcharge.charging_point.ChargingPointRepository
import ntua.softeng28.evcharge.charging_station.Address
import ntua.softeng28.evcharge.charging_station.AddressRepository
import ntua.softeng28.evcharge.charging_station.ChargingStation
import ntua.softeng28.evcharge.charging_station.ChargingStationRepository
import ntua.softeng28.evcharge.charging_station.Country
import ntua.softeng28.evcharge.operator.Operator
import ntua.softeng28.evcharge.operator.OperatorRepository
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
@DataJpaTest
@TestPropertySource(locations="classpath:application-test.properties")
class ChargingStationSpec extends Specification{

	@Autowired
	ChargingStationRepository stationrepo

	@Autowired
	AddressRepository addressrepo

	@Autowired
	ChargingPointRepository chargingpointrepo

	@Autowired
	OperatorRepository operatorrepo

	def "testing saving and reading operation"() {
		given:
		def station = new ChargingStation()
		def address = new Address()
		def operator = new Operator()
		def chargingpointset = new HashSet<ChargingPoint>()
		def country = new Country()
		def chargingpoint = new ChargingPoint()

		country.setName("Greece")
		country.setISOCode("123456789")
		country.setContinentCode("23.24.123")

		address.setAddress("Gkazi")
		address.setTown("Athens")
		address.setProvince("some province")
		address.setCountry(country)

		operator.setName("Energy")

		chargingpoint.setOperator(operator)
		chargingpointset.add(chargingpoint)

		station.setAddress(address)
		station.setChargingPoints(chargingpointset)
		station.setOperator(operator)

		when:
		def savedoperator=operatorrepo.save(operator)
		def savedaddress=addressrepo.save(address)
		def savedchargingpoint=chargingpointrepo.save(chargingpoint)
		def savedchargingstation=stationrepo.save(station)

		def List<ChargingStation> stationsfromdb = stationrepo.findAll()

		then:
		stationsfromdb.size() == 1

		and:
		stationsfromdb[0].getAddress().getTown()==savedaddress.getTown()
		stationsfromdb[0].getOperator().getName()==savedoperator.getName()
	}

	def "testing deletion operation"(){
		given:
		def station = new ChargingStation()
		def address = new Address()
		def operator = new Operator()
		def chargingpointset = new HashSet<ChargingPoint>()
		def country = new Country()
		def chargingpoint = new ChargingPoint()

		country.setName("Greece")
		country.setISOCode("123456789")
		country.setContinentCode("23.24.123")

		address.setAddress("Gkazi")
		address.setTown("Athens")
		address.setProvince("some province")
		address.setCountry(country)

		operator.setName("Energy")

		chargingpoint.setOperator(operator)
		chargingpointset.add(chargingpoint)

		station.setAddress(address)
		station.setChargingPoints(chargingpointset)
		station.setOperator(operator)

		when:
		def savedoperator=operatorrepo.save(operator)
		def savedaddress=addressrepo.save(address)
		def savedchargingpoint=chargingpointrepo.save(chargingpoint)
		def savedchargingstation=stationrepo.save(station)

		def List<ChargingStation> stationsfromdb_1 = stationrepo.findAll()
		
		stationrepo.deleteAll()
		
		def List<ChargingStation> stationsfromdb_2 = stationrepo.findAll()
		
		then:
		stationsfromdb_1.size() == 1
		
		and:
		stationsfromdb_2.size() == 0
	}
	
	def "testing the update operation"(){
		given:
		def station = new ChargingStation()
		def address = new Address()
		def operator = new Operator()
		def chargingpointset = new HashSet<ChargingPoint>()
		def country = new Country()
		def chargingpoint = new ChargingPoint()

		country.setName("Greece")
		country.setISOCode("123456789")
		country.setContinentCode("23.24.123")

		address.setAddress("Gkazi")
		address.setTown("Athens")
		address.setProvince("some province")
		address.setCountry(country)

		operator.setName("Energy")

		chargingpoint.setOperator(operator)
		chargingpointset.add(chargingpoint)

		station.setAddress(address)
		station.setChargingPoints(chargingpointset)
		station.setOperator(operator)
		
		when:
		def savedoperator=operatorrepo.save(operator)
		def savedaddress=addressrepo.save(address)
		def savedchargingpoint=chargingpointrepo.save(chargingpoint)
		def savedchargingstation=stationrepo.save(station)
		
		def List<ChargingStation> stationsfromdb = stationrepo.findAll()
		
		stationsfromdb[0].getOperator().setName("Protergia")
		stationsfromdb[0].getAddress().setTown("Patra")
				
		stationrepo.save(stationsfromdb[0])
		
		stationsfromdb=stationrepo.findAll()
		
		then:
		stationsfromdb.size() == 1
		
		and:
		stationsfromdb[0].getOperator().getName() == "Protergia" //changed values should be affected
		stationsfromdb[0].getAddress().getTown() == "Patra"
		stationsfromdb[0].getAddress().getAddress() == "Gkazi" //unchanged values should not be affected

	}
}
