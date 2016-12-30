package com.skreaper.game.util;

import com.skreaper.game.ormlite.entity.Enemy;

/**
 * @author Darius Wattimena
 *         on 29-Dec-16.
 */
public class EnemyUtil {

    public Enemy getNewEnemy(Integer currentLevel){
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
        newEnemy.setName(RandomValues.getName());
        newEnemy.setStats(RandomValues.getRandomEnemyStats());
        return newEnemy;
    }
}
