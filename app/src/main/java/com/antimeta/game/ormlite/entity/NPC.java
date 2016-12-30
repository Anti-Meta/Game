package com.antimeta.game.ormlite.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class NPC implements Identifiable {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    @DatabaseField
    private Integer level;

    @DatabaseField
    private Integer skillpoints;

    @DatabaseField
    private Integer totalSkillpoints;

    @DatabaseField(foreign = true)
    private Stats stats;

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

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }
}
