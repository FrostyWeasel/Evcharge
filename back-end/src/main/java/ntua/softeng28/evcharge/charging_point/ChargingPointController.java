package ntua.softeng28.evcharge.charging_point;

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
public class ChargingPointController {

    private final String baseURL = "/evcharge/api";

    @Autowired
	ChargingPointRepository chargingPointRepository;
    
    @GetMapping(path = baseURL + "/chargingPoints")
    public ResponseEntity<List<ChargingPoint>> all() {
        return new ResponseEntity<>(chargingPointRepository.findAll(), HttpStatus.OK);
    }

	// @PostMapping(path = baseURL + "/chargingPoints")
	// public ResponseEntity<ChargingPoint> createChargingPoint(@RequestBody ChargingPoint chargingPoint) {

	// 	return new ResponseEntity<>(chargingPointRepository.save(chargingPoint), HttpStatus.OK);

	// }

	@GetMapping(path = baseURL + "/chargingPoints/{id}")
	public ResponseEntity<ChargingPoint> getChargingPointbyId(@PathVariable Long id) {

		ChargingPoint chargingPoint = chargingPointRepository.findById(id).orElse(null);

		if (chargingPoint == null)
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(chargingPoint, HttpStatus.OK);

	}

	//TODO: Add delete and put, post
	// @DeleteMapping(path = baseURL + "/chargingPoints/{id}")
	// public ResponseEntity deleteById(@PathVariable Long id) {
    //     chargingPointRepository.deleteById(id);
    //     return new ResponseEntity(HttpStatus.OK);
	// }

	// @PutMapping(path = baseURL + "/chargingPoints/{id}")
	// public ResponseEntity<ChargingPoint> updateById(@RequestBody ChargingPoint newChargingPoint, @PathVariable Long id) {

	// 	ChargingPoint chargingPointToUpdate = chargingPointRepository.findById(id).orElse(null);

	// 	if (chargingPointToUpdate == null) 
	// 		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	// 	else {

    //         chargingPointToUpdate.setLocation(newChargingPoint.getLocation());
	// 		chargingPointToUpdate.setChargingstation(newChargingPoint.getChargingstation());

	// 		return new ResponseEntity<>(chargingPointRepository.save(chargingPointToUpdate), HttpStatus.OK);
	// 	}

    // }
}

