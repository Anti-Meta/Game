package com.antimeta.game.util;

import android.support.annotation.Nullable;

import com.antimeta.game.Constants;
import com.antimeta.game.ormlite.DatabaseAccessor;
import com.antimeta.game.ormlite.entity.EquipmentType;
import com.antimeta.game.ormlite.entity.Stats;

import java.util.Random;

public class RandomValues {
    private final static Random r = new Random();
    private static DatabaseAccessor databaseAccessor = Constants.databaseAccessor;

    public static String getName(String className) {
        switch (className){
            case "Knight":
                return getRandomKnightName();
            case "Ranger":
                return getRandomRangerName();
            case "Mage":
                return getRandomMageName();
        }
        return "test";
    }

    public static String getRandomKnightName(){
        int randomNumber = r.nextInt(4 - 1) + 1;
        switch (randomNumber){
            case 1:
                return "Warrior";
            case 2:
                return "Paladin";
            case 3:
                return "Death Knight";
            default:
                return "Knight";
        }
    }

    public static String getRandomMaterial(){
        int randomNumber = r.nextInt(4 - 1) + 1;
        switch (randomNumber){
            case 1:
                return "Plate";
            case 2:
                return "Leather";
            case 3:
                return "Cloth";
        }
        return null;
    }

    public static String getRandomRangerName(){
        int randomNumber = r.nextInt(4 - 1) + 1;
        switch (randomNumber){
            case 1:
                return "Archer";
            case 2:
                return "Gunner";
            case 3:
                return "Thrower";
            default:
                return "Ranger";
        }
    }
    public static String getRandomMageName(){
        int randomNumber = r.nextInt(4 - 1) + 1;
        switch (randomNumber){
            case 1:
                return "Mage";
            case 2:
                return "Necromancer";
            case 3:
                return "Priest";
            default:
                return "Mage";
        }
    }

    public static EquipmentType getRandomEquipmentType(){
        int randomNumber = r.nextInt(6 - 1) + 1;
        switch (randomNumber){
            case 1:
                return databaseAccessor.equipmentTypeDM.find("Name", "Helmet");
            case 2:
                return databaseAccessor.equipmentTypeDM.find("Name", "Chestplate");
            case 3:
                return databaseAccessor.equipmentTypeDM.find("Name", "Platelegs");
            case 4:
                return databaseAccessor.equipmentTypeDM.find("Name", "Weapon");
            case 5:
                return databaseAccessor.equipmentTypeDM.find("Name", "Ring");
            default:
                return databaseAccessor.equipmentTypeDM.find("Name", "Weapon");
        }
    }

    public static String getRandomEquipmentName(){
        return "test";
    }

    public static String getStageName(){
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
        return StatsUtil.calculateEnemyStats(stats, level);
    }

    public static Integer getRandomInteger(Integer max){
        Integer number = max + 1;
        return r.nextInt(number - 1) + 1;
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
