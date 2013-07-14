package com.doomcrow.shmup.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;

import com.doomcrow.shmup.components.Position;
import com.doomcrow.shmup.components.Velocity;

public class MovementSystem extends EntityProcessingSystem {
  @Mapper ComponentMapper<Position> positionMapper;
  @Mapper ComponentMapper<Velocity> velocityMapper;

  @SuppressWarnings("unchecked")
  public MovementSystem() {
    super(Aspect.getAspectForAll(Position.class, Velocity.class));
  }
  
  @Override
  protected void process(Entity entity) {
    Position position = positionMapper.get(entity);
    Velocity velocity = velocityMapper.get(entity);
    
    position.pos.x += velocity.vel.x * world.delta;
    position.pos.y += velocity.vel.y * world.delta;
  }
}
