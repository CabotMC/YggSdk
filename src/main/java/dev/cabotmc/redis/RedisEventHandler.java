package dev.cabotmc.redis;

import redis.clients.jedis.JedisPubSub;

public class RedisEventHandler extends JedisPubSub {
  YggRedisClient owner;
  public RedisEventHandler(YggRedisClient client) {
    super();
    owner = client;
  }
  @Override
  public void onMessage(String channel, String message) {

  }
}
