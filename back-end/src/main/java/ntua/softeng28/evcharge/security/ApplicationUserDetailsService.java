package ntua.softeng28.evcharge.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ntua.softeng28.evcharge.user.User;
import ntua.softeng28.evcharge.user.UserRepository;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        return new ApplicationUserDetails(user.orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found.")));
    }
    
}
