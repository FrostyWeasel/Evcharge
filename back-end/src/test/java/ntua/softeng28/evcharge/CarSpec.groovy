package ntua.softeng28.evcharge

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
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
@DataJpaTest
@TestPropertySource(locations="classpath:application-test.properties")
class CarSpec extends Specification{

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

	def "testing saving and reading operation"() {
		given:
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

		when:
		def savedaccharger=acchargerrepo.save(accharger)
		def savedbrand=brandrepo.save(brand)
		def savedcurvepoint=curverepo.save(curvepoint)
		def saveddccharger=dcchargerrepo.save(dccharger)
		def savedcar=carrepo.save(car)

		def List<Car> carsfromdb = carrepo.findAll()

		then:
		carsfromdb.size() == 1

		and:
		carsfromdb[0].getBrand().getName() == savedcar.getBrand().getName()
		carsfromdb[0].getDc_charger().getId()==savedcar.getDc_charger().getId()
	}

	def "testing deletion operation"(){
		given:
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

		when:
		def savedaccharger=acchargerrepo.save(accharger)
		def savedbrand=brandrepo.save(brand)
		def savedcurvepoint=curverepo.save(curvepoint)
		def saveddccharger=dcchargerrepo.save(dccharger)
		def savedcar=carrepo.save(car)

		def List<Car> carsfromdb_1 = carrepo.findAll()

		carrepo.deleteAll()

		def List<Car> carsfromdb_2 = carrepo.findAll()

		then:
		carsfromdb_1.size() == 1

		and:
		carsfromdb_2.size() == 0
	}
	
	def "testing update operation"(){
		given:
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

		when:
		def savedaccharger=acchargerrepo.save(accharger)
		def savedbrand=brandrepo.save(brand)
		def savedcurvepoint=curverepo.save(curvepoint)
		def saveddccharger=dcchargerrepo.save(dccharger)
		def savedcar=carrepo.save(car)

		def List<Car> carsfromdb_1 = carrepo.findAll()
		
		carsfromdb_1[0].getBrand().setName("Fiat")
		carsfromdb_1[0].setModel("Panda")
		
		carrepo.save(carsfromdb_1[0])
		
		def List<Car> carsfromdb_2 = carrepo.findAll()

		then:
		carsfromdb_2.size() == 1
		
		and:
		carsfromdb_2[0].getBrand().getName() == "Fiat"
		carsfromdb_2[0].getModel() == "Panda"
        carsfromdb_2[0].getId() == "1" 
	}
}
