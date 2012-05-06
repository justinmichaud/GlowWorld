package com.GlowWorld.BlockPopulators;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ChestPopulator extends BlockPopulator {

	int CHEST_CHANCE = 60;
	
	@Override
	public void populate(World world, Random rand, Chunk chunk) {
		
		if (rand.nextInt(100) < CHEST_CHANCE) {
			
			//choose the materials inside the chest
			int MaxItems = rand.nextInt(12) + 1;
			
			int x = rand.nextInt(15) + chunk.getX() * 16;
			int z = rand.nextInt(15) + chunk.getZ() * 16;
			int y = world.getHighestBlockYAt(x, z);
			Location block = new Location(world,x,y,z);
			
			block.getBlock().setType(Material.CHEST);
			
			if (! (y<256) ) y = 256;
					
			Chest chest = (Chest) block.getBlock().getState();
			Inventory inv = chest.getBlockInventory();
			
			for (int i = 0; i < MaxItems;i++) {
				
				//chose the items.
				inv.setItem(inv.firstEmpty(), new ItemStack(rand.nextInt(126) + 256));
				
			}
			
		}

	}

}
