package ntua.softeng28.evcharge.UnitTests

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

import ntua.softeng28.evcharge.user.User
import ntua.softeng28.evcharge.user.UserRepository
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
@DataJpaTest
@TestPropertySource(locations="classpath:application-test.properties")
class UserSpec extends Specification{

	@Autowired
	private UserRepository userrepo

	def "testing saving and reading from database"() {
		given:
		def user = new User()
		user.setUsername("Lebron James")
		user.setPassword("2020 Champion")
		user.setLoggedIn(false)
		user.setRole("Basketball Player")

		when:
		def savedUser=userrepo.save(user)

		def List<User> userfromdb = userrepo.findAll()

		then:
		savedUser == userfromdb[0]

		and:
		userfromdb.size() == 1
	}

	def "testing the deletion of a user from database"(){
		given:
		def user = new User()
		user.setUsername("Lebron James")
		user.setPassword("2020 Champion")
		user.setLoggedIn(false)
		user.setRole("Basketball Player")

		when:
		userrepo.save(user)

		def List<User> userfromdb = userrepo.findAll()

		userrepo.deleteById(userfromdb[0].getId())

		userfromdb = userrepo.findAll()

		then:
		userfromdb.size() == 0
	}

	def "testing the update operation"(){
		given:
		def user = new User()
		user.setUsername("Lebron James")
		user.setPassword("2020 Champion")
		user.setLoggedIn(false)
		user.setRole("Basketball Player")

		when:
		def saveduser=userrepo.save(user)
		
		def usertoupdate = userrepo.findByUsername("Lebron James").orElse(null)
		
		usertoupdate.setUsername("Kobe Bryant")
		usertoupdate.setPassword("2010 Champion")
		usertoupdate.setRole("Retired")
		
		def savedupdateduser = userrepo.save(usertoupdate)
		
		def List<User> usersfromdb = userrepo.findAll()
		
		then:
		usersfromdb[0].getUsername() == "Kobe Bryant"
		usersfromdb[0].getPassword() == "2010 Champion"
		usersfromdb[0].getRole() == "Retired"
		
		and:
		usersfromdb.size() == 1
	}
}
