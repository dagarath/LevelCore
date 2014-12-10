package dagarath.levelcore.Core.Client;

import api.player.client.ClientPlayerAPI;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import dagarath.levelcore.Core.CommonProxy;
import dagarath.levelcore.config.LevelCoreReference;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by dagarath on 2014-12-08.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event){
        super.preInit(event);
        ClientPlayerAPI.register(LevelCoreReference.MODID, LevelCoreClientPlayerBase.class);
    }

    @Override
    public void initSounds()
    {
    }

    @Override
    public void initRenderers()
    {

    }

    @Override
    public void initGui()
    {

    }

    @Override
    public void initCapes()
    {

    }

    @Override
    public EntityPlayer getPlayerFromMessageContext(MessageContext ctx)
    {
        switch(ctx.side)
        {
            case CLIENT:
            {
                EntityPlayer entityClientPlayerMP = Minecraft.getMinecraft().thePlayer;
                return entityClientPlayerMP;
            }
            case SERVER:
            {
                EntityPlayer entityPlayerMP = ctx.getServerHandler().playerEntity;
                return entityPlayerMP;
            }
            default:
                assert false : "Invalid side in TestMsgHandler: " + ctx.side;
        }
        return null;
    }
}
