package ntua.softeng28.evcharge.service;

import java.sql.Timestamp;
import java.util.List;
import java.time.Clock;

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
import ntua.softeng28.evcharge.charging_point.ChargingPoint;
import ntua.softeng28.evcharge.charging_point.ChargingPointRepository;
import ntua.softeng28.evcharge.session.Session;
import ntua.softeng28.evcharge.session.SessionRepository;

@RestController
public class ServiceController {
    private final String baseURL = "/evcharge/api";

    Logger logger = LoggerFactory.getLogger(ServiceController.class);

	@Autowired
	CarRepository carRepository;

	@Autowired
	BrandRepository brandRepository;

	@Autowired
	SessionRepository sessionRepository;

	@Autowired
	ChargingPointRepository chargingPointRepository;

    @GetMapping(path = baseURL + "/SessionsPerPoint/{pointID}/{date_from}/{date_to}")
    public ResponseEntity<SessionsPerPointResponse> getSessionsPerPoint(@PathVariable("pointID") Long pointID, @PathVariable("date_from") Timestamp date_from, @PathVariable("date_to") Timestamp date_to) {
		try{	
			ChargingPoint chargingPoint = chargingPointRepository.findById(pointID).orElseThrow(() -> new RuntimeException(String.format("PointID: %d not found in DB", pointID)));
			List<Session> sessions = sessionRepository.findByChargingPointAndStartedOnBetween(chargingPoint, date_from, date_to);

			if(sessions.isEmpty()){
				return new ResponseEntity<>(HttpStatus.PAYMENT_REQUIRED);
			}

			SessionsPerPointResponse sessionsPerPointResponse = new SessionsPerPointResponse(pointID.toString(), java.time.Clock.systemUTC().instant().toString(), date_from.toInstant().toString(), date_to.toInstant().toString(), Long.valueOf(sessions.size()), sessions);
			return new ResponseEntity<>(sessionsPerPointResponse, HttpStatus.OK);
		} 
		catch (RuntimeException e) {
			logger.error(e.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
    }

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
