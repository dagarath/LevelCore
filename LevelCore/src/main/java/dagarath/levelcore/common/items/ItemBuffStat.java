package dagarath.levelcore.common.items;

import dagarath.levelcore.LevelCore;
import dagarath.levelcore.config.LevelCoreReference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * Created by dagarath on 2014-12-08.
 */
public class ItemBuffStat extends ItemFood {
    private PotionEffect[] effects;

    public ItemBuffStat(String unlocalizedName, int healAmount, float saturationModifier, boolean wolvesFavorite, PotionEffect... effects) {
        super(healAmount, saturationModifier, wolvesFavorite);
        this.setUnlocalizedName(unlocalizedName);
        System.out.print(unlocalizedName);
        this.setTextureName(LevelCoreReference.MODID + ":" + "powerPill");
        this.setCreativeTab(LevelCore.tabLevelCore);
        this.effects = effects;
        setHasSubtypes(true);
        setMaxDamage(0);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
        super.onFoodEaten(stack, world, player);

        for (int i = 0; i < effects.length; i ++) {
            if (!world.isRemote && effects[i] != null && effects[i].getPotionID() > 0)
                player.addPotionEffect(new PotionEffect(this.effects[i].getPotionID(), this.effects[i].getDuration(), this.effects[i].getAmplifier(), this.effects[i].getIsAmbient()));
        }
    }

}
