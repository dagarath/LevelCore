package dagarath.levelcore.common.properties;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

/**
 * Created by dagarath on 2014-12-08.
 */
public class ExtendedPropertiesPlayer implements IExtendedEntityProperties {

    public final static String EXT_PROP_NAME = "LevelCorePlayer";

    public final EntityPlayer player;
    private float coreExperience;
    private int coreExperienceLevel;
    private float coreStrength;
    private float coreEndurance;
    private float coreDexterity;
    private float coreAgility;
    private float corePerception;
    private float coreWillpower;
    private float coreIntelligence;
    private float coreLuck;
    private float modStrength;
    private float modEndurance;
    private float modDexterity;
    private float modAgility;
    private float modPerception;
    private float modWillpower;
    private float modIntelligence;
    private float modLuck;

    public ExtendedPropertiesPlayer(EntityPlayer player)
    {
        this.player = player;
        this.coreExperience = 0.0f;
        this.coreExperienceLevel = 1;
        this.coreStrength = 5.0f;
        this.coreEndurance = 5.0f;
        this.coreDexterity = 5.0f;
        this.coreAgility = 5.0f;
        this.corePerception = 5.0f;
        this.coreWillpower = 5.0f;
        this.coreIntelligence = 5.0f;
        this.coreLuck = 5.0f;
        this.modStrength = 0f;
    }

    @Override
    public void init(Entity entity, World world) {

    }

    public static final void register(EntityPlayer player)
    {
        player.registerExtendedProperties(ExtendedPropertiesPlayer.EXT_PROP_NAME, new ExtendedPropertiesPlayer(player));
    }

    public static final ExtendedPropertiesPlayer get(EntityPlayer player)
    {
        return (ExtendedPropertiesPlayer) player.getExtendedProperties(EXT_PROP_NAME);
    }

    //<editor-fold desc="NBT Data">
    @Override
    public void saveNBTData(NBTTagCompound compound) {

        NBTTagCompound extendedProperties = new NBTTagCompound();
        compound.setTag(EXT_PROP_NAME, extendedProperties);
        extendedProperties.setFloat("experience", this.coreExperience);
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
        this.coreExperience = extendedProperties.getFloat("experience");
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

    //Return total experience.
    public float getExperience (){
        return coreExperience;
    }

    //Update total experience.
    public void setExperience (float f) {
        coreExperience = f;
    }

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
        return coreAgility + modAgility;
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

    //Set strength modifier.
    public void modStrength(float f) {
        this.modStrength += f;

    }
    //Set endurance modifier.
    public void modEndurance(float f) {
        this.modEndurance += f;

    }

    //Set
    public void modDexterity(float f) {
        this.modDexterity += f;

    }
    public void modAgility(float f) {
        this.modAgility += f;

    }


    //</editor-fold>

    public void dumpExperience()
    {
        this.coreExperience += this.player.experienceTotal;
        this.player.experience = 0;
        this.player.experienceLevel = 0;
        this.player.experienceTotal = 0;
        calculateExperienceLevel();
    }

    public void calculateExperienceLevel()
    {
        if(Math.round(this.coreExperience / (15 * this.coreExperienceLevel)) > (this.coreExperienceLevel - 1))
        {
            this.coreExperienceLevel += 1;
        }


    }


}
