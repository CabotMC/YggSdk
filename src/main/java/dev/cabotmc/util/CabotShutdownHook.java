package dev.cabotmc.util;

import dev.cabotmc.CabotSDK;

public class CabotShutdownHook extends Thread {
  CabotSDK sdk;
  public CabotShutdownHook(CabotSDK sdk) {
    this.sdk = sdk;
  }

  @Override
  public void run() {

  }
}
