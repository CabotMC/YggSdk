package dev.cabotmc;

import redis.clients.jedis.JedisPool;

public class CabotSDK {
  private String apiBaseUrl;
  private String redisUrl;

  private JedisPool jedisPool;


  public CabotSDK(String api, String redis) {
    apiBaseUrl = api;
    redisUrl = redis;
  }
  public CabotSDK() {
    apiBaseUrl = "http://yggdrasil/api/";
    redisUrl = "redis:6379";
    jedisPool = new JedisPool(redisUrl);
  }
}
