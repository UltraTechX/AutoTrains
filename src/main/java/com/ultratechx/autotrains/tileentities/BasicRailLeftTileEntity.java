package com.ultratechx.autotrains.tileentities;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BasicRailLeftTileEntity extends TileEntity{
	private boolean hasMaster, check;
	public boolean isMaster;
	private int masterX, masterY, masterZ;
	
	@Override
    public void updateEntity() { 
        super.updateEntity();
        if (!worldObj.isRemote) {
            if (hasMaster()) { 
                if (isMaster()) {
                    // Put stuff you want the multiblock to do here!
                }
            } else {
                // Constantly check if structure is formed until it is.
                if (!(checkMultiBlockForm() == 8 || checkMultiBlockForm() == 3 || checkMultiBlockForm() == 5)){
                    setupStructure();
                }
            }
        }
    }
	
	@Override
	public void writeToNBT(NBTTagCompound data){
		super.writeToNBT(data);
		data.setInteger("masterX", masterX);
		data.setInteger("masterY", masterY);
		data.setInteger("masterZ", masterZ);
		data.setBoolean("hasMaster", hasMaster);
		data.setBoolean("isMaster", isMaster);
		if(hasMaster() && isMaster()){
			
		}
	}
	
	private void initialCheck(){
		int i=0;
		for (int x = xCoord - 1; x < xCoord + 3; x++){
			for (int y = yCoord; y < yCoord + 1; y++){
				for (int z = zCoord - 1; z < zCoord + 2; z++){
					TileEntity tile = worldObj.getTileEntity(x, y, z);
					if(tile != null && (tile instanceof BasicRailTileEntity)){
						if(this.isMaster()){
							if(((BasicRailTileEntity)tile).hasMaster()){
								i++;
								//System.out.println(i);
							}
						}else if(!((BasicRailTileEntity)tile).hasMaster()){
							//i++;
							//System.out.println(i);
						}
					}
				}
			}
		}
		if(i == 3 || i == 5 || i == 8){
			check = true;
		}
	}
	
	public void setNeighborAir(){
		for (int x = xCoord - 2; x < xCoord + 3; x++){
			for (int y = yCoord; y < yCoord + 1; y++){
				for (int z = zCoord - 2; z < zCoord + 3; z++){
					TileEntity tile = worldObj.getTileEntity(x, y, z);
					if(tile != null && (tile instanceof BasicRailTileEntity)){
						worldObj.setBlock(x, y, z, Blocks.air);
					}
				}
			}
		}
	}
	
	public void setNeighborUpdate(){
		for (int x = xCoord - 2; x < xCoord + 3; x++){
			for (int y = yCoord; y < yCoord + 1; y++){
				for (int z = zCoord - 2; z < zCoord + 3; z++){
					TileEntity tile = worldObj.getTileEntity(x, y, z);
					if(tile != null && (tile instanceof BasicRailTileEntity)){
						tile.updateEntity();
					}
				}
			}
		}
	}
		
	@Override
	public void readFromNBT(NBTTagCompound data){
		super.readFromNBT(data);
		masterX = data.getInteger("masterX");
		masterY = data.getInteger("masterY");
		masterZ = data.getInteger("masterZ");
		hasMaster = data.getBoolean("hasMaster");
		isMaster = data.getBoolean("isMaster");
		if(hasMaster() && isMaster()){
			
		}
	}
		
	public boolean hasMaster(){
		return hasMaster;
	}
	
	public boolean isMaster(){
		return isMaster;
	}
	
	public int getMasterX(){
		return masterX;
	}
	
	public int getMasterY(){
		return masterY;
	}
	
	public int getMasterZ(){
		return masterZ;
	}
	
	public void setHasMaster(boolean bool){
		hasMaster = bool;
	}
		
	public void setIsMaster(boolean bool){
		isMaster = bool;
	}
	
	public void setMasterCoords(int x, int y, int z){
		masterX = x;
		masterY = y;
		masterZ = z;
	}
	
	public int checkMultiBlockForm(){
		int i=0;
		
		for (int x = xCoord - 5; x < xCoord + 6; x++){
			for (int y = yCoord; y < yCoord + 1; y++){
				for (int z = zCoord - 5; z < zCoord + 6; z++){
					TileEntity tile = worldObj.getTileEntity(x, y, z);
					if(tile != null && (tile instanceof BasicRailLeftTileEntity)){
						if(this.isMaster()){
							if(((BasicRailLeftTileEntity) tile).hasMaster()){
								i++;
								//System.out.println(i);
							}
						}else if(!((BasicRailLeftTileEntity)tile).hasMaster()){
							//i++;
							//System.out.println(i);
						}
					}
				}
			}
		}
		return i;
	}
	
	public void setupStructure(){
		for (int x = xCoord - 5; x < xCoord + 6; x++){
			for (int y = yCoord; y < yCoord + 1; y++){
				for (int z = zCoord - 5; z < zCoord + 6; z++){
					TileEntity tile = worldObj.getTileEntity(x, y, z);
					boolean master = (x == xCoord && y == yCoord && z == zCoord);
					if(tile != null && (tile instanceof BasicRailTileEntity)){
						((BasicRailTileEntity) tile).setMasterCoords(xCoord, yCoord, zCoord);
						((BasicRailTileEntity) tile).setHasMaster(true);
						((BasicRailTileEntity) tile).setIsMaster(master);
					}
				}
			}
		}
	}
	
	public void reset(){
		masterX = 0;
		masterY = 0;
		masterZ = 0;
		hasMaster = false;
		isMaster = false;
	}
	
	public boolean checkForMaster(){
		TileEntity tile = worldObj.getTileEntity(masterX, masterY, masterZ);
		return (tile != null && (tile instanceof BasicRailLeftTileEntity));
	}
	
	public void resetStructure(){
		for (int x = xCoord - 5; x < xCoord + 6; x++){
			for (int y = yCoord; y < yCoord + 1; y++){
				for (int z = zCoord - 5; z < zCoord + 6; z++){
					TileEntity tile = worldObj.getTileEntity(x, y, z);
					if(tile != null && (tile instanceof BasicRailLeftTileEntity)){
						((BasicRailLeftTileEntity) tile).reset();
					}
				}
			}
		}
	}
}
