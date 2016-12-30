package com.antimeta.game.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.antimeta.game.Constants;
import com.antimeta.game.ormlite.DatabaseHelper;
import com.antimeta.game.ormlite.DatabaseStartValues;
import com.antimeta.game.ormlite.entity.Enemy;
import com.antimeta.game.ormlite.entity.Equipment;
import com.antimeta.game.ormlite.entity.EquipmentType;
import com.antimeta.game.ormlite.entity.NPC;
import com.antimeta.game.ormlite.entity.Player;
import com.antimeta.game.ormlite.entity.Stats;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO add everything we need to load here before we show the main menu
        DatabaseHelper.setClasses(createDatabaseClassesList());
        new Constants(getApplicationContext());
        new DatabaseStartValues();

        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
        finish();
    }

    private List<Class> createDatabaseClassesList(){
        //TODO add all the needed entities
        List<Class> classes = new ArrayList<>();
        classes.add(Player.class);
        classes.add(Equipment.class);
        classes.add(EquipmentType.class);
        classes.add(Stats.class);
        classes.add(NPC.class);
        classes.add(Enemy.class);
        return classes;
    }
}
