package com.doomcrow.shmup.systems;

import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.doomcrow.shmup.screens.GameScreen;

public class PlayerStartScreenInputSystem extends VoidEntitySystem implements InputProcessor {
  private boolean pressedStart;
  private Game game;
  
  public PlayerStartScreenInputSystem(Game game) {
    pressedStart = false;
    this.game = game;
  }
  
  @Override
  protected void processSystem() {
    if (pressedStart) {
      game.setScreen(new GameScreen());
      
    } 
  }
  
  @Override
  protected void initialize() {
    Gdx.input.setInputProcessor(this);
  }


  @Override
  public boolean keyDown(int keycode) {
    pressedStart = true;
    return true;
  }

  @Override
  public boolean keyUp(int keycode) {
    return false;
  }

  @Override
  public boolean keyTyped(char character) {
    return false;
  }

  @Override
  public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override
  public boolean touchUp(int screenX, int screenY, int pointer, int button) {
    return false;
  }

  @Override
  public boolean touchDragged(int screenX, int screenY, int pointer) {
    return false;
  }

  @Override
  public boolean mouseMoved(int screenX, int screenY) {
    return false;
  }

  @Override
  public boolean scrolled(int amount) {
    return false;
  }
  
}
