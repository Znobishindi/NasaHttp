package nasa;

import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;


public class Http {
    public CloseableHttpClient setHttpClient() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setUserAgent("NASA_HW");
        httpClientBuilder.setDefaultRequestConfig(RequestConfig.DEFAULT);
        return httpClientBuilder
                .build();
    }

    public CloseableHttpResponse getResponseJson(String Url, CloseableHttpClient httpClient) throws IOException {
        HttpGet request = new HttpGet(Url);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

        return httpClient.execute(request);
    }


}
