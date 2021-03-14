package UnitTests

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
	AcChargerRepository acrepo

	@Autowired
	BrandRepository brandrepo

	@Autowired
	CarRepository carrepo

	@Autowired
	ChargingCurvePointRepository curvepointrepo

	@Autowired
	DcChargerRepository dcrepo

	def "testing saving operation"() {
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

		when:
		def savedAcCharger=acrepo.save(acCharger)
		def savedBrand=brandrepo.save(brand)
		def savedCurvePoint=curvepointrepo.save(curvePoint)
		def savedDcCharger=dcrepo.save(dcCharger)
		def savedCar=carrepo.save(car)

		then:
		savedCar.getBrand().getId()==savedBrand.getId()
		savedCar.getRelease_year()==car.getRelease_year()
	}

	def "testing reading operation"(){
		given:
		def powerPerPoint = new PowerPerChargingPoint(2.0,2.3,3.7,7.4,11,16,22,43)
		def acCharger = new AcCharger()
		acCharger.setUsable_phases(3)
		acCharger.setMax_power(1000.0F)
		acCharger.setPower_per_charging_point(powerPerPoint)
		String[] ports = ["1", "2", "3","4"]
		acCharger.setPorts(ports)

		def brand = new Brand()
		brand.setId("2")
		brand.setName("BMW")

		def curvePoint = new ChargingCurvePoint()
		curvePoint.setPercentage(76)
		curvePoint.setPower(646)
		def curvePointSet = new HashSet()
		curvePointSet.add(curvePoint)

		def dcCharger = new DcCharger()
		dcCharger.setPorts(ports)
		dcCharger.setCharging_curve(curvePointSet)
		dcCharger.setMax_power(123456)
		dcCharger.setIs_default_charging_curve(true)

		def consumption = new EnergyConsumption()
		consumption.setAverage_consumption(35)

		def car = new Car()
		car.setAc_charger(acCharger)
		car.setBrand(brand)
		car.setDc_charger(dcCharger)
		car.setEnergy_consumption(consumption)
		car.setId("2")
		car.setModel("who knows")
		car.setRelease_year(2015)
		car.setType("dunno")
		car.setUsable_battery_size(123)
		car.setVariant("who cares")

		when:
		def savedAcCharger=acrepo.save(acCharger)
		def savedBrand=brandrepo.save(brand)
		def savedCurvePoint=curvepointrepo.save(curvePoint)
		def savedDcCharger=dcrepo.save(dcCharger)
		def savedCar=carrepo.save(car)
		
		def List<Car> carsFromDb = carrepo.findAll()

		then:
		carsFromDb.size() == 1
		
		and:
		carsFromDb[0].getId()==savedCar.getId()
	}
	
	def "testing delete operation"(){
		given:
		def powerPerPoint = new PowerPerChargingPoint(2.0,2.3,3.7,7.4,11,16,22,43)
		def acCharger = new AcCharger()
		acCharger.setUsable_phases(3)
		acCharger.setMax_power(1000.0F)
		acCharger.setPower_per_charging_point(powerPerPoint)
		String[] ports = ["1", "2", "3","4","5"]
		acCharger.setPorts(ports)

		def brand = new Brand()
		brand.setId("3")
		brand.setName("Audi")

		def curvePoint = new ChargingCurvePoint()
		curvePoint.setPercentage(76)
		curvePoint.setPower(646)
		def curvePointSet = new HashSet()
		curvePointSet.add(curvePoint)

		def dcCharger = new DcCharger()
		dcCharger.setPorts(ports)
		dcCharger.setCharging_curve(curvePointSet)
		dcCharger.setMax_power(123456)
		dcCharger.setIs_default_charging_curve(true)

		def consumption = new EnergyConsumption()
		consumption.setAverage_consumption(35)

		def car = new Car()
		car.setAc_charger(acCharger)
		car.setBrand(brand)
		car.setDc_charger(dcCharger)
		car.setEnergy_consumption(consumption)
		car.setId("3")
		car.setModel("who knows")
		car.setRelease_year(2015)
		car.setType("dunno")
		car.setUsable_battery_size(123)
		car.setVariant("who cares")

		when:
		def savedAcCharger=acrepo.save(acCharger)
		def savedBrand=brandrepo.save(brand)
		def savedCurvePoint=curvepointrepo.save(curvePoint)
		def savedDcCharger=dcrepo.save(dcCharger)
		def savedCar=carrepo.save(car)
		
		carrepo.deleteAll()
		
		def List<Car> carsFromDb = carrepo.findAll()

		then:
		carsFromDb.size() == 0
	}
	
	def "testing update operation"(){
		given:
		def powerPerPoint = new PowerPerChargingPoint(2.0,2.3,3.7,7.4,11,16,22,43)
		def acCharger = new AcCharger()
		acCharger.setUsable_phases(3)
		acCharger.setMax_power(1000.0F)
		acCharger.setPower_per_charging_point(powerPerPoint)
		String[] ports = ["1", "2", "3","4","5"]
		acCharger.setPorts(ports)

		def brand = new Brand()
		brand.setId("4")
		brand.setName("Mitsubishi")

		def curvePoint = new ChargingCurvePoint()
		curvePoint.setPercentage(76)
		curvePoint.setPower(646)
		def curvePointSet = new HashSet()
		curvePointSet.add(curvePoint)

		def dcCharger = new DcCharger()
		dcCharger.setPorts(ports)
		dcCharger.setCharging_curve(curvePointSet)
		dcCharger.setMax_power(123456)
		dcCharger.setIs_default_charging_curve(true)

		def consumption = new EnergyConsumption()
		consumption.setAverage_consumption(35)

		def car = new Car()
		car.setAc_charger(acCharger)
		car.setBrand(brand)
		car.setDc_charger(dcCharger)
		car.setEnergy_consumption(consumption)
		car.setId("4")
		car.setModel("who knows")
		car.setRelease_year(2015)
		car.setType("dunno")
		car.setUsable_battery_size(123)
		car.setVariant("who cares")

		when:
		def savedAcCharger=acrepo.save(acCharger)
		def savedBrand=brandrepo.save(brand)
		def savedCurvePoint=curvepointrepo.save(curvePoint)
		def savedDcCharger=dcrepo.save(dcCharger)
		def savedCar=carrepo.save(car)
		
		def List<Car> carsFromDB = carrepo.findAll()
		
		carsFromDB[0].getBrand().setName("Toyota")
		carsFromDB[0].setRelease_year(2016)
		
		def updatedCar=carrepo.save(carsFromDB[0])
		
		carsFromDB=carrepo.findAll()

		then:
		carsFromDB.size() == 1
		
		and:
		updatedCar.getBrand().getName()==carsFromDB[0].getBrand().getName()
	}
}
