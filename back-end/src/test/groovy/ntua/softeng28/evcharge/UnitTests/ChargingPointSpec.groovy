package ntua.softeng28.evcharge.UnitTests

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

import ntua.softeng28.evcharge.charging_point.ChargingPoint
import ntua.softeng28.evcharge.charging_point.ChargingPointRepository
import ntua.softeng28.evcharge.operator.Operator
import ntua.softeng28.evcharge.operator.OperatorRepository
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
@DataJpaTest
@TestPropertySource(locations="classpath:application-test.properties")
class ChargingPointSpec extends Specification{
	
	@Autowired
	ChargingPointRepository chargingPointRepo
	
	@Autowired
	OperatorRepository operatorrepo
	
	def "testing saving operation"() {
		given:
		def operator = new Operator("Kobe Bryant")
		def chargingpoint=new ChargingPoint(operator)
		
		when:
		def savedoperator=operatorrepo.save(operator)
		def savedchargingpoint=chargingPointRepo.save(chargingpoint)
		
		then:
		savedchargingpoint.getOperator().getName()==chargingpoint.getOperator().getName()
	}
	
	def "testing reading operation"(){
		given:
		def operator = new Operator("Lebron James")
		def chargingpoint=new ChargingPoint(operator)

		when:
		def savedoperator=operatorrepo.save(operator)
		def savedchargingpoint=chargingPointRepo.save(chargingpoint)
		
		def List<ChargingPoint>chargingpointfromdb = chargingPointRepo.findAll()
		
		then:
		chargingpointfromdb[0].getOperator().getId()==savedoperator.getId()
	}
	
	def "testing delete operation"(){
		given:
		def operator = new Operator("Kevin Durant")
		def chargingpoint=new ChargingPoint(operator)

		when:
		def savedoperator=operatorrepo.save(operator)
		def savedchargingpoint=chargingPointRepo.save(chargingpoint)
		
		operatorrepo.deleteAll()
		chargingPointRepo.deleteAll()
		
		def List<ChargingPoint>chargingpointfromdb = chargingPointRepo.findAll()
		
		then:
		chargingpointfromdb.size() == 0
	}
	
	def "testing update operation"(){
		given:
		def operator = new Operator("Kevin Durant")
		def chargingpoint=new ChargingPoint(operator)

		when:
		def savedoperator=operatorrepo.save(operator)
		def savedchargingpoint=chargingPointRepo.save(chargingpoint)
		
		def List<ChargingPoint>chargingpointfromdb = chargingPointRepo.findAll()
		
		chargingpointfromdb[0].getOperator().setName("Giannis Antetokounmpo")
		
		chargingPointRepo.save(chargingpointfromdb[0])
		
		chargingpointfromdb=chargingPointRepo.findAll()
		
		then:
		chargingpointfromdb.size() == 1
		
		and:
		chargingpointfromdb[0].getOperator().getName()=="Giannis Antetokounmpo"
	}
}
