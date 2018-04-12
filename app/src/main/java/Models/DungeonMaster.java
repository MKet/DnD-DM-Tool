package Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxhe on 15-3-2018.
 */

public class DungeonMaster implements Parcelable, Serializable {

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
    private List<Campaign> campaigns;
    private String id;
    private String name;

    public DungeonMaster(GoogleSignInAccount account) {
        id = account.getId();
        name = account.getDisplayName();
        campaigns = new ArrayList<>();
    }

    public DungeonMaster() {

    }

    protected DungeonMaster(Parcel in) {
        id = in.readString();
        name = in.readString();

        int size = in.readInt();
        campaigns = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Campaign.CREATOR.createFromParcel(in);
        }
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

    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<Campaign> campaigns) {
        this.campaigns = campaigns;
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

        dest.writeInt(campaigns.size());
        for (Campaign campaign : campaigns) {
            campaign.writeToParcel(dest, flags);
        }
    }
}
