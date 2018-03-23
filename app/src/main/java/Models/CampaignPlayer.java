package Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by maxhe on 15-3-2018.
 */

public class CampaignPlayer implements Parcelable{
    private String id;
    private List<Skill> skillList;
    private String name;

    public CampaignPlayer(){

    }

    protected CampaignPlayer(Parcel in) {
        id = in.readString();
        name = in.readString();
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

    public List<Skill> getSkillList() {
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
    }
}
