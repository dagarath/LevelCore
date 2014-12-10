package dagarath.levelcore.common.properties;

import dagarath.levelcore.Core.Skills.Skills;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import java.util.List;


/**
 * Created by dagarath on 2014-12-10.
 */
public class ExtendedPropertiesMob implements IExtendedEntityProperties {

    public final static String EXT_PROP_NAME = "LevelCoreCreature";

    public EntityMob entity;
    private int coreExperienceLevel;
    private float coreStrength;
    private float coreEndurance;
    private float coreDexterity;
    private float coreAgility;
    private float corePerception;
    private float coreWillpower;
    private float coreIntelligence;
    private float coreLuck;
    private int strengthModifier;

    private int strengthX;
    private int strengthZ;
    private Skills skills;

    public ExtendedPropertiesMob(EntityMob entity)
    {
        World mcWorld = Minecraft.getMinecraft().theWorld;
        this.entity = entity;

        int spawnX = mcWorld.getSpawnPoint().posX;
        int spawnZ = mcWorld.getSpawnPoint().posZ;

        if(entity.posX > spawnX && entity.posX > 0 && spawnX > 0){
            strengthX = (int)entity.posX - spawnX;
        }
        else if (entity.posX < spawnX && entity.posX < 0 && spawnX < 0){
            strengthX = spawnX - (int)entity.posX;
        }


        if(entity.posZ > spawnZ && entity.posZ > 0 && spawnZ > 0){
            strengthZ = (int)entity.posX - spawnX;
        }
        else if (entity.posZ < spawnZ && entity.posZ < 0 && spawnZ < 0){
            strengthZ = spawnZ - (int)entity.posZ;
        }

        strengthModifier = strengthX + strengthZ + 1;


        this.coreExperienceLevel = strengthModifier;
        this.coreStrength = 5.0f * strengthModifier;
        this.coreEndurance = 5.0f * strengthModifier;
        this.coreDexterity = 5.0f * strengthModifier;
        this.coreAgility = 5.0f * strengthModifier;
        this.corePerception = 5.0f * strengthModifier;
        this.coreWillpower = 5.0f * strengthModifier;
        this.coreIntelligence = 5.0f * strengthModifier;
        this.coreLuck = 5.0f * strengthModifier;

    }

    @Override
    public void init(Entity entity, World world) {

    }

    public static final void register(EntityMob entity)
    {
        entity.registerExtendedProperties(ExtendedPropertiesMob.EXT_PROP_NAME, new ExtendedPropertiesMob(entity));
    }

    public static final ExtendedPropertiesPlayer get(EntityMob entity)
    {
        return (ExtendedPropertiesPlayer) entity.getExtendedProperties(EXT_PROP_NAME);
    }

    //<editor-fold desc="NBT Data">
    @Override
    public void saveNBTData(NBTTagCompound compound) {

        NBTTagCompound extendedProperties = new NBTTagCompound();
        compound.setTag(EXT_PROP_NAME, extendedProperties);
        extendedProperties.setFloat("experienceLevel", this.coreExperienceLevel);
        extendedProperties.setFloat("strength", this.coreStrength);
        extendedProperties.setFloat("endurance", this.coreEndurance);
        extendedProperties.setFloat("dexterity", this.coreDexterity);
        extendedProperties.setFloat("agility", this.coreAgility);
        extendedProperties.setFloat("perception", this.corePerception);
        extendedProperties.setFloat("willpower", this.coreWillpower);
        extendedProperties.setFloat("intelligence", this.coreIntelligence);
        extendedProperties.setFloat("luck", this.coreLuck);

    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        NBTTagCompound extendedProperties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
        this.coreExperienceLevel = extendedProperties.getInteger("experienceLevel");
        this.coreStrength = extendedProperties.getFloat("strength");
        this.coreEndurance = extendedProperties.getFloat("endurance");
        this.coreDexterity = extendedProperties.getFloat("dexterity");
        this.coreAgility = extendedProperties.getFloat("agility");
        this.corePerception = extendedProperties.getFloat("perception");
        this.coreWillpower = extendedProperties.getFloat("willpower");
        this.coreIntelligence = extendedProperties.getFloat("intelligence");
        this.coreLuck = extendedProperties.getFloat("luck");
    }

    //</editor-fold>

    //<editor-fold desc="Setters and Getters">

    //Return experience level.
    public int getExperienceLevel (){
        return coreExperienceLevel;
    }

    //Return experience level.
    public void setExperienceLevel (int i){
        coreExperienceLevel = i;
    }

    //Return strength level.
    public float getStrength (){
        return coreStrength;
    }

    //Update strength level.
    public void setStrength (float f) {
        coreStrength = f;
    }

    //Return endurance level.
    public float getEndurance() {
        return coreEndurance;
    }

    //Update endurance level.
    public void setEndurance(float f) {
        coreEndurance = f;
    }

    //Return dexterity level.
    public float getDexterity() {
        return coreDexterity;
    }

    //Update dexterity level.
    public void setDexterity(float f) {
        coreDexterity = f;
    }

    //Return agility level.
    public float getAgility() {
        return coreAgility;
    }

    //Update agility level.
    public void setAgility(float f) {
        coreAgility = f;
    }

    //Return perception level.
    public float getPerception() {
        return corePerception;
    }

    //Update perception level.
    public void setPerception(float f) {
        corePerception = f;
    }

    //Get willpower level.
    public float getWillpower() {
        return coreWillpower;
    }

    //Update willpower level.
    public void setWillpower(float f) {
        coreWillpower = f;
    }

    //Get intelligence level.
    public float getIntelligence() {
        return coreIntelligence;
    }

    //Update intelligence level.
    public void setIntelligence(float f) {
        coreIntelligence = f;
    }

    //Get luck level.
    public float getLuck() {
        return coreLuck;
    }

    //Update luck level.
    public void setLuck(float f) {
        coreLuck = f;
    }

    //</editor-fold>

}
