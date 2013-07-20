package com.doomcrow.shmup.systems;

import static com.doomcrow.shmup.Constants.CAM_HEIGHT;
import static com.doomcrow.shmup.Constants.CAM_WIDTH;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.doomcrow.shmup.Assets;
import com.doomcrow.shmup.components.Position;
import com.doomcrow.shmup.components.Text;

public class TextRenderingSystem extends EntitySystem {
  @Mapper ComponentMapper<Text> textMapper;
  @Mapper ComponentMapper<Position> positionMapper;

  private SpriteBatch batch;
  private float ppuX;
  private float ppuY;
  
  @SuppressWarnings("unchecked")
  public TextRenderingSystem(float ppuX, float ppuY) {
    super(Aspect.getAspectForAll(Position.class, Text.class));
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
      Position position = positionMapper.get(entity);
      Text text = textMapper.get(entity);

      BitmapFont font = Assets.getFont(text.fontName);
      font.setColor(text.color);
      font.draw(batch, text.str, position.pos.x * ppuX, position.pos.y * ppuY);
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
