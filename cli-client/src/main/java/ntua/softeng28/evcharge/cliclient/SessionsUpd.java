package ntua.softeng28.evcharge.cliclient;

import okhttp3.*;
import picocli.CommandLine.*;

import java.io.*;
import java.util.concurrent.Callable;


@Command(name = "--sessionsupd", description = "Create new user or modify existing")
public class SessionsUpd implements Callable<Integer> {

    public final String baseURL = "http://localhost:8765/evcharge/api/admin/system/sessionsupd/";

    @Option(names = "--source", required = true, description = "Source File for updating the system's sessions")
    private String filename;

    @Option(names = "--format", required = true, description = "File format")
    private String format;

    @Option(names = "--apikey", required = true, description = "API key")
    private String apikey;

    String token;
    File login_token = new File("softeng20bAPI.token");

    @Override
    public Integer call() throws IOException {

        String url = baseURL;
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("file","sessions.csv",
            RequestBody.create(new File(filename), MediaType.parse("application/octet-stream")))
            .build();

        Request request = new Request.Builder()
            .url(url)
            .method("POST", body)
            .addHeader("X-OBSERVATORY-AUTH", this.getToken(login_token))
            .build();
        Response response = client.newCall(request).execute();
        if(response.code() == 200)
            System.out.println("Sessions were updated succesfully!");
        else
            System.out.println("Something went wrong, please try again.");

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
