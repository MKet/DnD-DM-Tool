package Models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by maxhe on 15-3-2018.
 */

public class CampaignPlayer {
    private String id;
    private List<Skill> skillList;
    private String name;

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
}
