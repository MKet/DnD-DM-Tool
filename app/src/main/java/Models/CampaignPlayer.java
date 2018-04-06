package Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DndUtil.DndUtil;

/**
 * Created by maxhe on 15-3-2018.
 */

public class CampaignPlayer implements Parcelable{
    private String id;
    private List<Skill> skillList;
    private String name;
    private int level;
    private List<AbilityValueWrapper> abilities;

    public CampaignPlayer(){

    }

    public CampaignPlayer(String name, String id){
        setName(name);
        this.id = id;
        setLevel(1);

        abilities = new ArrayList<>(5);
        AbilityValueWrapper strength = new AbilityValueWrapper(Ability.Strength,DndUtil.calculateProficiency(getLevel()));
        AbilityValueWrapper charisma = new AbilityValueWrapper(Ability.Charisma,DndUtil.calculateProficiency(getLevel()));
        AbilityValueWrapper intelligence = new AbilityValueWrapper(Ability.Intelligence,DndUtil.calculateProficiency(getLevel()));
        AbilityValueWrapper wisdom = new AbilityValueWrapper(Ability.Wisdom,DndUtil.calculateProficiency(getLevel()));
        AbilityValueWrapper dexterity = new AbilityValueWrapper(Ability.Dexterity,DndUtil.calculateProficiency(getLevel()));

        abilities.add(strength);
        abilities.add(charisma);
        abilities.add(intelligence);
        abilities.add(wisdom);
        abilities.add(dexterity);

        skillList = new ArrayList<>(18);
        skillList.add(new Skill("Athletics",level, strength));
        skillList.add(new Skill("Acrobatics",level, dexterity));
        skillList.add(new Skill("Sleight of Hand",level, dexterity));
        skillList.add(new Skill("Stealth",level, dexterity));
        skillList.add(new Skill("Arcana",level, intelligence));
        skillList.add(new Skill("History",level, intelligence));
        skillList.add(new Skill("Investigation",level, intelligence));
        skillList.add(new Skill("Nature",level, intelligence));
        skillList.add(new Skill("Religion",level, intelligence));
        skillList.add(new Skill("Animal Handling",level, wisdom));
        skillList.add(new Skill("Insight",level, wisdom));
        skillList.add(new Skill("Medicine",level, wisdom));
        skillList.add(new Skill("Perception",level, wisdom));
        skillList.add(new Skill("Survival",level, wisdom));
        skillList.add(new Skill("Deception",level, charisma));
        skillList.add(new Skill("Intimidation",level, charisma));
        skillList.add(new Skill("Performance",level, charisma));
        skillList.add(new Skill("Persuasion",level, charisma));

        setSkillList(skillList);

    }


    protected CampaignPlayer(Parcel in) {
        id = in.readString();
        name = in.readString();
        level = in.readInt();
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

    public List<Skill> getSkillList(){
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }

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

    public List<AbilityValueWrapper> getWrappers() {
        return abilities;
    }

    public void setWrappers(List<AbilityValueWrapper> wrappers) {
        this.abilities = wrappers;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<AbilityValueWrapper> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<AbilityValueWrapper> abilities) {
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

    }
}
