package ntua.softeng28.evcharge.service;

import java.util.ArrayList;
import java.util.HashSet;
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
import org.springframework.web.bind.annotation.RestController;

import ntua.softeng28.evcharge.car.Car;
import ntua.softeng28.evcharge.car.CarRepository;
import ntua.softeng28.evcharge.user.User;
import ntua.softeng28.evcharge.user.UserRepository;

@RestController
public class UserCarsController {
    private final String baseURL = "/evcharge/api";
    Logger logger = LoggerFactory.getLogger(UserCarsController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    CarRepository carRepository;

    @GetMapping(path = baseURL + "/UserCars/{username}")
    public ResponseEntity<List<Car>> getUserCars(@PathVariable String username) {
        User user = userRepository.findByUsername(username).orElse(null);

        if(user == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        
        List<Car> cars = new ArrayList<>(user.getCars());
        if(cars.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.PAYMENT_REQUIRED);

        logger.info("Added car to user {}", user.getCars());

        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

     //TODO: Check token to see if user token matches username
     @PostMapping(path = baseURL + "/UserCars/{username}/{car_id}")
     public ResponseEntity addCarToUser(@PathVariable String username, @PathVariable String car_id) {
         Car car = carRepository.findById(car_id).orElse(null);
         User user = userRepository.findByUsername(username).orElse(null);
 
         if(user == null || car == null)
             return new ResponseEntity(HttpStatus.BAD_REQUEST);
         
         user.getCars().add(car);
         user = userRepository.save(user);
 
         logger.info("Added car to user {}", user.getCars());
 
         return new ResponseEntity(HttpStatus.OK);
     }
 
     @DeleteMapping(path = baseURL + "/UserCars/{username}/{car_id}")
     public ResponseEntity removeCarFromUser(@PathVariable String username, @PathVariable String car_id) {
         Car car = carRepository.findById(car_id).orElse(null);
         User user = userRepository.findByUsername(username).orElse(null);
 
         if(user == null || car == null || !user.getCars().contains(car))
             return new ResponseEntity(HttpStatus.BAD_REQUEST);
         
         user.getCars().remove(car);
         user = userRepository.save(user);
 
         logger.info("Removed car from user {}", user);
 
         return new ResponseEntity(HttpStatus.OK);
     }

      
     @DeleteMapping(path = baseURL + "/UserCars/{username}")
     public ResponseEntity removeAllCarsFromUser(@PathVariable String username) {
         User user = userRepository.findByUsername(username).orElse(null);
 
         if(user == null)
             return new ResponseEntity(HttpStatus.BAD_REQUEST);
         
         user.setCars(new HashSet<>());
         user = userRepository.save(user);
 
         logger.info("Removed all cars from user {}", user);
 
         return new ResponseEntity(HttpStatus.OK);
     }
}
