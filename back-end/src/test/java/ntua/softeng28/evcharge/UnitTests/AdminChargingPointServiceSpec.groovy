package ntua.softeng28.evcharge.UnitTests

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
@DataJpaTest
@TestPropertySource(locations="classpath:application-test.properties")
class AdminChargingPointServiceSpec extends Specification {
}
