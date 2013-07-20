package com.doomcrow.shmup.systems;

import com.doomcrow.shmup.Constants.Groups;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.managers.GroupManager;
import com.artemis.utils.Bag;
import com.artemis.utils.ImmutableBag;
import com.doomcrow.shmup.components.Bounds;
import com.doomcrow.shmup.components.Position;
import com.doomcrow.shmup.entities.EntityFactory;

public class CollisionSystem extends EntitySystem {
  @Mapper ComponentMapper<Bounds> boundsMapper;
  @Mapper ComponentMapper<Position> positionMapper;
  
  private Bag<CollisionPair> collisionPairs;
  
  @SuppressWarnings("unchecked")
  public CollisionSystem() {
    super(Aspect.getAspectForAll(Bounds.class, Position.class));
  }
  
  @Override
  public void initialize() {
    collisionPairs = new Bag<CollisionPair>();
    
    collisionPairs.add(new CollisionPair(Groups.PLAYER_BULLETS, Groups.ENEMY_SHIPS, new CollisionHandler() {
      // TODO separate this out into a seperate collision handler so I can have both this and player/enemy collisions
      @Override
      public void handleCollision(Entity bullet, Entity enemy) {
        Position enemyPosition = positionMapper.get(enemy);
        EntityFactory.createEnemyExplosion(world, enemyPosition.pos.x, enemyPosition.pos.y).addToWorld();
        EntityFactory.freeEntity(bullet);
        EntityFactory.freeEntity(enemy);
        bullet.deleteFromWorld();
        enemy.deleteFromWorld();
      }
    }));
  }
  
  @Override
  protected void processEntities(ImmutableBag<Entity> entities) {
    for (int i = 0; i < collisionPairs.size(); i++) {
      collisionPairs.get(i).checkForCollisions();
    }
  }

  @Override
  protected boolean checkProcessing() {
    return true;
  }
  
  
  private class CollisionPair {
    private ImmutableBag<Entity> entities1;
    private ImmutableBag<Entity> entities2;
    private CollisionHandler handler;
    
    public CollisionPair(String group1, String group2, CollisionHandler handler) {
      entities1 = world.getManager(GroupManager.class).getEntities(group1);
      entities2 = world.getManager(GroupManager.class).getEntities(group2);
      this.handler = handler;
    }
    
    public void checkForCollisions() {
      for (int i = 0; i < entities1.size(); i++) {
        for (int j = 0; j < entities2.size(); j++) {
          Entity entity1 = entities1.get(i);
          Entity entity2 = entities2.get(j);
          if (collides(entity1, entity2)) {
            handler.handleCollision(entity1, entity2);
          }
        }
      }
    }
    
    private boolean collides(Entity entity1, Entity entity2) {
      Bounds bounds1 = boundsMapper.get(entity1);
      Bounds bounds2 = boundsMapper.get(entity2);
      
      return bounds1.rect.overlaps(bounds2.rect);
    }
  }
  
  private interface CollisionHandler {
    void handleCollision(Entity a, Entity b);
  }

}
