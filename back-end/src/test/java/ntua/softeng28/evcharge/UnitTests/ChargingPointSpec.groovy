package ntua.softeng28.evcharge.UnitTests

import javax.transaction.Transactional

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import ntua.softeng28.evcharge.charging_point.ChargingPointRepository

//@ContextConfiguration(locations = "src/test/resources/application.properties")
@WebMvcTest
@AutoConfigureMockMvc
@Transactional
class ChargingPointSpec extends Specification {

	@Autowired
	private MockMvc mvc

	def "pray to work"() {

		expect: "Status is 200 and the response is 'Hello world!'"
		mvc.perform(get("/"))
				.andExpect(status().isOk())
				.andReturn()
				.response
				.contentAsString == "Hello world!"
	}
}
