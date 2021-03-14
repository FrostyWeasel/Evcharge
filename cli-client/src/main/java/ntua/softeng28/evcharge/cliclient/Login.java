package ntua.softeng28.evcharge.cliclient;

import org.json.*;
import okhttp3.*;
import picocli.CommandLine.*;

import java.io.*;
import java.util.concurrent.Callable;


@Command(name = "login", description = "User login")
public class Login implements Callable<Integer> {

    public final String baseURL = "http://localhost:8765/evcharge/api/login";

    @Option(names = "--username", required = true, description = "Username")
    private String username;

    @Option(names = "--passw", required = true, description = "User's password")
    private String passw;

    @Option(names = "--format", required = true, description = "File format")
    private String format;

    @Option(names = "--apikey", required = true, description = "API key")
    private String apikey;

    File login_token = new File("softeng20bAPI.token");

    @Override
    public Integer call() throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(login_token));
        writer.write(username + ":");
        writer.write(this.getToken());
        writer.write("\n");
        writer.close();
        return 0;
    }

    public String getToken() throws IOException{

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create("username=" + username +"&password=" + passw, mediaType);
        Request request = new Request.Builder()
            .url(baseURL)
            .method("POST", body)
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .build();
        Response response = client.newCall(request).execute();

        String responseBody = response.body().string();
        JSONObject jObj = new JSONObject(responseBody);
        String token = jObj.getString("token");
        return token;
    }

    public static void main(String[] args) {

    }
}
