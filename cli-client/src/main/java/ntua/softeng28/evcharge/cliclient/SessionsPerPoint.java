package ntua.softeng28.evcharge.cliclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.json.*;

import okhttp3.*;

import picocli.CommandLine.*;

import java.io.*;
import java.lang.Object.*;
import java.lang.String.*;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.concurrent.Callable;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.List;

@Command(name = "SessionsPerPoint", description = "Session Information Per Charging Point")
public class SessionsPerPoint implements Callable<Integer> {

    public final String baseURL = "http://localhost:8765/evcharge/api/SessionsPerPoint/";

    @Option(names = "--point", required = true, description = "Charging Point ID")
    private String pointID;

    @Option(names = "--from", required = true, description = "Start Date")
    private String dateFrom;

    @Option(names = "--to", required = true, description = "End Date")
    private String dateTo;

    @Option(names = "--format", required = true, defaultValue = "json", description = "File format")
    private String format;

    @Option(names = "--apikey", required = true, description = "API key")
    private String apikey;

    String token;
    File login_token = new File("softeng20bAPI.token");

    @Override
    public Integer call() throws IOException {
        if(isValid(dateFrom) && isValid(dateTo)){
            String url = new String();
            if(format.equals("csv")){
                url = baseURL + pointID + "/" + dateFrom + "/" + dateTo + "?format=" + format;
                this.httpRequest(url, format);
            }
            else if(format.equals("json")){
                url = baseURL + pointID + "/" + dateFrom + "/" + dateTo;
                this.httpRequest(url, format);
            }
            else
                System.out.println("Please select a valid format: json or csv");
            }
        else
            System.out.println("Please try again with a valid date.\nNote that the appropriate date format is YYYY-MM-DD.");

        return 0;
    }

    public void httpRequest(String url, String format) throws IOException{
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
            .url(url)
            .method("GET", null)
            .addHeader("X-OBSERVATORY-AUTH", this.getToken(login_token))
            .build();
        Response response = client.newCall(request).execute();
        int responseCode = response.code();
        String responseBody = response.body().string();
        if(responseCode == 200){
            if(format.equals("json")){
                JSONObject jObj = new JSONObject(responseBody);
                System.out.println(jObj.toString(4));
            }
            else
                System.out.println(responseBody);
        }
        else{
            System.out.println("Please select a valid session!");
            System.out.println("For your convenience please check the Charging Point ID and the appropriate dates.");
        }
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

    public boolean isValid(String dateStr) {
        Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
        return pattern.matcher(dateStr).matches();

    }

    public static void main(String[] args){

    }

}
