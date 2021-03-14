package ntua.softeng28.evcharge.cliclient;

import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.*;
import java.util.concurrent.Callable;

@Command(name = "ev_group28", mixinStandardHelpOptions = true,
        version = "ev_group28 1.0", description ="CommandLine",
        subcommands = {Login.class, Logout.class, HealthCheck.class,
        ResetSessions.class, SessionsPerPoint.class, SessionsPerStation.class,
        SessionsPerEV.class, SessionsPerProvider.class, Admin.class}
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
