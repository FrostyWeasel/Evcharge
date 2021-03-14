package ntua.softeng28.evcharge.UnitTests

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

import ntua.softeng28.evcharge.admin.BrandData
import ntua.softeng28.evcharge.admin.CarData
import ntua.softeng28.evcharge.admin.CarDataRequest
import ntua.softeng28.evcharge.admin.CarService
import ntua.softeng28.evcharge.admin.DcChargerRequest
import ntua.softeng28.evcharge.admin.MetaData
import ntua.softeng28.evcharge.car.AcCharger
import ntua.softeng28.evcharge.car.Car
import ntua.softeng28.evcharge.car.CarRepository
import ntua.softeng28.evcharge.car.ChargingCurvePoint
import ntua.softeng28.evcharge.car.DcCharger
import ntua.softeng28.evcharge.car.EnergyConsumption
import ntua.softeng28.evcharge.car.PowerPerChargingPoint
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
@DataJpaTest
@TestPropertySource(locations="classpath:application-test.properties")
class AdminCarServiceSpec extends Specification{
	
	@Autowired
	CarRepository carrepo

	def "testing if carService class saves given car to DB"(){
		given:
		def metadata = new MetaData("14-3-2021","1")
		def cardata = new CarData()
		def branddata = new BrandData("1","Volvo")
		def cardatarequest = new CarDataRequest()
		def carservice = new CarService()
		
		def powerPerPoint = new PowerPerChargingPoint(2.0,2.3,3.7,7.4,11,16,22,43)		
		def acCharger = new AcCharger()
		acCharger.setUsable_phases(3)
		acCharger.setMax_power(1000.0F)
		acCharger.setPower_per_charging_point(powerPerPoint)
		String[] ports = ["1", "2", "3"]
		acCharger.setPorts(ports)
		
		def curvePoint = new ChargingCurvePoint()
		curvePoint.setPercentage(75)
		curvePoint.setPower(645)
		ChargingCurvePoint[] curvePointList = [curvePoint]
		
		def dcChargerRequest = new DcChargerRequest()
		dcChargerRequest.setPorts(ports)
		dcChargerRequest.setCharging_curve(curvePointList)
		dcChargerRequest.setMax_power(12345)
		dcChargerRequest.setIs_default_charging_curve(true)

		def consumption = new EnergyConsumption()
		consumption.setAverage_consumption(34)

        cardata.setAc_charger(acCharger)
		cardata.setBrand("Volvo")
		cardata.setBrand_id("1")
		cardata.setDc_charger(dcChargerRequest)
		cardata.setEnergyConsumption(consumption)
		cardata.setId("1")
		cardata.setModel("turbo")
		cardata.setRelease_year(2015)
		cardata.setType("electric")
		cardata.setUsable_battery_size(120)
		cardata.setVariant("something")
		
		CarData[] carRequest=[cardata]
		BrandData[] brandRequest=[branddata]
		
		cardatarequest.setBrandData(brandRequest)
		cardatarequest.setCarData(carRequest)
		cardatarequest.setMetaData(metadata)
		
		when:
		carservice.saveCarsToDB(cardatarequest)
		
		def List<Car> carsFromDB = carrepo.findAll()
		
		then:
		carsFromDB.size() == 1
		
	}
}
