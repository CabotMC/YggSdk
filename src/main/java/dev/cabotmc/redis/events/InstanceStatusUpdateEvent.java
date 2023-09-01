package dev.cabotmc.redis.events;

public class InstanceStatusUpdateEvent implements CabotRedisEvent {
  String instance;
  int status;
  public InstanceStatusUpdateEvent(String instance, int status) {
    this.instance = instance;
    this.status = status;
  }

  public String getInstance() {
    return instance;
  }
  public int getStatus() {
    return status;
  }
}
