package dev.cabotmc;

import dev.cabotmc.http.YggHttpClient;
import redis.clients.jedis.JedisPool;

import java.net.http.HttpClient;

public class CabotSDK {
  private String apiBaseUrl;
  private String redisUrl;

  private JedisPool jedisPool;

  private YggHttpClient client;


  public CabotSDK(String api, String redis) {
    apiBaseUrl = api;
    redisUrl = redis;
  }
  public CabotSDK() {
    apiBaseUrl = "http://yggdrasil";
    redisUrl = "redis:6379";
    jedisPool = new JedisPool(redisUrl);
    client = new YggHttpClient(apiBaseUrl);
  }
}
