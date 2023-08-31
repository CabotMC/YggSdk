package dev.cabotmc.http;

import java.net.http.HttpClient;

public class YggHttpClient {
  String baseURL;
  HttpClient client;
  public YggHttpClient(String baseURL) {
    this.baseURL = baseURL;
    client = HttpClient.newBuilder()
            .build();
  }
}

