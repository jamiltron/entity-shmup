package com.doomcrow.shmup;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "EntityShmup";
		cfg.useGL20 = false;
		cfg.width = 480;
		cfg.height = 640;
		cfg.vSyncEnabled = true;
		
		
		new LwjglApplication(new EntityShmupGame(), cfg);
	}
}
