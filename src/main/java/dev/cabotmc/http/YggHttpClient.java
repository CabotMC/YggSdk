package dev.cabotmc.http;

import com.google.gson.Gson;
import dev.cabotmc.http.types.InstanceCreateRequest;
import dev.cabotmc.http.types.InstanceCreateResponse;
import dev.cabotmc.types.Instance;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class YggHttpClient {
  String baseURL;
  HttpClient client;
  Gson g;
  public YggHttpClient(String baseURL) {
    this.baseURL = baseURL;
    client = HttpClient.newBuilder()
            .build();
    g = new Gson();
  }

  public CompletableFuture<InstanceCreateResponse> createInstance(InstanceCreateRequest i) {
    URI u;
    try {
      u = new URI(baseURL + "/api/instance");
    } catch (URISyntaxException e) {
      return CompletableFuture.completedFuture(new InstanceCreateResponse(new RuntimeException("Invalid base URL!")));
    }
    var str = g.toJson(i);
    var req = HttpRequest.newBuilder()
            .POST(HttpRequest.BodyPublishers.ofString(str))
            .header("Content-Type", "application/json")
            .build();
    return client.sendAsync(req, HttpResponse.BodyHandlers.ofString())
            .thenApply(r -> {
              if (r.statusCode() != 200) {
                return new InstanceCreateResponse(new RuntimeException(r.body()));
              }
              var created = g.fromJson(r.body(), Instance.class);
              return new InstanceCreateResponse(created);
            });
  }

}

