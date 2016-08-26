package com.ultratechx.autotrains.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.ultratechx.autotrains.AutoTrains;
import com.ultratechx.autotrains.lib.Reference;
import com.ultratechx.autotrains.tileentities.BasicRailLeftTileEntity;
import com.ultratechx.autotrains.tileentities.BasicRailTileEntity;

public class BasicRailBlockSlaveLeft extends BlockContainer{
	public BasicRailBlockSlaveLeft(String unlocalizedName, Material material) {
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
		return new BasicRailLeftTileEntity();
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block){
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile != null && tile instanceof BasicRailLeftTileEntity){
			BasicRailLeftTileEntity BasicRail = (BasicRailLeftTileEntity) tile;
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
		for (int xCoord = x- 1; xCoord < x + 2; xCoord++){
			for (int yCoord = y; yCoord < y + 1; yCoord++){
				for (int zCoord = z - 1; zCoord < z + 2; zCoord++){
					Block tile = world.getBlock(xCoord, yCoord, zCoord);
					if(tile == AutoTrains.BasicRail){
						world.setBlock(xCoord, yCoord, zCoord, Blocks.air);
						
					}
				}
			}
		}
	}
}
