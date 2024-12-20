package httpClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class httpPythonClient {
    private final HttpClient client;

    //testen met een gemockte HttpClient
    public httpPythonClient(HttpClient client) {
        this.client = client;
    }

    // voor productiegebruik
    public httpPythonClient() {
        this.client = HttpClient.newHttpClient();
    }

    public String getMessage() {
        String url = "http://localhost:8080/bonjour"; // Endpoint URL
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                ResponseModel responseModel = objectMapper.readValue(response.body(), ResponseModel.class);
                return responseModel.getBericht();
            } else {
                return "Error: " + response.statusCode();   //TODO make test for this
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    //JSON-response
    static class ResponseModel {
        private String bericht;

        public String getBericht() {
            return bericht;
        }

        public void setBericht(String bericht) {
            this.bericht = bericht;
        }
    }
}
