package ntua.softeng28.evcharge.cliclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.json.*;
import org.json.*;

import okhttp3.*;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.*;
import java.lang.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.concurrent.Callable;
import java.util.Arrays;
import java.util.List;
import java.net.URL;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;

@Command(name = "--sessionsupd", description = "Create new user or modify existing")
public class SessionsUpd implements Callable<Integer> {

    public final String baseURL = "http://localhost:8765/evcharge/api/admin/system/sessionsupd/";

    @Option(names = "--source", required = true, description = "Source File for updating the system's sessions")
    private String filename;

    @Option(names = "--format", required = true, description = "File format")
    private String format;

    @Option(names = "--apikey", required = true, description = "API key")
    private String apikey;

    String token;
    File login_token = new File("softeng20bAPI.token");

    @Override
    public Integer call() throws IOException {

        String url = baseURL;
        OkHttpClient client = new OkHttpClient().newBuilder()
        .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
        .addFormDataPart("file","sessions.csv",
        RequestBody.create(MediaType.parse("application/octet-stream"),
        new File(filename)))
        .build();

        Request request = new Request.Builder()
        .url(url)
        .method("POST", body)
        .addHeader("X-OBSERVATORY-AUTH", this.getToken(login_token))
        .build();
        Response response = client.newCall(request).execute();

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
