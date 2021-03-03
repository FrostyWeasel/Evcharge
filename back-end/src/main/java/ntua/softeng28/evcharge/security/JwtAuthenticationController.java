package ntua.softeng28.evcharge.security;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ntua.softeng28.evcharge.user.User;
import ntua.softeng28.evcharge.user.UserRepository;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ApplicationUserDetailsService applicationUserDetailsService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class);

    private final String baseURL = "/evcharge/api";

    @PostMapping(path = baseURL + "/login", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity createAuthenticationToken(@RequestParam MultiValueMap<String,String> authenticationRequest) throws Exception {
        logger.info("Log in request form: {}", authenticationRequest.getFirst("username"));
        try{
            UserDetails userDetails = applicationUserDetailsService.loadUserByUsername(authenticationRequest.getFirst("username"));

            String givenPassword = authenticationRequest.getFirst("password");
            String userPassword = userDetails.getPassword();
            if(bCryptPasswordEncoder.matches(givenPassword, userPassword)){
                String token = jwtTokenUtil.generateToken(userDetails);

                User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new RuntimeException(String.format("User not found")));
                user.setLoggedIn(true);
                userRepository.save(user);

                return ResponseEntity.ok().body(new AuthenticationResponse(token));
            }
            else
                return ResponseEntity.badRequest().build();
        }
        catch(RuntimeException e){
            logger.error(e.getMessage());
			return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(path = baseURL + "/logout")
    public ResponseEntity deleteAuthenticationToken(HttpServletRequest request){
        try{
            String token = request.getHeader("X-OBSERVATORY-AUTH");

            User user = userRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(token)).orElseThrow(() -> new RuntimeException(String.format("User token not valid")));
            user.setLoggedIn(false);
            userRepository.save(user);

            return ResponseEntity.ok().build();
        }
        catch(RuntimeException e){
            logger.error(e.getMessage());
			return ResponseEntity.badRequest().build();
        }
    }
}
