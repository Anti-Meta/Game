package com.antimeta.game.ormlite;

import com.antimeta.game.Constants;
import com.antimeta.game.ormlite.entity.EquipmentType;
import com.antimeta.game.ormlite.entity.Player;
import com.antimeta.game.ormlite.entity.Stats;
import com.antimeta.game.util.PlayerUtil;
import com.antimeta.game.util.StageUtil;

public class DatabaseStartValues {

    private DatabaseAccessor databaseAccessor = Constants.databaseAccessor;

    public DatabaseStartValues(){
        createDatabaseDataIfNeeded();
    }

    private void createDatabaseDataIfNeeded(){
        insertStatsProfiles();
        insertPlayer();
        insertEquipmentTypes();
    }

    private void insertEquipmentTypeStat(String equipmentName, String equipmentType){
        Stats databaseStats = databaseAccessor.statsDM.find("statsProfileName", equipmentType+equipmentName);

        if(databaseStats == null){
            Stats stats = new Stats();
            stats.setStatsProfileName(equipmentName);
            stats.setAttack(0);
            stats.setAttackSpeed(0.00);
            stats.setArmor(0);
            stats.setBlock(0.00);
            stats.setCrit(0.00);
            stats.setCritDamage(0.00);
            stats.setDefense(0);
            stats.setDodge(0.00);
            stats.setHealth(0);
            stats.setLifeSteal(0.00);
            stats.setMana(0);
            stats.setManaRegen(0);
            stats.setSpeed(0.00);
            stats.setSpellPower(0);

            stats = insertEquipmentTypeStatWithType(stats, equipmentType, equipmentName);

            databaseAccessor.statsDM.save(stats);
        }
    }

    private Stats insertEquipmentTypeStatWithType(Stats stats, String type, String name) {
        Integer vitality, strength, agility, intellect;
        switch(name){
            case "Helm":
                vitality = 2;
                strength = 1;
                agility = 1;
                intellect = 1;
                break;
            case "Chestplate":
                vitality = 4;
                strength = 3;
                agility = 3;
                intellect = 3;
                break;
            case "Platelegs":
                vitality = 3;
                strength = 2;
                agility = 2;
                intellect = 2;
                break;
            case "Weapon":
                stats = insertWeaponStats(stats, type);
                return stats;
            case "Ring":
                //TODO maak een stats calculator voor ringen.
                vitality = 2;
                strength = 2;
                agility = 2;
                intellect = 2;
                break;
            default:
                vitality = 0;
                strength = 0;
                agility = 0;
                intellect = 0;
        }

        switch (type){
            case "Plate":
                stats.setVitality(vitality + 1);
                stats.setStrength(strength + 1);
                stats.setAgility(0);
                stats.setIntellect(0);
                break;
            case "Leather":
                stats.setVitality(vitality);
                stats.setStrength(0);
                stats.setAgility(agility + 2);
                stats.setIntellect(0);
                break;
            case "Cloth":
                stats.setVitality(vitality - 1);
                stats.setStrength(0);
                stats.setAgility(0);
                stats.setIntellect(intellect + 3);
                break;
        }
        return stats;
    }

    private Stats insertWeaponStats(Stats stats, String type){
        switch (type){
            case "Plate":
                stats.setVitality(1);
                stats.setStrength(5);
                stats.setAgility(0);
                stats.setIntellect(0);
            case "Leather":
                stats.setVitality(1);
                stats.setStrength(0);
                stats.setAgility(5);
                stats.setIntellect(0);
            case "Cloth":
                stats.setVitality(1);
                stats.setStrength(0);
                stats.setAgility(0);
                stats.setIntellect(5);
        }
        return stats;
    }

    private void insertEquipmentTypes() {
        insertEquipmentType("Helm");
        insertEquipmentTypeStat("Helm", "Plate");
        insertEquipmentTypeStat("Helm", "Leather");
        insertEquipmentTypeStat("Helm", "Cloth");
        insertEquipmentType("Chestplate");
        insertEquipmentTypeStat("Chestplate", "Plate");
        insertEquipmentTypeStat("Chestplate", "Leather");
        insertEquipmentTypeStat("Chestplate", "Cloth");
        insertEquipmentType("Platelegs");
        insertEquipmentTypeStat("Platelegs", "Plate");
        insertEquipmentTypeStat("Platelegs", "Leather");
        insertEquipmentTypeStat("Platelegs", "Cloth");
        insertEquipmentType("Weapon");
        insertEquipmentTypeStat("Weapon", "Plate");
        insertEquipmentTypeStat("Weapon", "Leather");
        insertEquipmentTypeStat("Weapon", "Cloth");
        insertEquipmentType("Ring");
        insertEquipmentTypeStat("Ring", "Plate");
        insertEquipmentTypeStat("Ring", "Leather");
        insertEquipmentTypeStat("Ring", "Cloth");
    }

    private void insertEquipmentType(String equipmentName) {
        EquipmentType databaseType = databaseAccessor.equipmentTypeDM.find("Name", equipmentName);

        if(databaseType == null){
            EquipmentType equipmentType = new EquipmentType();
            equipmentType.setName(equipmentName);
            databaseAccessor.equipmentTypeDM.save(equipmentType);
        }
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
            user.setXp(0);
            user.setTotalXPNeeded(50);
            user.setSkillpoints(0);
            user.setTotalSkillpoints(0);
            user.setGold(0);
            user.setDiamond(0);
            user.setStats(playerStatsProfile());
            user.setCurrentStage(StageUtil.createNewLevelOneStage());
            databaseAccessor.playerDM.save(user);
            PlayerUtil.player = user;
        }
        else{
            PlayerUtil.player = databasePlayer;
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
