package ntua.softeng28.evcharge.UnitTests

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

import ntua.softeng28.evcharge.energy_provider.EnergyProvider
import ntua.softeng28.evcharge.energy_provider.EnergyProviderRepository
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
@DataJpaTest
@TestPropertySource(locations="classpath:application-test.properties")
class EnergyProviderSpec extends Specification {

	@Autowired
	EnergyProviderRepository energyproviderrepo

	def "testing if save and read from db are ok"() {
		given:
		def provider = new EnergyProvider("Energy",100,200,300,150,250)

		when:
		def savedprovider=energyproviderrepo.save(provider)

		def List<EnergyProvider>providersfromdb=energyproviderrepo.findAll()

		then:
		providersfromdb.size() == 1

		and:
		savedprovider.equals(providersfromdb[0])
	}

	def "testing delete from database operation"(){
		given:
		def provider = new EnergyProvider("Energy",100,200,300,150,250)

		when:
		def savedprovider=energyproviderrepo.save(provider)

		energyproviderrepo.deleteAll()

		def List<EnergyProvider>providersfromdb=energyproviderrepo.findAll()

		then:
		providersfromdb.size() == 0
	}
	
	def "testing update operation"(){
		given:
		def provider = new EnergyProvider("Energy",100,200,300,150,250)

		when:
		def savedprovider=energyproviderrepo.save(provider)

		def List<EnergyProvider>providersfromdb=energyproviderrepo.findAll()

		providersfromdb[0].setLowPrice(150)
		providersfromdb[0].setMidPrice(250)
		providersfromdb[0].setHighPrice(350)
		
		providersfromdb=energyproviderrepo.findAll()
		
		then:
		providersfromdb[0].getBrandName()=="Energy"
		providersfromdb[0].getLowPrice()==150
		providersfromdb[0].getMidPrice()==250
		providersfromdb[0].getHighPrice()==350
		and:
		providersfromdb.size() == 1

	}
}
