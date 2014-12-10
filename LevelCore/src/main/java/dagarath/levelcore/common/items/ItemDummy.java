package dagarath.levelcore.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dagarath.levelcore.config.LevelCoreReference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

/**
 * Created by dagarath on 2014-12-10.
 */
public class ItemDummy extends Item {

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon(LevelCoreReference.MODID + ":" + "textures/LevelCore");
    }

}
