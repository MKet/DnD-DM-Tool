package Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by maxhe on 5-4-2018.
 */

public class PlayerAbility implements Parcelable, Serializable {
    private Abilities abilities;
    private Integer value;
    private Map<Skills, Skill> skills;

    public Map<Skills, Skill> getSkills() {
        return skills;
    }

    public void setSkills(Map<Skills, Skill> skills) {
        this.skills = skills;
    }

    public static final Creator<PlayerAbility> CREATOR = new Creator<PlayerAbility>() {
        @Override
        public PlayerAbility createFromParcel(Parcel in) {
            return new PlayerAbility(in);
        }

        @Override
        public PlayerAbility[] newArray(int size) {
            return new PlayerAbility[size];
        }
    };

    public void setAbilities(Abilities abilities) {
        this.abilities = abilities;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public PlayerAbility(){
        skills = abilities.generateSkills();
    }

    public PlayerAbility(Abilities abilities, Integer value) {
        this();
        this.abilities = abilities;
        this.value = value;
    }

    public Abilities getAbilities() {
        return abilities;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(abilities.ordinal());
        if (value == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(value);
        }

        dest.writeInt(skills.size());
        for(Map.Entry<Skills, Skill> entry : skills.entrySet()){
            dest.writeInt(entry.getKey().ordinal());
            entry.getValue().writeToParcel(dest, flags);
        }
    }

    protected PlayerAbility(Parcel in) {
        abilities = Abilities.values()[in.readInt()];

        if (in.readByte() == 0) {
            value = null;
        } else {
            value = in.readInt();
        }

        int size = in.readInt();
        for(int i = 0; i < size; i++){
            Skills key = Skills.values()[in.readInt()];
            Skill value = Skill.CREATOR.createFromParcel(in);
            skills.put(key,value);
        }
    }
}
