package com.doomcrow.shmup.screens;

import static com.doomcrow.shmup.Constants.TARGET_PPUX;
import static com.doomcrow.shmup.Constants.TARGET_PPUY;
import static com.doomcrow.shmup.Constants.CAM_HEIGHT;

import com.artemis.World;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.doomcrow.shmup.Assets;
import com.doomcrow.shmup.entities.EntityFactory;
import com.doomcrow.shmup.systems.FPSConsoleLoggingSystem;
import com.doomcrow.shmup.systems.TextWhiteToBlackFlickeringSystem;
import com.doomcrow.shmup.systems.TextRenderingSystem;
import com.doomcrow.shmup.systems.PlayerStartScreenInputSystem;

public class StartScreen implements Screen {
  private World world;
  private TextRenderingSystem textRenderer;
  
  public StartScreen(Game game) {
    Assets.loadFonts();
    world = new World();
    world.setSystem(new PlayerStartScreenInputSystem(game));
    world.setSystem(new TextWhiteToBlackFlickeringSystem());
    world.setSystem(new FPSConsoleLoggingSystem());
    textRenderer = world.setSystem(new TextRenderingSystem(TARGET_PPUX, TARGET_PPUY), true);
    world.initialize();
    // TODO: remove magic numbers
    EntityFactory.createText(world, 3f, CAM_HEIGHT - 2f, "ENTITY SHMUP", "orbitron-32").addToWorld();
    EntityFactory.createFlickeringText(world, 4.35f, 2f, "Press Any Key", "orbitron-24").addToWorld();    
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0f, 0f, 0f, 1);
    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    world.setDelta(delta);
    world.process();    
    textRenderer.process();
  }

  @Override
  public void resize(int width, int height) {
    // TODO: scale fonts
    textRenderer.resize(width, height);
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
