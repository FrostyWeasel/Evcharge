package ntua.softeng28.evcharge.charging_station;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChargingStationController {

    private final String baseURL = "/evcharge/api/admin";

    @Autowired
	ChargingStationRepository chargingStationRepository;
    
    @GetMapping(path = baseURL + "/chargingStations")
    public ResponseEntity<List<ChargingStation>> all() {
        return new ResponseEntity<>(chargingStationRepository.findAll(), HttpStatus.OK);
    }

	// @PostMapping(path = baseURL + "/chargingStations")
	// public ResponseEntity<ChargingStation> createChargingStation(@RequestBody ChargingStation chargingStation) {

	// 	return new ResponseEntity<>(chargingStationRepository.save(chargingStation), HttpStatus.OK);

	// }

	@GetMapping(path = baseURL + "/chargingStations/{id}")
	public ResponseEntity<ChargingStation> getChargingStationbyId(@PathVariable Long id) {

		ChargingStation chargingStation = chargingStationRepository.findById(id).orElse(null);

		if (chargingStation == null)
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(chargingStation, HttpStatus.OK);

	}

	//TODO: Add delete and put
	// @DeleteMapping(path = baseURL + "/chargingStations/{id}")
	// public ResponseEntity deleteById(@PathVariable Long id) {
    //     chargingStationRepository.deleteById(id);
    //     return new ResponseEntity(HttpStatus.OK);
	// }

	// @PutMapping(path = baseURL + "/chargingStations/{id}")
	// public ResponseEntity<ChargingStation> updateById(@RequestBody ChargingStation newChargingStation, @PathVariable Long id) {

	// 	ChargingStation ChargingStationToUpdate = chargingStationRepository.findById(id).orElse(null);

	// 	if (ChargingStationToUpdate == null) 
	// 		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	// 	else {

    //         ChargingStationToUpdate.setLocation(newChargingStation.getLocation());

	// 		return new ResponseEntity<>(chargingStationRepository.save(ChargingStationToUpdate), HttpStatus.OK);
	// 	}

    // }

}

