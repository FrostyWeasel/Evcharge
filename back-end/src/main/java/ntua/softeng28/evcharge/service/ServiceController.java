package ntua.softeng28.evcharge.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.lang.model.util.ElementScanner6;

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
import ntua.softeng28.evcharge.charging_station.ChargingStation;
import ntua.softeng28.evcharge.charging_station.ChargingStationRepository;
import ntua.softeng28.evcharge.energy_provider.EnergyProvider;
import ntua.softeng28.evcharge.energy_provider.EnergyProviderRepository;
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

	@Autowired
	ChargingStationRepository chargingStationRepository;

	@Autowired
	EnergyProviderRepository energyProviderRepository;

    @GetMapping(path = baseURL + "/SessionsPerPoint/{pointID}/{date_from}/{date_to}")
    public ResponseEntity<SessionsPerPointResponse> getSessionsPerPoint(@PathVariable("pointID") Long pointID, @PathVariable("date_from") Timestamp date_from, @PathVariable("date_to") Timestamp date_to) {
		try{	
			ChargingPoint chargingPoint = chargingPointRepository.findById(pointID).orElseThrow(() -> new RuntimeException(String.format("PointID: %d not found in DB", pointID)));
			List<Session> sessions = sessionRepository.findByChargingPointAndStartedOnBetween(chargingPoint, date_from, date_to);

			if(sessions.isEmpty()){
				return new ResponseEntity<>(null, HttpStatus.PAYMENT_REQUIRED);
			}

			ChargingStation chargingStation = chargingStationRepository.findByChargingPoints(chargingPoint).orElse(null);

			SessionsPerPointResponse sessionsPerPointResponse = null;

			if(chargingStation == null){
				sessionsPerPointResponse = new SessionsPerPointResponse(pointID.toString(), null, java.time.Clock.systemUTC().instant().toString(), date_from.toInstant().toString(), date_to.toInstant().toString(), Long.valueOf(sessions.size()), sessions);
			}
			else{
				if(chargingStation.getOperator() == null){
					sessionsPerPointResponse = new SessionsPerPointResponse(pointID.toString(), null, java.time.Clock.systemUTC().instant().toString(), date_from.toInstant().toString(), date_to.toInstant().toString(), Long.valueOf(sessions.size()), sessions);
				}
				else{
					sessionsPerPointResponse = new SessionsPerPointResponse(pointID.toString(), chargingStation.getOperator().getName(), java.time.Clock.systemUTC().instant().toString(), date_from.toInstant().toString(), date_to.toInstant().toString(), Long.valueOf(sessions.size()), sessions);
				}
			}

			return new ResponseEntity<>(sessionsPerPointResponse, HttpStatus.OK);
		} 
		catch (RuntimeException e) {
			logger.error(e.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
    }

	@GetMapping(path = baseURL + "/SessionsPerStation/{stationID}/{date_from}/{date_to}")
    public ResponseEntity<SessionsPerStationResponse> getSessionsPerStation(@PathVariable("stationID") Long stationID, @PathVariable("date_from") Timestamp date_from, @PathVariable("date_to") Timestamp date_to) {
		try{
			
			ChargingStation chargingStation = chargingStationRepository.findById(stationID).orElseThrow(() -> new RuntimeException(String.format("StationID: %d not found in DB", stationID)));
			Set<ChargingPoint> chargingStationPoints = chargingStation.getChargingPoints();

			if(chargingStationPoints.isEmpty()){
				return new ResponseEntity<>(HttpStatus.PAYMENT_REQUIRED);
			}

			Float totalStationEnergy = Float.valueOf(0);
			Integer totalSessionCount = Integer.valueOf(0);
			List<SessionsSummary> sessionsSummaryList = new ArrayList<>();

			for(ChargingPoint chargingPoint: chargingStationPoints){
				List<Session> sessions = sessionRepository.findByChargingPointAndStartedOnBetween(chargingPoint, date_from, date_to);
				
				Float totalEnergy = Float.valueOf(0);
				for(Session session: sessions){
					totalEnergy += session.getEnergyDelivered();
				}
				totalStationEnergy += totalEnergy;
				totalSessionCount += sessions.size();

				sessionsSummaryList.add(new SessionsSummary(chargingPoint.getId(), Integer.valueOf(sessions.size()), totalEnergy));
			} 

			SessionsPerStationResponse sessionsPerStationResponse = null;
			if(chargingStation.getOperator() == null){
				sessionsPerStationResponse = new SessionsPerStationResponse(stationID.toString(), null, java.time.Clock.systemUTC().instant().toString(), date_from.toInstant().toString(), date_to.toInstant().toString(), totalStationEnergy, Long.valueOf(totalSessionCount), Long.valueOf(chargingStationPoints.size()), sessionsSummaryList);
			}
			else{
				sessionsPerStationResponse = new SessionsPerStationResponse(stationID.toString(), chargingStation.getOperator().getName(), java.time.Clock.systemUTC().instant().toString(), date_from.toInstant().toString(), date_to.toInstant().toString(), totalStationEnergy, Long.valueOf(totalSessionCount), Long.valueOf(chargingStationPoints.size()), sessionsSummaryList);
			}			
			return new ResponseEntity<>(sessionsPerStationResponse, HttpStatus.OK);
		} 
		catch (RuntimeException e) {
			logger.error(e.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
    }

	@GetMapping(path = baseURL + "/SessionsPerEV/{vehicleID}/{date_from}/{date_to}")
    public ResponseEntity<SessionsPerEVResponse> getSessionsPerEV(@PathVariable("vehicleID") String vehicleID, @PathVariable("date_from") Timestamp date_from, @PathVariable("date_to") Timestamp date_to) {
		try{
			
			Car car = carRepository.findById(vehicleID).orElseThrow(() -> new RuntimeException(String.format("vehicleID: %s not found in DB", vehicleID)));
			List<Session> sessions = sessionRepository.findByCarAndStartedOnBetween(car, date_from, date_to);

			Integer numberOfVehicleChargingSessions = Integer.valueOf(sessions.size());
			Integer numberOfVisitedPoints = Integer.valueOf(0);
			Float totalEnergyConsumed = Float.valueOf(0);
			Set<Long> chargingPoints = new HashSet<>();
			List<VehicleChargingSession> vehicleChargingSessions = new ArrayList<>();

			Integer sessionIndex = Integer.valueOf(1);

			for(Session session: sessions){
				if(!chargingPoints.contains(session.getChargingPoint().getId())){
					chargingPoints.add(session.getChargingPoint().getId());
					numberOfVisitedPoints++;
				}

				Float costPerKWh = null;
				if(session.getEnergyDelivered() != 0)
					costPerKWh = session.getCost() / session.getEnergyDelivered();
				
				String pricePolicyRef = null;
				if(session.getEnergyDelivered() != null){
					if(session.getEnergyDelivered() >= session.getEnergyProvider().getMidtoHighLimit())
						pricePolicyRef = "High";
					else if(session.getEnergyDelivered() >= session.getEnergyProvider().getLowtoMidLimit())
						pricePolicyRef = "Mid";
					else
						pricePolicyRef = "Low";
				}

				vehicleChargingSessions.add(new VehicleChargingSession(sessionIndex, session.getId().toString(), session.getEnergyProvider().getBrandName(), session.getStartedOn().toString(), session.getFinishedOn().toString(), session.getEnergyDelivered(), pricePolicyRef, costPerKWh, session.getCost()));

				totalEnergyConsumed += session.getEnergyDelivered();
				sessionIndex++;
			}


			SessionsPerEVResponse sessionsPerEVResponse = new SessionsPerEVResponse(vehicleID, java.time.Clock.systemUTC().instant().toString(), date_from.toInstant().toString(), date_to.toInstant().toString(), totalEnergyConsumed, numberOfVisitedPoints, numberOfVehicleChargingSessions, vehicleChargingSessions.toArray(new VehicleChargingSession[0]));	
			return new ResponseEntity<>(sessionsPerEVResponse, HttpStatus.OK);
		} 
		catch (RuntimeException e) {
			logger.error(e.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
    }

	@GetMapping(path = baseURL + "/SessionsPerProvider/{providerID}/{date_from}/{date_to}")
    public ResponseEntity<List<SessionsPerProviderResponse>> getSessionsPerProvider(@PathVariable("providerID") Long providerID, @PathVariable("date_from") Timestamp date_from, @PathVariable("date_to") Timestamp date_to) {
		try{
			
			EnergyProvider energyProvider = energyProviderRepository.findById(providerID).orElseThrow(() -> new RuntimeException(String.format("providerID: %d not found in DB", providerID)));
			List<Session> sessions = sessionRepository.findByEnergyProviderAndStartedOnBetween(energyProvider, date_from, date_to);

			List<SessionsPerProviderResponse> sessionsPerProviderResponses = new ArrayList<>();

			Integer sessionIndex = Integer.valueOf(1);

			for(Session session: sessions){

				Float costPerKWh = null;
				if(session.getEnergyDelivered() != 0)
					costPerKWh = session.getCost() / session.getEnergyDelivered();
				
				String pricePolicyRef = null;
				if(session.getEnergyDelivered() != null){
					if(session.getEnergyDelivered() >= session.getEnergyProvider().getMidtoHighLimit())
						pricePolicyRef = "High";
					else if(session.getEnergyDelivered() >= session.getEnergyProvider().getLowtoMidLimit())
						pricePolicyRef = "Mid";
					else
						pricePolicyRef = "Low";
				}

				ChargingStation chargingStation = chargingStationRepository.findByChargingPoints(session.getChargingPoint()).orElse(null);
				String stationId = null;
				if(chargingStation != null)
					stationId = chargingStation.getId().toString();

				sessionsPerProviderResponses.add(new SessionsPerProviderResponse(providerID.toString(), energyProvider.getBrandName(), stationId, session.getId(),
					session.getCar().getId(), session.getStartedOn().toString(), session.getFinishedOn().toString(), session.getEnergyDelivered(), pricePolicyRef,
					costPerKWh, session.getCost()));
			}

			return new ResponseEntity<>(sessionsPerProviderResponses, HttpStatus.OK);
		} 
		catch (RuntimeException e) {
			logger.error(e.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
    }

    @GetMapping(path = baseURL + "/CarsByBrand/{id}")
	public ResponseEntity<List<CarsByBrandResponse>> getCarsOfBrandWithGivenID(@PathVariable String id) {

		Brand brand = brandRepository.findById(id).orElse(null);
		if(brand == null)
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		List<Car> cars = carRepository.findByBrand(brand);

		List<CarsByBrandResponse> carsByBrandResponses = new ArrayList();

		for(Car car: cars){
			carsByBrandResponses.add(new CarsByBrandResponse(car.getId(), car.getType(), car.getModel(), car.getRelease_year(), car.getVariant(), car.getUsable_battery_size()));
		}

		
		if (carsByBrandResponses.isEmpty())
			return new ResponseEntity<>(null, HttpStatus.PAYMENT_REQUIRED);
		else
			return new ResponseEntity<>(carsByBrandResponses, HttpStatus.OK);
	}
}
