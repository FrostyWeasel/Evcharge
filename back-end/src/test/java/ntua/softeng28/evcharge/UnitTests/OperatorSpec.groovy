package ntua.softeng28.evcharge.UnitTests

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

import ntua.softeng28.evcharge.operator.*
import spock.lang.Specification

@DataJpaTest
@ActiveProfiles("Test")
class OperatorSpec extends Specification {
	@Autowired
	private OperatorRepository operatorrepo
	
	def operator = new Operator(1L,"John Cena")
	
	def "Check if crud is ok"(){
		given:
		def savedoperator=operatorrepo.save(operator)
		
		when:
		def operatorfromdb=operatorrepo.findById(operator.getId())
		
		then:
		operatorfromdb.getId()==savedoperator.getId()
	}
}
