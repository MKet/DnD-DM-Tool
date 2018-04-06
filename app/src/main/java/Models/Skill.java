package Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import DndUtil.DndUtil;

/**
 * Created by maxhe on 15-3-2018.
 */

public class Skill implements Parcelable {
    private String name;
    private int playerLevel;
    private AbilityValueWrapper ability;
    private boolean proficiency = false;


    public static Creator<Skill> getCREATOR() {
        return CREATOR;
    }

    protected Skill(Parcel in) {
        name = in.readString();
        playerLevel = in.readInt();
        proficiency = in.readByte() != 0;
    }

    public static final Creator<Skill> CREATOR = new Creator<Skill>() {
        @Override
        public Skill createFromParcel(Parcel in) {
            return new Skill(in);
        }

        @Override
        public Skill[] newArray(int size) {
            return new Skill[size];
        }
    };

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }

    public void setAbility(AbilityValueWrapper ability) {
        this.ability = ability;
    }

    public boolean isProficiency() {
        return proficiency;
    }

    public Skill(){

    }

    public Skill(String name, int playerLevel, AbilityValueWrapper ability) {
        this.name = name;
        this.playerLevel = playerLevel;
        this.ability = ability;
    }

    public String getName() {
        return name;
    }


    public int calculateValue()
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
        return name + " : " + calculateValue();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(playerLevel);
        dest.writeByte((byte) (proficiency ? 1 : 0));
    }
}


