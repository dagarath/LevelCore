package dagarath.levelcore.Core.Client;

import api.player.client.ClientPlayerAPI;
import api.player.client.ClientPlayerBase;
import dagarath.levelcore.common.properties.ExtendedPropertiesPlayer;
import net.minecraft.client.entity.EntityPlayerSP;

/**
 * Created by dagarath on 2014-12-08.
 */
public class LevelCoreClientPlayerBase extends ClientPlayerBase
{
    private float baseJump = 0.4f;

    public LevelCoreClientPlayerBase(ClientPlayerAPI playerAPI)
    {
        super(playerAPI);
    }

    public void move() {

    }

    @Override
    public void jump() {
        ExtendedPropertiesPlayer extendedProperties = ExtendedPropertiesPlayer.get(player);
        float jumpMod = extendedProperties.getAgility() / 1000;
        if(player instanceof EntityPlayerSP) {
            if (player.motionX > 0) player.motionX += jumpMod;
            if (player.motionX < 0) player.motionX -= jumpMod;
            if (player.motionZ > 0) player.motionZ += jumpMod;
            if (player.motionZ < 0) player.motionZ -= jumpMod;

            player.motionY = baseJump + jumpMod;
        }


        //System.out.print("Jump parameters X:" + player.motionX + " Y:" + player.motionY + " Z:" + player.motionZ + " Jumpmod: " + jumpMod);
    }

    @Override
    public void fall(float paramFloat)
    {
        ExtendedPropertiesPlayer extendedProperties = ExtendedPropertiesPlayer.get(player);
        if (player instanceof EntityPlayerSP)
        {
            player.fallDistance = player.fallDistance - (extendedProperties.getAgility() / 100);
        }

        System.out.print("Fall Distance: " + player.fallDistance);
    }

    public void calculateJump () {

    }



}
