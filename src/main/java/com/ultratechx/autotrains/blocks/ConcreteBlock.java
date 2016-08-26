package com.ultratechx.autotrains.blocks;

import java.util.Random;

import com.ultratechx.autotrains.AutoTrains;
import com.ultratechx.autotrains.lib.Reference;
import com.ultratechx.autotrains.tileentities.BasicRailTileEntity;
import com.ultratechx.autotrains.tileentities.ConcreteSlaveTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ConcreteBlock extends Block{

	private Item item;
	
	public ConcreteBlock(Material material, String name, CreativeTabs tab) {
		super(material);
		this.setCreativeTab(tab);
		this.setBlockName(name);
		this.setBlockTextureName(Reference.MODID + ":" + "concrete_texture");
        this.setHardness(1.0F);
        this.setResistance(0.5F);
        this.setLightLevel(0.0F);
        this.setHarvestLevel("pickaxe", 0);
	}
	
	@Override public boolean isOpaqueCube() { return false; }
	
	public boolean renderAsNormalBlock() { return true; }
}
