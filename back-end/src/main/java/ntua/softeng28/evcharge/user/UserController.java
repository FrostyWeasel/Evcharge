package ntua.softeng28.evcharge.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final String baseURL = "/evcharge/api/admin";

    @Autowired
    UserRepository userRepository;

    //TODO: Handle errors
    //TODO: Allow the addition of admins?
    @PostMapping(path = baseURL + "/usermod/{username}/{password}")
    public ResponseEntity<String> createUser(@RequestBody User user, @PathVariable String username, @PathVariable String password) {

        user.setRole("ROLE_USER");
        user.setLoggedIn(false);
        user.setUsername(username);
        user.setPassword(password);

        try {
            userRepository.save(user);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(user.toString(), HttpStatus.OK);
    }

    @GetMapping(path = baseURL + "/users")
    public ResponseEntity<List<User>> all() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = baseURL + "/users/{username}")
    public ResponseEntity<User> user(@PathVariable String username) {

        Optional<User> user = userRepository.findByUsername(username);

        if(user.isPresent()){
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    } 
}
