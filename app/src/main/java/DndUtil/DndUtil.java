package DndUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Marco on 3/22/2018.
 */

public class DndUtil {
    public static final Map<Float, Integer> ExpMap;
    public static final Random random = new Random();

    static {
        // The experience per challenge rating is handpicked for an optimal curve in D&D
        // some levels go by faster that other, etc
        // because of this we need to put in the experience by challenge rating manualle
        // this cannot be simply calculated using a formula
        Map<Float, Integer> temp = new HashMap<>(34, 1);
        temp.put(0f, 10);
        temp.put(1 / 8f, 25);
        temp.put(1 / 4f, 50);
        temp.put(1 / 2f, 100);
        temp.put(1f, 200);
        temp.put(2f, 450);
        temp.put(3f, 700);
        temp.put(4f, 1_100);
        temp.put(5f, 1_800);
        temp.put(6f, 2_300);
        temp.put(7f, 2_900);
        temp.put(8f, 3_900);
        temp.put(9f, 5_000);
        temp.put(10f, 5_900);
        temp.put(11f, 7_200);
        temp.put(12f, 8_400);
        temp.put(13f, 10_000);
        temp.put(14f, 11_500);
        temp.put(15f, 13_000);
        temp.put(16f, 15_000);
        temp.put(17f, 18_000);
        temp.put(18f, 20_000);
        temp.put(19f, 22_000);
        temp.put(20f, 25_000);
        temp.put(21f, 33_000);
        temp.put(22f, 41_000);
        temp.put(23f, 50_000);
        temp.put(24f, 62_000);
        temp.put(25f, 75_000);
        temp.put(26f, 90_000);
        temp.put(27f, 105_000);
        temp.put(28f, 120_000);
        temp.put(29f, 135_000);
        temp.put(30f, 155_000);
        ExpMap = Collections.unmodifiableMap(temp);
    }

    public static int ScoreToModifier(int roll) {
        return (int) Math.floor(roll / 2 - 5);
    }

    public static int RollD20() {
        return random.nextInt(20) + 1;
    }

    public static int calculateProficiency(int level) {
        return (int) Math.floor((7 + level) / 4);
    }

}