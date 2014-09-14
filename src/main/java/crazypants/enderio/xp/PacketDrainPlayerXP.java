package crazypants.enderio.xp;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import crazypants.enderio.network.MessageTileEntity;
import crazypants.enderio.network.PacketHandler;

public class PacketDrainPlayerXP extends MessageTileEntity<TileEntity> implements IMessageHandler<PacketDrainPlayerXP, IMessage> {

  private static boolean isRegistered = false;
  
  public static void register() {
    if(!isRegistered) {
      PacketHandler.INSTANCE.registerMessage(PacketDrainPlayerXP.class, PacketDrainPlayerXP.class, PacketHandler.nextID(), Side.SERVER);
      isRegistered = true;
    }
  }
  
  
  int targetLevel;
  
  public PacketDrainPlayerXP() {
  }

  public PacketDrainPlayerXP(TileEntity tile, int targetLevel) {
    super(tile);
    this.targetLevel = targetLevel;
  }

  @Override
  public void toBytes(ByteBuf buf) {
    super.toBytes(buf);
    buf.writeShort((short)targetLevel);
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    super.fromBytes(buf);
    targetLevel = buf.readShort();
  }

  @Override
  public IMessage onMessage(PacketDrainPlayerXP message, MessageContext ctx) {
    EntityPlayer player = ctx.getServerHandler().playerEntity;
    TileEntity tile = message.getTileEntity(player.worldObj);
    if (tile instanceof IHaveExperience) {      
      IHaveExperience xpTile = (IHaveExperience)tile;           
      xpTile.getContainer().drainPlayerXpToReachLevel(player, message.targetLevel);      
    }
    return null;
  }

}
