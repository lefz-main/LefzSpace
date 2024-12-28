package httpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class httpPythonClientTest {
    @Test
    public void testHttpClientTestHandlerSucces() throws Exception {
        //given
        HttpClient mockClient = Mockito.mock(HttpClient.class);
        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);

        //when
        Mockito.when(mockResponse.statusCode()).thenReturn(200);
        Mockito.when(mockResponse.body()).thenReturn("{\"bericht\":\"Bonjour from Python\"}");
        Mockito.when(mockClient.send(Mockito.any(HttpRequest.class), Mockito.any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        //given
        httpPythonClient client = new httpPythonClient(mockClient);

        //then
        String result = client.getMessage();
        assertEquals("Bonjour from Python", result);
    }

    @Test
    public void testHttpClientTestHandlerIOException() throws Exception{
        //Mock
        HttpClient mockclient = Mockito.mock(HttpClient.class);

        //when
        Mockito.when(mockclient.send(Mockito.any(HttpRequest.class), Mockito.eq(HttpResponse.BodyHandlers.ofString())))
                .thenThrow(new java.io.IOException("Network error"));
        //then
        httpPythonClient client = new httpPythonClient(mockclient);
        String result = client.getMessage();
        assertTrue(result.contains("Error: Network error"));
    }

    @Test
    public void testHttpClientLoginHandlerSucces() throws Exception{
        //given
        HttpClient mockClient = Mockito.mock(HttpClient.class);
        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);

        Mockito.when(mockResponse.statusCode()).thenReturn(200);
        Mockito.when(mockResponse.body()).thenReturn("{\"bericht\":\"Logged In!\"}");
        Mockito.when(mockClient.send(Mockito.any(HttpRequest.class), Mockito.any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        httpPythonClient client = new httpPythonClient(mockClient);

        //when
        String result = client.tryLogin("test1", "123");

        //then
        assertEquals("Logged In!", result);
    }

    @Test
    public void testHttpClientLoginHandlerMailNotFound() throws Exception{
        //given
        HttpClient mockClient = Mockito.mock(HttpClient.class);
        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        //when
        Mockito.when(mockResponse.statusCode()).thenReturn(200);
        Mockito.when(mockResponse.body()).thenReturn("{\"bericht\": \"Mail not Found\"}");
        Mockito.when(mockClient.send(Mockito.any(HttpRequest.class), Mockito.any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        httpPythonClient client = new httpPythonClient(mockClient);
        String result = client.tryLogin("WrongMail", "doens'tmatterlol");

        //then
        assertEquals("Mail not Found", result);
    }
    @Test
    public void testHttpClientLoginHandlerIOException() throws Exception{
        //TODO
    }

}
