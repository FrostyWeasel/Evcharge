package ntua.softeng28.evcharge.UnitTests

import java.sql.Timestamp

import javax.transaction.Transactional

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

import ntua.softeng28.evcharge.car.AcCharger
import ntua.softeng28.evcharge.car.AcChargerRepository
import ntua.softeng28.evcharge.car.Brand
import ntua.softeng28.evcharge.car.BrandRepository
import ntua.softeng28.evcharge.car.Car
import ntua.softeng28.evcharge.car.CarRepository
import ntua.softeng28.evcharge.car.ChargingCurvePoint
import ntua.softeng28.evcharge.car.ChargingCurvePointRepository
import ntua.softeng28.evcharge.car.DcCharger
import ntua.softeng28.evcharge.car.DcChargerRepository
import ntua.softeng28.evcharge.car.EnergyConsumption
import ntua.softeng28.evcharge.car.PowerPerChargingPoint
import ntua.softeng28.evcharge.charging_point.ChargingPoint
import ntua.softeng28.evcharge.charging_point.ChargingPointRepository
import ntua.softeng28.evcharge.energy_provider.EnergyProvider
import ntua.softeng28.evcharge.energy_provider.EnergyProviderRepository
import ntua.softeng28.evcharge.operator.Operator
import ntua.softeng28.evcharge.operator.OperatorRepository
import ntua.softeng28.evcharge.session.Session
import ntua.softeng28.evcharge.session.SessionRepository
import ntua.softeng28.evcharge.user.User
import ntua.softeng28.evcharge.user.UserRepository
import spock.lang.Specification
import spock.lang.Stepwise

@Transactional
@Stepwise
@DataJpaTest
@TestPropertySource(locations="classpath:application-test.properties")
class SessionSpec extends Specification{

	@Autowired
	SessionRepository sessionrepo

	@Autowired
	UserRepository userrepo

	@Autowired
	AcChargerRepository acchargerrepo

	@Autowired
	BrandRepository brandrepo

	@Autowired
	CarRepository carrepo

	@Autowired
	ChargingCurvePointRepository curverepo

	@Autowired
	DcChargerRepository dcchargerrepo

	@Autowired
	EnergyProviderRepository providerrepo

	@Autowired
	ChargingPointRepository chargingpointrepo

	@Autowired
	OperatorRepository operatorrepo

	def "testing saving and reading operation"() {
		given:
		def timestamp = new Timestamp(System.currentTimeMillis())
		def date = new Date()

		def session = new Session()
		session.setStartedOn(timestamp)
		session.setFinishedOn(new Timestamp(date.getTime()))
		session.setProtocol("some protocol")
		session.setPayment("Card")
		session.setEnergyDelivered(1234.0F)
		session.setCost(100.0F)

		def accharger = new AcCharger()
		def powerperchargingpoint = new PowerPerChargingPoint(2.0, 2.3, 3.7, 7.4, 11, 16, 22, 43)

		accharger.setUsable_phases(3)
		String [] ports = ["1", "2", "3"]
		accharger.setPorts(ports)
		accharger.setMax_power(1234.5F)
		accharger.setPower_per_charging_point(powerperchargingpoint)

		def brand = new Brand("1","Volvo")

		def curvepoint = new ChargingCurvePoint(57,124)

		def dccharger = new DcCharger()
		dccharger.setPorts(ports)
		dccharger.setMax_power(1234.5F)
		def curvepointsset = new HashSet<ChargingCurvePoint>()
		curvepointsset.add(curvepoint)
		dccharger.setCharging_curve(curvepointsset)
		dccharger.setIs_default_charging_curve(true)

		def averageconsumption = new EnergyConsumption(100)

		def car = new Car("1","electric",brand,"some model",2010,"some variant",10.5F,accharger,dccharger,averageconsumption)

		session.setCar(car)

		def operator = new Operator("Some Operator")
		def chargingpoint = new ChargingPoint(operator)
		session.setChargingPoint(chargingpoint)

		def energyprovider = new EnergyProvider("Protergia",100,200,300,150,250)
		session.setEnergyProvider(energyprovider)

		def user = new User()
		user.setUsername("random")
		user.setPassword("empty")
		user.setRole("user")
		user.setLoggedIn(false)
		def carset = new HashSet<Car>()
		carset.add(car)
		user.setCars(carset)

		session.setUser(user)

		when:
		def savedaccharger=acchargerrepo.save(accharger)
		def savedbrand=brandrepo.save(brand)
		def savedcurvepoint=curverepo.save(curvepoint)
		def saveddccharger=dcchargerrepo.save(dccharger)
		def savedcar=carrepo.save(car)
		def savedoperator=operatorrepo.save(operator)
		def savedchargingpoint=chargingpointrepo.save(chargingpoint)
		def savedenergyprovider=providerrepo.save(energyprovider)
		def saveduser=userrepo.save(user)
		def savedsession=sessionrepo.save(session)

		def List<Session> sessionsfromdb = sessionrepo.findAll()

		then:
		sessionsfromdb.size() == 1

		and:
		sessionsfromdb[0].getStartedOn()==savedsession.getStartedOn()
		sessionsfromdb[0].getFinishedOn()==savedsession.getFinishedOn()
		sessionsfromdb[0].getProtocol()==savedsession.getProtocol()
	}
}
