package Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by maxhe on 5-4-2018.
 */
public class PlayerAbility implements Parcelable, Serializable {
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
    private Abilities abilities;
    private Integer value;
    private HashMap<String, Skill> skills;

    public PlayerAbility() {
    }

    public PlayerAbility(Abilities abilities, Integer value) {
        this.abilities = abilities;
        this.skills = abilities.generateSkills();
        this.value = value;
    }

    protected PlayerAbility(Parcel in) {
        abilities = Abilities.values()[in.readInt()];

        if (in.readByte() == 0) {
            value = null;
        } else {
            value = in.readInt();
        }

        skills = new HashMap<>();
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            String key = in.readString();
            Skill value = Skill.CREATOR.createFromParcel(in);
            skills.put(key, value);
        }
    }

    public HashMap<String, Skill> getSkills() {
        return skills;
    }

    public void setSkills(HashMap<String, Skill> skills) {
        this.skills = skills;
    }

    public Abilities getAbilities() {
        return abilities;
    }

    public void setAbilities(Abilities abilities) {
        this.abilities = abilities;
        skills = abilities.generateSkills();
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
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
        for (Map.Entry<String, Skill> entry : skills.entrySet()) {
            dest.writeString(entry.getKey());
            entry.getValue().writeToParcel(dest, flags);
        }
    }
}
