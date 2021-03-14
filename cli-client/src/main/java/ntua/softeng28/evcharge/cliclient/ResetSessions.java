package ntua.softeng28.evcharge.cliclient;

//import org.json.*;
import okhttp3.*;
import picocli.CommandLine.*;

import java.io.*;
import java.util.concurrent.Callable;


@Command(name = "--resetsessions", description = "Reset System's Sessions")
public class ResetSessions implements Callable<Integer> {

    public final String baseURL = "http://localhost:8765/evcharge/api/admin/resetsessions";

    @Option(names = "--format", required = true, defaultValue = "json", description = "File format")
    private String format;

    @Option(names = "--apikey", required = true, description = "API key")
    private String apikey;


    @Override
    public Integer call() throws IOException{
        if(!(format.equals("json")) && !(format.equals("csv")))
            System.out.println("Please enter a valid format: json or csv.");
        else{
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create("", mediaType);
            Request request = new Request.Builder()
                .url(baseURL)
                .method("POST", body)
                .addHeader("X-OBSERVATORY-AUTH", apikey)
                .build();
            Response response = client.newCall(request).execute();
            if(response.code() == 200)
                System.out.println("Sessions were reset successfully!");
            else if(response.code() == 401)
                System.out.println("Unauthorized access. Please log in first as the administrator.");
            else
                System.out.println("Something went wrong. Please check the APIkey, otherwise contact the software administrators.");
            /*
            String responseBody = response.body().string();
            JSONObject jObj = new JSONObject(responseBody);
            System.out.println(jObj.toString(4));
            */
        }
        return 0;
    }

    public static void main(String[] args){

    }

}
