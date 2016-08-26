package com.ultratechx.autotrains;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;

import com.ultratechx.autotrains.blocks.BasicRailBlock;
import com.ultratechx.autotrains.lib.Reference;
import com.ultratechx.autotrains.proxies.ClientProxy;
import com.ultratechx.autotrains.proxies.CommonProxy;
import com.ultratechx.autotrains.tileentities.BasicRailLeftTileEntity;
import com.ultratechx.autotrains.tileentities.BasicRailRightTileEntity;
import com.ultratechx.autotrains.tileentities.BasicRailTileEntity;
import com.ultratechx.autotrains.blocks.BasicRailBlockSlave;
import com.ultratechx.autotrains.blocks.BasicRailBlockSlaveLeft;
import com.ultratechx.autotrains.blocks.BasicRailBlockSlaveRight;

import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(name = Reference.NAME, modid = Reference.MODID, version = Reference.VERSION)
public class AutoTrains
{
	public static Block BasicRail, BasicRailSlave, BasicRailSlaveLeft, BasicRailSlaveRight;
	
	@SidedProxy(clientSide="com.ultratechx.autotrains.proxies.ClientProxy", serverSide="com.ultratechx.autotrains.proxies.CommonProxy")
	public static CommonProxy proxy;
	public static ClientProxy cproxy;
	
    public static Logger LOG = FMLLog.getLogger();
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
    	LOG.info("Automatic Trains Mod is now being loaded");
    	BasicRail = new BasicRailBlock("basicrailblock", Material.anvil);
    	BasicRailSlave = new BasicRailBlockSlave("basicrailblockslave", Material.anvil);
    	BasicRailSlaveLeft = new BasicRailBlockSlaveLeft("basicrailblockslaveleft", Material.anvil);
    	BasicRailSlaveRight = new BasicRailBlockSlaveRight("basicrailblockslaveright", Material.anvil);
    	GameRegistry.registerBlock(BasicRail, "basicrailblock");
    	GameRegistry.registerBlock(BasicRailSlave, "basicrailblockslave");
    	GameRegistry.registerBlock(BasicRailSlaveLeft, "basicrailblockslaveleft");
    	GameRegistry.registerBlock(BasicRailSlaveRight, "basicrailblockslaveright");
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		proxy.registerRenders();
		GameRegistry.registerTileEntity(BasicRailTileEntity.class, "autotrains.rail.basic");
		GameRegistry.registerTileEntity(BasicRailLeftTileEntity.class, "autotrains.rail.slaveleft");
		GameRegistry.registerTileEntity(BasicRailRightTileEntity.class, "autotrains.rail.slaveright");
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
    	
    }
}
