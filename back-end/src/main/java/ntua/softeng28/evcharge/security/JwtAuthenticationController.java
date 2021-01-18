package ntua.softeng28.evcharge.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
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

    private final String baseURL = "/evcharge/api";

    @PostMapping(path = baseURL + "/login", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestParam MultiValueMap<String,String> authenticationRequest) throws Exception {

        UserDetails userDetails = applicationUserDetailsService.loadUserByUsername(authenticationRequest.getFirst("username"));

        String token = jwtTokenUtil.generateToken(userDetails);

        User user = userRepository.findByUsername(userDetails.getUsername()).get();
        user.setLoggedIn(true);
        userRepository.save(user);

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @PostMapping(path = baseURL + "/logout")
    public ResponseEntity  deleteAuthenticationToken(HttpServletRequest request){
        String token = request.getHeader("X-OBSERVATORY-AUTH");

        User user = userRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(token)).get();
        user.setLoggedIn(false);
        userRepository.save(user);

        return new ResponseEntity(HttpStatus.OK);
    }
}
