package Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by maxhe on 5-4-2018.
 */

public class AbilityValueWrapper implements Parcelable {
    private Ability ability;
    private Integer value;

    protected AbilityValueWrapper(Parcel in) {
        if (in.readByte() == 0) {
            value = null;
        } else {
            value = in.readInt();
        }
    }

    public static final Creator<AbilityValueWrapper> CREATOR = new Creator<AbilityValueWrapper>() {
        @Override
        public AbilityValueWrapper createFromParcel(Parcel in) {
            return new AbilityValueWrapper(in);
        }

        @Override
        public AbilityValueWrapper[] newArray(int size) {
            return new AbilityValueWrapper[size];
        }
    };

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public AbilityValueWrapper(){

    }

    public AbilityValueWrapper(Ability ability, Integer value) {
        this.ability = ability;
        this.value = value;
    }

    public Ability getAbility() {
        return ability;
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
        if (value == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(value);
        }
    }
}
