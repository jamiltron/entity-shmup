package com.doomcrow.shmup;

public abstract class Constants {
	public static final int TARGET_WIDTH = 480;
	public static final int TARGET_HEIGHT = 640;
	public static final float TARGET_PPUX = 32f;
	public static final float TARGET_PPUY = 32f;
	
	public static final float CAM_WIDTH = TARGET_WIDTH / TARGET_PPUX;
	public static final float CAM_HEIGHT = TARGET_HEIGHT / TARGET_PPUY;
	
	public static final float SHIP_VEL = 4.0f;
	public static final float SHIP_WIDTH = 1f;
	public static final float SHIP_HEIGHT = 1f;
	
	public static final float BULLET_VEL = 8.0f;
	public static final float BULLET_WIDTH = 0.25f;
	public static final float BULLET_HEIGHT = 0.25f;
	
	public static final float ENEMY_VEL = 2.0f;
	
	public class Groups {
	  public static final String PLAYER_SHIP = "player ship";
	  public static final String PLAYER_BULLETS = "player bullets";
	  public static final String ENEMY_SHIPS = "enemy ships";
	  public static final String ENEMY_BULLETS = "enemy bullets";
	}
}
