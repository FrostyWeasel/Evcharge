package ntua.softeng28.evcharge.service;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
import ntua.softeng28.evcharge.user.User;
import ntua.softeng28.evcharge.user.UserRepository;

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

	@Autowired
	UserRepository userRepository;

	@GetMapping(path = baseURL + "/SessionsPerPoint/{pointID}/{date_from}/{date_to}")
	public ResponseEntity getSessionsPerPoint(@PathVariable("pointID") Long pointID,
			@PathVariable("date_from") Timestamp date_from, @PathVariable("date_to") Timestamp date_to, @RequestParam(required = false) String format) throws IOException {
		try (Writer writer = new StringWriter()) {
			if(format == null || format.equals("json")){
				ChargingPoint chargingPoint = chargingPointRepository.findById(pointID)
						.orElseThrow(() -> new RuntimeException(String.format("PointID: %d not found in DB", pointID)));
				List<Session> sessions = sessionRepository.findByChargingPointAndStartedOnBetween(chargingPoint, date_from,
						date_to);

				if (sessions.isEmpty()) {
					return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).build();
				}

				ChargingStation chargingStation = chargingStationRepository.findByChargingPoints(chargingPoint)
						.orElse(null);

				SessionsPerPointResponse sessionsPerPointResponse = null;

				if (chargingStation == null) {
					sessionsPerPointResponse = new SessionsPerPointResponse(pointID.toString(), null,
							java.time.Clock.systemUTC().instant().toString(), date_from.toInstant().toString(),
							date_to.toInstant().toString(), Long.valueOf(sessions.size()), sessions);
				} else {
					if (chargingStation.getOperator() == null) {
						sessionsPerPointResponse = new SessionsPerPointResponse(pointID.toString(), null,
								java.time.Clock.systemUTC().instant().toString(), date_from.toInstant().toString(),
								date_to.toInstant().toString(), Long.valueOf(sessions.size()), sessions);
					} else {
						sessionsPerPointResponse = new SessionsPerPointResponse(pointID.toString(),
								chargingStation.getOperator().getName(), java.time.Clock.systemUTC().instant().toString(),
								date_from.toInstant().toString(), date_to.toInstant().toString(),
								Long.valueOf(sessions.size()), sessions);
					}
				}

				return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(sessionsPerPointResponse);
			}
			else if(format.equals("csv")){
				ChargingPoint chargingPoint = chargingPointRepository.findById(pointID)
					.orElseThrow(() -> new RuntimeException(String.format("PointID: %d not found in DB", pointID)));
				List<Session> sessions = sessionRepository.findByChargingPointAndStartedOnBetween(chargingPoint, date_from,
						date_to);

				if (sessions.isEmpty()) {
					return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).build();
				}

				ChargingStation chargingStation = chargingStationRepository.findByChargingPoints(chargingPoint)
						.orElse(null);

				List<SessionsPerPointCsvResponse> sessionsPerPointCsvResponses = new ArrayList<>();
				String operatorName = null;
				String requestTimestamp = java.time.Clock.systemUTC().instant().toString();

				if (chargingStation != null) {
					if (chargingStation.getOperator() == null) {
						operatorName = null;
					} else {
						operatorName = chargingStation.getOperator().getName();
					}
				}

				int i = 1;

				for(Session session: sessions){	
					sessionsPerPointCsvResponses.add(new SessionsPerPointCsvResponse(pointID.toString(), operatorName,
						requestTimestamp, date_from.toInstant().toString(),
						date_to.toInstant().toString(), Long.valueOf(sessions.size()), Integer.valueOf(i), 
						session.getId().toString(),
						session.getStartedOn().toString(),
						session.getFinishedOn().toString(), 
						session.getProtocol(), 
						Float.valueOf(session.getEnergyDelivered()), 
						session.getPayment(), 
						session.getCar().getType()));
						
					i++;
				}

				StatefulBeanToCsv csvResponse = new StatefulBeanToCsvBuilder(writer).build();
				csvResponse.write(sessionsPerPointCsvResponses);

				return ResponseEntity.ok().contentType(MediaType.parseMediaType("text/csv")).body(writer.toString());
			}
			else
				throw new RuntimeException(String.format("Format %s not known", format));
			
		}
		catch (RuntimeException e) {
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		} catch (CsvDataTypeMismatchException e) {
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		} catch (CsvRequiredFieldEmptyException e) {
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping(path = baseURL + "/SessionsPerStation/{stationID}/{date_from}/{date_to}")
	public ResponseEntity getSessionsPerStation(@PathVariable("stationID") Long stationID,
			@PathVariable("date_from") Timestamp date_from, @PathVariable("date_to") Timestamp date_to, @RequestParam(required = false) String format) throws IOException {
		try (Writer writer = new StringWriter()) {
			if(format == null || format.equals("json")){
				ChargingStation chargingStation = chargingStationRepository.findById(stationID)
						.orElseThrow(() -> new RuntimeException(String.format("StationID: %d not found in DB", stationID)));
				Set<ChargingPoint> chargingStationPoints = chargingStation.getChargingPoints();

				if (chargingStationPoints.isEmpty()) {
					return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).build();
				}

				Float totalStationEnergy = Float.valueOf(0);
				Integer totalSessionCount = Integer.valueOf(0);
				List<SessionsSummary> sessionsSummaryList = new ArrayList<>();

				for (ChargingPoint chargingPoint : chargingStationPoints) {
					List<Session> sessions = sessionRepository.findByChargingPointAndStartedOnBetween(chargingPoint,
							date_from, date_to);

					Float totalEnergy = Float.valueOf(0);
					for (Session session : sessions) {
						totalEnergy += session.getEnergyDelivered();
					}
					totalStationEnergy += totalEnergy;
					totalSessionCount += sessions.size();

					sessionsSummaryList
							.add(new SessionsSummary(chargingPoint.getId(), Integer.valueOf(sessions.size()), totalEnergy));
				}

				SessionsPerStationResponse sessionsPerStationResponse = null;
				if (chargingStation.getOperator() == null) {
					sessionsPerStationResponse = new SessionsPerStationResponse(stationID.toString(), null,
							java.time.Clock.systemUTC().instant().toString(), date_from.toInstant().toString(),
							date_to.toInstant().toString(), totalStationEnergy, Long.valueOf(totalSessionCount),
							Long.valueOf(chargingStationPoints.size()), sessionsSummaryList);
				} else {
					sessionsPerStationResponse = new SessionsPerStationResponse(stationID.toString(),
							chargingStation.getOperator().getName(), java.time.Clock.systemUTC().instant().toString(),
							date_from.toInstant().toString(), date_to.toInstant().toString(), totalStationEnergy,
							Long.valueOf(totalSessionCount), Long.valueOf(chargingStationPoints.size()),
							sessionsSummaryList);
				}
				return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(sessionsPerStationResponse);
			}
			else if(format.equals("csv")){
				ChargingStation chargingStation = chargingStationRepository.findById(stationID)
					.orElseThrow(() -> new RuntimeException(String.format("StationID: %d not found in DB", stationID)));
				Set<ChargingPoint> chargingStationPoints = chargingStation.getChargingPoints();

				if (chargingStationPoints.isEmpty()) {
					return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).build();
				}

				Float totalStationEnergy = Float.valueOf(0);
				Integer totalSessionCount = Integer.valueOf(0);
				List<SessionsPerStationCsvResponse> sessionsPerStationCsvResponses = new ArrayList<>();
				String requestTimestamp = java.time.Clock.systemUTC().instant().toString();

				String operatorName = null;

				if (chargingStation.getOperator() != null)
					operatorName = chargingStation.getOperator().getName();

				for (ChargingPoint chargingPoint : chargingStationPoints) {
					List<Session> sessions = sessionRepository.findByChargingPointAndStartedOnBetween(chargingPoint,
							date_from, date_to);

					Float totalEnergy = Float.valueOf(0);
					for (Session session : sessions) {
						totalEnergy += session.getEnergyDelivered();
					}
					totalStationEnergy += totalEnergy;
					totalSessionCount += sessions.size();
				}
				
				for (ChargingPoint chargingPoint : chargingStationPoints) {
					List<Session> sessions = sessionRepository.findByChargingPointAndStartedOnBetween(chargingPoint,
					date_from, date_to);

					Float totalEnergy = Float.valueOf(0);
					for (Session session : sessions) {
						totalEnergy += session.getEnergyDelivered();
					}

					sessionsPerStationCsvResponses
						.add(new SessionsPerStationCsvResponse(stationID.toString(), operatorName,
							requestTimestamp, date_from.toInstant().toString(),
							date_to.toInstant().toString(), totalStationEnergy, Long.valueOf(totalSessionCount),
							Long.valueOf(chargingStationPoints.size()), chargingPoint.getId(), Integer.valueOf(sessions.size()), totalEnergy));
				}
				
				StatefulBeanToCsv csvResponse = new StatefulBeanToCsvBuilder(writer).build();
				csvResponse.write(sessionsPerStationCsvResponses);

				return ResponseEntity.ok().contentType(MediaType.parseMediaType("text/csv")).body(writer.toString());
			}
			else
				throw new RuntimeException(String.format("Format %s not known", format));

		} catch (RuntimeException e) {
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		} catch (CsvDataTypeMismatchException e) {
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		} catch (CsvRequiredFieldEmptyException e) {
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping(path = baseURL + "/SessionsPerEV/{vehicleID}/{date_from}/{date_to}")
	public ResponseEntity getSessionsPerEV(@PathVariable("vehicleID") String vehicleID,
			@PathVariable("date_from") Timestamp date_from, @PathVariable("date_to") Timestamp date_to,
			@RequestParam(required = false) String format) throws IOException {
				
		try (Writer writer = new StringWriter()) {
			if(format == null || format.equals("json")){
			
			Car car = carRepository.findById(vehicleID)
					.orElseThrow(() -> new RuntimeException(String.format("vehicleID: %s not found in DB", vehicleID)));
			List<Session> sessions = sessionRepository.findByCarAndStartedOnBetween(car, date_from, date_to);

			Integer numberOfVehicleChargingSessions = Integer.valueOf(sessions.size());
			Integer numberOfVisitedPoints = Integer.valueOf(0);
			Float totalEnergyConsumed = Float.valueOf(0);
			Set<Long> chargingPoints = new HashSet<>();
			List<VehicleChargingSession> vehicleChargingSessions = new ArrayList<>();

			Integer sessionIndex = Integer.valueOf(1);

			for (Session session : sessions) {
				if (!chargingPoints.contains(session.getChargingPoint().getId())) {
					chargingPoints.add(session.getChargingPoint().getId());
					numberOfVisitedPoints++;
				}

				Float costPerKWh = null;
				if (session.getEnergyDelivered() != 0)
					costPerKWh = session.getCost() / session.getEnergyDelivered();

				String pricePolicyRef = null;
				if (session.getEnergyDelivered() != null) {
					if (session.getEnergyDelivered() >= session.getEnergyProvider().getMidtoHighLimit())
						pricePolicyRef = "High";
					else if (session.getEnergyDelivered() >= session.getEnergyProvider().getLowtoMidLimit())
						pricePolicyRef = "Mid";
					else
						pricePolicyRef = "Low";
				}

				vehicleChargingSessions.add(new VehicleChargingSession(sessionIndex, session.getId().toString(),
						session.getEnergyProvider().getBrandName(), session.getStartedOn().toString(),
						session.getFinishedOn().toString(), session.getEnergyDelivered(), pricePolicyRef, costPerKWh,
						session.getCost()));

				totalEnergyConsumed += session.getEnergyDelivered();
				sessionIndex++;
			}

			SessionsPerEVResponse sessionsPerEVResponse = new SessionsPerEVResponse(vehicleID,
					java.time.Clock.systemUTC().instant().toString(), date_from.toInstant().toString(),
					date_to.toInstant().toString(), totalEnergyConsumed, numberOfVisitedPoints,
					numberOfVehicleChargingSessions, vehicleChargingSessions.toArray(new VehicleChargingSession[0]));

			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(sessionsPerEVResponse);
		}
		else if(format.equals("csv")){

			Car car = carRepository.findById(vehicleID)
					.orElseThrow(() -> new RuntimeException(String.format("vehicleID: %s not found in DB", vehicleID)));
			List<Session> sessions = sessionRepository.findByCarAndStartedOnBetween(car, date_from, date_to);

			Integer numberOfVehicleChargingSessions = Integer.valueOf(sessions.size());
			Integer numberOfVisitedPoints = Integer.valueOf(0);
			Float totalEnergyConsumed = Float.valueOf(0);
			Set<Long> chargingPoints = new HashSet<>();
			List<SessionsPerEVCsvResponse> sessionsPerEVCsvResponses = new ArrayList<>();
			String requestTimestamp = java.time.Clock.systemUTC().instant().toString();

			Integer sessionIndex = Integer.valueOf(1);

			for (Session session : sessions) {
				if (!chargingPoints.contains(session.getChargingPoint().getId())) {
					chargingPoints.add(session.getChargingPoint().getId());
					numberOfVisitedPoints++;
				}

				totalEnergyConsumed += session.getEnergyDelivered();
			}
			for (Session session : sessions) {
				Float costPerKWh = null;
				if (session.getEnergyDelivered() != 0)
					costPerKWh = session.getCost() / session.getEnergyDelivered();

				String pricePolicyRef = null;
				if (session.getEnergyDelivered() != null) {
					if (session.getEnergyDelivered() >= session.getEnergyProvider().getMidtoHighLimit())
						pricePolicyRef = "High";
					else if (session.getEnergyDelivered() >= session.getEnergyProvider().getLowtoMidLimit())
						pricePolicyRef = "Mid";
					else
						pricePolicyRef = "Low";
				}

				sessionsPerEVCsvResponses.add(new SessionsPerEVCsvResponse(vehicleID,
					requestTimestamp, date_from.toInstant().toString(),
					date_to.toInstant().toString(), totalEnergyConsumed, numberOfVisitedPoints,
					numberOfVehicleChargingSessions, sessionIndex, session.getId().toString(),
						session.getEnergyProvider().getBrandName(), session.getStartedOn().toString(),
						session.getFinishedOn().toString(), session.getEnergyDelivered(), pricePolicyRef, costPerKWh,
						session.getCost()));

					sessionIndex++;
			}

			
			StatefulBeanToCsv csvResponse = new StatefulBeanToCsvBuilder(writer).build();
			csvResponse.write(sessionsPerEVCsvResponses);

			return ResponseEntity.ok().contentType(MediaType.parseMediaType("text/csv")).body(writer.toString());
		}
		else
			throw new RuntimeException(String.format("Format %s not known", format));

		} catch (RuntimeException e) {
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		} catch (CsvDataTypeMismatchException e) {
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		} catch (CsvRequiredFieldEmptyException e) {
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
    }

	@GetMapping(path = baseURL + "/SessionsPerProvider/{providerID}/{date_from}/{date_to}")
    public ResponseEntity getSessionsPerProvider(@PathVariable("providerID") Long providerID, @PathVariable("date_from") Timestamp date_from, @PathVariable("date_to") Timestamp date_to, @RequestParam(required = false) String format) throws IOException {
		try (Writer writer = new StringWriter()) {
			if(format == null || format.equals("json")){
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

				return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(sessionsPerProviderResponses);
			}
			else if(format.equals("csv")){
				EnergyProvider energyProvider = energyProviderRepository.findById(providerID).orElseThrow(() -> new RuntimeException(String.format("providerID: %d not found in DB", providerID)));
				List<Session> sessions = sessionRepository.findByEnergyProviderAndStartedOnBetween(energyProvider, date_from, date_to);

				List<SessionsPerProviderCsvResponse> sessionsPerProviderCsvResponses = new ArrayList<>();

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

					sessionsPerProviderCsvResponses.add(new SessionsPerProviderCsvResponse(providerID.toString(), energyProvider.getBrandName(), stationId, session.getId(),
						session.getCar().getId(), session.getStartedOn().toString(), session.getFinishedOn().toString(), session.getEnergyDelivered(), pricePolicyRef,
						costPerKWh, session.getCost()));
				}

				StatefulBeanToCsv csvResponse = new StatefulBeanToCsvBuilder(writer).build();
				csvResponse.write(sessionsPerProviderCsvResponses);
	
				return ResponseEntity.ok().contentType(MediaType.parseMediaType("text/csv")).body(writer.toString());
			} 
			else
				throw new RuntimeException(String.format("Format %s not known", format));

		} catch (RuntimeException e) {
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		} catch (CsvDataTypeMismatchException e) {
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		} catch (CsvRequiredFieldEmptyException e) {
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().build();
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

	@GetMapping(path = baseURL + "/UserReport/{username}/{date_from}/{date_to}")
	public ResponseEntity getUserReport(@PathVariable("username") String username, @PathVariable("date_from") Timestamp date_from, @PathVariable("date_to") Timestamp date_to, @RequestParam(required = false) String format) throws IOException {
		try(Writer writer = new StringWriter()){
			if(format == null || format.equals("json")){
				User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException(String.format("Username: %s not found in DB", username)));
				Set<Car> userCars = user.getCars();
				
				Float totalCost = Float.valueOf(0);
				Integer totalSessions = Integer.valueOf(0);
				Integer totalCars = Integer.valueOf(userCars.size());
				Float totalEnergy = Float.valueOf(0);
				List<CarSummary> carSummaryList = new ArrayList<>();

				Float cost = Float.valueOf(0);
				Float energy = Float.valueOf(0);
				List<Session> sessions = null;

				for(Car car: userCars){
					sessions = sessionRepository.findByUserAndCarAndStartedOnBetween(user, car, date_from, date_to);
					cost = Float.valueOf(0);
					energy = Float.valueOf(0);

					for(Session session: sessions){
						cost += session.getCost();
						energy += session.getEnergyDelivered();
					}

					carSummaryList.add(new CarSummary(car.getId(), car.getBrand().getName(), car.getModel(), cost, Integer.valueOf(sessions.size()), energy));
					totalCost += cost;
					totalSessions += sessions.size();
					totalEnergy += energy;
				}

				UserReportResponse userReportResponse = new UserReportResponse(username,
						java.time.Clock.systemUTC().instant().toString(), date_from.toInstant().toString(),
						date_to.toInstant().toString(), totalCost, totalSessions,
						Integer.valueOf(userCars.size()), totalEnergy, carSummaryList.toArray(new CarSummary[0]));

				return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(userReportResponse);
			}
			else if(format.equals("csv")){
				User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException(String.format("Username: %s not found in DB", username)));
				Set<Car> userCars = user.getCars();
				
				Float totalCost = Float.valueOf(0);
				Integer totalSessions = Integer.valueOf(0);
				Integer totalCars = Integer.valueOf(userCars.size());
				Float totalEnergy = Float.valueOf(0);
				List<CarSummary> carSummaryList = new ArrayList<>();

				Float cost = Float.valueOf(0);
				Float energy = Float.valueOf(0);
				List<Session> sessions = null;

				List<UserReportCsvResponse> userReportCsvResponseList = new ArrayList<>();

				for(Car car: userCars){
					sessions = sessionRepository.findByUserAndCarAndStartedOnBetween(user, car, date_from, date_to);
					cost = Float.valueOf(0);
					energy = Float.valueOf(0);

					for(Session session: sessions){
						cost += session.getCost();
						energy += session.getEnergyDelivered();
					}

					totalCost += cost;
					totalSessions += sessions.size();
					totalEnergy += energy;
				}

				for(Car car: userCars){
					sessions = sessionRepository.findByCarAndStartedOnBetween(car, date_from, date_to);
					cost = Float.valueOf(0);
					energy = Float.valueOf(0);

					for(Session session: sessions){
						cost += session.getCost();
						energy += session.getEnergyDelivered();
					}

					userReportCsvResponseList.add(new UserReportCsvResponse(username,
					java.time.Clock.systemUTC().instant().toString(), date_from.toInstant().toString(),
					date_to.toInstant().toString(), totalCost, totalSessions,
					Integer.valueOf(userCars.size()), totalEnergy,car.getId(), car.getBrand().getName(), car.getModel(), cost, Integer.valueOf(sessions.size()), energy));
				}

				StatefulBeanToCsv csvResponse = new StatefulBeanToCsvBuilder(writer).build();
				csvResponse.write(userReportCsvResponseList);
	
				return ResponseEntity.ok().contentType(MediaType.parseMediaType("text/csv")).body(writer.toString());
			}
			else
				throw new RuntimeException(String.format("Format %s not known", format));

		}
		catch (RuntimeException e) {
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		} catch (CsvDataTypeMismatchException e) {
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		} catch (CsvRequiredFieldEmptyException e) {
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}

}