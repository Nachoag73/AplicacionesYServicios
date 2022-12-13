package uma.com.client;

  import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class Cliente {
  public static void main(String[] args) {

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://localhost:8080/uma/"))
        .GET()
        //  .POST(HttpRequest.BodyPublishers.ofString("{\"action\":\"hello\"}"))
        .build();

    try {
      HttpResponse<String> response = client.send(request,  BodyHandlers.ofString());
      System.out.println(response.body());
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}
