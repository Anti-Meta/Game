package com.skreaper.game;

import android.content.Context;

import com.skreaper.game.ormlite.DatabaseAccessor;

public class Constants {
    public final static String DATABASE_NAME = "database.db";
    public final static int DATABASE_CODE = 1;
    public static int CURRENT_LEVEL = 10;

    public static DatabaseAccessor databaseAccessor;

    public static void setCurrentLevel(int currentLevel) {
        CURRENT_LEVEL = currentLevel;
    }

    public Constants(Context context){
        databaseAccessor = new DatabaseAccessor(context);
    }
}
