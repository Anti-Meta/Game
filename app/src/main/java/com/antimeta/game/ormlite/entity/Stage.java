package com.antimeta.game.ormlite.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author Darius Wattimena
 *         on 01-Jan-17.
 */

@DatabaseTable
public class Stage implements Identifiable{
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    @DatabaseField
    private Integer level;

    @DatabaseField
    private Integer killsNeeded;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getKillsNeeded() {
        return killsNeeded;
    }

    public void setKillsNeeded(Integer killsNeeded) {
        this.killsNeeded = killsNeeded;
    }
}
