package io.eoctodolphin.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestClientTest {
    public static MockWebServer mockBackEnd;
    public RestClient client = RestClientBuilder.builder().timeoutMs(1000).build().generateClient();
    public URI baseUrl;

    @BeforeAll
    static void setup() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @BeforeEach
    void initialize() throws URISyntaxException {
        baseUrl = new URI(String.format("http://localhost:%s/v1", mockBackEnd.getPort()));
    }

    @AfterAll
    static void clean() throws IOException {
        mockBackEnd.shutdown();
    }

    @Test
    public void shouldRequestCorrectlyForGet() throws Exception {
        mockBackEnd.enqueue(new MockResponse()
                .setBody("{}")
                .addHeader("Content-Type", "application/json"));

        client.get(baseUrl, Map.of("Header-Key", "Header-Value"));
        RecordedRequest recordedRequest = mockBackEnd.takeRequest();

        assertEquals("GET", recordedRequest.getMethod());
        assertEquals("/v1", recordedRequest.getPath());
        assertEquals("", recordedRequest.getBody().readUtf8());
        assertEquals("1000", recordedRequest.getHeader("Request-Timeout"));
        assertEquals("Header-Value", recordedRequest.getHeader("Header-Key"));
    }
}
