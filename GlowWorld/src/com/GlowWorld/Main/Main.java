package com.GlowWorld.Main;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	public void onEnable() {}
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new com.GlowWorld.ChunkGenerator.ChunkGenerator();
    }
	
}
