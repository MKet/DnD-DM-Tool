package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by maxhe on 16-3-2018.
 */

public enum Abilities{

    Strength("Strength"),
    Dexterity("Dexterity"),
    Intelligence("Intelligence"),
    Wisdom("Wisdom"),
    Charisma("Charisma"),
    Constitution("Constitution");


    private String name;

    Abilities(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }

    public Map<Skills, Skill> generateSkills() {
        Map<Skills, Skill> skills = new HashMap<>();
        switch (this) {
            case Strength:
                skills.put(Skills.Athletics, new Skill(Skills.Athletics));
                break;
            case Dexterity:
                skills.put(Skills.Acrobatics, new Skill(Skills.Acrobatics));
                skills.put(Skills.SleightOfHand, new Skill(Skills.SleightOfHand));
                skills.put(Skills.Stealth, new Skill(Skills.Stealth));
                break;
            case Constitution:
                break;
            case Wisdom:
                skills.put(Skills.AnimalHandling, new Skill(Skills.AnimalHandling));
                skills.put(Skills.Insight, new Skill(Skills.Insight));
                skills.put(Skills.Medicine, new Skill(Skills.Medicine));
                skills.put(Skills.Perception, new Skill(Skills.Perception));
                skills.put(Skills.Survival, new Skill(Skills.Survival));
                break;
            case Intelligence:
                skills.put(Skills.Arcana, new Skill(Skills.Arcana));
                skills.put(Skills.History, new Skill(Skills.History));
                skills.put(Skills.Investigation, new Skill(Skills.Investigation));
                skills.put(Skills.Nature, new Skill(Skills.Nature));
                skills.put(Skills.Religion, new Skill(Skills.Religion));
                break;
            case Charisma:
                skills.put(Skills.Persuasion, new Skill(Skills.Persuasion));
                skills.put(Skills.Intimidation, new Skill(Skills.Intimidation));
                skills.put(Skills.Deception, new Skill(Skills.Deception));
                skills.put(Skills.Performance, new Skill(Skills.Performance));
                break;

        }
        return skills;
    }
}
