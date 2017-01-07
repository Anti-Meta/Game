package com.antimeta.game.ormlite.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Player implements Identifiable {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    @DatabaseField
    private Integer xp;

    @DatabaseField
    private Integer totalXPNeeded;

    @DatabaseField
    private Integer level;

    @DatabaseField
    private Integer skillpoints;

    @DatabaseField
    private Integer totalSkillpoints;

    @DatabaseField
    private Integer gold;

    @DatabaseField
    private Integer diamond;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Stats stats;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Stage currentStage;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public Integer getTotalXPNeeded() {
        return totalXPNeeded;
    }

    public void setTotalXPNeeded(Integer totalXPNeeded) {
        this.totalXPNeeded = totalXPNeeded;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSkillpoints() {
        return skillpoints;
    }

    public void setSkillpoints(Integer skillpoints) {
        this.skillpoints = skillpoints;
    }

    public Integer getTotalSkillpoints() {
        return totalSkillpoints;
    }

    public void setTotalSkillpoints(Integer totalSkillpoints) {
        this.totalSkillpoints = totalSkillpoints;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Integer getDiamond() {
        return diamond;
    }

    public void setDiamond(Integer diamond) {
        this.diamond = diamond;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }
}
