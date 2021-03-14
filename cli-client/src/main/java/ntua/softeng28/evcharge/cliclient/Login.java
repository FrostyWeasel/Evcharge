package ntua.softeng28.evcharge.cliclient;

import org.json.*;
import okhttp3.*;
import picocli.CommandLine.*;

import java.io.*;
import java.util.concurrent.Callable;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

@Command(name = "login", description = "User login")
public class Login implements Callable<Integer> {

    public final String baseURL = "https://localhost:8765/evcharge/api/login";

    @Option(names = "--username", required = true, description = "Username")
    private String username;

    @Option(names = "--passw", required = true, description = "User's password")
    private String passw;

    @Option(names = "--format", required = true, description = "File format")
    private String format;

    /*
    @Option(names = "--apikey", required = true, description = "API key")
    private String apikey;
    */

    File login_token = new File("softeng20bAPI.token");

    @Override
    public Integer call() throws IOException {

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
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create("username=" + username +"&password=" + passw, mediaType);
            Request request = new Request.Builder()
                .url(baseURL)
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            if(response.code() == 200){
                System.out.println("Welcome " + username + "!");
                JSONObject jObj = new JSONObject(responseBody);
                String token = jObj.getString("token");
                BufferedWriter writer = new BufferedWriter(new FileWriter(login_token));
                writer.write(token);
                writer.close();
            }
            else{
                System.out.println("Something went wrong. Please make sure that you entered a valid username and password, otherwise contact the software administrators.");

            }
        }

        return 0;
    }

    public static void main(String[] args) {

    }
}
