package Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by maxhe on 15-3-2018.
 */

public class Skill implements Serializable {
    private String name;
    private int value;
    private Ability ability;
    private boolean proficiancy;

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

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isProficiancy() {
        return proficiancy;
    }

    public void setProficiancy(boolean proficiancy) {
        this.proficiancy = proficiancy;
    }

    @Override
    public String toString(){
        return name + " : " + value;
    }
}


