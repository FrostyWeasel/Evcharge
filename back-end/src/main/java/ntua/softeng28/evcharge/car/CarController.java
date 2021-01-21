package ntua.softeng28.evcharge.car;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {

	private final String baseURL = "/evcharge/api/";

	Logger logger = LoggerFactory.getLogger(CarController.class);

	@Autowired
	CarRepository carRepository;

	@Autowired
	BrandRepository brandRepository;


	@GetMapping(path = baseURL + "/cars")
	public ResponseEntity<List<Car>> allCars() {
		return new ResponseEntity<>(carRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = baseURL + "/cars/{id}")
	public ResponseEntity<Car> getCarbyId(@PathVariable String id) {

		Car car = carRepository.findById(id).orElse(null);

		if (car == null)
			return new ResponseEntity<>(null, HttpStatus.PAYMENT_REQUIRED);
		else
			return new ResponseEntity<>(car, HttpStatus.OK);
	}

	@GetMapping(path = baseURL + "/cars/brands")
	public ResponseEntity<List<Brand>> allBrands() {
		return new ResponseEntity<>(brandRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = baseURL + "/cars/brands/{id}")
	public ResponseEntity<Brand> getBrandbyID(@PathVariable String id) {

		Brand brand = brandRepository.findById(id).orElse(null);
		if(brand == null)
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(brand, HttpStatus.OK);
	}


	@DeleteMapping(path = baseURL + "admin/cars/{id}")
	public ResponseEntity deleteById(@PathVariable String id) {
		try{
			carRepository.deleteById(id);
			return new ResponseEntity(HttpStatus.OK);
		}
		catch(RuntimeException e){
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}
}
