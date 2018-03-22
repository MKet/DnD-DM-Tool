package DndUtil;

/**
 * Created by Marco on 3/22/2018.
 */

public class DndUtil {
    public static int RollToModifier(int roll) {
        return (int)Math.floor(roll/2 -5);
    }

}
