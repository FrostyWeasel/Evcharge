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

@Command(name = "logout", description = "User login")
public class Logout implements Callable<Integer> {

    public final String baseURL = "http://localhost:8765/evcharge/api/logout";
    String token;
    String username;
    File login_token = new File("softeng20bAPI.token");

    @Override
    public Integer call() throws IOException {

        if(login_token.exists()){
            String url = baseURL;
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

            conn.setDoOutput(true);
            conn.setRequestMethod("POST");

            String urlParameters = "";
            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
            int postDataLength = postData.length;
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            conn.setRequestProperty("X-OBSERVATORY-AUTH", this.getToken(login_token));

            DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
            writer.write(postData);

            System.out.println("Goodbye " + this.getUserName(login_token) + "\nLogout Successful!");
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
