package ntua.softeng28.evcharge.cliclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.*;
import java.util.concurrent.Callable;

@Command(name = "ev_group28", mixinStandardHelpOptions = true,
        version = "ev_group28 1.0", description = "CommandLine",
        subcommands = {Login.class, Logout.class, HealthCheck.class, ResetSessions.class}
        )
public class ev_group28 implements Callable<Integer>{

    public final String baseURL = "http://localhost:8765/evcharge/api";

	@Override
    public Integer call() throws IOException {
        return 0;
    }

	public static void main(String[] args){
        int exitCode = new CommandLine(new ev_group28()).execute(args);
        System.exit(exitCode);
	}

}
