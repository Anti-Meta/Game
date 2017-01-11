package com.antimeta.game.util;

import android.util.Log;

import com.antimeta.game.Constants;
import com.antimeta.game.ormlite.DatabaseAccessor;
import com.antimeta.game.ormlite.entity.Equipment;
import com.antimeta.game.ormlite.entity.EquipmentType;
import com.antimeta.game.ormlite.entity.Stats;

/**
 * @author Darius Wattimena
 *         on 07-Jan-17.
 */
public class EquipmentUtil {
    private static DatabaseAccessor databaseAccessor = Constants.databaseAccessor;
    private static final String TAG = "EquipmentUtil";

    public static Equipment tryToFindEquipment(Integer findChance, String rarity){
        if(findChance >= 90){
            Log.d(TAG, "Found equipment with rarity: " + rarity);
            return getEquipment(rarity);
        }
        else {
            Log.d(TAG, "No equipment found");
            return null;
        }
    }

    public static Equipment getEquipment(String rarity){
        EquipmentType randomEquipmentType = RandomValues.getRandomEquipmentType();
        String randomMaterial = RandomValues.getRandomMaterial();

        Equipment equipment = new Equipment();
        equipment.setEquipmentType(randomEquipmentType);
        Log.d(TAG, "This equipment is made of " + randomMaterial);
        Stats databaseStats = databaseAccessor.statsDM.find("statsProfileName", randomMaterial + randomEquipmentType.getName());
        equipment.setStats(StatsUtil.getCalculatedEquipmentStats(databaseStats, rarity));
        equipment.setName(RandomValues.getRandomEquipmentName());
        equipment.setLevel(1);
        equipment.setMaxLevel(Constants.CURRENT_LEVEL);
        return equipment;
    }

    public static Equipment upgradeEquipment(Equipment equipment){
        if(equipment.getMaxLevel() > equipment.getLevel()){
            Integer oldLevel = equipment.getLevel();
            Integer newLevel = equipment.getLevel() + 1;
            Log.d(TAG, "Upgrading " + equipment.getName() + " from " + oldLevel.toString() + " to " + newLevel.toString());
            equipment.setLevel(newLevel);
            databaseAccessor.equipmentDM.save(equipment);
        }
        else{
            Log.d(TAG, "Your equipment is already max level");
        }
        return equipment;
    }
}
