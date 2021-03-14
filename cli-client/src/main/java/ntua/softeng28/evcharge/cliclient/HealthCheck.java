package ntua.softeng28.evcharge.cliclient;


//import org.json.*;
import okhttp3.*;
import picocli.CommandLine.*;

import java.io.*;
import java.util.concurrent.Callable;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;


@Command(name = "--healthcheck", description = "System's Healthcheck")
public class HealthCheck implements Callable<Integer> {

    public final String baseURL = "https://localhost:8765/evcharge/api/admin/healthcheck";

    @Option(names = "--format", required = true, defaultValue = "json", description = "File format")
    private String format;

    @Option(names = "--apikey", required = true, description = "API key")
    private String apikey;

    String username;
    String token;
    File login_token = new File("softeng20bAPI.token");


    @Override
    public Integer call() throws IOException{
        if(!(format.equals("json")) && !(format.equals("csv")))
            System.out.println("Please enter a valid format: json or csv.");
        else{
            OkHttpClient client = new OkHttpClient.Builder()
               .hostnameVerifier(new HostnameVerifier() {
                   @Override
                   public boolean verify(String hostname, SSLSession session) {
                       return true;
                   }
               })
               .build();
            Request request = new Request.Builder()
                .url(baseURL)
                .method("GET", null)
                .addHeader("X-OBSERVATORY-AUTH", apikey)
                .build();
            Response response = client.newCall(request).execute();
            if(response.code() == 200)
                System.out.println("All systems are up and running!");
            else if(response.code() == 401)
                System.out.println("Unauthorized access. Please log in first as the administrator.");
            else
                System.out.println("Something went wrong. Please check the apikey, otherwise contact the software administrators.");
            /*
            String responseBody = response.body().string();
            JSONObject jObj = new JSONObject(responseBody);
            System.out.println(jObj.toString(4));
            */
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
        reader.close();
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
        reader.close();
        return username;
    }

    public static void main(String[] args){

    }

}
