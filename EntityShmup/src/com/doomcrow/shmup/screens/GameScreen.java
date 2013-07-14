package com.doomcrow.shmup.screens;

import static com.doomcrow.shmup.Constants.TARGET_PPUX;
import static com.doomcrow.shmup.Constants.TARGET_PPUY;
import static com.doomcrow.shmup.Constants.CAM_WIDTH;
import static com.doomcrow.shmup.Constants.CAM_HEIGHT;

import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.doomcrow.shmup.Assets;
import com.doomcrow.shmup.entities.EntityFactory;
import com.doomcrow.shmup.systems.ImageRenderingSystem;
import com.doomcrow.shmup.systems.MovementSystem;
import com.doomcrow.shmup.systems.OffScreenEntityRemovingSystem;
import com.doomcrow.shmup.systems.PlayerInputSystem;
import com.doomcrow.shmup.systems.PlayerScreenBoundingSystem;

public class GameScreen implements Screen {
  private World world;
  private ImageRenderingSystem imageRenderer;
  
  public GameScreen() {
    
    world = new World();
    world.setManager(new GroupManager());
    world.setSystem(new MovementSystem());
    world.setSystem(new PlayerInputSystem());
    world.setSystem(new OffScreenEntityRemovingSystem());
    world.setSystem(new PlayerScreenBoundingSystem());
    
    imageRenderer = world.setSystem(new ImageRenderingSystem(TARGET_PPUX, TARGET_PPUY), true);
    world.initialize();
    EntityFactory.createSpaceship(world, CAM_WIDTH / 2f, CAM_HEIGHT / 2f).addToWorld();
    Assets.loadImages();
  }
  
  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0f, 0f, 0f, 1);
    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    world.setDelta(delta);
    world.process();
    
    imageRenderer.process();
  }
  @Override
  public void resize(int width, int height) {
    imageRenderer.resize(width, height);
    
  }
  @Override
  public void show() {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void hide() {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void pause() {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void resume() {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void dispose() {
    // TODO Auto-generated method stub
    
  }

}
