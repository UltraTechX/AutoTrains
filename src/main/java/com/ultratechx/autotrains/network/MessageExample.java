package com.ultratechx.autotrains.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class MessageExample extends MessageBase<MessageExample>{

	boolean bool;
	
	@Override
	public void fromBytes(ByteBuf buf) {
		bool = buf.readBoolean();
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(true);
		
	}

	@Override
	public void handleClientSide(MessageExample message, EntityPlayer player) {
		// runs when the client gets THIS SPECIFIC packet from the server
		
	}

	@Override
	public void handleServerSide(MessageExample message, EntityPlayer player) {
		// runs when the server get THIS SPECIFIC packet from the client
		
	}
	
}
