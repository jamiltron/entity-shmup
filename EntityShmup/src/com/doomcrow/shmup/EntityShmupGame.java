package com.doomcrow.shmup;

import com.badlogic.gdx.Game;
import com.doomcrow.shmup.screens.StartScreen;

public class EntityShmupGame extends Game {
  
  @Override
  public void create() {
    setScreen(new StartScreen(this));
  }
}