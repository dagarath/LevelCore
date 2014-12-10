package dagarath.levelcore.Core;

import api.player.server.ServerPlayerAPI;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import dagarath.levelcore.Core.Network.LevelCoreServerPlayerBase;
import dagarath.levelcore.common.properties.ExtendedPropertiesPlayer;
import dagarath.levelcore.config.LevelCoreReference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dagarath on 2014-12-08.
 */
public class CommonProxy {

    private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();

    public void preInit(FMLPreInitializationEvent event)
    {
        ServerPlayerAPI.register(LevelCoreReference.MODID, LevelCoreServerPlayerBase.class);
    }

    public void initSounds()
    {

    }


    public void initRenderers()
    {

    }


    public void initGui()
    {

    }



    public void initCapes()
    {

    }

    private static final String getSaveKey(EntityPlayer player)
    {
        return player.getCommandSenderName() + ":" + ExtendedPropertiesPlayer.EXT_PROP_NAME;
    }
    public static void saveProxyData(EntityPlayer player)
    {
        ExtendedPropertiesPlayer playerData = ExtendedPropertiesPlayer.get(player);
        NBTTagCompound savedData = new NBTTagCompound();

        playerData.saveNBTData(savedData);
        CommonProxy.storeEntityData(getSaveKey(player), savedData);
    }

    public static void loadProxyData(EntityPlayer player)
    {
        ExtendedPropertiesPlayer playerData = ExtendedPropertiesPlayer.get(player);
        NBTTagCompound savedData = CommonProxy.getEntityData(getSaveKey(player));

        if(savedData != null)
        {
            playerData.loadNBTData(savedData);
        }
    }

    public static void storeEntityData(String name, NBTTagCompound compound)
    {
        extendedEntityData.put(name, compound);
    }

    public static NBTTagCompound getEntityData(String name)
    {
        return extendedEntityData.remove(name);
    }

    public EntityPlayer getPlayerFromMessageContext(MessageContext ctx)
    {
        switch(ctx.side)
        {
            case CLIENT:
            {
                assert false : "Message for CLIENT received on dedicated server";
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
