package ntua.softeng28.evcharge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EvchargeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvchargeApplication.class, args);
	}

	// Generate a BCryptPasswordEncoder Instance
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
