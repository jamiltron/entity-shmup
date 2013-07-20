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
import com.doomcrow.shmup.components.Explosion;
import com.doomcrow.shmup.components.Position;
import com.doomcrow.shmup.entities.EntityFactory;

public class ExplosionRenderingSystem extends EntitySystem {
  @Mapper ComponentMapper<Explosion> explosionMapper;
  @Mapper ComponentMapper<Position> positionMapper;
  @Mapper ComponentMapper<Dimensions> dimensionsMapper;

  private SpriteBatch batch;
  private float ppuX;
  private float ppuY;
  
  @SuppressWarnings("unchecked")
  public ExplosionRenderingSystem(float ppuX, float ppuY) {
    super(Aspect.getAspectForAll(Position.class, Explosion.class, Dimensions.class));
    this.ppuX = ppuX;
    this.ppuY = ppuY;
    batch = new SpriteBatch();
  }
  
  public void resize(float width, float height) {
    ppuX = width / CAM_WIDTH;
    ppuY = height / CAM_HEIGHT;
  }
  
  protected void process(Entity entity) {
    Position position = positionMapper.get(entity);
    Explosion explosion = explosionMapper.get(entity);
    Dimensions dimensions = dimensionsMapper.get(entity);
    
    if (Assets.isAnimationFinished(explosion.name, explosion.currentTime)) {
      EntityFactory.freeEntity(entity);
      world.deleteEntity(entity);
    } else {
      batch.draw(Assets.getAnimationFrame(explosion.name, explosion.currentTime), 
          position.pos.x * ppuX, position.pos.y * ppuY, 
          dimensions.width * ppuX, dimensions.height * ppuY);
      explosion.currentTime += world.getDelta();
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