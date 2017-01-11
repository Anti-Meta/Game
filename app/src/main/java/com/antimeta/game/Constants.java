package com.antimeta.game;

import android.content.Context;

import com.antimeta.game.ormlite.DatabaseAccessor;

public class Constants {
    public final static String DATABASE_NAME = "database.db";
    public final static int DATABASE_CODE = 1;
    public static int BEGIN_XP_TOTAL = 25;
    public static int CURRENT_LEVEL = 1;

    public static DatabaseAccessor databaseAccessor;

    public static void setCurrentLevel(int currentLevel) {
        CURRENT_LEVEL = currentLevel;
    }

    public Constants(Context context){
        databaseAccessor = new DatabaseAccessor(context);
    }
}
