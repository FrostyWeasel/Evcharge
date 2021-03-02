package ntua.softeng28.evcharge.UnitTests

import javax.transaction.Transactional

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

import ntua.softeng28.evcharge.charging_point.*
import ntua.softeng28.evcharge.operator.*
import spock.lang.Specification
import spock.lang.Stepwise

@DataJpaTest
@TestPropertySource(locations="classpath:application-test.properties")
@Transactional
@Stepwise
class ChargingPointSpec extends Specification {
	@Autowired
	private ChargingPointRepository chargingpointrepo
	
	@Autowired
	private OperatorRepository operatorrepo
	
	def operator = new Operator("Kobe Bryant")
	def chargingpoint = new ChargingPoint(operator)
	
	def "Checking if creat and read is ok"(){
		given:
		operatorrepo.save(operator)
		chargingpointrepo.save(chargingpoint)
		
		when:
		def List<ChargingPoint> chargingpointsfromdb=chargingpointrepo.findAll()
		
		then:
		chargingpointsfromdb[0] == chargingpoint
		
		and:
		chargingpointsfromdb.size() == 1
	}
	
	def "Checking if update is ok"(){
		given:				
		operatorrepo.save(operator)
		chargingpointrepo.save(chargingpoint)
		
		when:
		def List<ChargingPoint> chargingpointsfromdb=chargingpointrepo.findAll()
		chargingpointsfromdb[0].getOperator().setName("John Cena")
		chargingpointrepo.saveAll(chargingpointsfromdb)
		chargingpointsfromdb=chargingpointrepo.findAll()
		
		then:
		chargingpointsfromdb[0].getOperator().name=="John Cena"
		
		and:
		chargingpointsfromdb.size()==1
	}
	
	def "Checking if delete is ok"(){
		given:
		operatorrepo.save(operator)
		chargingpointrepo.save(chargingpoint)
		
		when:
		chargingpointrepo.deleteAll()
		def List<ChargingPoint> chargingpointsfromdb=chargingpointrepo.findAll()
		
		then:
		chargingpointsfromdb.size() == 0

	}
}
