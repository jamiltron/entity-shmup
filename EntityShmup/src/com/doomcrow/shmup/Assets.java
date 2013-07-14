package com.doomcrow.shmup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Assets {
  
  private static TextureRegion spaceship;
  private static TextureRegion playerBullet;
  
  public static void loadImages() {
    TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("data/pack/game.pack"));
    spaceship = atlas.createSprite("spaceship");
    playerBullet = atlas.createSprite("playerBullet");
  }

  public static TextureRegion getImage(String name) {
    if (name.equals("spaceship")) {
      return spaceship;
    } else if (name.equals("playerBullet")) {
      return playerBullet;
    } else {
      throw new IllegalArgumentException("Invalid image name");
    }
  }
}
