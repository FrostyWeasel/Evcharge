package ntua.softeng28.evcharge.session;

import java.util.List;

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

import ntua.softeng28.evcharge.car.Car;
import ntua.softeng28.evcharge.car.CarRepository;
import ntua.softeng28.evcharge.charging_point.ChargingPoint;
import ntua.softeng28.evcharge.charging_point.ChargingPointRepository;
import ntua.softeng28.evcharge.energy_provider.EnergyProvider;
import ntua.softeng28.evcharge.energy_provider.EnergyProviderRepository;
import ntua.softeng28.evcharge.user.User;
import ntua.softeng28.evcharge.user.UserRepository;

@RestController
public class SessionController {

	private final String baseURL = "/evcharge/api";

	Logger logger = LoggerFactory.getLogger(SessionController.class);

	@Autowired
	CarRepository carRepository;

	@Autowired
	SessionRepository sessionRepository;

	@Autowired
	ChargingPointRepository chargingPointRepository;

	@Autowired
	EnergyProviderRepository energyProviderRepository;

	@Autowired
	UserRepository userRepository;

	@GetMapping(path = baseURL + "/sessions")
	public ResponseEntity<List<Session>> all() {
		return new ResponseEntity<>(sessionRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping(path = baseURL + "/sessions")
	public ResponseEntity<Session> createSession(@RequestBody SessionDataRequest sessionDataRequest) {
		try {
			Car car = carRepository.findById(sessionDataRequest.getVehicleID()).orElseThrow(() -> new RuntimeException(
					String.format("VehicleID: %s not found in DB", sessionDataRequest.getVehicleID())));
			ChargingPoint chargingPoint = chargingPointRepository.findById(sessionDataRequest.getChargingPointID())
					.orElseThrow(() -> new RuntimeException(String.format("ChargingPointID: %d not found in DB",
							sessionDataRequest.getChargingPointID())));
			EnergyProvider energyProvider = energyProviderRepository.findById(sessionDataRequest.getEnergyProviderID())
					.orElseThrow(() -> new RuntimeException(String.format("EnergyProviderID: %d not found in DB",
							sessionDataRequest.getEnergyProviderID())));
			User user = userRepository.findByUsername(sessionDataRequest.getUsername())
					.orElseThrow(() -> new RuntimeException(
							String.format("Username: %s not found in DB", sessionDataRequest.getUsername())));

			Session session = new Session();

			session.setCar(car);
			session.setChargingPoint(chargingPoint);
			session.setEnergyDelivered(sessionDataRequest.getEnergyDelivered());
			session.setFinishedOn(sessionDataRequest.getFinishedOn());
			session.setProtocol(sessionDataRequest.getProtocol());
			session.setStartedOn(sessionDataRequest.getStartedOn());
			session.setEnergyProvider(energyProvider);
			session.setUser(user);

			if (sessionDataRequest.getCost() == 0 || sessionDataRequest.getCost() == null) {
				Float energyRemaining = sessionDataRequest.getEnergyDelivered();
				Float totalCost = Float.valueOf(0);

				if (sessionDataRequest.getEnergyDelivered() >= energyProvider.getMidtoHighLimit()) {
					totalCost += energyProvider.getLowtoMidLimit() * energyProvider.getLowPrice();
					totalCost += (energyProvider.getMidtoHighLimit() - energyProvider.getLowtoMidLimit())
							* energyProvider.getMidPrice();

					energyRemaining -= energyProvider.getMidtoHighLimit();
					totalCost += energyRemaining * energyProvider.getHighPrice();
				} else {
					if (sessionDataRequest.getEnergyDelivered() >= energyProvider.getLowtoMidLimit()) {
						totalCost += energyProvider.getLowtoMidLimit() * energyProvider.getLowPrice();

						energyRemaining -= energyProvider.getLowtoMidLimit();
						totalCost += energyRemaining * energyProvider.getMidPrice();
					} else {
						totalCost += energyRemaining * energyProvider.getLowPrice();
					}
				}
				session.setCost(totalCost);
			} else
				session.setCost(sessionDataRequest.getCost());

			session.setPayment("card"); // !Change if we add more payment methods

			return new ResponseEntity<>(sessionRepository.save(session), HttpStatus.OK);
		} catch (RuntimeException e) {
			logger.error(e.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path = baseURL + "/sessions/{id}")
	public ResponseEntity<Session> getSessionbyId(@PathVariable Long id) {

		Session session = sessionRepository.findById(id).orElse(null);

		if (session == null)
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(session, HttpStatus.OK);

	}

	@DeleteMapping(path = baseURL + "/admin/sessions/{id}")
	public ResponseEntity deleteById(@PathVariable Long id) {
		try {
			sessionRepository.deleteById(id);
			return new ResponseEntity(HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	// @PutMapping(path = baseURL + "/sessions/{id}")
	// public ResponseEntity<Session> updateById(@RequestBody Session newSession,
	// @PathVariable Long id) {

	// Session sessionToUpdate = sessionRepository.findById(id).orElse(null);

	// if (sessionToUpdate == null)
	// return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	// else {

	// sessionToUpdate.setType(newSession.getType());
	// sessionToUpdate.setTime(newSession.getTime());
	// sessionToUpdate.setDescription(newSession.getDescription());
	// sessionToUpdate.setCar(newSession.getCar());
	// sessionToUpdate.setChargingpoint(newSession.getChargingpoint());
	// sessionToUpdate.setChargingstation(newSession.getChargingstation());

	// return new ResponseEntity<>(sessionRepository.save(sessionToUpdate),
	// HttpStatus.OK);
	// }

	// }

}
