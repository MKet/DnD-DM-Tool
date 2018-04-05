package Models;

/**
 * Created by maxhe on 5-4-2018.
 */

public class AbilityValueWrapper {
    private Ability ability;
    private Integer value;

    public AbilityValueWrapper(){

    }

    public AbilityValueWrapper(Ability ability, Integer value) {
        this.ability = ability;
        this.value = value;
    }

    public Ability getAbility() {
        return ability;
    }

    public Integer getValue() {
        return value;
    }
}
