package com.GlowWorld.ChunkGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

public class ChunkGenerator extends org.bukkit.generator.ChunkGenerator {

	public List<BlockPopulator> getDefaultPopulators(World world) {
    	
    	ArrayList<BlockPopulator> pop = new ArrayList<BlockPopulator>();
    	pop.add(new com.GlowWorld.BlockPopulators.ChestPopulator());
    	return pop;
    	
    }

    @Override
    public boolean canSpawn(World world, int x, int z) {
        return true;
    }

    public int xyzToByte(int x, int y, int z) {
    	return (x * 16 + z) * 256 + y;
    }
    @Override
    //make the hills using 3d simplex octave noise
	public byte[] generate(World world, Random rand, int ChunkX, int ChunkZ) {
    	
    	Random random = new Random(world.getSeed());
    	SimplexOctaveGenerator gen = new SimplexOctaveGenerator(random, 8);
    	byte[] chunk = new byte[16*16*256];
    	 
    	gen.setScale(1/256.0);
    	double threshold = 0.0;
    	
    	for (int x=0;x<16;x++) { 
    		for (int z=0;z<16;z++) {
    			int real_x = x+ChunkX * 16;
    			int real_z = z+ChunkZ*16;
    			
    			for (int y=1;y<256;y++) {
    				if(gen.noise(real_x, y, real_z, 0.5, 0.5) > threshold) {
    					chunk[xyzToByte(x,y,z)] = (byte)Material.GLOWSTONE.getId();
    				}
    			}
    		}
    	}
    	return chunk;    	
	}
}
