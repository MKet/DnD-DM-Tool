package Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import DndUtil.DndUtil;

/**
 * Created by maxhe on 15-3-2018.
 */

public class Skill implements Serializable {
    private String name;
    private int playerLevel;
    private AbilityValueWrapper ability;
    private boolean proficiency = false;

    public Skill(String name, int playerLevel, AbilityValueWrapper ability) {
        this.name = name;
        this.playerLevel = playerLevel;
        this.ability = ability;
    }

    public String getName() {
        return name;
    }


    public int getValue()
    {
        return DndUtil.ScoreToModifier(ability.getValue()) +
                ((proficiency)? DndUtil.calculateProficiency(playerLevel) : 0);
    }

    public AbilityValueWrapper getAbility() {
        return ability;
    }

    public boolean hasProficiency() {
        return proficiency;
    }

    public void setProficiency(boolean proficiency) {
        this.proficiency = proficiency;
    }

    public void toggleProficiency() {
        proficiency = !proficiency;
    }

    @Override
    public String toString(){
        return name + " : " + getValue();
    }
}


