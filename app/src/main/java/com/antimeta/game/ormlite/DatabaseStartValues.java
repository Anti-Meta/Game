package com.antimeta.game.ormlite;

import com.antimeta.game.Constants;
import com.antimeta.game.ormlite.entity.Player;
import com.antimeta.game.ormlite.entity.Stats;
import com.antimeta.game.util.StageUtil;

public class DatabaseStartValues {

    private DatabaseAccessor databaseAccessor = Constants.databaseAccessor;

    public DatabaseStartValues(){
        createDatabaseDataIfNeeded();
    }

    private void createDatabaseDataIfNeeded(){
        insertStatsProfiles();
        insertPlayer();
    }

    private void insertStatsProfiles(){
        enemyStatsProfile1();
        enemyStatsProfile2();
        enemyStatsProfile3();
        bossStatsProfile();
    }

    private void insertPlayer(){
        //Find Database Stats
        Player databasePlayer = databaseAccessor.playerDM.findFirst();

        //If empty create new one and insert in database
        if(databasePlayer == null) {
            Player user = new Player();
            user.setLevel(Constants.CURRENT_LEVEL);
            user.setName("Darius");
            user.setSkillpoints(0);
            user.setTotalSkillpoints(0);
            user.setGold(0);
            user.setDiamond(0);
            user.setStats(playerStatsProfile());
            user.setCurrentStage(StageUtil.createNewLevelOneStage());
            databaseAccessor.playerDM.save(user);
        }
    }

    private Stats playerStatsProfile(){
        //Find Database Stats
        Stats databaseStats = databaseAccessor.statsDM.find("statsProfileName", "player");

        //If empty create new one and insert in database
        if(databaseStats == null){
            Stats stats = new Stats();
            stats.setStatsProfileName("playerStatsProfile");
            stats.setAttack(100);
            stats.setAttackSpeed(1.00);
            stats.setAgility(0);
            stats.setArmor(0);
            stats.setBlock(0.00);
            stats.setCrit(0.05);
            stats.setCritDamage(1.02);
            stats.setDefense(0);
            stats.setDodge(0.00);
            stats.setHealth(0);
            stats.setIntellect(0);
            stats.setLifeSteal(0.00);
            stats.setMana(100);
            stats.setManaRegen(2);
            stats.setStrength(0);
            stats.setSpeed(0.00);
            stats.setSpellPower(5);
            stats.setVitality(10);
            databaseAccessor.statsDM.save(stats);
            return stats;
        }
        else{
            return databaseStats;
        }

    }

    private Stats enemyStatsProfile1(){
        //Find Database Stats
        Stats databaseStats = databaseAccessor.statsDM.find("statsProfileName", "Knight");

        //If empty create new one and insert in database
        if(databaseStats == null) {
            Stats stats = new Stats();
            stats.setStatsProfileName("Knight");
            stats.setAttack(0);
            stats.setAttackSpeed(0.00);
            stats.setAgility(0);
            stats.setArmor(15);
            stats.setBlock(0.02);
            stats.setCrit(0.00);
            stats.setCritDamage(0.00);
            stats.setDefense(5);
            stats.setDodge(0.00);
            stats.setHealth(50);
            stats.setIntellect(0);
            stats.setLifeSteal(0.00);
            stats.setMana(0);
            stats.setManaRegen(0);
            stats.setStrength(5);
            stats.setSpeed(0.00);
            stats.setSpellPower(0);
            stats.setVitality(10);
            databaseAccessor.statsDM.save(stats);
            return stats;
        }
        else{
            return databaseStats;
        }
    }

    private Stats enemyStatsProfile2(){
        //Find Database Stats
        Stats databaseStats = databaseAccessor.statsDM.find("statsProfileName", "Ranger");

        //If empty create new one and insert in database
        if(databaseStats == null) {
            Stats stats = new Stats();
            stats.setStatsProfileName("Ranger");
            stats.setAttack(0);
            stats.setAttackSpeed(0.00);
            stats.setAgility(5);
            stats.setArmor(10);
            stats.setBlock(0.02);
            stats.setCrit(0.00);
            stats.setCritDamage(0.00);
            stats.setDefense(3);
            stats.setDodge(0.00);
            stats.setHealth(50);
            stats.setIntellect(0);
            stats.setLifeSteal(0.00);
            stats.setMana(0);
            stats.setManaRegen(0);
            stats.setStrength(0);
            stats.setSpeed(0.00);
            stats.setSpellPower(0);
            stats.setVitality(7);
            databaseAccessor.statsDM.save(stats);
            return stats;
        }
        else{
            return databaseStats;
        }
    }

    private Stats enemyStatsProfile3(){
        //Find Database Stats
        Stats databaseStats = databaseAccessor.statsDM.find("statsProfileName", "Mage");

        //If empty create new one and insert in database
        if(databaseStats == null) {
            Stats stats = new Stats();
            stats.setStatsProfileName("Mage");
            stats.setAttack(0);
            stats.setAttackSpeed(0.00);
            stats.setAgility(5);
            stats.setArmor(10);
            stats.setBlock(0.02);
            stats.setCrit(0.00);
            stats.setCritDamage(0.00);
            stats.setDefense(2);
            stats.setDodge(0.00);
            stats.setHealth(50);
            stats.setIntellect(0);
            stats.setLifeSteal(0.00);
            stats.setMana(0);
            stats.setManaRegen(0);
            stats.setStrength(0);
            stats.setSpeed(0.00);
            stats.setSpellPower(0);
            stats.setVitality(5);
            databaseAccessor.statsDM.save(stats);
            return stats;
        }
        else{
            return databaseStats;
        }
    }

    private Stats bossStatsProfile(){
        //Find Database Stats
        Stats databaseStats = databaseAccessor.statsDM.find("statsProfileName", "Boss");

        //If empty create new one and insert in database
        if(databaseStats == null) {
            Stats stats = new Stats();
            stats.setStatsProfileName("Boss");
            stats.setAttack(0);
            stats.setAttackSpeed(0.00);
            stats.setAgility(5);
            stats.setArmor(10);
            stats.setBlock(0.05);
            stats.setCrit(0.00);
            stats.setCritDamage(0.00);
            stats.setDefense(1);
            stats.setDodge(0.00);
            stats.setHealth(200);
            stats.setIntellect(0);
            stats.setLifeSteal(0.00);
            stats.setMana(0);
            stats.setManaRegen(0);
            stats.setStrength(0);
            stats.setSpeed(0.00);
            stats.setSpellPower(0);
            stats.setVitality(10);
            databaseAccessor.statsDM.save(stats);
            return stats;
        }
        else{
            return databaseStats;
        }
    }
}
