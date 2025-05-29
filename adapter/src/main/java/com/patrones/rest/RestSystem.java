package com.patrones.rest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import com.patrones.utils.PropsUtil;

public class RestSystem {

    private String uri;

    public RestSystem() {
        uri = new PropsUtil().loadProperties().getProperty("rest.url");
    }

    public RestResponse sendRestRequest(String path, String body, RestActions action) {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                                    .uri(URI.create(uri+path))
                                    .header("Content-Type", "application/json");

        if (action == RestActions.GET) requestBuilder = requestBuilder.GET();
        if (action == RestActions.POST) requestBuilder = requestBuilder.POST(BodyPublishers.ofString(body));
        if (action == RestActions.PUT) requestBuilder = requestBuilder.PUT(BodyPublishers.ofString(body));
        if (action == RestActions.DELETE) requestBuilder = requestBuilder.DELETE();

        HttpRequest request = requestBuilder.build();

        RestResponse result = new RestResponse();

        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString()); 

            result.setStatus(response.statusCode());
            result.setPayload(response.body());

        } catch(Exception e) {
            result.setStatus(500);
            e.printStackTrace();
        }

        return result;
    }

}
