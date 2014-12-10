package dagarath.levelcore.Core;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dagarath.levelcore.common.properties.ExtendedPropertiesMob;
import dagarath.levelcore.common.properties.ExtendedPropertiesPlayer;
import dagarath.levelcore.LevelCore;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

/**
 * Created by dagarath on 2014-12-06.
 */
public class LevelCoreEventHandler {
    private boolean agilityPotion = false;

    @SubscribeEvent
    public void onEntityConstructing(EntityConstructing event)
    {
        if (event.entity instanceof EntityPlayer && ExtendedPropertiesPlayer.get((EntityPlayer) event.entity) == null) {
            ExtendedPropertiesPlayer.register((EntityPlayer) event.entity);
        }

        if (event.entity instanceof EntityMob && ExtendedPropertiesMob.get((EntityMob) event.entity) == null) {
            ExtendedPropertiesMob.register((EntityMob) event.entity);
        }
    }

    @SubscribeEvent
    public void onEntityUpdate(LivingUpdateEvent event) {
        if(event.entityLiving instanceof EntityPlayer) {
            ExtendedPropertiesPlayer extProps = ExtendedPropertiesPlayer.get((EntityPlayer) event.entity);
            if (event.entityLiving.worldObj.rand.nextInt(20) == 0) {
                extProps.calculateExperienceLevel();
                //System.out.print("LevelCore ExperienceLevel: " + extProps.getExperienceLevel());
            }
            if (event.entityLiving.isPotionActive(LevelCore.agilityPotion)&& agilityPotion == false) {
                if (event.entityLiving.worldObj.rand.nextInt(20) == 0) {
                    extProps.calculateExperienceLevel();
                     extProps.modAgility(1000.0f);
                     agilityPotion = true;
                     //System.out.print("Agility: " + extProps.getAgility());

                }


                if (event.entityLiving.getActivePotionEffect(LevelCore.agilityPotion).getDuration() == 0) {
                    event.entityLiving.removePotionEffect(LevelCore.agilityPotion.id);
                    extProps.modAgility(-1000.0f);
                    agilityPotion = false;
                    return;
                }
            }
        }
    }


    @SubscribeEvent
    public void onLivingUpdateEvent(LivingUpdateEvent event)
    {

    }

}
