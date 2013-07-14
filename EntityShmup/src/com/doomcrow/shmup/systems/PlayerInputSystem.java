package com.doomcrow.shmup.systems;

import static com.doomcrow.shmup.Constants.SHIP_VEL;
import static com.doomcrow.shmup.Constants.BULLET_WIDTH;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.doomcrow.shmup.components.Dimensions;
import com.doomcrow.shmup.components.Position;
import com.doomcrow.shmup.components.Velocity;
import com.doomcrow.shmup.components.Player;
import com.doomcrow.shmup.entities.EntityFactory;

public class PlayerInputSystem  extends EntityProcessingSystem implements InputProcessor {
  @Mapper ComponentMapper<Velocity> velocityMapper;
  @Mapper ComponentMapper<Position> positionMapper;
  @Mapper ComponentMapper<Dimensions> dimensionsMapper;

  private boolean up, down, left, right, shoot;
  private float shootCountDown = 0f;
  
  // TODO: make fireRate a component
  private float fireRate = 0.1f;
  
  @SuppressWarnings("unchecked")
  public PlayerInputSystem() {
    super(Aspect.getAspectForAll(Position.class, Velocity.class, Player.class));
  }
  
  @Override
  protected void initialize() {
    Gdx.input.setInputProcessor(this);
  }
  
  @Override
  protected void process(Entity entity) {
    Velocity velocity = velocityMapper.get(entity);
    Position position = positionMapper.get(entity);
    Dimensions dimensions = dimensionsMapper.get(entity);
    
    if (up) velocity.vel.y = SHIP_VEL;
    if (down) velocity.vel.y = -SHIP_VEL;
    if ((up && down) || (!up && !down)) velocity.vel.y = 0;
    
    if (left) velocity.vel.x = -SHIP_VEL;
    if (right) velocity.vel.x = SHIP_VEL;
    if ((left && right) || (!left && !right)) velocity.vel.x = 0;
    
    if (shoot && shootCountDown <= 0f) {
      EntityFactory.createPlayerBullet(world, position.pos.x + dimensions.width / 2f - BULLET_WIDTH / 2f, 
          position.pos.y + dimensions.height).addToWorld();
      shootCountDown = fireRate;
    }
    
    if (shootCountDown > 0f) {
      shootCountDown -= world.delta;
    }
  }

  @Override
  public boolean keyDown(int keycode) {
    if (keycode == Input.Keys.A) {
      left = true;
    } else if (keycode == Input.Keys.D) {
      right = true;
    } else if (keycode == Input.Keys.W) {
      up = true;
    } else if (keycode == Input.Keys.S) {
      down = true;
    } else if (keycode == Input.Keys.SPACE) {
      shoot = true;
    }
    
    return true;
  }

  @Override
  public boolean keyUp(int keycode) {
    if (keycode == Input.Keys.A) {
      left = false;
    } else if (keycode == Input.Keys.D) {
      right = false;
    } else if (keycode == Input.Keys.W) {
      up = false;
    } else if (keycode == Input.Keys.S) {
      down = false;
    } else if (keycode == Input.Keys.SPACE) {
      shoot = false;
    }

    return true;
  }

  @Override
  public boolean keyTyped(char character) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean touchDragged(int screenX, int screenY, int pointer) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean mouseMoved(int screenX, int screenY) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean scrolled(int amount) {
    // TODO Auto-generated method stub
    return false;
  }

}
