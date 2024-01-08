package com.example.lab9.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClient {
    private final java.net.http.HttpClient http = java.net.http.HttpClient.newHttpClient();

    private HttpRequest createGetHttpRequest(URI uri) {
        return HttpRequest.newBuilder().uri(uri).GET().build();
    }

    public HttpResponse<String> get(String url) throws URISyntaxException {
        URI uri = new URI(url);
        try {
            return http.send(createGetHttpRequest(uri), HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getJson(String url) throws URISyntaxException {
        HttpResponse<String> httpResponse = get(url);
        return httpResponse.body();
    }
}
