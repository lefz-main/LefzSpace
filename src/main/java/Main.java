import httpClient.httpPythonClient;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        httpPythonClient client = new httpPythonClient();

        String json = client.getMessage();
        System.out.println(json);

        String loginResponse = client.tryLogin("test1", "123");
        System.out.println(loginResponse);
    }
}