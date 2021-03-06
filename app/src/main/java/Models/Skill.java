package Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by maxhe on 15-3-2018.
 */

public class Skill implements Parcelable, Serializable {
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
    private Skills name;
    private boolean proficienct = false;

    protected Skill(Parcel in) {
        name = Skills.valueOf(in.readString());
        proficienct = in.readByte() != 0;
    }

    public Skill() {

    }

    public Skill(Skills name) {
        this.name = name;
    }

    public Skill(Skills name, boolean proficienct) {
        this(name);
        this.proficienct = proficienct;
    }

    public static Creator<Skill> getCREATOR() {
        return CREATOR;
    }

    public boolean isProficienct() {
        return proficienct;
    }

    public void setProficienct(boolean proficienct) {
        this.proficienct = proficienct;
    }

    public Skills getName() {
        return name;
    }

    public void setName(Skills name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name.name());
        dest.writeByte((byte) (proficienct ? 1 : 0));

    }

}


