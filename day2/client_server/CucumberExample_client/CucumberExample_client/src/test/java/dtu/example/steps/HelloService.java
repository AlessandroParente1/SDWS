
package dtu.example.steps;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HelloService {
    private final String baseUrl;

    public HelloService() {
        this("http://localhost:8080"); // 按需改成你的服务地址
    }

    public HelloService(String baseUrl) {
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
    }

    public String hello() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest req = HttpRequest.newBuilder(URI.create(baseUrl + "/hello"))
                                         .GET().build();
            HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
            return resp.body();
        } catch (Exception e) {
            throw new RuntimeException("Failed to call /hello", e);
        }
    }
}
