package io.eoctodolphin.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RestClientBuilder {

    @Builder.Default
    private int connectionTimeoutMs = -1;
    @Builder.Default
    private int timeoutMs = -1;

    public RestClient generateClient() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        RequestConfig.Builder config = RequestConfig.custom();
        config.setConnectTimeout(connectionTimeoutMs);
        config.setConnectionRequestTimeout(connectionTimeoutMs);
        config.setSocketTimeout(timeoutMs);
        List<BasicHeader> deafultHeaders = new ArrayList<>();
        if (timeoutMs > 0) {
            deafultHeaders.add(new BasicHeader("Request-Timeout", String.valueOf(timeoutMs)));
        }
        httpClientBuilder.setDefaultHeaders(deafultHeaders);
        return new RestClient(httpClientBuilder.setDefaultRequestConfig(config.build()).build());
    }
}