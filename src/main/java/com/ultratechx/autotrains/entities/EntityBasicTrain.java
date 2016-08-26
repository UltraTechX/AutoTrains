package com.ultratechx.autotrains.entities;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityBasicTrain extends Entity{

	public int speed;
	public boolean moving;
	public boolean forward;
	
	public EntityBasicTrain(World world) {
		super(world);
	}

	@Override
	protected void entityInit() {
		speed = 0;
		moving = false;
		forward = false;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound NBT) {
		super.readFromNBT(NBT);
		speed = NBT.getInteger("Speed");
		moving = NBT.getBoolean("Moving");
		forward = NBT.getBoolean("Forward");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound NBT) {
		super.writeToNBT(NBT);
		NBT.setInteger("Speed", speed);
		NBT.setBoolean("Moving", moving);
		NBT.setBoolean("Forward", forward);
	}

}
