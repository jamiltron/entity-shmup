package com.doomcrow.shmup.entities;

import static com.doomcrow.shmup.Constants.SHIP_WIDTH;
import static com.doomcrow.shmup.Constants.SHIP_HEIGHT;

import com.artemis.Entity;
import com.artemis.World;
import com.doomcrow.shmup.components.ComponentFactory;

public abstract class EntityFactory {
  
  public static Entity createSpaceship(World world, float x, float y) {
    Entity entity = world.createEntity();
    
    entity.addComponent(ComponentFactory.createBounds(x, y, SHIP_WIDTH, SHIP_HEIGHT));
    entity.addComponent(ComponentFactory.createImage("spaceship"));
    entity.addComponent(ComponentFactory.createPosition(x, y));
    entity.addComponent(ComponentFactory.createVelocity(0f, 0f));
    entity.addComponent(ComponentFactory.createDimensions(SHIP_WIDTH, SHIP_HEIGHT));
    entity.addComponent(ComponentFactory.createPlayer());
    
    return entity;
  }
}
