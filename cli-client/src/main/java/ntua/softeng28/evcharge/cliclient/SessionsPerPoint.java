package ntua.softeng28.evcharge.cliclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.json.*;
import org.json.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.*;
import java.lang.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.concurrent.Callable;
import java.util.Arrays;
import java.util.List;
import java.net.URL;
import java.net.URLEncoder;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;

@Command(name = "SessionsPerPoint", description = "Session Information Per Charging Point")
public class SessionsPerPoint implements Callable<Integer> {

    public final String baseURL = "http://localhost:8765/evcharge/api/SessionsPerPoint/";

    @Option(names = "--point", required = true, description = "Charging Point ID")
    private String pointID;

    @Option(names = "--datefrom", required = true, description = "Start Date")
    private String dateFrom;

    @Option(names = "--dateto", required = true, description = "End Date")
    private String dateTo;

    @Option(names = "--format", required = true, description = "File format")
    private String format;

    @Option(names = "--apikey", required = true, description = "API key")
    private String apikey;

    String token;
    File login_token = new File("softeng20bAPI.token");

    @Override
    public Integer call() throws IOException {

        String url = baseURL + pointID + "/" + dateFrom + "/" + dateTo;
        HttpURLConnection conn = (HttpURLConnection) new URL(url.replaceAll(" ", "%20")).openConnection();

        conn.setDoOutput(true);
        conn.setRequestMethod("GET");
        String urlParameters = "";
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setRequestProperty("X-OBSERVATORY-AUTH", this.getToken(login_token));

        int responseCode = conn.getResponseCode();
        if(responseCode == 200){
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

            JSONObject json = new JSONObject(response.toString());
            System.out.println(json.toString(4));
        }
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
