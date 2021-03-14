package ntua.softeng28.evcharge.cliclient;

import org.json.*;
import okhttp3.*;
import picocli.CommandLine.*;

import java.io.*;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;


@Command(name = "--sessionsupd", description = "Create new user or modify existing")
public class SessionsUpd implements Callable<Integer> {

    public final String baseURL = "https://localhost:8765/evcharge/api/admin/system/sessionsupd/";

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
        if(!(format.equals("json")) && !(format.equals("csv")))
            System.out.println("Please enter a valid format: json or csv.");
        else{
            String url = baseURL;
            OkHttpClient client = new OkHttpClient.Builder()
               .hostnameVerifier(new HostnameVerifier() {
                   @Override
                   public boolean verify(String hostname, SSLSession session) {
                       return true;
                   }
               })
               .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file","sessions.csv",
                RequestBody.create(new File(filename), MediaType.parse("application/octet-stream")))
                .build();

            Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("X-OBSERVATORY-AUTH", apikey)
                .build();
            Response response = client.newCall(request).execute();

            if(response.code() == 200){
                String responseBody = response.body().string();
                JSONObject jObj = new JSONObject(responseBody);
                System.out.println("Reading sessions in file...");
                System.out.println("File contains " + jObj.getInt("SessionsInUploadedFile") + " sessions.");
                System.out.println("Updating sessions in database...");
                System.out.println("Sessions were updated successfully!");
                System.out.println(jObj.getInt("SessionsImported") + " sessions were imported.");
                System.out.println("Total sessions in database: " + jObj.getInt("TotalSessionsInDatabase"));
            }
            else if(response.code() == 401)
                System.out.println("Unauthorized access. Please log in first as the administrator.");
            else
                System.out.println("Something went wrong. Please check the apikey and the file name & path, otherwise contact the software administrators.");
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
