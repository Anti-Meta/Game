package com.skreaper.game.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.skreaper.game.Constants;
import com.skreaper.game.R;
import com.skreaper.game.ormlite.DatabaseAccessor;
import com.skreaper.game.ormlite.entity.Enemy;
import com.skreaper.game.ormlite.entity.Player;
import com.skreaper.game.util.CalculateStats;
import com.skreaper.game.util.EnemyUtil;

public class StartActivity extends AppCompatActivity {
    private DatabaseAccessor databaseAccessor = Constants.databaseAccessor;

    private Enemy currentEnemy;
    private Player currentPlayer;

    private String currentPlayerName;
    private Integer currentPlayerHealth;
    private Integer currentPlayerAttack;
    private Integer currentPlayerDefense;

    private String currentEnemyName;
    private Integer currentEnemyHealth;
    private Integer currentEnemyAttack;
    private Integer currentEnemyDefense;

    private boolean keepSearching = true;

    private TextView textViewPlayerName;
    private TextView textViewPlayerHealth;
    private TextView textViewPlayerAttack;
    private TextView textViewPlayerDefense;
    private TextView textViewEnemyName;
    private TextView textViewEnemyHealth;
    private TextView textViewEnemyAttack;
    private TextView textViewEnemyDefense;

    public StartActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        getDataForScreen();
        getScreenTextFields();

        //TODO set screen values
        setClickListener();
        setDataOnScreen();
    }

    private void searchForEnemies(){
        //TODO maak random for zoek kans voor enemies
        while(keepSearching){
            if(currentEnemy == null){
                currentEnemy = findEnemy();
            }
            //Anders doe niks
        }
    }

    public void setClickListener(){
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.startScreen);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentEnemyHealth -= CalculateStats.getPlayerDamage(currentPlayer, currentEnemy);
                currentPlayerHealth -= CalculateStats.getEnemyDamage(currentPlayer, currentEnemy);
                if(CalculateStats.checkIfAlive(currentEnemyHealth)) {
                    updateEnemyHealthOnScreen();
                }
                else {
                    databaseAccessor.enemyDM.delete(currentEnemy);
                    getEnemyData();
                    updateEnemyDataOnScreen();
                    Log.d("StartActivity", "Found new Enemy");
                }

                //TODO for now set player health back to 100
                if(CalculateStats.checkIfAlive(currentPlayerHealth)){
                    updatePlayerHealthOnScreen();
                }
                else {
                    currentPlayerHealth = 100;
                    updatePlayerHealthOnScreen();
                }
            }
        });
    }

    private Enemy findEnemy(){
        Enemy databaseEnemy = databaseAccessor.enemyDM.findFirst();

        if(databaseEnemy == null){
            Enemy newEnemy = EnemyUtil.getNewEnemy(Constants.CURRENT_LEVEL);
            databaseAccessor.enemyDM.save(newEnemy);
            return newEnemy;
        }
        else{
            return databaseEnemy;
        }
    }

    private void getDataForScreen(){
        getPlayerData();
        getEnemyData();
    }

    private void setDataOnScreen(){
        textViewPlayerName.setText(currentPlayerName);
        textViewPlayerHealth.setText(currentPlayerHealth.toString());
        textViewPlayerAttack.setText(currentPlayerAttack.toString());
        textViewPlayerDefense.setText(currentPlayerDefense.toString());

        textViewEnemyName.setText(currentEnemyName);
        textViewEnemyHealth.setText(currentEnemyHealth.toString());
        textViewEnemyAttack.setText(currentEnemyAttack.toString());
        textViewEnemyDefense.setText(currentEnemyDefense.toString());
    }

    private void updatePlayerHealthOnScreen(){
        textViewPlayerHealth.setText(currentPlayerHealth.toString());
    }

    private void updatePlayerDataOnScreen(){
        textViewPlayerName.setText(currentPlayerName);
        textViewPlayerHealth.setText(currentPlayerHealth.toString());
        textViewPlayerAttack.setText(currentPlayerAttack.toString());
        textViewPlayerDefense.setText(currentPlayerDefense.toString());
    }

    private void updateEnemyHealthOnScreen(){
        textViewEnemyHealth.setText(currentEnemyHealth.toString());
    }

    private void updateEnemyDataOnScreen(){
        textViewEnemyName.setText(currentEnemyName);
        textViewEnemyHealth.setText(currentEnemyHealth.toString());
        textViewEnemyAttack.setText(currentEnemyAttack.toString());
        textViewEnemyDefense.setText(currentEnemyDefense.toString());
    }

    private void getScreenTextFields(){
        textViewPlayerName = (TextView) findViewById(R.id.textViewPlayerName);
        textViewPlayerHealth = (TextView) findViewById(R.id.textViewPlayerHealth);
        textViewPlayerAttack = (TextView) findViewById(R.id.textViewPlayerAttack);
        textViewPlayerDefense = (TextView) findViewById(R.id.textViewPlayerDefense);

        textViewEnemyName = (TextView) findViewById(R.id.textViewEnemyName);
        textViewEnemyHealth = (TextView) findViewById(R.id.textViewEnemyHealth);
        textViewEnemyAttack = (TextView) findViewById(R.id.textViewEnemyAttack);
        textViewEnemyDefense = (TextView) findViewById(R.id.textViewEnemyDefense);
    }

    private void getPlayerData(){
        currentPlayer = databaseAccessor.playerDM.findFirst();
        databaseAccessor.statsDM.refresh(currentPlayer.getStats());
        currentPlayerName = currentPlayer.getName();
        currentPlayerHealth = currentPlayer.getStats().getHealth();
        currentPlayerAttack = currentPlayer.getStats().getAttack();
        currentPlayerDefense = currentPlayer.getStats().getDefense();
    }

    private void getEnemyData(){
        currentEnemy = findEnemy();
        currentEnemyName = currentEnemy.getName();
        currentEnemyHealth = currentEnemy.getStats().getHealth();
        currentEnemyAttack = currentEnemy.getStats().getAttack();
        currentEnemyDefense = currentEnemy.getStats().getDefense();
    }
}
