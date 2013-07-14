package com.doomcrow.shmup.entities;

import static com.doomcrow.shmup.Constants.SHIP_WIDTH;
import static com.doomcrow.shmup.Constants.SHIP_HEIGHT;
import static com.doomcrow.shmup.Constants.BULLET_WIDTH;
import static com.doomcrow.shmup.Constants.BULLET_HEIGHT;
import static com.doomcrow.shmup.Constants.BULLET_VEL;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.doomcrow.shmup.Constants.Groups;
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
    
    world.getManager(GroupManager.class).add(entity, Groups.PLAYER_SHIP);
    
    return entity;
  }
  
  public static Entity createPlayerBullet(World world, float x, float y) {
    Entity entity = world.createEntity();
    
    entity.addComponent(ComponentFactory.createBounds(x, y, BULLET_WIDTH, BULLET_HEIGHT));
    entity.addComponent(ComponentFactory.createImage("playerBullet"));
    entity.addComponent(ComponentFactory.createPosition(x, y));
    entity.addComponent(ComponentFactory.createVelocity(0f, BULLET_VEL));
    entity.addComponent(ComponentFactory.createDimensions(BULLET_WIDTH, BULLET_HEIGHT));
    
    world.getManager(GroupManager.class).add(entity, Groups.PLAYER_BULLETS);
    
    return entity;
  }
  
}
