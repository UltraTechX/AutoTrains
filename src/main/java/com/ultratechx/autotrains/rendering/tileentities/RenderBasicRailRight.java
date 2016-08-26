package com.ultratechx.autotrains.rendering.tileentities;

import org.lwjgl.opengl.GL11;

import com.ultratechx.autotrains.models.ModelBasicRail;
import com.ultratechx.autotrains.models.ModelBasicRailRight;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderBasicRailRight extends TileEntitySpecialRenderer{

	ModelBasicRailRight model;
	private ResourceLocation texture = new ResourceLocation("autotrains:textures/blocks/rail_normal_right.png");
	
	
	public RenderBasicRailRight(){
		model = new ModelBasicRailRight();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float scale) {
		// TODO Auto-generated method stub
		GL11.glPushMatrix();
		GL11.glTranslated(x+0.5, y+0.5, z+0.5);
		GL11.glRotated(180, 0, 0, 1);
		this.bindTexture(texture);
		this.model.render((Entity)null, 0, -0.1f, 0, 0, 0, 0.0625f);
		GL11.glPopMatrix();
	}

}
