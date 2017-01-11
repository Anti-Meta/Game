package com.antimeta.game.util;

import com.antimeta.game.ormlite.entity.Enemy;

/**
 * @author Darius Wattimena
 *         on 29-Dec-16.
 */
public class EnemyUtil {
    public static Enemy getNewEnemy(){
        Integer currentLevel = PlayerUtil.getCurrentPlayerStage().getLevel();
        Enemy newEnemy = new Enemy();
        Float randomFloat = RandomValues.getRandomFloat();
        if(randomFloat >= 0.65f)
        {
            currentLevel += 1;
        }
        else if(randomFloat >= 0.80f){
            currentLevel += 2;
        }
        newEnemy.setLevel(currentLevel);
        newEnemy.setStats(RandomValues.getRandomEnemyStats(currentLevel));
        newEnemy.setName(currentLevel+ ": " +  RandomValues.getName(newEnemy.getStats().getStatsProfileName()));
        return newEnemy;
    }
}
