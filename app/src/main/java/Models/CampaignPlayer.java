package Models;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import DndUtil.DndUtil;

/**
 * Created by maxhe on 15-3-2018.
 */

public class CampaignPlayer implements Parcelable, Serializable{
    private String id;
    private String name;
    private int level;
    private Map<Abilities, PlayerAbility> abilities;

    public CampaignPlayer(){

    }

    public CampaignPlayer(String name, String id){
        setName(name);
        this.id = id;
        setLevel(1);

        abilities = new HashMap<>(5);
        PlayerAbility strength = new PlayerAbility(Abilities.Strength, 10);
        PlayerAbility charisma = new PlayerAbility(Abilities.Charisma, 10);
        PlayerAbility intelligence = new PlayerAbility(Abilities.Intelligence, 10);
        PlayerAbility wisdom = new PlayerAbility(Abilities.Wisdom, 10);
        PlayerAbility dexterity = new PlayerAbility(Abilities.Dexterity, 10);

        abilities.put(Abilities.Strength ,strength);
        abilities.put(Abilities.Charisma, charisma);
        abilities.put(Abilities.Intelligence, intelligence);
        abilities.put(Abilities.Wisdom, wisdom);
        abilities.put(Abilities.Dexterity, dexterity);


    }

    public static final Creator<CampaignPlayer> CREATOR = new Creator<CampaignPlayer>() {
        @Override
        public CampaignPlayer createFromParcel(Parcel in) {
            return new CampaignPlayer(in);
        }

        @Override
        public CampaignPlayer[] newArray(int size) {
            return new CampaignPlayer[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Map<Abilities, PlayerAbility> getAbilities() {
        return abilities;
    }

    public void setAbilities(Map<Abilities, PlayerAbility> abilities) {
        this.abilities = abilities;
    }

    public static Creator<CampaignPlayer> getCREATOR() {
        return CREATOR;
    }

    @Override
    public String toString(){
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeInt(level);

        dest.writeInt(abilities.size());
        for(Map.Entry<Abilities, PlayerAbility> entry : abilities.entrySet()){
            dest.writeInt(entry.getKey().ordinal());
            entry.getValue().writeToParcel(dest, flags);
        }
    }

    protected CampaignPlayer(Parcel in) {
        id = in.readString();
        name = in.readString();
        level = in.readInt();

        int size = in.readInt();
        for(int i = 0; i < size; i++){
            Abilities key = Abilities.values()[in.readInt()];
            PlayerAbility value = PlayerAbility.CREATOR.createFromParcel(in);
            abilities.put(key,value);
        }
    }

    public int calculateSkillValue(Skills skill) {
        PlayerAbility ability = abilities.get(skill.getAbilities());
        int value = ability.getValue();

        Skill playerSkill = ability.getSkills().get(skill);

        if(playerSkill.isProficienct()) {
            int proficiency = DndUtil.calculateProficiency(getLevel());
            value += proficiency;
        }

        return value;
    }
}
