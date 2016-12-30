package com.antimeta.game.ormlite;

import android.content.Context;

import com.antimeta.game.ormlite.entity.Enemy;
import com.antimeta.game.ormlite.entity.Equipment;
import com.antimeta.game.ormlite.entity.EquipmentType;
import com.antimeta.game.ormlite.entity.NPC;
import com.antimeta.game.ormlite.entity.Player;
import com.antimeta.game.ormlite.entity.Stats;

public class DatabaseAccessor {
    public DatabaseManager<Player> playerDM;
    public DatabaseManager<NPC> npcDM;
    public DatabaseManager<Enemy> enemyDM;
    public DatabaseManager<Stats> statsDM;
    public DatabaseManager<Equipment> equipmentDM;
    public DatabaseManager<EquipmentType> equipmentTypeDM;

    public DatabaseAccessor(Context context){
        playerDM = new DatabaseManager<>(context, Player.class);
        npcDM = new DatabaseManager<>(context, NPC.class);
        enemyDM = new DatabaseManager<>(context, Enemy.class);
        statsDM = new DatabaseManager<>(context, Stats.class);
        equipmentDM = new DatabaseManager<>(context, Equipment.class);
        equipmentTypeDM = new DatabaseManager<>(context, EquipmentType.class);
    }

    //CUSTOM SQL BELOW!!

}
