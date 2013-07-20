package com.doomcrow.shmup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Assets {
  
  private static Animation enemyExplosion;
  private static BitmapFont orbitron24;
  private static BitmapFont orbitron32;
  private static TextureRegion spaceship;
  private static TextureRegion playerBullet;
  private static TextureRegion enemyShip;
  
  public static void loadFonts() {
    orbitron24 = new BitmapFont(Gdx.files.internal("data/font/orbitron-24.fnt"),
        Gdx.files.internal("data/font/orbitron-24.png"), false);
    
    orbitron32 = new BitmapFont(Gdx.files.internal("data/font/orbitron-32.fnt"),
        Gdx.files.internal("data/font/orbitron-32.png"), false);
  }
  
  public static void loadImages() {
    TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("data/pack/game.pack"));
    spaceship = atlas.createSprite("spaceship");
    playerBullet = atlas.createSprite("playerBullet");
    enemyShip = atlas.createSprite("enemyShip");
    enemyExplosion = new Animation(Constants.ENEMY_EXPLODE_SPEED, 
        atlas.createSprite("enemyExplosion1"),
        atlas.createSprite("enemyExplosion2"),
        atlas.createSprite("enemyExplosion3"),
        atlas.createSprite("enemyExplosion4"),
        atlas.createSprite("enemyExplosion5"),
        atlas.createSprite("enemyExplosion6"),
        atlas.createSprite("enemyExplosion7"));
  }
  
  public static boolean isAnimationFinished(String name, float currentTime) {
    if (name == "enemyExplosion") {
      return enemyExplosion.isAnimationFinished(currentTime);
    } else {
      throw new IllegalArgumentException("Invalid animation name");
    }

  }
  
  public static TextureRegion getAnimationFrame(String name, float currentTime) {
    if (name == "enemyExplosion") {
      return enemyExplosion.getKeyFrame(currentTime);
    } else {
      throw new IllegalArgumentException("Invalid animation name");
    }
  }
  
  public static BitmapFont getFont(String name) {
    if (name.equals("orbitron-24")) {
      return orbitron24;
    } else if (name.equals("orbitron-32")) {
      return orbitron32;
    } else {
      throw new IllegalArgumentException("Invalid font name");
    }
  }

  public static TextureRegion getImage(String name) {
    if (name.equals("spaceship")) {
      return spaceship;
    } else if (name.equals("playerBullet")) {
      return playerBullet;
    } else if (name.equals("enemyShip")) {
      return enemyShip;
    } else {
      throw new IllegalArgumentException("Invalid image name");
    }
  }
}
