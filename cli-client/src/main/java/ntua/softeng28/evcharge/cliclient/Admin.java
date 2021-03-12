package ntua.softeng28.evcharge.cliclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.json.*;

import okhttp3.*;

import picocli.CommandLine.*;

import java.io.*;
import java.lang.*;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.concurrent.Callable;
import java.util.Arrays;
import java.util.List;

@Command(name = "admin", description = "Administrator functions",
        subcommands = {Usermod.class, SessionsPerPoint.class,
        SessionsPerStation.class, SessionsPerEV.class, SessionsPerProvider.class,
        HealthCheck.class, ResetSessions.class}
        )
public class Admin implements Callable<Integer> {

    public final String baseURL = "http://localhost:8765/evcharge/api/admin";

    @Option(names = "--users", required = false, description = "File format")
    private String users;

    @Option(names = "--format", required = false, description = "File format")
    private String format;

    @Option(names = "--apikey", required = false, description = "API key")
    private String apikey;

    String token;
    File login_token = new File("softeng20bAPI.token");

    @Override
    public Integer call() throws IOException{

        String url = baseURL + "/users/" + users;
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
            .url(url)
            .method("GET", null)
            .addHeader("X-OBSERVATORY-AUTH", this.getToken(login_token))
            .build();
        Response response = client.newCall(request).execute();

        String responseBody = response.body().string();
        JSONObject jObj = new JSONObject(responseBody);
        System.out.println(jObj.toString(4));
        return 0;
    }

    public String getToken(File file) throws IOException{
        String line;
        String delims = "[:]";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while((line = reader.readLine()) != null){
            String[] tokens = line.split(delims);
            token = tokens[1];
        }

        return token;
    }

    public static void main(String[] args){

    }


}
