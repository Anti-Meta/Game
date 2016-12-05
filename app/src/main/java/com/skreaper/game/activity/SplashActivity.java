package com.skreaper.game.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.skreaper.game.ormlite.DatabaseHelper;
import com.skreaper.game.ormlite.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO add everything we need to load here before we show the main menu
        DatabaseHelper.setClasses(createDatabaseClassesList());

        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
        finish();
    }

    private List<Class> createDatabaseClassesList(){
        //TODO add all the needed entities
        List<Class> classes = new ArrayList<>();
        classes.add(Player.class);
        return classes;
    }
}
