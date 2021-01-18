package ntua.softeng28.evcharge.session;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    private final String baseURL = "/evcharge/api/admin";

    @Autowired
	SessionRepository sessionRepository;
    
    @GetMapping(path = baseURL + "/sessions")
    public ResponseEntity<List<Session>> all() {
        return new ResponseEntity<>(sessionRepository.findAll(), HttpStatus.OK);
    }

	@PostMapping(path = baseURL + "/sessions")
	public ResponseEntity<Session> createSession(@RequestBody Session session) {

		return new ResponseEntity<>(sessionRepository.save(session), HttpStatus.OK);

	}

	@GetMapping(path = baseURL + "/sessions/{id}")
	public ResponseEntity<Session> getSessionbyId(@PathVariable Long id) {

		Session session = sessionRepository.findById(id).orElse(null);

		if (session == null)
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(session, HttpStatus.OK);

	}

	@DeleteMapping(path = baseURL + "/sessions/{id}")
	public ResponseEntity deleteById(@PathVariable Long id) {
        sessionRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
	}

	// @PutMapping(path = baseURL + "/sessions/{id}")
	// public ResponseEntity<Session> updateById(@RequestBody Session newSession, @PathVariable Long id) {

	// 	Session sessionToUpdate = sessionRepository.findById(id).orElse(null);

	// 	if (sessionToUpdate == null) 
	// 		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	// 	else {

    //         sessionToUpdate.setType(newSession.getType());
	// 		sessionToUpdate.setTime(newSession.getTime());
	// 		sessionToUpdate.setDescription(newSession.getDescription());
	// 		sessionToUpdate.setCar(newSession.getCar());
	// 		sessionToUpdate.setChargingpoint(newSession.getChargingpoint());
	// 		sessionToUpdate.setChargingstation(newSession.getChargingstation());

	// 		return new ResponseEntity<>(sessionRepository.save(sessionToUpdate), HttpStatus.OK);
	// 	}

    // }

}
