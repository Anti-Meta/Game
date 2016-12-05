package com.skreaper.game.ormlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.Collection;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static Collection<Class> CLASSES;

    public DatabaseHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        for (Class item : CLASSES){
            try {
                TableUtils.createTable(connectionSource, item);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        for (Class item : CLASSES){
            try {
                TableUtils.dropTable(connectionSource, item, true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        onCreate(database,connectionSource);
    }

    public static void setClasses(Collection<Class> returnedClasses) {
        CLASSES = returnedClasses;
    }
}
