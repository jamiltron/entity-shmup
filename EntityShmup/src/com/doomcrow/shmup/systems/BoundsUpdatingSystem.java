package com.doomcrow.shmup.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.doomcrow.shmup.components.Bounds;
import com.doomcrow.shmup.components.Position;

public class BoundsUpdatingSystem extends EntityProcessingSystem {
  @Mapper ComponentMapper<Position> positionMapper;
  @Mapper ComponentMapper<Bounds> boundsMapper;
  

  @SuppressWarnings("unchecked")
  public BoundsUpdatingSystem() {
    super(Aspect.getAspectForAll(Bounds.class, Position.class));
  }
  
  @Override
  protected void process(Entity entity) {
    Position position = positionMapper.get(entity);
    Bounds bounds = boundsMapper.get(entity);
    
    bounds.rect.x = position.pos.x + bounds.xOffset;
    bounds.rect.y = position.pos.y + bounds.yOffset;
  }
}
