package dnd.tools.dnddmtools;

import android.support.annotation.NonNull;

/**
 * Created by Marco on 3/16/2018.
 */

public class CreatureTurnItem implements Comparable<CreatureTurnItem> {

    private String name;
    private int CR;
    private int Dexterity;
    private int Initiative;
    private int turns;

    public CreatureTurnItem(String name, int CR, int dexterity, int initiative, int turns) {
        this.name = name;
        this.CR = CR;
        Dexterity = dexterity;
        Initiative = initiative;
        this.turns = turns;
    }

    public CreatureTurnItem(String name, int CR, int dexterity, int initiative) {
        this.name = name;
        this.CR = CR;
        Dexterity = dexterity;
        Initiative = initiative;
        this.turns = 0;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreatureTurnItem that = (CreatureTurnItem) o;

        return CR == that.CR && Dexterity == that.Dexterity && Initiative == that.Initiative && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + CR;
        result = 31 * result + Dexterity;
        result = 31 * result + Initiative;
        return result;
    }

    @Override
    public String toString() {
        return "CreatureTurnItem{" +
                "name='" + name + '\'' +
                ", CR=" + CR +
                ", Dexterity=" + Dexterity +
                ", Initiative=" + Initiative +
                '}';
    }

    @Override
    public int compareTo(@NonNull CreatureTurnItem creatureTurnItem) {
        int comparison = creatureTurnItem.turns - turns;
        if (comparison == 0)
            comparison = Initiative - creatureTurnItem.Initiative;
        if (comparison == 0)
            comparison = Dexterity - creatureTurnItem.Dexterity;

        return comparison;

    }
}
