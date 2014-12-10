package dagarath.levelcore.Core.Skills;

/**
 * Created by dagarath on 2014-12-10.
 */
public class Skills {
public enum Enum {
    MELEEATTACK("Melee Attack", "Strength", ""),
    MELEEDEFENSE("Melee Defense", "Strength", ""),
    RANGEDEFENSE("Melee Attack", "Strength", ""),
    MAGICDEFENSE("Melee Attack", "Strength", ""),
    SUPPORT("Melee Attack", "Strength", ""),
    RUN(" ", "", ""),
    JUMP(" ", "", ""),
    SNEAK(" ", "", ""),
    EXCAVATION(" ", "", ""),
    BREWING(" ", "", ""),
    CRAFTING(" ", "", ""),
    BUILDING(" ", "", ""),
    MERCANTILISM(" ", "", ""),
    TECHNOLOGY(" ", "", "");

    private String name;
    private String modifier1;
    private String modifier2;



    Enum (String name, String mod1, String mod2)
    {
        this.name = name;
        this.modifier1 = mod1;
        this.modifier2 =  mod2;
    }

}
}

