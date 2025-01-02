package httpClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpResponse;

public class jsonResponseTransformerHelper {
    public Object jsonTransformer(String rawJsonInput){
        try {
                ObjectMapper objectMapper = new ObjectMapper();
                httpPythonClient.ResponseModel responseModel = objectMapper.readValue(rawJsonInput, httpPythonClient.ResponseModel.class);
                return responseModel.getBericht();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
