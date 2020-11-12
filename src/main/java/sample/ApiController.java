package sample;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiController {

    public static String getMethod(String u){
        Client client = Client.create();
        WebResource resource = client.resource(u);
        ClientResponse response = resource.get(ClientResponse.class);
        String s = response.getEntity(String.class);
        System.out.println(s);
        return s ;
    }

    public static void postMethod(String u, String data) throws IOException {
//        String data = "{\"menu_id\": \"C2\",\"menu_status\": \"Yes\",\"menu_name\": \"ramen\",\"price\": \"125\"}";
////        System.out.println(data);
        URL url = new URL(u) ;
        HttpURLConnection post = (HttpURLConnection) url.openConnection() ;
        post.setDoOutput(true);
        post.setRequestMethod("POST");
        post.setRequestProperty("Content-Type", "application/json");

        OutputStream outputStream = post.getOutputStream() ;
        outputStream.write(data.getBytes());
        outputStream.flush();
        outputStream.close();

        int responseCode = post.getResponseCode() ;
        System.out.println(responseCode);
        System.out.println(post.getResponseMessage());
    }

    public static void deleteMethod(String u) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault() ;
        HttpDelete httpDelete = new HttpDelete(u);
        httpclient.execute(httpDelete);
    }

    public static void putMethod(String u ,String data) throws IOException {
//        String data = "{\"menu_status\": \"Yes\",\"menu_name\": \"ramen tomyum\",\"price\": \"130\"}";
        CloseableHttpClient httpclient = HttpClients.createDefault() ;
        HttpPut httpPut = new HttpPut(u);
        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("Content-type", "application/json");
        StringEntity stringEntity = new StringEntity(data);
        httpPut.setEntity(stringEntity);
        httpclient.execute(httpPut);
    }
}
