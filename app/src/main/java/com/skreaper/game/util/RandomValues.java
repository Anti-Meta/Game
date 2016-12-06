package com.skreaper.game.util;

import android.content.Context;

import com.skreaper.game.ormlite.DatabaseAdapter;
import com.skreaper.game.ormlite.entity.Stats;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomValues {
    final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final Random r = new Random();
    final Set<String> identifiers = new HashSet<>();

    private DatabaseAdapter databaseAdapter;

    public RandomValues(Context context){
        databaseAdapter = new DatabaseAdapter(context);
    }

    public String getName() {
        StringBuilder builder = new StringBuilder();
        while(builder.toString().length() == 0) {
            int length = r.nextInt(5)+5;
            for(int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(r.nextInt(lexicon.length())));
            }
            if(identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }

    public Stats getRandomEnemyStats(){
        int randomProfileNumber = r.nextInt(4 - 1) + 1;
        return getStats(randomProfileNumber);
    }

    public Stats getStats(int profileNumber){
        Stats result = null;
        switch(profileNumber) {
            case 1:
                result = databaseAdapter.statsDM.find("statsProfileName", "enemy1StatsProfile");
                break;
            case 2:
                result = databaseAdapter.statsDM.find("statsProfileName", "enemy2StatsProfile");
                break;
            case 3:
                result = databaseAdapter.statsDM.find("statsProfileName", "enemy3StatsProfile");
                break;
        }
        return result;
    }
}
