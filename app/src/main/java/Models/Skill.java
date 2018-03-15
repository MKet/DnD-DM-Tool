package Models;

/**
 * Created by maxhe on 15-3-2018.
 */

public class Skill {
    private String name;
    private int value;

    public Skill(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }


    public int getValue() {
        return value;
    }

}
