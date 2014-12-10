package dagarath.levelcore.Core.Network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import dagarath.levelcore.common.properties.ExtendedPropertiesPlayer;
import dagarath.levelcore.LevelCore;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by dagarath on 2014-12-08.
 */
public class MessageSyncLevelCore implements IMessage, IMessageHandler<MessageSyncLevelCore, IMessage> {

    public float currentExperience, currentStrength, currentEndurance, currentDexterity, currentAgility, currentPerception, currentWillpower, currentIntelligence, currentLuck;
    public int currentExperienceLevel;

    public MessageSyncLevelCore()
    {
    }

    public MessageSyncLevelCore(EntityPlayer p)
    {
        ExtendedPropertiesPlayer extendedProperties = ExtendedPropertiesPlayer.get(p);

        currentExperience = extendedProperties.getExperience();
        currentExperienceLevel = extendedProperties.getExperienceLevel();
        currentStrength = extendedProperties.getStrength();
        currentEndurance = extendedProperties.getEndurance();
        currentDexterity = extendedProperties.getDexterity();
        currentAgility = extendedProperties.getAgility();
        currentPerception = extendedProperties.getPerception();
        currentWillpower = extendedProperties.getWillpower();
        currentIntelligence = extendedProperties.getIntelligence();
        currentLuck = extendedProperties.getLuck();

    }

    @Override
    public void fromBytes(ByteBuf buf) {
        currentExperience = buf.readFloat();
        currentExperienceLevel = buf.readInt();
        currentStrength = buf.readFloat();
        currentEndurance = buf.readFloat();
        currentDexterity = buf.readFloat();
        currentAgility = buf.readFloat();
        currentPerception = buf.readFloat();
        currentWillpower = buf.readFloat();
        currentIntelligence = buf.readFloat();
        currentLuck = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeFloat(currentExperience);
        buf.writeInt(currentExperienceLevel);
        buf.writeFloat(currentStrength);
        buf.writeFloat(currentEndurance);
        buf.writeFloat(currentDexterity);
        buf.writeFloat(currentAgility);
        buf.writeFloat(currentPerception);
        buf.writeFloat(currentWillpower);
        buf.writeFloat(currentIntelligence);
        buf.writeFloat(currentLuck);
    }

    @Override
    public IMessage onMessage(MessageSyncLevelCore message, MessageContext ctx)
    {
        EntityPlayer player = LevelCore.proxy.getPlayerFromMessageContext(ctx);
        if(ctx.side == Side.CLIENT)
        {
            ExtendedPropertiesPlayer extendedProperties = ExtendedPropertiesPlayer.get(player);
            if(player != null)
            {
                if(extendedProperties != null)
                {
                    extendedProperties.setExperience(currentExperience);
                    extendedProperties.setExperienceLevel(currentExperienceLevel);
                    extendedProperties.setStrength(currentStrength);
                    extendedProperties.setEndurance(currentEndurance);
                    extendedProperties.setDexterity(currentDexterity);
                    extendedProperties.setAgility(currentAgility);
                    extendedProperties.setPerception(currentPerception);
                    extendedProperties.setWillpower(currentWillpower);
                    extendedProperties.setIntelligence(currentIntelligence);
                    extendedProperties.setLuck(currentLuck);
                }
            }
        }
        return null;
    }
}
