package ntua.softeng28.evcharge.car;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class CarController {

	private final String baseURL = "/evcharge/api/admin";

	@Autowired
	CarRepository carRepository;

	@Autowired
	AcChargerRepository acChargerRepository;

	@Autowired
	ChargingCurvePointRepository chargingCurvePointRepository;

	@Autowired
	DcChargerRepository dcChargerRepository;

	@Autowired
	BrandRepository brandRepository;

	Logger logger = LoggerFactory.getLogger(CarController.class);

	@GetMapping(path = baseURL + "/cars")
	public ResponseEntity<List<Car>> all() {
		return new ResponseEntity<>(carRepository.findAll(), HttpStatus.OK);
	}

	//TODO: Auto add brands if not in database
	@PostMapping(path = baseURL + "/cars")
	public ResponseEntity createCar(@RequestBody CarDataRequest carDataRequest) {
		logger.info("Received Car Creation request: {}", carDataRequest);

		try {
			if(carDataRequest.getBrandData() != null){
				for(BrandData brandData: carDataRequest.getBrandData()){
					logger.info("Brand Data: {}", brandData);

					Brand newBrand = new Brand();

					newBrand.setId(brandData.getId());
					newBrand.setName(brandData.getName());

					brandRepository.save(newBrand);
				}
			}

			if(carDataRequest.getCarData() != null){
				for(CarData carData: carDataRequest.getCarData()){
					logger.info("Car Data: {}", carData);

					Car newCar = new Car();

					DcCharger dcCharger = null;
					if(carData.getDc_charger() != null){
						Set<ChargingCurvePoint> chargingCurve = new HashSet<>();
						
						for(ChargingCurvePoint chargingCurvePoint: carData.getDc_charger().getCharging_curve()){
							chargingCurve.add(chargingCurvePointRepository.save(chargingCurvePoint));
						}

						dcCharger = new DcCharger();
						dcCharger.setCharging_curve(chargingCurve);
						dcCharger.setIs_default_charging_curve(carData.getDc_charger().getIs_default_charging_curve());
						dcCharger.setMax_power(carData.getDc_charger().getMax_power());
						dcCharger.setPorts(carData.getDc_charger().getPorts());
						
						dcCharger = dcChargerRepository.save(dcCharger);
					}

					AcCharger acCharger = null;
					if(carData.getAc_charger() != null)
						acCharger = acChargerRepository.save(carData.getAc_charger());

					newCar.setAc_charger(acCharger);
					newCar.setBrand(brandRepository.findById(carData.getBrand_id()).orElse(null));
					newCar.setId(carData.getId());
					newCar.setModel(carData.getModel());
					newCar.setRelease_year(carData.getRelease_year());
					newCar.setType(carData.getType());
					newCar.setUsable_battery_size(carData.getUsable_battery_size());
					newCar.setVariant(carData.getVariant());
					newCar.setDc_charger(dcCharger);
					newCar.setEnergy_consumption(carData.getEnergyConsumption());

					carRepository.save(newCar);
				}
			}

			return new ResponseEntity(HttpStatus.OK);
		} 
		catch (IllegalArgumentException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = baseURL + "/cars/{id}")
	public ResponseEntity<Car> getCarbyId(@PathVariable String id) {

		Car car = carRepository.findById(id).orElse(null);

		if (car == null)
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(car, HttpStatus.OK);

	}

	//TODO: If id doesnt exist throws 500 error, fix?
	@DeleteMapping(path = baseURL + "/cars/{id}")
	public ResponseEntity deleteById(@PathVariable String id) {
        carRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
	}
}
