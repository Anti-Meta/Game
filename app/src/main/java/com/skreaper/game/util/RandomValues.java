package com.skreaper.game.util;

import com.skreaper.game.Constants;
import com.skreaper.game.ormlite.DatabaseAccessor;
import com.skreaper.game.ormlite.entity.Stats;

import java.util.Random;

public class RandomValues {
    final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final static Random r = new Random();
    private static DatabaseAccessor databaseAccessor = Constants.databaseAccessor;

    public static String getName() {
        StringBuilder builder = new StringBuilder();
        while(builder.toString().length() == 0) {
            int length = r.nextInt(5)+5;
            for(int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(r.nextInt(lexicon.length())));
            }
        }
        return builder.toString();
    }

    public static Stats getRandomEnemyStats(){
        int randomProfileNumber = r.nextInt(4 - 1) + 1;
        return getStats(randomProfileNumber);
    }

    public static Stats getStats(int profileNumber){
        Stats profileTemplate;
        switch(profileNumber) {
            case 1:
                profileTemplate = databaseAccessor.statsDM.find("statsProfileName", "enemy1StatsProfile");
                return getCalculatedStats(profileTemplate);
            case 2:
                profileTemplate = databaseAccessor.statsDM.find("statsProfileName", "enemy2StatsProfile");
                return getCalculatedStats(profileTemplate);
            case 3:
                profileTemplate = databaseAccessor.statsDM.find("statsProfileName", "enemy3StatsProfile");
                return getCalculatedStats(profileTemplate);
        }
        return null;
    }

    public static Stats getCalculatedStats(Stats stats){
        Integer startHealth = stats.getHealth();
        Integer startMana = stats.getMana();
        Integer startAttackDamage = stats.getAttack();
        Integer startDefense = stats.getDefense();

        stats.setAttack(Math.round(stats.getAttack() * getRandomFloat()));
        stats.setDefense(Math.round(stats.getDefense() * getRandomFloat()));
        stats.setVitality(Math.round(stats.getVitality() * getRandomFloat()));
        return stats;
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
