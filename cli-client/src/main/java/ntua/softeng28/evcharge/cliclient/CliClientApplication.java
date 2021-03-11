package ntua.softeng28.evcharge.cliclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.*;


@SpringBootApplication
public class CliClientApplication {


	public static void main(String[] args){
		ev_group28 cli = new ev_group28();
		cli.main(args);
		SpringApplication.run(CliClientApplication.class, args);
	}

}
