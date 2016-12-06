package com.skreaper.game.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.skreaper.game.R;
import com.skreaper.game.ormlite.DatabaseAdapter;
import com.skreaper.game.ormlite.entity.Enemy;
import com.skreaper.game.ormlite.entity.Player;
import com.skreaper.game.util.CalculateStats;
import com.skreaper.game.util.RandomValues;

public class StartActivity extends AppCompatActivity {
    private DatabaseAdapter databaseAdapter;
    private RandomValues randomValues;
    private CalculateStats calculator;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        databaseAdapter = new DatabaseAdapter(this);
        randomValues = new RandomValues(this);
        calculator = new CalculateStats();

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
                currentEnemyHealth -= calculator.getPlayerDamage(currentPlayer, currentEnemy);
                currentPlayerHealth -= calculator.getEnemyDamage(currentPlayer, currentEnemy);
                if(calculator.checkIfAlive(currentEnemyHealth)) {
                    updateEnemyHealthOnScreen();
                }
                else {
                    databaseAdapter.enemyDM.delete(currentEnemy);
                    getEnemyData();
                    updateEnemyDataOnScreen();
                    Log.d("StartActivity", "Found new Enemy");
                }

                //TODO for now set player health back to 100
                if(calculator.checkIfAlive(currentPlayerHealth)){
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
        Enemy databaseEnemy = databaseAdapter.enemyDM.findFirst();

        if(databaseEnemy == null){
            Enemy newEnemy = new Enemy();
            newEnemy.setName(randomValues.getName());
            newEnemy.setStats(randomValues.getRandomEnemyStats());
            newEnemy.setLevel(1);
            databaseAdapter.enemyDM.save(newEnemy);
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
        currentPlayer = databaseAdapter.playerDM.findFirst();
        databaseAdapter.statsDM.refresh(currentPlayer.getStats());
        currentPlayerName = currentPlayer.getName();
        currentPlayerHealth = currentPlayer.getStats().getVitality();
        currentPlayerAttack = currentPlayer.getStats().getAttackDamage();
        currentPlayerDefense = currentPlayer.getStats().getDefense();
    }

    private void getEnemyData(){
        currentEnemy = findEnemy();
        databaseAdapter.statsDM.refresh(currentEnemy.getStats());
        currentEnemyName = currentEnemy.getName();
        currentEnemyHealth = currentEnemy.getStats().getVitality();
        currentEnemyAttack = currentEnemy.getStats().getAttackDamage();
        currentEnemyDefense = currentEnemy.getStats().getDefense();
    }
}
