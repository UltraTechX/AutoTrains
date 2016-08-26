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

public class ConcreteSlaveBlock extends BlockContainer{

	private Item item;
	
	public ConcreteSlaveBlock(Material material, String name, CreativeTabs tab) {
		super(material);
		this.setCreativeTab(tab);
		this.setBlockName(name);
		this.setBlockTextureName(Reference.MODID + ":" + "concrete_slave_texture");
        this.setHardness(1.0F);
        this.setResistance(0.5F);
        this.setLightLevel(0.0F);
        this.setHarvestLevel("pickaxe", 0);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta){
		return new ConcreteSlaveTileEntity();
	}
	
	@Override public boolean isOpaqueCube() { return false; }
	
	public boolean renderAsNormalBlock() { return true; }
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block){
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile != null && tile instanceof BasicRailTileEntity){
			BasicRailTileEntity BasicRail = (BasicRailTileEntity) tile;
			if(BasicRail.hasMaster()){
				if(BasicRail.isMaster()){
					if(!(BasicRail.checkMultiBlockForm() == 5 || BasicRail.checkMultiBlockForm() == 3 || BasicRail.checkMultiBlockForm() == 8)){
						BasicRail.resetStructure();
					}
				}else{
					if(!BasicRail.checkForMaster()){
						BasicRail.reset();
						world.markBlockForUpdate(x, y, z);
					}
				}
			}
		}
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta){
		for (int xCoord = x- 5; xCoord < x + 6; xCoord++){
			for (int yCoord = y; yCoord < y + 1; yCoord++){
				for (int zCoord = z - 5; zCoord < z + 6; zCoord++){
					Block tile = world.getBlock(xCoord, yCoord, zCoord);
					if(tile == AutoTrains.BasicRail){
						this.item = this.getItem(world, y, z, x);
						this.getItemDropped(1, new Random(), 1);
						world.setBlock(xCoord, yCoord, zCoord, Blocks.air);
					}
				}
			}
		}
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z){
		
	}
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random rnd, int p_149650_3_)
    {
        return Item.getItemFromBlock(AutoTrains.BasicRail);
    }
}
