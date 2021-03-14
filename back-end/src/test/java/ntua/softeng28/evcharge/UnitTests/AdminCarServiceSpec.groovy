package ntua.softeng28.evcharge.UnitTests

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

import ntua.softeng28.evcharge.admin.BrandData
import ntua.softeng28.evcharge.admin.CarData
import ntua.softeng28.evcharge.admin.CarDataRequest
import ntua.softeng28.evcharge.admin.CarService
import ntua.softeng28.evcharge.admin.MetaData
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
@DataJpaTest
@TestPropertySource(locations="classpath:application-test.properties")
class AdminCarServiceSpec extends Specification{

	def "testing if carService class saves given car to DB"(){
		given:
		def metadata = new MetaData("14-3-2021","1")
		def cardata = new CarData()
		def branddata = new BrandData()
		def cardatarequest = new CarDataRequest()
		def carservice = new CarService()
	}
}
