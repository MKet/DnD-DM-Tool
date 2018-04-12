package Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxhe on 15-3-2018.
 */

public class Campaign implements Parcelable, Serializable {

    public static final Creator<Campaign> CREATOR = new Creator<Campaign>() {
        @Override
        public Campaign createFromParcel(Parcel in) {
            return new Campaign(in);
        }

        @Override
        public Campaign[] newArray(int size) {
            return new Campaign[size];
        }
    };
    private String id;
    private String name;
    private List<CampaignPlayer> players = new ArrayList<>();
    private String note;
    private String dungeonMaster;

    public Campaign() {

    }

    protected Campaign(Parcel in) {
        id = in.readString();
        name = in.readString();

        players = new ArrayList<>();
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            players.add(CampaignPlayer.CREATOR.createFromParcel(in));
        }
        note = in.readString();
        dungeonMaster = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDungeonMaster() {
        return dungeonMaster;
    }

    public void setDungeonMaster(String dungeonMaster) {
        this.dungeonMaster = dungeonMaster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CampaignPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<CampaignPlayer> players) {
        this.players = players;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
        int size = players.size();
        dest.writeInt(size);
        for (CampaignPlayer player : players) {
            player.writeToParcel(dest, flags);
        }
        dest.writeString(note);
        dest.writeString(dungeonMaster);
    }
}
