package ntua.softeng28.evcharge.UnitTests

import javax.annotation.sql.DataSourceDefinition

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource
import org.springframework.transaction.annotation.Transactional

import ntua.softeng28.evcharge.operator.*
import spock.lang.Specification
import spock.lang.Stepwise

@DataJpaTest
@TestPropertySource(locations="classpath:application-test.properties")
@Stepwise
@Transactional
class OperatorSpec extends Specification {
	@Autowired
	private OperatorRepository operatorrepo

	def operator1 = new Operator("John Cena")
	def operator2 = new Operator("Cristiano Ronaldo")
	def operator3 = new Operator("Kobe Bryant")

	def "Check if creating and reading is ok"(){
		given:
		operatorrepo.deleteAll()
		
		operatorrepo.save(operator1)
		operatorrepo.save(operator2)
		operatorrepo.save(operator3)

		when:
		def List<Operator> operatorsfromdb=operatorrepo.findAll()

		then:
		operatorsfromdb.size() == 3

		and:
		operatorsfromdb[0].name.equals("John Cena")
		operatorsfromdb[1].name.equals("Cristiano Ronaldo")
		operatorsfromdb[2].name.equals("Kobe Bryant")
	}

	def "Check if update is ok"(){
		given:
		operatorrepo.deleteAll()
		
		operatorrepo.save(operator1)
		operatorrepo.save(operator2)
		operatorrepo.save(operator3)

		when:
		def List<Operator> operatorsfromdb=operatorrepo.findAll()
		
		operatorsfromdb[0].setName("Donovan Mitchell")
		operatorsfromdb[1].setName("Jayson Tatum")
		operatorsfromdb[2].setName("Kawhi Leonard")
		operatorrepo.saveAll(operatorsfromdb)
		
		def List<Operator> updatedoperatorsfromdb=operatorrepo.findAll()

		then:
		updatedoperatorsfromdb[0].name=="Donovan Mitchell"
		updatedoperatorsfromdb[1].name=="Jayson Tatum"
		updatedoperatorsfromdb[2].name=="Kawhi Leonard"

	}

	def "Check if delete is ok"(){
		given:
		operatorrepo.deleteAll()
		operatorrepo.save(operator1)
		operatorrepo.save(operator2)
		operatorrepo.save(operator3)

		when:
		operatorrepo.deleteAll()
		def List<Operator> operatorsfromdb=operatorrepo.findAll()

		then:
		operatorsfromdb.size() == 0
	}
}