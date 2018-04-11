package Models;

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

    public HashMap<String, Skill> generateSkills() {
        HashMap<String, Skill> skills = new HashMap<>();
        switch (this) {
            case Strength:
                skills.put(Skills.Athletics.name(), new Skill(Skills.Athletics));
                break;
            case Dexterity:
                skills.put(Skills.Acrobatics.name(), new Skill(Skills.Acrobatics));
                skills.put(Skills.SleightOfHand.name(), new Skill(Skills.SleightOfHand));
                skills.put(Skills.Stealth.name(), new Skill(Skills.Stealth));
                break;
            case Constitution:
                break;
            case Wisdom:
                skills.put(Skills.AnimalHandling.name(), new Skill(Skills.AnimalHandling));
                skills.put(Skills.Insight.name(), new Skill(Skills.Insight));
                skills.put(Skills.Medicine.name(), new Skill(Skills.Medicine));
                skills.put(Skills.Perception.name(), new Skill(Skills.Perception));
                skills.put(Skills.Survival.name(), new Skill(Skills.Survival));
                break;
            case Intelligence:
                skills.put(Skills.Arcana.name(), new Skill(Skills.Arcana));
                skills.put(Skills.History.name(), new Skill(Skills.History));
                skills.put(Skills.Investigation.name(), new Skill(Skills.Investigation));
                skills.put(Skills.Nature.name(), new Skill(Skills.Nature));
                skills.put(Skills.Religion.name(), new Skill(Skills.Religion));
                break;
            case Charisma:
                skills.put(Skills.Persuasion.name(), new Skill(Skills.Persuasion));
                skills.put(Skills.Intimidation.name(), new Skill(Skills.Intimidation));
                skills.put(Skills.Deception.name(), new Skill(Skills.Deception));
                skills.put(Skills.Performance.name(), new Skill(Skills.Performance));
                break;

        }
        return skills;
    }
}
