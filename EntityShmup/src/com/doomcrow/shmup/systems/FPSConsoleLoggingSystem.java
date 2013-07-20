package com.doomcrow.shmup.systems;

import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.graphics.FPSLogger;

public class FPSConsoleLoggingSystem extends VoidEntitySystem {
  private FPSLogger logger;
  
  public FPSConsoleLoggingSystem() {
    logger = new FPSLogger();
  }

  @Override
  protected void processSystem() {
    logger.log();
  }

}
