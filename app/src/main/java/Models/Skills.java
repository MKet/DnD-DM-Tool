package Models;

/**
 * Created by Marco on 4/6/2018.
 */

public enum Skills {
    Athletics(Abilities.Strength),
    Acrobatics(Abilities.Dexterity),
    SleightOfHand(Abilities.Dexterity),
    Stealth(Abilities.Dexterity),
    Arcana(Abilities.Intelligence),
    History(Abilities.Intelligence),
    Investigation(Abilities.Intelligence),
    Nature(Abilities.Intelligence),
    Religion(Abilities.Intelligence),
    AnimalHandling(Abilities.Wisdom),
    Insight(Abilities.Wisdom),
    Medicine(Abilities.Wisdom),
    Perception(Abilities.Wisdom),
    Survival(Abilities.Wisdom),
    Deception(Abilities.Charisma),
    Performance(Abilities.Charisma),
    Persuasion(Abilities.Charisma),
    Intimidation(Abilities.Charisma);

    private Abilities abilities;

    Skills(Abilities abilities) {
        this.abilities = abilities;
    }

    public Abilities getAbilities() {
        return abilities;
    }
}
