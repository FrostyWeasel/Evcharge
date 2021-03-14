package ntua.softeng28.evcharge.UnitTests

import java.sql.Timestamp

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

@Stepwise
@DataJpaTest
@TestPropertySource(locations="classpath:application-test.properties")
class SessionSpec extends Specification{

	@Autowired
	AcChargerRepository acrepo

	@Autowired
	BrandRepository brandrepo

	@Autowired
	CarRepository carrepo

	@Autowired
	ChargingCurvePointRepository curvepointrepo

	@Autowired
	DcChargerRepository dcrepo

	@Autowired
	ChargingPointRepository chargingPointRepo
	
	@Autowired
	OperatorRepository operatorrepo

    @Autowired
	EnergyProviderRepository energyproviderrepo

    @Autowired
	private UserRepository userrepo

    @Autowired
	SessionRepository sessionrepo

	def "testing a saving operation"(){
		given:
        def powerPerPoint = new PowerPerChargingPoint(2.0,2.3,3.7,7.4,11,16,22,43)
		def acCharger = new AcCharger()
		acCharger.setUsable_phases(3)
		acCharger.setMax_power(1000.0F)
		acCharger.setPower_per_charging_point(powerPerPoint)
		String[] ports = ["1", "2", "3"]
		acCharger.setPorts(ports)

		def brand = new Brand()
		brand.setId("1")
		brand.setName("Volvo")

		def curvePoint = new ChargingCurvePoint()
		curvePoint.setPercentage(75)
		curvePoint.setPower(645)
		def curvePointSet = new HashSet()
		curvePointSet.add(curvePoint)

		def dcCharger = new DcCharger()
		dcCharger.setPorts(ports)
		dcCharger.setCharging_curve(curvePointSet)
		dcCharger.setMax_power(12345)
		dcCharger.setIs_default_charging_curve(true)

		def consumption = new EnergyConsumption()
		consumption.setAverage_consumption(34)

		def car = new Car()
		car.setAc_charger(acCharger)
		car.setBrand(brand)
		car.setDc_charger(dcCharger)
		car.setEnergy_consumption(consumption)
		car.setId("1")
		car.setModel("who knows")
		car.setRelease_year(2015)
		car.setType("dunno")
		car.setUsable_battery_size(123)
		car.setVariant("who cares")

        def operator = new Operator("Kobe Bryant")
		def chargingpoint=new ChargingPoint(operator)

        def provider = new EnergyProvider("Energy",100,200,300,150,250)

        def user = new User()
		user.setUsername("Lebron James")
		user.setPassword("2020 Champion")
		user.setLoggedIn(false)
		user.setRole("Basketball Player")
        def userCars = new HashSet<Car>()
		userCars.add(car)
		user.setCars(userCars)

		Timestamp ts1 = Timestamp.valueOf("2018-09-01")
		Timestamp ts2 = Timestamp.valueOf("2019-09-01")
		def session = new Session(1L,ts1,ts2,"random","card",124.4F,45,car,chargingpoint,provider,user)

		when:
		def savedAcCharger=acrepo.save(acCharger)
		def savedBrand=brandrepo.save(brand)
		def savedCurvePoint=curvepointrepo.save(curvePoint)
		def savedDcCharger=dcrepo.save(dcCharger)
		def savedCar=carrepo.save(car)
        def savedUser=userrepo.save(user)
		def savedoperator=operatorrepo.save(operator)
		def savedchargingpoint=chargingPointRepo.save(chargingpoint)
        def savedprovider=energyproviderrepo.save(provider)
		def savedSession=sessionrepo.save(session)

		def List<Session> sessionsfromDB=sessionrepo.findAll()

		then:
		sessionsfromDB.size() == 1
	}

}