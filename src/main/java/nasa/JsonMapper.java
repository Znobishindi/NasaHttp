package nasa;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;


public class JsonMapper {
    public ObjectMapper mapper = new ObjectMapper();

    public NASA getNASAList(CloseableHttpResponse response) throws IOException {

        return mapper.readValue(response.getEntity().getContent(), new TypeReference<>() {
        });
    }
}
