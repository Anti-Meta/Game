package com.antimeta.game.util;

import android.util.Log;

import com.antimeta.game.Constants;
import com.antimeta.game.ormlite.DatabaseAccessor;
import com.antimeta.game.ormlite.entity.Enemy;
import com.antimeta.game.ormlite.entity.Equipment;
import com.antimeta.game.ormlite.entity.Player;
import com.antimeta.game.ormlite.entity.Stage;
import com.antimeta.game.ormlite.entity.Stats;

/**
 * @author Darius Wattimena
 * on 29-Dec-16.
 */
public class PlayerUtil {
    private static DatabaseAccessor databaseAccessor = Constants.databaseAccessor;
    private static final String TAG = "PlayerUtil";
    public static Player player;
    public static Player getCurrentPlayer(){
        if(player == null){
            Player databasePlayer = Constants.databaseAccessor.playerDM.findFirst();
            if(databasePlayer == null){
                return null;
            }
            else{
                player = databasePlayer;
                return databasePlayer;
            }
        }
        else{
            return player;
        }
    }

    public static Integer getCurrentPlayerLevel(){
        return player.getLevel();
    }

    public static Stage getCurrentPlayerStage(){
        return player.getCurrentStage();
    }

    public static void processKilledEnemy(Enemy killedEnemy){
        Log.d(TAG, "Processing killed enemy");
        databaseAccessor.enemyDM.delete(killedEnemy);
        earnXP(killedEnemy);
        checkForEquipment(killedEnemy);
        Stage currentStage = player.getCurrentStage();
        Stage newStage = StageUtil.checkStageIfNewNeeded(currentStage);
        player.setCurrentStage(newStage);
        databaseAccessor.playerDM.save(player);
    }

    public static Player earnXP(Enemy killedEnemy){
        Log.d(TAG, "Player earned XP");
        Integer currentXP = player.getXp();
        Integer totalXPNeeded = player.getTotalXPNeeded();
        Integer increaseXP = killedEnemy.getLevel();

        if((currentXP + increaseXP) >= totalXPNeeded){
            Integer XPLeft = (currentXP + increaseXP) - totalXPNeeded;
            Player upgradedPlayer = levelUpPlayer(XPLeft);
            databaseAccessor.playerDM.save(upgradedPlayer);
            return upgradedPlayer;
        }
        else{
            player.setXp(currentXP + increaseXP);
            databaseAccessor.playerDM.save(player);
            return player;
        }
    }

    public static Equipment checkForEquipment(Enemy killedEnemy){
        //TODO maak beter finding als killedEnemy rarity hoger is.
        return EquipmentUtil.tryToFindEquipment(RandomValues.getRandomInteger(100), killedEnemy.getRarety());
    }

    public static Player levelUpPlayer(Integer XPLeft){
        Log.d(TAG, "Player level up");
        player.setLevel(player.getLevel() + 1);
        player.setSkillpoints(player.getSkillpoints() + 2);
        player.setTotalSkillpoints(player.getTotalSkillpoints() + 2);
        player.setXp(XPLeft);
        player.setTotalXPNeeded(Constants.BEGIN_XP_TOTAL * player.getLevel());
        return player;
    }

    public static Player useSkillPoint(String skillName){
        if(player.getSkillpoints() > 0){
            Log.d(TAG, "Spending skillpoint on:" + skillName);
            Stats currentStats = player.getStats();
            Stats newStats = spendSkillPointOn(skillName, currentStats);
            player.setStats(newStats);
            databaseAccessor.playerDM.save(player);
            return player;
        }
        else{
            Log.d(TAG, "You don't have skillpoints");
            return player;
        }
    }

    private static Stats spendSkillPointOn(String skillName, Stats currentStats){
        switch(skillName){
            case "Vitality":
                currentStats.setVitality(currentStats.getVitality() + 1);
                break;
            case "Agility":
                currentStats.setAgility(currentStats.getAgility() + 1);
                break;
            case "Strength":
                currentStats.setStrength(currentStats.getStrength() + 1);
                break;
            case "Intellect":
                currentStats.setIntellect(currentStats.getIntellect() + 1);
                break;
        }
        return currentStats;
    }
}
