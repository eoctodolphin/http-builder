package io.eoctodolphin.client;

import static java.util.Optional.empty;
import static java.util.Optional.of;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;

import lombok.Builder;

public class Client {

    private final HttpClient httpClient;

    Client(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Optional<HttpResponse> get(URI uri) {
        try {
            return of(httpClient.execute(new HttpGet(uri)));
        } catch (IOException e) {
            // TODO add log
            return empty();
        }
    }

}