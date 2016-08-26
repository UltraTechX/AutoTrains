package com.ultratechx.autotrains.proxies;

import com.ultratechx.autotrains.rendering.tileentities.RenderBasicRail;
import com.ultratechx.autotrains.rendering.tileentities.RenderBasicRailLeft;
import com.ultratechx.autotrains.rendering.tileentities.RenderBasicRailRight;
import com.ultratechx.autotrains.tileentities.BasicRailLeftTileEntity;
import com.ultratechx.autotrains.tileentities.BasicRailRightTileEntity;
import com.ultratechx.autotrains.tileentities.BasicRailTileEntity;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy{

	@Override
	public void registerRenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(BasicRailTileEntity.class, new RenderBasicRail());
		ClientRegistry.bindTileEntitySpecialRenderer(BasicRailLeftTileEntity.class, new RenderBasicRailLeft());
		ClientRegistry.bindTileEntitySpecialRenderer(BasicRailRightTileEntity.class, new RenderBasicRailRight());
	}
}
