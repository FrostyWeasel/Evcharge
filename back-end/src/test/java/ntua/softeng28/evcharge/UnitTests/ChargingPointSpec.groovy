package ntua.softeng28.evcharge.UnitTests

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

import ntua.softeng28.evcharge.charging_point.ChargingPoint
import ntua.softeng28.evcharge.charging_point.ChargingPointRepository
import ntua.softeng28.evcharge.operator.Operator
import ntua.softeng28.evcharge.operator.OperatorRepository
import spock.lang.Specification

@DataJpaTest
@ActiveProfiles("test")
class ChargingPointSpec extends Specification {

	@Autowired
	private ChargingPointRepository chargingpointrepo

	@Autowired
	private OperatorRepository operatorrepo

	def operator = new Operator(1L,"John Cena")
	def chargingpoint = new ChargingPoint(1L,operator)

	def "find charging point by id"() {

		given:
		def savedoperator = operatorrepo.save(operator)
		def savedchargingpoint  = chargingpointrepo.save(chargingpoint)

		when: "load chargingpoint entity"
		def chargingpointfromdb = chargingpointrepo.findOne(savedchargingpoint.getId())

		then:"saved and retrieved entity by id must be equal"
		savedchargingpoint.getId() == chargingpointfromdb.getId()
	}


	def "find chargingpoint by operator name"() {

		given:
		def savedoperator = operatorrepo.save(operator)
		def savedchargingpoint  = chargingpointrepo.save(chargingpoint)

		when: "find chargingpoint by operator name"
		def chargingpointentity = chargingpointrepo.findOne(savedchargingpoint.getOperator().getId())

		then: "saved and retrieved entity by name must be equal"
		chargingpointentity.getId() == savedchargingpoint.getId()
	}
}