package com.ultratechx.autotrains.blocks;

import java.util.Random;

import com.ultratechx.autotrains.AutoTrains;
import com.ultratechx.autotrains.lib.Reference;
import com.ultratechx.autotrains.tileentities.BasicRailTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BasicRailBlock extends BlockContainer{
    Block BasicRailBlockSlave = null;
    int brnd = 0;
    private EntityPlayer player;
    private Item item;
    private boolean normalbreak = true;
	public BasicRailBlock(String unlocalizedName, Material material) {
        super(material);
        this.setBlockName(unlocalizedName);
        this.setBlockTextureName(Reference.MODID + ":" + "rail_normal_icon");
        this.setCreativeTab(CreativeTabs.tabTransport);
        this.setHardness(2.0F);
        this.setResistance(6.0F);
        this.setLightLevel(0.0F);
        this.setHarvestLevel("pickaxe", 2);
        this.setStepSound(soundTypeMetal);
        this.setBlockBounds(0f, 0f, 0f, 1f, 0.0625f, 1f);
		Block basicRailBlockSlave = BasicRailBlockSlave;
    }
	
	@Override public int getRenderType() { return -1; }
	
	@Override public boolean isOpaqueCube() { return false; }
	
	public boolean renderAsNormalBlock() { return false; }
	
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
	public void onBlockAdded(World world, int x, int y, int z){
		this.player = world.getClosestPlayer(x, y, z, 100);
		brnd = 0;
		normalbreak = true;
		for (int xCoord = x- 5; xCoord < x + 6; xCoord++){
			for (int yCoord = y; yCoord < y + 1; yCoord++){
				for (int zCoord = z - 5; zCoord < z + 6; zCoord++){
					Block tile = world.getBlock(xCoord, yCoord, zCoord);
					if(tile != Blocks.air && xCoord != x && zCoord != z){
						brnd++;
						//System.out.println(x + ", " + y + ", " + z);
					}else{
						tile = world.getBlock(xCoord, yCoord - 1, zCoord);
						if(tile == AutoTrains.BasicRail || tile == AutoTrains.BasicRailSlave || tile == AutoTrains.BasicRailSlaveLeft || tile == AutoTrains.BasicRailSlaveRight){
							brnd++;
							//System.out.println(x + ", " + y + ", " + z);
						}
						tile = world.getBlock(xCoord, yCoord + 1, zCoord);
						if(tile == AutoTrains.BasicRail || tile == AutoTrains.BasicRailSlave || tile == AutoTrains.BasicRailSlaveLeft || tile == AutoTrains.BasicRailSlaveRight){
							brnd++;
							//System.out.println(x + ", " + y + ", " + z);
						}
					}
				}
			}
		}
		System.out.println(brnd);
		if(brnd == 0){
			/*world.setBlock(x, y, z + 1, AutoTrains.BasicRailSlave);
			world.setBlock(x, y, z - 1, AutoTrains.BasicRailSlave);
			world.setBlock(x + 1, y, z + 1, AutoTrains.BasicRailSlaveLeft);
			world.setBlock(x - 1, y, z + 1, AutoTrains.BasicRailSlaveRight);
			world.setBlock(x + 1, y, z, AutoTrains.BasicRailSlaveLeft);
			world.setBlock(x - 1, y, z, AutoTrains.BasicRailSlaveRight);
			world.setBlock(x + 1, y, z - 1, AutoTrains.BasicRailSlaveLeft);
			world.setBlock(x - 1, y, z - 1, AutoTrains.BasicRailSlaveRight);
			world.setBlock(x + 1, y, z - 2, AutoTrains.BasicRailSlaveLeft);
			world.setBlock(x - 1, y, z - 2, AutoTrains.BasicRailSlaveRight);
			world.setBlock(x, y, z - 2, AutoTrains.BasicRailSlave);
			world.setBlock(x + 1, y, z + 2, AutoTrains.BasicRailSlaveLeft);
			world.setBlock(x - 1, y, z + 2, AutoTrains.BasicRailSlaveRight);
			world.setBlock(x, y, z + 2, AutoTrains.BasicRailSlave);
			world.setBlock(x - 2, y, z, AutoTrains.ConcreteSlave);
			world.setBlock(x - 2, y, z - 1, AutoTrains.ConcreteSlave);
			world.setBlock(x - 2, y, z - 2, AutoTrains.ConcreteSlave);
			world.setBlock(x - 2, y, z + 1, AutoTrains.ConcreteSlave);
			world.setBlock(x - 2, y, z + 2, AutoTrains.ConcreteSlave);
			world.setBlock(x + 2, y, z, AutoTrains.ConcreteSlave);
			world.setBlock(x + 2, y, z - 1, AutoTrains.ConcreteSlave);
			world.setBlock(x + 2, y, z - 2, AutoTrains.ConcreteSlave);
			world.setBlock(x + 2, y, z + 1, AutoTrains.ConcreteSlave);
			world.setBlock(x + 2, y, z + 2, AutoTrains.ConcreteSlave);
			*/
			// 1
			world.setBlock(x - 5, y, z + 5, AutoTrains.ConcreteSlave);
			world.setBlock(x - 4, y, z + 5, AutoTrains.ConcreteSlave);
			world.setBlock(x - 3, y, z + 5, AutoTrains.ConcreteSlave);
			world.setBlock(x - 2, y, z + 5, AutoTrains.BasicRailSlaveRight);
			world.setBlock(x - 1, y, z + 5, AutoTrains.BasicRailSlave);
			world.setBlock(x, y, z + 5, AutoTrains.BasicRailSlave);
			world.setBlock(x + 1, y, z + 5, AutoTrains.BasicRailSlave);
			world.setBlock(x + 2, y, z + 5, AutoTrains.BasicRailSlaveLeft);
			world.setBlock(x + 3, y, z + 5, AutoTrains.ConcreteSlave);
			world.setBlock(x + 4, y, z + 5, AutoTrains.ConcreteSlave);
			world.setBlock(x + 5, y, z + 5, AutoTrains.ConcreteSlave);
			// 2
			world.setBlock(x - 5, y, z + 4, AutoTrains.ConcreteSlave);
			world.setBlock(x - 4, y, z + 4, AutoTrains.ConcreteSlave);
			world.setBlock(x - 3, y, z + 4, AutoTrains.ConcreteSlave);
			world.setBlock(x - 2, y, z + 4, AutoTrains.BasicRailSlaveRight);
			world.setBlock(x - 1, y, z + 4, AutoTrains.BasicRailSlave);
			world.setBlock(x, y, z + 4, AutoTrains.BasicRailSlave);
			world.setBlock(x + 1, y, z + 4, AutoTrains.BasicRailSlave);
			world.setBlock(x + 2, y, z + 4, AutoTrains.BasicRailSlaveLeft);
			world.setBlock(x + 3, y, z + 4, AutoTrains.ConcreteSlave);
			world.setBlock(x + 4, y, z + 4, AutoTrains.ConcreteSlave);
			world.setBlock(x + 5, y, z + 4, AutoTrains.ConcreteSlave);
			// 3
			world.setBlock(x - 5, y, z + 3, AutoTrains.ConcreteSlave);
			world.setBlock(x - 4, y, z + 3, AutoTrains.ConcreteSlave);
			world.setBlock(x - 3, y, z + 3, AutoTrains.ConcreteSlave);
			world.setBlock(x - 2, y, z + 3, AutoTrains.BasicRailSlaveRight);
			world.setBlock(x - 1, y, z + 3, AutoTrains.BasicRailSlave);
			world.setBlock(x, y, z + 3, AutoTrains.BasicRailSlave);
			world.setBlock(x + 1, y, z + 3, AutoTrains.BasicRailSlave);
			world.setBlock(x + 2, y, z + 3, AutoTrains.BasicRailSlaveLeft);
			world.setBlock(x + 3, y, z + 3, AutoTrains.ConcreteSlave);
			world.setBlock(x + 4, y, z + 3, AutoTrains.ConcreteSlave);
			world.setBlock(x + 5, y, z + 3, AutoTrains.ConcreteSlave);
			// 4
			world.setBlock(x - 5, y, z + 2, AutoTrains.ConcreteSlave);
			world.setBlock(x - 4, y, z + 2, AutoTrains.ConcreteSlave);
			world.setBlock(x - 3, y, z + 2, AutoTrains.ConcreteSlave);
			world.setBlock(x - 2, y, z + 2, AutoTrains.BasicRailSlaveRight);
			world.setBlock(x - 1, y, z + 2, AutoTrains.BasicRailSlave);
			world.setBlock(x, y, z + 2, AutoTrains.BasicRailSlave);
			world.setBlock(x + 1, y, z + 2, AutoTrains.BasicRailSlave);
			world.setBlock(x + 2, y, z + 2, AutoTrains.BasicRailSlaveLeft);
			world.setBlock(x + 3, y, z + 2, AutoTrains.ConcreteSlave);
			world.setBlock(x + 4, y, z + 2, AutoTrains.ConcreteSlave);
			world.setBlock(x + 5, y, z + 2, AutoTrains.ConcreteSlave);
			// 5
			world.setBlock(x - 5, y, z + 1, AutoTrains.ConcreteSlave);
			world.setBlock(x - 4, y, z + 1, AutoTrains.ConcreteSlave);
			world.setBlock(x - 3, y, z + 1, AutoTrains.ConcreteSlave);
			world.setBlock(x - 2, y, z + 1, AutoTrains.BasicRailSlaveRight);
			world.setBlock(x - 1, y, z + 1, AutoTrains.BasicRailSlave);
			world.setBlock(x, y, z + 1, AutoTrains.BasicRailSlave);
			world.setBlock(x + 1, y, z + 1, AutoTrains.BasicRailSlave);
			world.setBlock(x + 2, y, z + 1, AutoTrains.BasicRailSlaveLeft);
			world.setBlock(x + 3, y, z + 1, AutoTrains.ConcreteSlave);
			world.setBlock(x + 4, y, z + 1, AutoTrains.ConcreteSlave);
			world.setBlock(x + 5, y, z + 1, AutoTrains.ConcreteSlave);
			// 6
			world.setBlock(x - 5, y, z, AutoTrains.ConcreteSlave);
			world.setBlock(x - 4, y, z, AutoTrains.ConcreteSlave);
			world.setBlock(x - 3, y, z, AutoTrains.ConcreteSlave);
			world.setBlock(x - 2, y, z, AutoTrains.BasicRailSlaveRight);
			world.setBlock(x - 1, y, z, AutoTrains.BasicRailSlave);
			world.setBlock(x + 1, y, z, AutoTrains.BasicRailSlave);
			world.setBlock(x + 2, y, z, AutoTrains.BasicRailSlaveLeft);
			world.setBlock(x + 3, y, z, AutoTrains.ConcreteSlave);
			world.setBlock(x + 4, y, z, AutoTrains.ConcreteSlave);
			world.setBlock(x + 5, y, z, AutoTrains.ConcreteSlave);
			// 7
			world.setBlock(x - 5, y, z - 1, AutoTrains.ConcreteSlave);
			world.setBlock(x - 4, y, z - 1, AutoTrains.ConcreteSlave);
			world.setBlock(x - 3, y, z - 1, AutoTrains.ConcreteSlave);
			world.setBlock(x - 2, y, z - 1, AutoTrains.BasicRailSlaveRight);
			world.setBlock(x - 1, y, z - 1, AutoTrains.BasicRailSlave);
			world.setBlock(x, y, z - 1, AutoTrains.BasicRailSlave);
			world.setBlock(x + 1, y, z - 1, AutoTrains.BasicRailSlave);
			world.setBlock(x + 2, y, z - 1, AutoTrains.BasicRailSlaveLeft);
			world.setBlock(x + 3, y, z - 1, AutoTrains.ConcreteSlave);
			world.setBlock(x + 4, y, z - 1, AutoTrains.ConcreteSlave);
			world.setBlock(x + 5, y, z - 1, AutoTrains.ConcreteSlave);
			// 8
			world.setBlock(x - 5, y, z - 2, AutoTrains.ConcreteSlave);
			world.setBlock(x - 4, y, z - 2, AutoTrains.ConcreteSlave);
			world.setBlock(x - 3, y, z - 2, AutoTrains.ConcreteSlave);
			world.setBlock(x - 2, y, z - 2, AutoTrains.BasicRailSlaveRight);
			world.setBlock(x - 1, y, z - 2, AutoTrains.BasicRailSlave);
			world.setBlock(x, y, z - 2, AutoTrains.BasicRailSlave);
			world.setBlock(x + 1, y, z - 2, AutoTrains.BasicRailSlave);
			world.setBlock(x + 2, y, z - 2, AutoTrains.BasicRailSlaveLeft);
			world.setBlock(x + 3, y, z - 2, AutoTrains.ConcreteSlave);
			world.setBlock(x + 4, y, z - 2, AutoTrains.ConcreteSlave);
			world.setBlock(x + 5, y, z - 2, AutoTrains.ConcreteSlave);
			// 9
			world.setBlock(x - 5, y, z - 3, AutoTrains.ConcreteSlave);
			world.setBlock(x - 4, y, z - 3, AutoTrains.ConcreteSlave);
			world.setBlock(x - 3, y, z - 3, AutoTrains.ConcreteSlave);
			world.setBlock(x - 2, y, z - 3, AutoTrains.BasicRailSlaveRight);
			world.setBlock(x - 1, y, z - 3, AutoTrains.BasicRailSlave);
			world.setBlock(x, y, z - 3, AutoTrains.BasicRailSlave);
			world.setBlock(x + 1, y, z - 3, AutoTrains.BasicRailSlave);
			world.setBlock(x + 2, y, z - 3, AutoTrains.BasicRailSlaveLeft);
			world.setBlock(x + 3, y, z - 3, AutoTrains.ConcreteSlave);
			world.setBlock(x + 4, y, z - 3, AutoTrains.ConcreteSlave);
			world.setBlock(x + 5, y, z - 3, AutoTrains.ConcreteSlave);
			// 10
			world.setBlock(x - 5, y, z - 4, AutoTrains.ConcreteSlave);
			world.setBlock(x - 4, y, z - 4, AutoTrains.ConcreteSlave);
			world.setBlock(x - 3, y, z - 4, AutoTrains.ConcreteSlave);
			world.setBlock(x - 2, y, z - 4, AutoTrains.BasicRailSlaveRight);
			world.setBlock(x - 1, y, z - 4, AutoTrains.BasicRailSlave);
			world.setBlock(x, y, z - 4, AutoTrains.BasicRailSlave);
			world.setBlock(x + 1, y, z - 4, AutoTrains.BasicRailSlave);
			world.setBlock(x + 2, y, z - 4, AutoTrains.BasicRailSlaveLeft);
			world.setBlock(x + 3, y, z - 4, AutoTrains.ConcreteSlave);
			world.setBlock(x + 4, y, z - 4, AutoTrains.ConcreteSlave);
			world.setBlock(x + 5, y, z - 4, AutoTrains.ConcreteSlave);
			// 11
			world.setBlock(x - 5, y, z - 5, AutoTrains.ConcreteSlave);
			world.setBlock(x - 4, y, z - 5, AutoTrains.ConcreteSlave);
			world.setBlock(x - 3, y, z - 5, AutoTrains.ConcreteSlave);
			world.setBlock(x - 2, y, z - 5, AutoTrains.BasicRailSlaveRight);
			world.setBlock(x - 1, y, z - 5, AutoTrains.BasicRailSlave);
			world.setBlock(x, y, z - 5, AutoTrains.BasicRailSlave);
			world.setBlock(x + 1, y, z - 5, AutoTrains.BasicRailSlave);
			world.setBlock(x + 2, y, z - 5, AutoTrains.BasicRailSlaveLeft);
			world.setBlock(x + 3, y, z - 5, AutoTrains.ConcreteSlave);
			world.setBlock(x + 4, y, z - 5, AutoTrains.ConcreteSlave);
			world.setBlock(x + 5, y, z - 5, AutoTrains.ConcreteSlave);
			this.item = this.getItem(world, y, z, x);
		}else{
			normalbreak = false;
			this.player.inventory.addItemStackToInventory(new ItemStack(Item.getItemFromBlock(AutoTrains.BasicRail)));
			world.setBlock(x, y, z, Blocks.air);
		}
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta){
		if(normalbreak == true){
			for (int xCoord = x- 5; xCoord < x + 6; xCoord++){
				for (int yCoord = y; yCoord == y; yCoord++){
					for (int zCoord = z - 5; zCoord < z + 6; zCoord++){
						System.out.println(x + ", " + y + ", " + z);
						Block tile = world.getBlock(xCoord, yCoord, zCoord);
						if(tile == AutoTrains.BasicRailSlave && !(xCoord == x && zCoord == z) || tile == AutoTrains.BasicRailSlaveRight && !(xCoord == x && zCoord == z) || tile == AutoTrains.BasicRailSlaveLeft && !(xCoord == x && zCoord == z) || tile == AutoTrains.ConcreteSlave && !(xCoord == x && zCoord == z)){
							world.setBlock(xCoord, yCoord, zCoord, Blocks.air);
						
						}
					}
				}
			}
		}else{
			normalbreak = true;
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta){
		return new BasicRailTileEntity();
	}
}
