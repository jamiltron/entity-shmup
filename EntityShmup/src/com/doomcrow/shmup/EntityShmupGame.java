package com.doomcrow.shmup;

import com.badlogic.gdx.Game;
import com.doomcrow.shmup.screens.GameScreen;

public class EntityShmupGame extends Game {
  
  @Override
  public void create() {
    setScreen(new GameScreen());
  }
}