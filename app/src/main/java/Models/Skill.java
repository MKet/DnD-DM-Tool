package Models;

/**
 * Created by maxhe on 15-3-2018.
 */

public class Skill {
    private String name;
    private int value;
    private Ability ability;

    public Skill() {
    }

    public Skill(String name, int value, Ability ability) {
        this.name = name;
        this.value = value;
        this.ability = ability;
    }

    public String getName() {
        return name;
    }


    public int getValue()
    {
        return value;
    }

    public Ability getAbility() {
        return ability;
    }
}


