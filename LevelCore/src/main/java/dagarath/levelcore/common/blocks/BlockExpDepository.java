package dagarath.levelcore.common.blocks;

import dagarath.levelcore.common.properties.ExtendedPropertiesPlayer;
import dagarath.levelcore.LevelCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by dagarath on 2014-12-10.
 */
public class BlockExpDepository extends Block{

    public BlockExpDepository(Material mat) {
        super(mat);
        this.setBlockName("expDepository");
        this.setCreativeTab(LevelCore.tabLevelCore);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        ExtendedPropertiesPlayer extProps = ExtendedPropertiesPlayer.get((EntityPlayer) player);
        extProps.dumpExperience();
        return false;
    }
}
