package com.skreaper.game.util;

import android.support.annotation.Nullable;

import com.skreaper.game.Constants;
import com.skreaper.game.ormlite.DatabaseAccessor;
import com.skreaper.game.ormlite.entity.Stats;

import java.util.Random;

public class RandomValues {
    private final static Random r = new Random();
    private static DatabaseAccessor databaseAccessor = Constants.databaseAccessor;

    public static String getName() {
        return "test";
    }

    public static Stats getRandomEnemyStats(Integer level){
        int randomProfileNumber = r.nextInt(4 - 1) + 1;
        return getStats(randomProfileNumber, level);
    }

    @Nullable
    public static Stats getStats(int profileNumber, Integer level){
        Stats profileTemplate;
        switch(profileNumber) {
            case 1:
                profileTemplate = databaseAccessor.statsDM.find("statsProfileName", "Knight");
                return getCalculatedEnemyStats(profileTemplate, level);
            case 2:
                profileTemplate = databaseAccessor.statsDM.find("statsProfileName", "Ranger");
                return getCalculatedEnemyStats(profileTemplate, level);
            case 3:
                profileTemplate = databaseAccessor.statsDM.find("statsProfileName", "Mage");
                return getCalculatedEnemyStats(profileTemplate, level);
        }
        return null;
    }

    public static Stats getCalculatedEnemyStats(Stats stats, Integer level){
        return CalculateStats.calculateEnemyStats(stats, level);
    }

    public static float getRandomFloat(){
        float chance = r.nextFloat();
        if (chance <= 0.10f){
            return 0.50f;
        }
        else if (chance <= 0.30f){
            return 0.55f;
        }
        else if (chance <= 0.50f){
            return 0.65f;
        }
        else if (chance <= 0.80f){
            return 0.75f;
        }
        else {
            return 0.80f;
        }
    }
}
