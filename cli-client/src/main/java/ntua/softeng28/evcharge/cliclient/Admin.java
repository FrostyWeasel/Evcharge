package ntua.softeng28.evcharge.cliclient;

import org.json.*;
import okhttp3.*;
import picocli.CommandLine.*;

import java.io.*;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

@Command(name = "admin", description = "Administrator functions",
        subcommands = {Usermod.class, SessionsPerPoint.class,
        SessionsPerStation.class, SessionsPerEV.class, SessionsPerProvider.class,
        HealthCheck.class, ResetSessions.class, SessionsUpd.class}
        )
public class Admin implements Callable<Integer> {

    public final String baseURL = "https://localhost:8765/evcharge/api/admin";

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
        if(!(format.equals("json")) && !(format.equals("csv")))
            System.out.println("Please enter a valid format: json or csv.");
        else{
            String url = baseURL + "/users/" + users;
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
            if(response.code() == 200){
                String responseBody = response.body().string();
                if(format.equals("json")){
                    JSONObject jObj = new JSONObject(responseBody);
                    System.out.println(jObj.toString(4));
                }
                else
                    System.out.println(responseBody);

            }
            else if(response.code() == 401)
                System.out.println("Unauthorized access. Please log in first as the administrator.");
            else
                System.out.println("Something went wrong. Please check the apikey, otherwise contact the software administrators.");

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

    public static void main(String[] args){

    }


}
