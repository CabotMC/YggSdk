package dev.cabotmc.redis;

import com.google.gson.Gson;
import dev.cabotmc.redis.events.CabotRedisEvent;
import dev.cabotmc.types.Instance;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.params.ScanParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

public class YggRedisClient {
  JedisPool pool;
  Jedis jedis;
  Gson g;

  HashMap<Class<? extends CabotRedisEvent>, HashSet<Consumer<? extends CabotRedisEvent>>> listeners = new HashMap<>();
  public YggRedisClient(String connectionUrl) {
    pool = new JedisPool(connectionUrl);
    jedis = pool.getResource();
    jedis.connect();
    g = new Gson();
    jedis.subscribe(new RedisEventHandler(this), "instanceStatusChanged");
  }
  public List<String> getActiveInstances() {
    String cursor = ScanParams.SCAN_POINTER_START;
    ScanParams params = new ScanParams()
            .match("instance:*");
    var result = jedis.scan(cursor, params);
    return result.getResult()
            .stream()
            .map(s -> s.replace("instance:", ""))
            .toList();
  }
  public Instance getInstance(String name) {
    if (!jedis.exists("instance:" + name)) {
      return null;
    }
    var str = jedis.get("instance:" + name);
    return g.fromJson(str, Instance.class);
  }
  public void writeInstance(Instance i) {
    var str = g.toJson(i);
    jedis.set("instance:" + i.name, str);
  }
  public <T extends CabotRedisEvent> void addListener(Class<T> eventClass, Consumer<T> listener) {
    if (!listeners.containsKey(eventClass)) {
      listeners.put(eventClass, new HashSet<>());
    }
    listeners.get(eventClass).add(listener);
  }
  public <T extends CabotRedisEvent> void removeListener(Consumer<T> listener) {
    for (var set : listeners.values()) {
      set.remove(listener);
    }
  }
}
