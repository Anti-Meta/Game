package com.skreaper.game.util;

import com.skreaper.game.ormlite.entity.Enemy;

/**
 * @author Darius Wattimena
 *         on 29-Dec-16.
 */
public class EnemyUtil {

    public static Enemy getNewEnemy(Integer currentLevel){
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
        newEnemy.setName(newEnemy.getStats().getStatsProfileName() + " " + currentLevel+ ": " +  RandomValues.getName());
        return newEnemy;
    }
}
