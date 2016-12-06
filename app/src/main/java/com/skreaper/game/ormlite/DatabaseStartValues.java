package com.skreaper.game.ormlite;

import android.content.Context;

import com.skreaper.game.Constants;
import com.skreaper.game.ormlite.entity.Player;
import com.skreaper.game.ormlite.entity.Stats;

public class DatabaseStartValues {

    private DatabaseAdapter databaseAdapter;

    public DatabaseStartValues(Context context){
        databaseAdapter = new DatabaseAdapter(context);

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
        Player databasePlayer = databaseAdapter.playerDM.findFirst();

        //If empty create new one and insert in database
        if(databasePlayer == null) {
            Player user = new Player();
            user.setLevel(Constants.START_LEVEL);
            user.setName("Darius");
            user.setSkillpoints(0);
            user.setTotalSkillpoints(0);
            user.setGold(0);
            user.setDiamond(0);
            user.setStats(playerStatsProfile());
            databaseAdapter.playerDM.save(user);
        }
    }

    private Stats playerStatsProfile(){
        //Find Database Stats
        Stats databaseStats = databaseAdapter.statsDM.find("statsProfileName", "player");

        //If empty create new one and insert in database
        if(databaseStats == null){
            Stats stats = new Stats();
            stats.setStatsProfileName("playerStatsProfile");
            stats.setAttackDamage(10);
            stats.setDefense(3);
            stats.setVitality(100);
            databaseAdapter.statsDM.save(stats);
            return stats;
        }
        else{
            return databaseStats;
        }

    }

    private Stats enemyStatsProfile1(){
        //Find Database Stats
        Stats databaseStats = databaseAdapter.statsDM.find("statsProfileName", "enemy1");

        //If empty create new one and insert in database
        if(databaseStats == null) {
            Stats stats = new Stats();
            stats.setStatsProfileName("enemy1StatsProfile");
            stats.setAttackDamage(5);
            stats.setDefense(3);
            stats.setVitality(50);
            databaseAdapter.statsDM.save(stats);
            return stats;
        }
        else{
            return databaseStats;
        }
    }

    private Stats enemyStatsProfile2(){
        //Find Database Stats
        Stats databaseStats = databaseAdapter.statsDM.find("statsProfileName", "enemy2");

        //If empty create new one and insert in database
        if(databaseStats == null) {
            Stats stats = new Stats();
            stats.setStatsProfileName("enemy2StatsProfile");
            stats.setAttackDamage(10);
            stats.setDefense(8);
            stats.setVitality(30);
            databaseAdapter.statsDM.save(stats);
            return stats;
        }
        else{
            return databaseStats;
        }
    }

    private Stats enemyStatsProfile3(){
        //Find Database Stats
        Stats databaseStats = databaseAdapter.statsDM.find("statsProfileName", "enemy3");

        //If empty create new one and insert in database
        if(databaseStats == null) {
            Stats stats = new Stats();
            stats.setStatsProfileName("enemy3StatsProfile");
            stats.setAttackDamage(7);
            stats.setDefense(5);
            stats.setVitality(40);
            databaseAdapter.statsDM.save(stats);
            return stats;
        }
        else{
            return databaseStats;
        }
    }

    private Stats bossStatsProfile(){
        //Find Database Stats
        Stats databaseStats = databaseAdapter.statsDM.find("statsProfileName", "boss");

        //If empty create new one and insert in database
        if(databaseStats == null) {
            Stats stats = new Stats();
            stats.setStatsProfileName("bossStatsProfile");
            stats.setAttackDamage(5);
            stats.setDefense(25);
            stats.setVitality(150);
            databaseAdapter.statsDM.save(stats);
            return stats;
        }
        else{
            return databaseStats;
        }
    }
}
