package dev.cabotmc.http.types;

import dev.cabotmc.types.Instance;

public class InstanceCreateResponse {
  Exception exception;
  Instance created;
  public InstanceCreateResponse(Exception e) {
    exception = e;
  }
  public InstanceCreateResponse(Instance c) {
    created = c;
  }
  public boolean isError() {
    return exception != null;
  }
}
