package com.antimeta.game.util;

import com.antimeta.game.Constants;
import com.antimeta.game.ormlite.DatabaseAccessor;
import com.antimeta.game.ormlite.entity.Enemy;
import com.antimeta.game.ormlite.entity.Player;
import com.antimeta.game.ormlite.entity.Stage;

/**
 * @author Darius Wattimena
 * on 29-Dec-16.
 */
public class PlayerUtil {
    private static DatabaseAccessor databaseAccessor = Constants.databaseAccessor;
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
        databaseAccessor.enemyDM.delete(killedEnemy);
        earnXP(killedEnemy);
        Stage currentStage = player.getCurrentStage();
        StageUtil.checkStageIfNewNeeded(currentStage);
    }

    public static Player earnXP(Enemy killedEnemy){
        Integer currentXP = player.getXp();
        Integer totalXPNeeded = player.getTotalXPNeeded();
        Integer increaseXP = killedEnemy.getLevel();

        if((currentXP + increaseXP) >= totalXPNeeded){
            Integer XPLeft = (currentXP + increaseXP) - totalXPNeeded;
            levelUpPlayer(XPLeft);
        }
        else{
            player.setXp(currentXP + increaseXP);
        }

        return player;
    }

    public static Player levelUpPlayer(Integer XPLeft){
        player.setLevel(player.getLevel() + 1);
        player.setXp(XPLeft);
        player.setTotalXPNeeded(Constants.BEGIN_XP_TOTAL * player.getLevel());
        return player;
    }
}
