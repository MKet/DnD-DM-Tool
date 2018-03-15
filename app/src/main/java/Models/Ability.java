package Models;

import android.content.Intent;

import java.util.List;
import java.util.Map;

/**
 * Created by maxhe on 15-3-2018.
 */

public class Ability {

    private String name;
    private List<Skill> skills;
    private int value;


    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
