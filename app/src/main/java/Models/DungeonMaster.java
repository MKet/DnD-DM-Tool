package Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.List;

/**
 * Created by maxhe on 15-3-2018.
 */

public class DungeonMaster implements Parcelable{

    private List<Campaign> campaigns;
    private String id;
    private String name;

    public DungeonMaster(GoogleSignInAccount account){
        id = account.getId();
        name = account.getDisplayName();
    }

    public DungeonMaster(){

    }

    public String getId() {
        return id;
    }

    protected DungeonMaster(Parcel in) {
        id = in.readString();
    }

    public static final Creator<DungeonMaster> CREATOR = new Creator<DungeonMaster>() {
        @Override
        public DungeonMaster createFromParcel(Parcel in) {
            return new DungeonMaster(in);
        }

        @Override
        public DungeonMaster[] newArray(int size) {
            return new DungeonMaster[size];
        }
    };

    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<Campaign> campaigns) {
        this.campaigns = campaigns;
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
    }
}
