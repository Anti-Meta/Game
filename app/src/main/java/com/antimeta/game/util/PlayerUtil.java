package com.antimeta.game.util;

import com.antimeta.game.Constants;
import com.antimeta.game.ormlite.entity.Player;
import com.antimeta.game.ormlite.entity.Stage;

/**
 * @author Darius Wattimena
 * on 29-Dec-16.
 */
public class PlayerUtil {
    private static Player player;
    public static Player getCurrentPlayer(){
        if(player == null){
            Player databasePlayer = Constants.databaseAccessor.playerDM.findFirst();
            if(databasePlayer == null){
                return null;
            }
            else{
                return databasePlayer;
            }
        }
        else{
            return player;
        }
    }

    public static void setCurrentPlayer(Player player){
        PlayerUtil.player = player;
    }

    public static Integer getCurrentPlayerLevel(){
        return player.getLevel();
    }

    public static Stage getCurrentPlayerStage(){
        return player.getCurrentStage();
    }

    public static void processKilledEnemy(){
        Stage currentStage = player.getCurrentStage();
        StageUtil.proceedKill(currentStage);
    }
}
