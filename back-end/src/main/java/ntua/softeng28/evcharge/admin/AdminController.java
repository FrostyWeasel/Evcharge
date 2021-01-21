package ntua.softeng28.evcharge.admin;

import java.io.Reader;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ntua.softeng28.evcharge.car.CarRepository;
import ntua.softeng28.evcharge.charging_point.ChargingPointRepository;
import ntua.softeng28.evcharge.charging_station.ChargingStationRepository;
import ntua.softeng28.evcharge.session.Session;
import ntua.softeng28.evcharge.session.SessionRepository;

@RestController
public class AdminController {
    private final String baseURL = "/evcharge/api";
    
    Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    CarRepository carRepository;
    
    @Autowired
    ChargingStationRepository chargingStationRepository;
   
    @Autowired
    ChargingPointRepository chargingPointRepository;

    @Autowired
    ChargingPointService chargingPointService;

	@Autowired
	CarService carService;
    
    @PostMapping(path = baseURL + "/admin/system/sessionsupd")
    public ResponseEntity loadChargingData(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else {
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                CsvToBean<SessionCsvRequest> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(SessionCsvRequest.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                List<SessionCsvRequest> sessions = csvToBean.parse();

                for(SessionCsvRequest sessionRequest: sessions){
                    Session session = new Session();

                    session.setCar(carRepository.findById(sessionRequest.getCar_id()).orElse(null));
                    session.setChargingPoint(chargingPointRepository.findById(sessionRequest.getCharging_point_id()).orElse(null));
                    session.setChargingStation(chargingStationRepository.findById(sessionRequest.getCharging_session_id()).orElse(null));
                    session.setDescription(sessionRequest.getDescription());
                    session.setEnergy_delivered(sessionRequest.getEnergy_delivered());
                    session.setFinished_on(sessionRequest.getFinished_on());
                    session.setProtocol(sessionRequest.getProtocol());
                    session.setStarted_on(sessionRequest.getStarted_on());
                    session.setType(sessionRequest.getType());

                    sessionRepository.save(session);
                }

            } catch (Exception e) {
                logger.error(e.getMessage());
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(path = baseURL + "/admin/carsupd")
	public ResponseEntity createCar(@RequestBody CarDataRequest carDataRequest) {
		logger.info("Received Car Creation request: {}", carDataRequest);
		try {
			carService.saveCarsToDB(carDataRequest);

			return new ResponseEntity(HttpStatus.OK);
		} 
		catch (RuntimeException e) {
            logger.error(e.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
    }
    
    @PostMapping(path = baseURL + "/admin/pointsupd")
	public ResponseEntity createPoints(@RequestBody ChargingPointDataRequest pointDataRequest) {
		logger.info("Received Point Creation request: {}", pointDataRequest);
		try {
			chargingPointService.saveChargingPointsToDB(pointDataRequest);

			return new ResponseEntity(HttpStatus.OK);
		} 
		catch (RuntimeException e) {
            logger.error(e.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

    // @GetMapping(path = baseURL + "/admin/healthcheck")
    // public ResponseEntity<?> healthcheck() {

    // }

    // @PostMapping(path = baseURL + "/admin/resetsessions")
    // public ResponseEntity<?> resetsessions() {

    // }
}