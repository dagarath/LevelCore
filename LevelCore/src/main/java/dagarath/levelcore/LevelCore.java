package dagarath.levelcore;


import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import dagarath.levelcore.Core.CommonProxy;
import dagarath.levelcore.Core.Network.PacketHandler;
import dagarath.levelcore.common.blocks.BlockExpDepository;
import dagarath.levelcore.common.items.ItemDummy;
import dagarath.levelcore.config.LevelCoreReference;
import dagarath.levelcore.Core.LevelCoreEventHandler;
import dagarath.levelcore.common.items.ItemBuffStat;
import dagarath.levelcore.common.potions.BuffPotion;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


@Mod(modid = LevelCoreReference.MODID, version = LevelCoreReference.VERSION, name = LevelCoreReference.NAME, dependencies = LevelCoreReference.DEPENDS)
public class LevelCore {

    @Mod.Instance(LevelCoreReference.MODID)
    public static LevelCore instance;

    @SidedProxy(clientSide = "dagarath.levelcore.Core.Client.ClientProxy", serverSide = "dagarath.levelcore.Core.CommonProxy")
    public static CommonProxy proxy;

    public static Item dummyItem = new ItemDummy();
    /** The creative tab for the mod */
    public static CreativeTabs tabLevelCore = new CreativeTabs("tabLevelCore")
    {
        @Override
        public Item getTabIconItem()
        {

            return dummyItem;
        }
    };

    //Potions
    public static Potion strengthPotion;
    public static Potion endurancePotion;
    public static Potion dexterityPotion;
    public static Potion agilityPotion;
    public static Potion perceptionPotion;
    public static Potion willpowerPotion;
    public static Potion intelligencePotion;
    public static Potion luckPotion;

    //Items
    public static Item itemStrengthPotion;
    public static Item itemEndurancePotion;
    public static Item itemDexterityPotion;
    public static Item itemAgilityPotion;
    public static Item itemPerceptionPotion;
    public static Item itemWillpowerPotion;
    public static Item itemIntelligencePotion;
    public static Item itemLuckPotion;

    //Blocks
    public static Block blockExpDepository;
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        PacketHandler.init();
        proxy.preInit(event);

        Potion[] potionTypes = null;

        for (Field f : Potion.class.getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) {
                    Field modfield = Field.class.getDeclaredField("modifiers");
                    modfield.setAccessible(true);
                    modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

                    potionTypes = (Potion[])f.get(null);
                    final Potion[] newPotionTypes = new Potion[256];
                    System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
                    f.set(null, newPotionTypes);
                }
            }
            catch (Exception e) {
                System.err.println("Severe error, please report this to the mod author:");
                System.err.println(e);
            }
        }

        MinecraftForge.EVENT_BUS.register(new LevelCoreEventHandler());
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {

    }

    @EventHandler
    public void load(FMLInitializationEvent event)
    {
        //Potions
        strengthPotion = (new BuffPotion(32, false, 0)).setIconIndex(0, 0).setPotionName("potion.strengthPotion");
        endurancePotion = (new BuffPotion(33, false, 0)).setIconIndex(1, 0).setPotionName("potion.endurancePotion");
        dexterityPotion = (new BuffPotion(34, false, 0)).setIconIndex(2, 0).setPotionName("potion.dexterityPotion");
        agilityPotion = (new BuffPotion(35, false, 0)).setIconIndex(3, 0).setPotionName("potion.agilityPotion");
        perceptionPotion = (new BuffPotion(36, false, 0)).setIconIndex(4, 0).setPotionName("potion.perceptionPotion");
        willpowerPotion = (new BuffPotion(37, false, 0)).setIconIndex(5, 0).setPotionName("potion.willpowerPotion");
        intelligencePotion = (new BuffPotion(38, false, 0)).setIconIndex(6, 0).setPotionName("potion.intelligencePotion");
        luckPotion = (new BuffPotion(39, false, 0)).setIconIndex(7, 0).setPotionName("potion.luckPotion");


        //Enchantments

        //Items
        GameRegistry.registerItem(itemStrengthPotion = new ItemBuffStat("strengthPotion", 0, 0f, false,
                new PotionEffect(strengthPotion.id, 6000, 0))
                .setAlwaysEdible(), "strengthPotion");
        GameRegistry.registerItem(itemEndurancePotion = new ItemBuffStat("endurancePotion", 0, 0f, false,
                new PotionEffect(endurancePotion.id, 6000, 0))
                .setAlwaysEdible(), "endurancePotion");
        GameRegistry.registerItem(itemDexterityPotion = new ItemBuffStat("dexterityPotion", 0, 0f, false,
                new PotionEffect(dexterityPotion.id, 6000, 0))
                .setAlwaysEdible(), "dexterityPotion");
        GameRegistry.registerItem(itemAgilityPotion = new ItemBuffStat("agilityPotion", 0, 0f, false,
                new PotionEffect(agilityPotion.id, 200, 0))
                .setAlwaysEdible(), "agilityPotion");
        GameRegistry.registerItem(itemPerceptionPotion = new ItemBuffStat("perceptionPotion", 0, 0f, false,
                new PotionEffect(perceptionPotion.id, 6000, 0))
                .setAlwaysEdible(), "perceptionPotion");
        GameRegistry.registerItem(itemWillpowerPotion = new ItemBuffStat("willpowerPotion", 0, 0f, false,
                new PotionEffect(willpowerPotion.id, 6000, 0))
                .setAlwaysEdible(), "willpowerPotion");
        GameRegistry.registerItem(itemIntelligencePotion = new ItemBuffStat("intelligencePotion", 0, 0f, false,
                new PotionEffect(intelligencePotion.id, 6000, 0))
                .setAlwaysEdible(), "intelligencePotion");
        GameRegistry.registerItem(itemLuckPotion = new ItemBuffStat("luckPotion", 0, 0f, false,
                new PotionEffect(luckPotion.id, 200, 0))
                .setAlwaysEdible(), "luckPotion");

                //Blocks
        GameRegistry.registerBlock(blockExpDepository = new BlockExpDepository(Material.ground), "expDepository");
                //Entities
    }
}
