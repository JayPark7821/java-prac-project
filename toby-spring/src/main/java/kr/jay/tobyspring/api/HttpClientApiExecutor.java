package kr.jay.tobyspring.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * HttpClientApiExecutor
 *
 * @author jaypark
 * @version 1.0.0
 * @since 8/6/24
 */
public class HttpClientApiExecutor implements ApiExecutor {

    @Override
    public String execute(URI uri) throws IOException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .GET()
            .build();

        try (HttpClient client = HttpClient.newBuilder().build()) {
            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
