package com.doomcrow.shmup.systems;

import static com.doomcrow.shmup.Constants.CAM_HEIGHT;
import static com.doomcrow.shmup.Constants.CAM_WIDTH;
import static com.doomcrow.shmup.Constants.SHIP_WIDTH;

import com.artemis.systems.VoidEntitySystem;
import com.artemis.utils.Timer;
import com.badlogic.gdx.math.MathUtils;
import com.doomcrow.shmup.entities.EntityFactory;

public class EnemySpawningSystem extends VoidEntitySystem {

  Timer timer;

  public EnemySpawningSystem() {
    timer = new Timer(3, true) {
      @Override
      public void execute() {
        EntityFactory.createEnemySpaceship(world, MathUtils.random(0f, CAM_WIDTH - SHIP_WIDTH), CAM_HEIGHT - 1f/32f).addToWorld();
      }
    };
  }
  
  @Override
  protected void processSystem() {
    timer.update(world.delta);
  }


}
