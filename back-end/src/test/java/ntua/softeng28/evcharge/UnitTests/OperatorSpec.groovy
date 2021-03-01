package ntua.softeng28.evcharge.UnitTests

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

import ntua.softeng28.evcharge.operator.*
import spock.lang.Specification

@DataJpaTest
@TestPropertySource(locations="classpath:application-test.properties")
class OperatorSpec extends Specification {
	@Autowired
	private OperatorRepository operatorrepo
	
	def operator1 = new Operator(1L,"John Cena")
	def operator2 = new Operator(2L,"Cristiano Ronaldo")
	def operator3 = new Operator(3L,"Kobe Bryant")
	
	def "Check if saving and retrieving is ok"(){
		given:
		operatorrepo.save(operator1)
		operatorrepo.save(operator2)
		operatorrepo.save(operator3)
		
		when:
		def List<Operator> operatorsfromdb=operatorrepo.findAll()
		
		then:
		operatorsfromdb.size == 3
	}
}