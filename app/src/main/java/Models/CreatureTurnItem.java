package Models;

import android.support.annotation.NonNull;

import DndUtil.DndUtil;

/**
 * Created by Marco on 3/16/2018.
 */

public class CreatureTurnItem implements Comparable<CreatureTurnItem> {

    private String name;
    private int CR;
    private int Dexterity;
    private int Initiative;
    private int turns;
    private boolean isFriendly = false;
    private int random;

    public CreatureTurnItem(CampaignPlayer player) {
        name = player.getName();
        CR = 0;
        Dexterity = player.getAbilities().get(Abilities.Dexterity.name()).getValue();
        Initiative = DndUtil.ScoreToModifier(Dexterity);
        turns = 0;
        isFriendly = true;
        random = DndUtil.random.nextInt();
    }

    public CreatureTurnItem(String name, int CR, int dexterity, int initiative, int turns) {
        this.name = name;
        this.CR = CR;
        Dexterity = dexterity;
        Initiative = initiative;
        this.turns = turns;
        random = DndUtil.random.nextInt();
    }

    public CreatureTurnItem(String name, int CR, int dexterity, int initiative) {
        this.name = name;
        this.CR = CR;
        Dexterity = dexterity;
        Initiative = initiative;
        this.turns = 0;
        random = DndUtil.random.nextInt();
    }

    public String getName() {
        return name;
    }

    public int getCR() {
        return CR;
    }

    public int getDexterity() {
        return Dexterity;
    }

    public int getInitiative() {
        return Initiative;
    }

    public int getTurns() {
        return turns;
    }

    public void endTurn() {
        turns++;
    }

    public boolean isFriendly() {
        return isFriendly;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreatureTurnItem that = (CreatureTurnItem) o;

        return CR == that.CR &&
                Dexterity == that.Dexterity &&
                Initiative == that.Initiative &&
                turns == that.turns &&
                (name != null ? name.equals(that.name) : that.name == null);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + CR;
        result = 31 * result + Dexterity;
        result = 31 * result + Initiative;
        result = 31 * result + turns;
        return result;
    }

    @Override
    public String toString() {
        return "CreatureTurnItem{" +
                "name='" + name + '\'' +
                ", CR=" + CR +
                ", Dexterity=" + Dexterity +
                ", Initiative=" + Initiative +
                ", turns=" + turns +
                '}';
    }

    @Override
    public int compareTo(@NonNull CreatureTurnItem creatureTurnItem) {
        if (this == creatureTurnItem)
            return 0;

        int comparison = turns - creatureTurnItem.turns;
        if (comparison == 0)
            comparison = creatureTurnItem.Initiative - Initiative;
        if (comparison == 0)
            comparison = creatureTurnItem.Dexterity - Dexterity;
        if (comparison == 0)
            comparison = creatureTurnItem.random - random;

        return comparison;

    }
}
