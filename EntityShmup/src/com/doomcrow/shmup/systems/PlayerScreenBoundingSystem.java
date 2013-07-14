package com.doomcrow.shmup.systems;

import static com.doomcrow.shmup.Constants.CAM_WIDTH;
import static com.doomcrow.shmup.Constants.CAM_HEIGHT;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.doomcrow.shmup.components.Dimensions;
import com.doomcrow.shmup.components.Player;
import com.doomcrow.shmup.components.Position;

public class PlayerScreenBoundingSystem extends EntityProcessingSystem {
  @Mapper ComponentMapper<Position> positionMapper;
  @Mapper ComponentMapper<Dimensions> dimensionsMapper;

  
  @SuppressWarnings("unchecked")
  public PlayerScreenBoundingSystem() {
    super(Aspect.getAspectForAll(Player.class, Position.class, Dimensions.class));
  }
  
  @Override
  protected void process(Entity entity) {
    Position position = positionMapper.get(entity);
    Dimensions dimensions = dimensionsMapper.get(entity);
    
    if (position.pos.x < 0) {
      position.pos.x = 0;
    } else if (position.pos.x + dimensions.width > CAM_WIDTH)  {
      position.pos.x = CAM_WIDTH - dimensions.width;
    }
    
    if (position.pos.y < 0) {
      position.pos.y = 0;
    } else if (position.pos.y + dimensions.height > CAM_HEIGHT) {
      position.pos.y = CAM_HEIGHT - dimensions.height;
    }
    
  }

}
