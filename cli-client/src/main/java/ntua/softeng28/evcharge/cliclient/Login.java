package ntua.softeng28.evcharge.cliclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.json.*;
import org.json.*;


import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.*;
import java.lang.*;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.concurrent.Callable;
import java.util.Arrays;
import java.util.List;
import java.net.URL;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;

@Command(name = "login", description = "User login")
public class Login implements Callable<Integer> {

    public final String baseURL = "http://localhost:8765/evcharge/api/login";

    @Option(names = "--username", required = true, description = "Username")
    private String username;

    @Option(names = "--passw", required = true, description = "User's password")
    private String passw;

    @Option(names = "--format", required = true, description = "File format")
    private String format;

    @Option(names = "--apikey", required = true, description = "API key")
    private String apikey;

    File login_token = new File("softeng20bAPI.token");

    @Override
    public Integer call() throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(login_token));
        writer.write(username + ":");
        writer.write(this.getToken());
        writer.write("\n");
        writer.close();
        return 0;
    }

    public String getToken() throws IOException{


        HttpURLConnection conn = (HttpURLConnection) new URL(baseURL).openConnection();

        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        String urlParameters = "username=" + username + "&password=" + passw;
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));

        DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
        writer.write(postData);
        int responseCode = conn.getResponseCode();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        String json = reader.readLine();
        JSONObject jObj = new JSONObject(json);
        String token = jObj.getString("token");
        return token;

    }

    public static void main(String[] args) {
    }
}
