package ntua.softeng28.evcharge.cliclient;

import org.json.*;
import okhttp3.*;
import picocli.CommandLine.*;

import java.io.*;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;


@Command(name = "SessionsPerEV", description = "Session Information Per Vehicle")
public class SessionsPerEV implements Callable<Integer> {

    public final String baseURL = "https://localhost:8765/evcharge/api/SessionsPerEV/";

    @Option(names = "--ev", required = true, description = "Vehicle ID")
    private String vehicleID;

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
                url = baseURL + vehicleID + "/" + dateFrom + "/" + dateTo + "?format=" + format;
                this.httpRequest(url);
            }
            else if(format.equals("json")){
                url = baseURL + vehicleID + "/" + dateFrom + "/" + dateTo;
                this.httpRequest(url);
            }
            else
                System.out.println("Please select a valid format: json or csv.");
            }
        else
            System.out.println("Please try again with a valid date.\nNote that the appropriate date format is YYYY-MM-DD.");

        return 0;
    }

    public void httpRequest(String url) throws IOException{
        OkHttpClient client = new OkHttpClient.Builder()
           .hostnameVerifier(new HostnameVerifier() {
               @Override
               public boolean verify(String hostname, SSLSession session) {
                   return true;
               }
           })
           .build();
        Request request = new Request.Builder()
            .url(url)
            .method("GET", null)
            .addHeader("X-OBSERVATORY-AUTH", apikey)
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
        else if (response.code() == 401)
            System.out.println("Unauthorized access!");
        else {
            System.out.println("Please select a valid session!");
            System.out.println("For your convenience please check the Vehicle ID and the appropriate dates.");
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
        reader.close();
        return token;
    }

    public boolean isValid(String dateStr) {
        Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
        return pattern.matcher(dateStr).matches();

    }

    public static void main(String[] args){

    }

}
