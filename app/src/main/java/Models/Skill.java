package Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by maxhe on 15-3-2018.
 */

public class Skill implements Parcelable {
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

    protected Skill(Parcel in) {
        name = in.readString();
        value = in.readInt();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(value);
    }
}


