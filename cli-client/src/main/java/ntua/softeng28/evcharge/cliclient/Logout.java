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

@Command(name = "logout", description = "User login")
public class Logout implements Callable<Integer> {

    public final String baseURL = "http://localhost:8765/evcharge/api/logout";
    String token;
    String username;
    File login_token = new File("softeng20bAPI.token");

    @Override
    public Integer call() throws IOException {

        if(login_token.exists()){

            OkHttpClient client = new OkHttpClient().newBuilder().build();
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create("", mediaType);
            Request request = new Request.Builder()
                .url(baseURL)
                .method("POST", body)
                .addHeader("X-OBSERVATORY-AUTH", this.getToken(login_token))
                .build();
            Response response = client.newCall(request).execute();

            System.out.println("Goodbye " + this.getUserName(login_token) + "!" + "Logout Successful!");
            login_token.delete();
        }
        else
            System.out.println("Please login first!");

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

    public String getUserName(File file) throws IOException{
        String line;
        String delims = "[:]";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while((line = reader.readLine()) != null){
            String[] tokens = line.split(delims);
            username = tokens[0];
        }
        return username;
    }

    public static void main(String[] args) {
    }
}
