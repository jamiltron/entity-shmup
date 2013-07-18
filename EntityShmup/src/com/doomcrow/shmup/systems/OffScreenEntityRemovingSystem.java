package com.doomcrow.shmup.systems;

import static com.doomcrow.shmup.Constants.CAM_HEIGHT;
import static com.doomcrow.shmup.Constants.CAM_WIDTH;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.doomcrow.shmup.components.Dimensions;
import com.doomcrow.shmup.components.Player;
import com.doomcrow.shmup.components.Position;
import com.doomcrow.shmup.entities.EntityFactory;

public class OffScreenEntityRemovingSystem extends EntityProcessingSystem {
  @Mapper ComponentMapper<Position> positionMapper;
  @Mapper ComponentMapper<Dimensions> dimensionsMapper;
  
  @SuppressWarnings("unchecked")
  public OffScreenEntityRemovingSystem() {
    super(Aspect.getAspectForAll(Position.class, Dimensions.class).exclude(Player.class));
  }

  @Override
  protected void process(Entity entity) {
    Position position = positionMapper.get(entity);
    Dimensions dimensions = dimensionsMapper.get(entity);
    
    if (position.pos.y < -dimensions.height ||
        position.pos.y  > CAM_HEIGHT + dimensions.height ||
        position.pos.x < -dimensions.width ||
        position.pos.x > CAM_WIDTH + dimensions.width) {
      EntityFactory.freeEntity(entity);
      entity.deleteFromWorld();
    }
  }

}
