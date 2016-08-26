package com.ultratechx.autotrains.network;

import com.ultratechx.autotrains.lib.Reference;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class NetworkHandler {
	private static SimpleNetworkWrapper INSTANCE;
	//send packets using template code : NetworkHandler.sendToServer(new MessageExample());
	public static void init(){
		INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
		
		INSTANCE.registerMessage(MessageExample.class, MessageExample.class, 0, Side.SERVER); // could also be side.client if it should be sent to the client
	}
}
