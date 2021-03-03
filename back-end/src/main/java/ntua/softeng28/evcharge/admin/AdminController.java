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
import ntua.softeng28.evcharge.energy_provider.EnergyProvider;
import ntua.softeng28.evcharge.energy_provider.EnergyProviderRepository;
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
    EnergyProviderRepository energyProviderRepository;

    @Autowired
    ChargingPointService chargingPointService;

	@Autowired
	CarService carService;
    
    //TODO: Return JSON with data
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

                logger.info("Received Session Creation request: {}", sessions);

                for(SessionCsvRequest sessionRequest: sessions){
                    Session session = new Session();
                    EnergyProvider energyProvider = energyProviderRepository.findById(sessionRequest.getEnergy_provider_id()).orElseThrow(() -> new RuntimeException(String.format("Energy_provider_id: %d not found in DB", sessionRequest.getEnergy_provider_id())));

                    session.setCar(carRepository.findById(sessionRequest.getCar_id()).orElseThrow(() -> new RuntimeException(String.format("Car_id: %s not found in DB", sessionRequest.getCar_id()))));
                    session.setChargingPoint(chargingPointRepository.findById(sessionRequest.getCharging_point_id()).orElseThrow(() -> new RuntimeException(String.format("Charging_point_id: %d not found in DB", sessionRequest.getCharging_point_id()))));
                    session.setEnergyDelivered(sessionRequest.getEnergy_delivered());
                    session.setFinishedOn(sessionRequest.getFinished_on());
                    session.setProtocol(sessionRequest.getProtocol());
                    session.setStartedOn(sessionRequest.getStarted_on());
                    session.setEnergyProvider(energyProvider);

                    if(sessionRequest.getCost() == 0 || sessionRequest.getCost() == null){
                        Float energyRemaining = sessionRequest.getEnergy_delivered();
                        Float totalCost = Float.valueOf(0);

                        if(sessionRequest.getEnergy_delivered() >= energyProvider.getMidtoHighLimit()) {
                            totalCost += energyProvider.getLowtoMidLimit() * energyProvider.getLowPrice();
                            totalCost += (energyProvider.getMidtoHighLimit() - energyProvider.getLowtoMidLimit())
                                    * energyProvider.getMidPrice();

                            energyRemaining -= energyProvider.getMidtoHighLimit();
                            totalCost += energyRemaining * energyProvider.getHighPrice();
                        } else {
                            if (sessionRequest.getEnergy_delivered() >= energyProvider.getLowtoMidLimit()) {
                                totalCost += energyProvider.getLowtoMidLimit() * energyProvider.getLowPrice();

                                energyRemaining -= energyProvider.getLowtoMidLimit();
                                totalCost += energyRemaining * energyProvider.getMidPrice();
                            } else {
                                totalCost += energyRemaining * energyProvider.getLowPrice();
                            }
                        }
                        session.setCost(totalCost);
                    }
                    else
                        session.setCost(sessionRequest.getCost());

                    session.setPayment("card"); //!Change if we add more payment methods
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
	public ResponseEntity createCars(@RequestBody CarDataRequest carDataRequest) {
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

    @PostMapping(path = baseURL + "/admin/providersupd")
	public ResponseEntity createProviders(@RequestBody ProviderDataRequest providerDataRequest) {
		logger.info("Received Provider Creation request: {}", providerDataRequest);
		try {
            for(ProviderData providerData: providerDataRequest.getProviderData()){

                EnergyProvider energyProvider = energyProviderRepository.findByBrandName(providerData.getBrandName()).orElse(null);
                if(energyProvider == null)
                    energyProvider = new EnergyProvider();

                energyProvider.setBrandName(providerData.getBrandName());
                energyProvider.setHighPrice(providerData.getHighPrice());
                energyProvider.setLowPrice(providerData.getLowPrice());
                energyProvider.setMidPrice(providerData.getMidPrice());
                energyProvider.setLowtoMidLimit(providerData.getLowtoMidLimit());
                energyProvider.setMidtoHighLimit(providerData.getMidtoHighLimit());    
                
                energyProviderRepository.save(energyProvider);
            }

			return new ResponseEntity(HttpStatus.OK);
		} 
		catch (RuntimeException e) {
            logger.error(e.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
    }
    
    @PostMapping(path = baseURL + "/admin/pointsupd")
	public ResponseEntity createPoints(@RequestBody ChargingPointDataRequest pointDataRequest) {
		//logger.info("Received Point Creation request: {}", pointDataRequest);
		try {
			chargingPointService.saveChargingPointsToDB(pointDataRequest);

			return new ResponseEntity(HttpStatus.OK);
		} 
		catch (RuntimeException e) {
            logger.error(e.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

    //! No user verification required
    // @GetMapping(path = baseURL + "/admin/healthcheck")
    // public ResponseEntity<?> healthcheck() {

    // }

    // @PostMapping(path = baseURL + "/admin/resetsessions")
    // public ResponseEntity<?> resetsessions() {

    // }
}