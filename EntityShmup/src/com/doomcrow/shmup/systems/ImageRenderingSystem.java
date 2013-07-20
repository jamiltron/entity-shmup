package com.doomcrow.shmup.systems;

import static com.doomcrow.shmup.Constants.CAM_HEIGHT;
import static com.doomcrow.shmup.Constants.CAM_WIDTH;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.doomcrow.shmup.Assets;
import com.doomcrow.shmup.components.Dimensions;
import com.doomcrow.shmup.components.Position;
import com.doomcrow.shmup.components.Image;

public class ImageRenderingSystem extends EntitySystem {
  @Mapper ComponentMapper<Image> imageMapper;
  @Mapper ComponentMapper<Position> positionMapper;
  @Mapper ComponentMapper<Dimensions> dimensionsMapper;

  private SpriteBatch batch;
  private float ppuX;
  private float ppuY;
  
  @SuppressWarnings("unchecked")
  public ImageRenderingSystem(float ppuX, float ppuY) {
    super(Aspect.getAspectForAll(Position.class, Image.class));
    this.ppuX = ppuX;
    this.ppuY = ppuY;
    batch = new SpriteBatch();
  }
  
  public void resize(float width, float height) {
    ppuX = width / CAM_WIDTH;
    ppuY = height / CAM_HEIGHT;
  }
  
  protected void process(Entity entity) {
    if (positionMapper.has(entity)) {
      Position position = positionMapper.getSafe(entity);
      Image image = imageMapper.get(entity);
      Dimensions dimensions = dimensionsMapper.get(entity);
      
      batch.draw(Assets.getImage(image.name), 
           position.pos.x * ppuX, position.pos.y * ppuY, 
           dimensions.width * ppuX, dimensions.height * ppuY);
    }
  }

  @Override
  protected boolean checkProcessing() {
    return true;
  }

  @Override
  protected void processEntities(ImmutableBag<Entity> entities) {
    batch.begin();
    for (int i = 0; i < entities.size(); i++) {
      process(entities.get(i));
    }
    batch.end();
  }

}
