package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxhe on 16-3-2018.
 */

public enum Ability implements Serializable{

    Strength("Strength"),
    Dexterity("Dexterity"),
    Intelligence("Intelligence"),
    Wisdom("Wisdom"),
    Charisma("Charisma");


    private String name;

    Ability(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
