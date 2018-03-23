package Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by maxhe on 15-3-2018.
 */

public class Campaign implements Parcelable{

    private String id;
    private String name;
    private List<CampaignPlayer> players;
    private String notes;
    private String dungeonMaster;

    public Campaign(){

    }

    protected Campaign(Parcel in) {
        id = in.readString();
        name = in.readString();
        players = in.createTypedArrayList(CampaignPlayer.CREATOR);
        notes = in.readString();
        dungeonMaster = in.readString();
    }

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

    public String getId() {
        return id;
    }

    public String getDungeonMaster() {
        return dungeonMaster;
    }

    public void setDungeonMaster(String dungeonMaster) {
        this.dungeonMaster = dungeonMaster;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
        dest.writeTypedList(players);
        dest.writeString(notes);
        dest.writeString(dungeonMaster);
    }
}
