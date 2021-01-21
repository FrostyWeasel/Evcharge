package ntua.softeng28.evcharge.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ntua.softeng28.evcharge.car.Brand;
import ntua.softeng28.evcharge.car.BrandRepository;
import ntua.softeng28.evcharge.car.Car;
import ntua.softeng28.evcharge.car.CarRepository;

@RestController
public class ServiceController {
    private final String baseURL = "/evcharge/api/";

    Logger logger = LoggerFactory.getLogger(ServiceController.class);

	@Autowired
	CarRepository carRepository;

	@Autowired
	BrandRepository brandRepository;

    // @GetMapping(path = baseURL + "/SessionsPerPoint/{pointID}/{date_from}/{date_to}")
    // public ResponseEntity<?> getSessionsPerPoint() {

        
    // }

    // @GetMapping(path = baseURL + "/SessionsPerStation/{stationID}/{date_from}/{date_to}")
    // public ResponseEntity<?> getSessionsPerStation() {
        
        
    // }

    @GetMapping(path = baseURL + "/CarsByBrand/{id}")
	public ResponseEntity<List<Car>> getCarsofBrandWithGivenID(@PathVariable String id) {

		Brand brand = brandRepository.findById(id).orElse(null);
		if(brand == null)
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		List<Car> cars = carRepository.findByBrand(brand);

		if (cars.isEmpty())
			return new ResponseEntity<>(null, HttpStatus.PAYMENT_REQUIRED);
		else
			return new ResponseEntity<>(cars, HttpStatus.OK);
	}
}
