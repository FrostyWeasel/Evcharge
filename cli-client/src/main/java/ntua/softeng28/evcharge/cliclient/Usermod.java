package ntua.softeng28.evcharge.cliclient;

//import org.json.*;
import okhttp3.*;
import picocli.CommandLine.*;

import java.io.*;
import java.util.concurrent.Callable;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

@Command(name = "--usermod", description = "Create new user or modify existing")
public class Usermod implements Callable<Integer> {

    public final String baseURL = "https://localhost:8765/evcharge/api/admin/usermod/";

    @Option(names = "--username", required = true, description = "Username")
    private String username;

    @Option(names = "--passw", required = true, description = "Password")
    private String passw;

    @Option(names = "--format", required = true, description = "File format")
    private String format;

    @Option(names = "--apikey", required = true, description = "API key")
    private String apikey;

    String token;
    File login_token = new File("softeng20bAPI.token");

    @Override
    public Integer call() throws IOException {
        if(!(format.equals("json")) && !(format.equals("csv")))
            System.out.println("Please enter a valid format: json or csv.");
        else{
            String url = baseURL + username + "/" + passw;
            OkHttpClient client = new OkHttpClient.Builder()
               .hostnameVerifier(new HostnameVerifier() {
                   @Override
                   public boolean verify(String hostname, SSLSession session) {
                       return true;
                   }
               })
               .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create("", mediaType);
            Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("X-OBSERVATORY-AUTH", apikey)
                .build();
            Response response = client.newCall(request).execute();
            if(response.code() == 200)
                System.out.println("Changes to user " + username + " were added successfully!");
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
