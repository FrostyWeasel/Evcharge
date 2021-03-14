package ntua.softeng28.evcharge.cliclient;

import org.json.*;
import okhttp3.*;
import picocli.CommandLine.*;

import java.io.*;
import java.util.concurrent.Callable;


@Command(name = "--resetsessions", description = "Reset System's Sessions")
public class ResetSessions implements Callable<Integer> {

    public final String baseURL = "http://localhost:8765/evcharge/api/admin/resetsessions";

    String token;
    File login_token = new File("softeng20bAPI.token");

    @Override
    public Integer call() throws IOException{

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create("", mediaType);
        Request request = new Request.Builder()
            .url(baseURL)
            .method("POST", body)
            .addHeader("X-OBSERVATORY-AUTH", this.getToken(login_token))
            .build();
        Response response = client.newCall(request).execute();

        String responseBody = response.body().string();
        JSONObject jObj = new JSONObject(responseBody);
        System.out.println(jObj.toString(4));

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
