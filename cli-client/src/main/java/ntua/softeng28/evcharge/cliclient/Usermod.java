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

@Command(name = "--usermod", description = "Create new user or modify existing")
public class Usermod implements Callable<Integer> {

    public final String baseURL = "http://localhost:8765/evcharge/api/admin/usermod/";

    @Option(names = "--username", required = true, description = "Username")
    private String username;

    @Option(names = "--passw", required = true, description = "Password")
    private String passw;

    @Option(names = "--format", required = true, description = "File format")
    private String format;

    @Option(names = "--apikey", required = true, description = "API key")
    private String apikey;

    String token;
    File login_token = new File("softeng20bAPI.token");

    @Override
    public Integer call() throws IOException {

        String url = baseURL + username + "/" + passw;
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create("", mediaType);
        Request request = new Request.Builder()
            .url(url)
            .method("POST", body)
            .addHeader("X-OBSERVATORY-AUTH", this.getToken(login_token))
            .build();
        Response response = client.newCall(request).execute();

        String responseBody = response.body().string();
        System.out.println(responseBody);
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
