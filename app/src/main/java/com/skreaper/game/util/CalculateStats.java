package com.skreaper.game.util;

import android.support.annotation.NonNull;

import com.skreaper.game.ormlite.entity.Enemy;
import com.skreaper.game.ormlite.entity.Player;
import com.skreaper.game.ormlite.entity.Stats;

public class CalculateStats {

    public static Integer getPlayerDamage(Player player, Enemy enemy){
        Integer result = player.getStats().getAttack() - enemy.getStats().getDefense();
        if(result <= 0){
            return 0;
        }
        return result;
    }

    public static Integer getEnemyDamage(Player player, Enemy enemy){
        Integer result = enemy.getStats().getAttack() - player.getStats().getDefense();
        if(result <= 0){
            return 0;
        }
        return result;
    }

    public static boolean checkIfAlive(Integer health){
        return health > 0;
    }

    public static Stats calculateProfile(Stats stats){
        Integer attack = stats.getAttack();
        Double attackSpeed = stats.getAttackSpeed();
        Integer agility = stats.getAgility();
        Integer armor = stats.getArmor();
        Double block = stats.getBlock();
        Double crit = stats.getCrit();
        Double critDamage = stats.getCritDamage();
        Integer defense = stats.getDefense();
        Double dodge = stats.getDodge();
        Integer health = stats.getHealth();
        Integer intellect = stats.getIntellect();
        Double lifeSteal = stats.getLifeSteal();
        Integer mana = stats.getMana();
        Integer manaRegen = stats.getManaRegen();
        Integer strength = stats.getStrength();
        Double speed = stats.getSpeed();
        Integer spellPower = stats.getSpellPower();
        Integer vitality = stats.getVitality();

        if(vitality != 0){
            stats = calculateVitalityStats(stats, health, defense);
        }

        if(strength != 0){
            stats = calculateStrengthStats(stats, health, armor, lifeSteal);
        }
        if(agility != 0) {
            stats = calculateAgilityStats(stats, attackSpeed, speed);
        }
        if(intellect != 0){
            stats = calculateIntellectStats(stats, mana, manaRegen, spellPower);
        }

        return stats;
    }

    private static Stats calculateVitalityStats(Stats stats, Integer startHealth, Integer startDefense){
        Integer vitality = stats.getVitality();

        stats.setHealth(calculateStatValue(0.01, vitality, startHealth, stats.getHealth()));
        stats.setDefense(calculateStatValue(0.01, vitality, startDefense, stats.getDefense()));

        return stats;
    }

    private static Stats calculateStrengthStats(Stats stats, Integer startHealth, Integer startArmor, Double startLifeSteal){
        Integer strength = stats.getStrength();

        stats.setHealth(calculateStatValue(0.01, strength, startHealth, stats.getHealth()));

        if(startArmor != 0){
            stats.setArmor(calculateStatValue(0.03, strength, startArmor, stats.getArmor()));
        }

        if(startLifeSteal != 0.00){
            stats.setLifeSteal(calculateStatValue(0.02, strength, startLifeSteal, stats.getLifeSteal()));
        }
        return stats;
    }

    private static Stats calculateAgilityStats(Stats stats, Double startASpeed, Double startSpeed){
        Integer agility = stats.getAgility();

        if(startASpeed != 0.00){
            stats.setCrit(calculateStatValue(0.01, agility, startASpeed, stats.getAttackSpeed()));
        }

        if(startSpeed != 0.00){
            stats.setCrit(calculateStatValue(0.01, agility, startSpeed, stats.getSpeed()));
        }
        return stats;
    }

    private static Stats calculateIntellectStats(Stats stats, Integer startMana, Integer startManaRegen, Integer startSpellPower){
        Integer intellect = stats.getIntellect();

        stats.setMana(calculateStatValue(0.01, intellect, startMana, stats.getMana()));

        if(startManaRegen != 0){
            stats.setManaRegen(calculateStatValue(0.02, intellect, startManaRegen, stats.getManaRegen()));
        }

        if(startSpellPower != 0.00){
            stats.setSpellPower(calculateStatValue(0.05, intellect, startSpellPower, stats.getSpellPower()));
        }
        return stats;
    }

    @NonNull
    private static Integer calculateStatValue(Double increasement, Integer statLevel, Integer baseValue, Integer currentValue){
        Double totalMultiplier = (1.00 + (increasement * statLevel));
        Double totalNeededToAdd = (baseValue * totalMultiplier) - currentValue;
        return totalNeededToAdd.intValue() + currentValue;
    }

    @NonNull
    private static Double calculateStatValue(Double increasement, Integer statLevel, Double baseValue, Double currentValue){
        Double totalMultiplier = (1.00 + (increasement * statLevel));
        Double totalNeededToAdd = (baseValue * totalMultiplier) - currentValue;
        return totalNeededToAdd + currentValue;
    }
}
