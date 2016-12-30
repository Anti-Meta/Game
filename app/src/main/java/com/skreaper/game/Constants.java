package com.skreaper.game;

import android.content.Context;

import com.skreaper.game.ormlite.DatabaseAccessor;

public class Constants {
    public final static String DATABASE_NAME = "database.db";
    public final static int DATABASE_CODE = 1;
    public static int START_LEVEL = 1;

    public static DatabaseAccessor databaseAccessor;

    public static void setStartLevel(int startLevel) {
        START_LEVEL = startLevel;
    }

    public Constants(Context context){
        databaseAccessor = new DatabaseAccessor(context);
    }
}
