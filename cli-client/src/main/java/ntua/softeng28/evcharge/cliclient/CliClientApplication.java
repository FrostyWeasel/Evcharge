package ntua.softeng28.evcharge.cliclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CliClientApplication {


	public static void main(String[] args){
		//ev_group28 cli = new ev_group28();
		ev_group28.main(args);
		SpringApplication.run(CliClientApplication.class, args);
	}

}
