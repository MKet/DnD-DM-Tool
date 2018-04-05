package DndUtil;

import java.security.cert.CRL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Marco on 3/22/2018.
 */

public class DndUtil {
    private static Map<Float, Integer> ExpMap;
    private static Random random = new Random();
    public static int RollToModifier(int roll) {
        return (int)Math.floor(roll/2 -5);
    }

    static {
        // The experience per challenge rating is handpicked for an optimal curve in D&D
        // some levels go by faster that other, etc
        // because of this we need to put in the experience by challenge rating manualle
        // this cannot be simply calculated using a formula
        ExpMap = new HashMap<>(34, 1);
        ExpMap.put(0f, 10);
        ExpMap.put(1/8f, 25);
        ExpMap.put(1/4f, 50);
        ExpMap.put(1/2f, 100);
        ExpMap.put(1f, 200);
        ExpMap.put(2f, 450);
        ExpMap.put(3f, 700);
        ExpMap.put(4f, 1_100);
        ExpMap.put(5f, 1_800);
        ExpMap.put(6f, 2_300);
        ExpMap.put(7f, 2_900);
        ExpMap.put(8f, 3_900);
        ExpMap.put(9f, 5_000);
        ExpMap.put(10f, 5_900);
        ExpMap.put(11f, 7_200);
        ExpMap.put(12f, 8_400);
        ExpMap.put(13f, 10_000);
        ExpMap.put(14f, 11_500);
        ExpMap.put(15f, 13_000);
        ExpMap.put(16f, 15_000);
        ExpMap.put(17f, 18_000);
        ExpMap.put(18f, 20_000);
        ExpMap.put(19f, 22_000);
        ExpMap.put(20f, 25_000);
        ExpMap.put(21f, 33_000);
        ExpMap.put(22f, 41_000);
        ExpMap.put(23f, 50_000);
        ExpMap.put(24f, 62_000);
        ExpMap.put(25f, 75_000);
        ExpMap.put(26f, 90_000);
        ExpMap.put(27f, 105_000);
        ExpMap.put(28f, 120_000);
        ExpMap.put(29f, 135_000);
        ExpMap.put(30f, 155_000);

    }

    public static int RollD20() {
        return random.nextInt(20)+1;
    }

    public static int calculateProficiency(int level){
        return (int)Math.floor((7 + level) / 4);
    }

    public static int calculateExperience(float CR) {
        if (!ExpMap.containsKey(CR))
            throw new IllegalArgumentException("Challenge rating does not exist");

        return ExpMap.get(CR);
    }

    public static int calculateExperience(Iterable<Float> CRList, int playerAmount) {
        int totalExperience = 0;
        for (float cr : CRList) {
            totalExperience += calculateExperience(cr);
        }

        return totalExperience / playerAmount;
    }

    public static int calculateExperience(Iterable<Float> CRList) {
        return calculateExperience(CRList, 1);
    }
}