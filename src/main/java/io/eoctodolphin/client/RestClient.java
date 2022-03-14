package io.eoctodolphin.client;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Optional;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter(lombok.AccessLevel.PROTECTED)
public class RestClient {
    final private HttpClient httpClient;

    public Optional<HttpResponse> get(URI uri, Map<String, String> headers) {
        HttpRequestBase requestBase = new HttpGet(uri);
        addHeaders(requestBase, headers);
        try {
            return Optional.of(httpClient.execute(requestBase));
        } catch (IOException e) {
            // TODO add log
            return Optional.empty();
        }
    }

    void addHeaders(HttpRequestBase requestBase, Map<String, String> headers) {
        if (headers != null) {
            headers.entrySet().forEach(entry -> requestBase.addHeader(entry.getKey(), entry.getValue()));
        }
    }

}
