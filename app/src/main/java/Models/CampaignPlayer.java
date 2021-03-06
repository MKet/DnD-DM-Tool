package Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import DndUtil.DndUtil;

/**
 * Created by maxhe on 15-3-2018.
 */

public class CampaignPlayer implements Parcelable, Serializable {
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
    private String id;
    private String name;
    private int level;
    private Map<String, PlayerAbility> abilities = new HashMap<>(5);

    public CampaignPlayer() {
        PlayerAbility strength = new PlayerAbility(Abilities.Strength, 10);
        PlayerAbility charisma = new PlayerAbility(Abilities.Charisma, 10);
        PlayerAbility intelligence = new PlayerAbility(Abilities.Intelligence, 10);
        PlayerAbility wisdom = new PlayerAbility(Abilities.Wisdom, 10);
        PlayerAbility dexterity = new PlayerAbility(Abilities.Dexterity, 10);

        abilities.put(Abilities.Strength.name(), strength);
        abilities.put(Abilities.Charisma.name(), charisma);
        abilities.put(Abilities.Intelligence.name(), intelligence);
        abilities.put(Abilities.Wisdom.name(), wisdom);
        abilities.put(Abilities.Dexterity.name(), dexterity);
    }

    public CampaignPlayer(String name, String id) {
        this();
        setName(name);
        this.id = id;
        setLevel(1);
    }

    protected CampaignPlayer(Parcel in) {
        id = in.readString();
        name = in.readString();
        level = in.readInt();

        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            String key = in.readString();
            PlayerAbility value = PlayerAbility.CREATOR.createFromParcel(in);
            abilities.put(key, value);
        }
    }

    public static Creator<CampaignPlayer> getCREATOR() {
        return CREATOR;
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

    public void setLevel(int level) {
        this.level = level;
    }

    public Map<String, PlayerAbility> getAbilities() {
        return abilities;
    }

    public void setAbilities(Map<String, PlayerAbility> abilities) {
        this.abilities = abilities;
    }

    @Override
    public String toString() {
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
        for (Map.Entry<String, PlayerAbility> entry : abilities.entrySet()) {
            dest.writeString(entry.getKey());
            entry.getValue().writeToParcel(dest, flags);
        }
    }

    public int calculateSkillValue(Skills skill) {
        PlayerAbility ability = abilities.get(skill.getAbilities().name());
        int value = ability.getValue();

        Skill playerSkill = ability.getSkills().get(skill.name());

        if (playerSkill.isProficienct()) {
            int proficiency = DndUtil.calculateProficiency(getLevel());
            value += proficiency;
        }

        return value;
    }
}
