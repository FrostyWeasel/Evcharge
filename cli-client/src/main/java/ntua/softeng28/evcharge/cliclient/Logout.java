package ntua.softeng28.evcharge.cliclient;

import okhttp3.*;
import picocli.CommandLine.*;

import java.io.*;
import java.util.concurrent.Callable;

@Command(name = "logout", description = "User login")
public class Logout implements Callable<Integer> {

    public final String baseURL = "http://localhost:8765/evcharge/api/logout";

    @Option(names = "--format", required = true, description = "File format")
    private String format;

    @Option(names = "--apikey", required = true, description = "API key")
    private String apikey;

    
    File login_token = new File("softeng20bAPI.token");

    @Override
    public Integer call() throws IOException {

        if(login_token.exists()){

            OkHttpClient client = new OkHttpClient().newBuilder().build();
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create("", mediaType);
            Request request = new Request.Builder()
                .url(baseURL)
                .method("POST", body)
                .addHeader("X-OBSERVATORY-AUTH", apikey)
                .build();
            Response response = client.newCall(request).execute();
            if(response.code() == 200){
                System.out.println("Goodbye!\n" + "Logout Successful!");
                login_token.delete();
            }
            else
                System.out.println("Something went wrong. Log out unsuccesful, please try again.");

        }
        else
            System.out.println("Please login first!");

        return 0;
    }

    public static void main(String[] args) {
    }
}
