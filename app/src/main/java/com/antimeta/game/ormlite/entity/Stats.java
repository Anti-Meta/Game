package com.antimeta.game.ormlite.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Stats implements Identifiable {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String statsProfileName;

    //PRIME STATS
    @DatabaseField
    private Integer health;

    @DatabaseField
    private Integer mana;

    @DatabaseField
    private Integer attack;

    @DatabaseField
    private Integer defense;

    //PRIME SUB-STATS

    /**
     * Health +++
     * Mana ~
     * Attack ~
     * Defense +
     */
    @DatabaseField
    private Integer vitality;

    /**
     * Health +
     * Mana ~
     * Attack ++
     * Defense +++
     */
    @DatabaseField
    private Integer strength;

    /**
     * Health +
     * Mana ~
     * Attack +++
     * Defense +
     */
    @DatabaseField
    private Integer agility;

    /**
     * Health ~
     * Mana +++
     * Attack +
     * Defense ~
     */
    @DatabaseField
    private Integer intellect;

    //ATTACK STATS
    @DatabaseField
    private Integer spellPower;

    @DatabaseField
    private Double attackSpeed;

    @DatabaseField
    private Double crit;

    @DatabaseField
    private Double critDamage;

    //DEFENSE STATS
    @DatabaseField
    private Integer armor;

    @DatabaseField
    private Double dodge;

    @DatabaseField
    private Double block;

    //SUB STATS
    @DatabaseField
    private Double speed;

    @DatabaseField
    private Double lifeSteal;

    @DatabaseField
    private Integer manaRegen;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatsProfileName() {
        return statsProfileName;
    }

    public void setStatsProfileName(String statsProfileName) {
        this.statsProfileName = statsProfileName;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getMana() {
        return mana;
    }

    public void setMana(Integer mana) {
        this.mana = mana;
    }


    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Integer getVitality() {
        return vitality;
    }

    public void setVitality(Integer vitality) {
        this.vitality = vitality;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getAgility() {
        return agility;
    }

    public void setAgility(Integer agility) {
        this.agility = agility;
    }

    public Integer getIntellect() {
        return intellect;
    }

    public void setIntellect(Integer intellect) {
        this.intellect = intellect;
    }

    public Integer getSpellPower() {
        return spellPower;
    }

    public void setSpellPower(Integer spellPower) {
        this.spellPower = spellPower;
    }

    public Double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(Double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public Double getCrit() {
        return crit;
    }

    public void setCrit(Double crit) {
        this.crit = crit;
    }

    public Double getCritDamage() {
        return critDamage;
    }

    public void setCritDamage(Double critDamage) {
        this.critDamage = critDamage;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public Double getDodge() {
        return dodge;
    }

    public void setDodge(Double dodge) {
        this.dodge = dodge;
    }

    public Double getBlock() {
        return block;
    }

    public void setBlock(Double block) {
        this.block = block;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getLifeSteal() {
        return lifeSteal;
    }

    public void setLifeSteal(Double lifeSteal) {
        this.lifeSteal = lifeSteal;
    }

    public Integer getManaRegen() {
        return manaRegen;
    }

    public void setManaRegen(Integer manaRegen) {
        this.manaRegen = manaRegen;
    }
}
