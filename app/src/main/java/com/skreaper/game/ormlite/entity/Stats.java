package com.skreaper.game.ormlite.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Stats implements Identifiable {

    @DatabaseField(generatedId = true)
    int id;

    //PRIME STATS
    @DatabaseField
    private Integer vitality;

    /*@DatabaseField
    private Integer strength;

    @DatabaseField
    private Integer agility;

    @DatabaseField
    private Integer intellect;*/

    //ATTACK STATS
    @DatabaseField
    private Integer attackDamage;

    /*@DatabaseField
    private Integer spellPower;

    @DatabaseField
    private Double attackSpeed;

    @DatabaseField
    private Double crit;

    @DatabaseField
    private Double critDamage;*/

    //DEFENSE STATS
    @DatabaseField
    private Integer defense;

    /*@DatabaseField
    private Integer armor;

    @DatabaseField
    private Double dodge;

    @DatabaseField
    private Double block;*/

    //SUB STATS
    /*@DatabaseField
    private Double speed;

    @DatabaseField
    private Double lifeSteal;

    @DatabaseField
    private Integer mana;

    @DatabaseField
    private Integer manaRegen;*/

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getVitality() {
        return vitality;
    }

    public void setVitality(Integer vitality) {
        this.vitality = vitality;
    }

    public Integer getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(Integer attackDamage) {
        this.attackDamage = attackDamage;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }
}
