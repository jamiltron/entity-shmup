package com.doomcrow.shmup.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.doomcrow.shmup.components.Flicker;
import com.doomcrow.shmup.components.Text;

public class TextWhiteToBlackFlickeringSystem extends EntityProcessingSystem {
  @Mapper ComponentMapper<Text> textMapper;
  @Mapper ComponentMapper<Flicker> flickerMapper;

  @SuppressWarnings("unchecked")
  public TextWhiteToBlackFlickeringSystem() {
    super(Aspect.getAspectForAll(Text.class, Flicker.class));
  }

  @Override
  protected void process(Entity entity) {
    Text text = textMapper.get(entity);
    Flicker flicker = flickerMapper.get(entity);
    
    if (flicker.flickerUp) {
      flicker.currentTime += flicker.flickerRate * world.getDelta();
    } else {
      flicker.currentTime -= flicker.flickerRate * world.getDelta();
    }
    
    if (flicker.currentTime <= 0f) {
      flicker.flickerUp = true;
      flicker.currentTime = 0f;
    } else if (flicker.currentTime >= 1f) {
      flicker.flickerUp = false;
      flicker.currentTime = 1f;
    }

    text.color.set(flicker.currentTime,
        flicker.currentTime,
        flicker.currentTime,
        text.color.a);

  }

}
