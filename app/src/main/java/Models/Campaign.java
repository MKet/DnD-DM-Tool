package Models;

import java.util.List;

/**
 * Created by maxhe on 15-3-2018.
 */

public class Campaign {

    private String id;
    private String name;
    private List<CampaignPlayer> players;
    private String notes;
    private String dungeonMaster;

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
}
