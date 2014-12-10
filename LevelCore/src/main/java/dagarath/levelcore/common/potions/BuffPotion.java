package dagarath.levelcore.common.potions;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

/**
 * Created by dagarath on 2014-12-08.
 */


public class BuffPotion extends Potion {

    private static final ResourceLocation resourceLocation = new ResourceLocation("levelcore:textures/potions/BuffEffects.png");


    public BuffPotion(int par1, boolean par2, int par3) {
        super(par1, par2, par3);
    }



    public Potion setIconIndex(int par1, int par2) {
        super.setIconIndex(par1, par2);
        return this;
    }



    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasStatusIcon()
    {

        Minecraft.getMinecraft().renderEngine.bindTexture(resourceLocation);
        return true;
    }
}
