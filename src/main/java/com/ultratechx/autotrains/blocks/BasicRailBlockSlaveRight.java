package com.ultratechx.autotrains.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

import com.ultratechx.autotrains.AutoTrains;
import com.ultratechx.autotrains.lib.Reference;
import com.ultratechx.autotrains.tileentities.BasicRailRightTileEntity;
import com.ultratechx.autotrains.tileentities.BasicRailRightTileEntity;

public class BasicRailBlockSlaveRight extends BlockContainer{
	
	private Item item;
	
	public BasicRailBlockSlaveRight(String unlocalizedName, Material material) {
        super(material);
        this.setBlockName(unlocalizedName);
        this.setHardness(2.0F);
        this.setResistance(6.0F);
        this.setLightLevel(0.0F);
        this.setHarvestLevel("pickaxe", 2);
        this.setStepSound(soundTypeMetal);
        this.setBlockBounds(0f, 0f, 0f, 1f, 0.125f, 1f);
        this.setBlockTextureName(Reference.MODID + ":" + "rail_normal_icon");
    }
	
	@Override public int getRenderType() { return -1; }
	
	@Override public boolean isOpaqueCube() { return false; }
	
	public boolean renderAsNormalBlock() { return false; }
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta){
		return new BasicRailRightTileEntity();
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block){
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile != null && tile instanceof BasicRailRightTileEntity){
			BasicRailRightTileEntity BasicRail = (BasicRailRightTileEntity) tile;
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
	public Item getItemDropped(int p_149650_1_, Random rnd, int p_149650_3_)
    {
		return Item.getItemFromBlock(AutoTrains.BasicRail);
    }
}
