package ntua.softeng28.evcharge.user;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final String baseURL = "/evcharge/api";
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder; 

    @PostMapping(path = baseURL + "/admin/usermod/{username}/{password}")
    public ResponseEntity<String> createUser(@PathVariable String username, @PathVariable String password) {

        User user = new User();

        user.setRole("ROLE_USER");
        user.setLoggedIn(false);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setCars(new HashSet<>());

        try {
            userRepository.save(user);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(user.toString(), HttpStatus.OK);
    }

    @GetMapping(path = baseURL + "/users")
    public ResponseEntity<List<User>> all() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = baseURL + "/users/{username}")
    public ResponseEntity<User> userByUsername(@PathVariable String username) {

        Optional<User> user = userRepository.findByUsername(username);

        if(user.isPresent()){
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    } 
}
