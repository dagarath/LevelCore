package dagarath.levelcore.Core.Network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

/**
 * Created by dagarath on 2014-12-08.
 */
public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("levelcore");

    public static void init()
    {
        INSTANCE.registerMessage(MessageSyncLevelCore.class, MessageSyncLevelCore.class, 0, Side.CLIENT);
    }
}
