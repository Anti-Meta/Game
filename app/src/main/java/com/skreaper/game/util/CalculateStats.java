package com.skreaper.game.util;

import com.skreaper.game.ormlite.entity.Enemy;
import com.skreaper.game.ormlite.entity.Player;

public class CalculateStats {

    public Integer getPlayerDamage(Player player, Enemy enemy){
        Integer result = player.getStats().getAttackDamage() - enemy.getStats().getDefense();
        if(result <= 0){
            return 0;
        }
        return result;
    }

    public Integer getEnemyDamage(Player player, Enemy enemy){
        Integer result = enemy.getStats().getAttackDamage() - player.getStats().getDefense();
        if(result <= 0){
            return 0;
        }
        return result;
    }

    public boolean checkIfAlive(Integer health){
        return health > 0;
    }
}
