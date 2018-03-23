package DndUtil;

import java.util.Random;

/**
 * Created by Marco on 3/22/2018.
 */

public class DndUtil {
    private static Random random = new Random();
    public static int RollToModifier(int roll) {
        return (int)Math.floor(roll/2 -5);
    }

    public static int RollD20() {
        return random.nextInt(20)+1;
    }

    public static int calculateProficiency(int level) {
        return (int)Math.floor((7 + level) / 4);
    }

}
