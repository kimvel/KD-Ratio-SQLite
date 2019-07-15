import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    String KEY = "";


        public static String sendGetRequest(String requestUrl){
            StringBuilder response = new StringBuilder();

            try {
                URL url = new URL(requestUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "*/*");
                connection.setRequestProperty("Content type", "application/json; charset=UTF-8");

                InputStream stream = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(stream);
                BufferedReader buffer = new BufferedReader(reader);

            } catch(MalformedURLException e){
                e.printStackTrace();
            } catch(IOException e) {
                e.printStackTrace();
            }
            return response.toString();

        }


    public static void main(String[] args) {
        System.out.println("Hello Worlds!");
    }
}
