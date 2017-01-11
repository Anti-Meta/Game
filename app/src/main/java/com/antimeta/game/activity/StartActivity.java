package com.antimeta.game.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.antimeta.game.Constants;
import com.antimeta.game.R;
import com.antimeta.game.ormlite.DatabaseAccessor;
import com.antimeta.game.ormlite.entity.Enemy;
import com.antimeta.game.ormlite.entity.Player;
import com.antimeta.game.ormlite.entity.Stage;
import com.antimeta.game.util.StatsUtil;
import com.antimeta.game.util.EnemyUtil;
import com.antimeta.game.util.PlayerUtil;

public class StartActivity extends AppCompatActivity {
    private DatabaseAccessor databaseAccessor = Constants.databaseAccessor;

    private Enemy currentEnemy;
    private Player currentPlayer;
    private Stage currentStage;

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

    private TextView textViewStage;

    private ProgressBar xpBar;

    private Boolean threadRunning = false;
    private Boolean xpIncrease;
    private Handler xpHandler = new Handler();

    public StartActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        getDataForScreen();
        getScreenIds();


        //TODO set screen values
        setClickListener();
        setDataOnScreen();

        createXPBarThread();

        xpIncrease = true;
        updatePlayerDataOnScreenForKill();
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
                currentEnemyHealth -= StatsUtil.getPlayerDamage(currentPlayer, currentEnemy);
                currentPlayerHealth -= StatsUtil.getEnemyDamage(currentPlayer, currentEnemy);
                if(StatsUtil.checkIfAlive(currentEnemyHealth)) {
                    updateEnemyHealthOnScreen();
                }
                else {
                    PlayerUtil.processKilledEnemy(currentEnemy);
                    getEnemyData();
                    updateEnemyDataOnScreen();
                    xpIncrease = true;
                    updatePlayerDataOnScreenForKill();
                    Log.d("StartActivity", "Found new Enemy");
                }

                //TODO for now set player health back to 100
                if(StatsUtil.checkIfAlive(currentPlayerHealth)){
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
            Enemy newEnemy = EnemyUtil.getNewEnemy();
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
        textViewPlayerName.setText(currentPlayerName + " " + currentPlayer.getLevel());
        textViewPlayerHealth.setText(currentPlayerHealth.toString());
        textViewPlayerAttack.setText(currentPlayerAttack.toString());
        textViewPlayerDefense.setText(currentPlayerDefense.toString());

        textViewEnemyName.setText(currentEnemyName);
        textViewEnemyHealth.setText(currentEnemyHealth.toString());
        textViewEnemyAttack.setText(currentEnemyAttack.toString());
        textViewEnemyDefense.setText(currentEnemyDefense.toString());

        textViewStage.setText(currentStage.getLevel().toString() + " - stage name:" + currentStage.getName() + " - kills needed:"  + currentStage.getKillsNeeded().toString());
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

    private void createXPBarThread(){
        new Thread(new Runnable(){
            public void run(){
                threadRunning = true;
                while(threadRunning){
                    if(xpIncrease) {
                        xpHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                xpBar.setMax(currentPlayer.getTotalXPNeeded());
                                xpBar.setProgress(currentPlayer.getXp());
                                xpIncrease = false;
                            }
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    private void updatePlayerDataOnScreenForKill(){
        textViewPlayerName.setText(currentPlayerName + " " + PlayerUtil.getCurrentPlayerLevel().toString());
    }

    private void updateEnemyHealthOnScreen(){
        textViewEnemyHealth.setText(currentEnemyHealth.toString());
    }

    private void updateEnemyDataOnScreen(){
        textViewEnemyName.setText(currentEnemyName);
        textViewEnemyHealth.setText(currentEnemyHealth.toString());
        textViewEnemyAttack.setText(currentEnemyAttack.toString());
        textViewEnemyDefense.setText(currentEnemyDefense.toString());
        textViewStage.setText(currentStage.getLevel().toString() + " - stage name:" + currentStage.getName() + " - kills needed:"  + currentStage.getKillsNeeded().toString());
    }

    private void getScreenIds(){
        textViewPlayerName = (TextView) findViewById(R.id.textViewPlayerName);
        textViewPlayerHealth = (TextView) findViewById(R.id.textViewPlayerHealth);
        textViewPlayerAttack = (TextView) findViewById(R.id.textViewPlayerAttack);
        textViewPlayerDefense = (TextView) findViewById(R.id.textViewPlayerDefense);

        textViewEnemyName = (TextView) findViewById(R.id.textViewEnemyName);
        textViewEnemyHealth = (TextView) findViewById(R.id.textViewEnemyHealth);
        textViewEnemyAttack = (TextView) findViewById(R.id.textViewEnemyAttack);
        textViewEnemyDefense = (TextView) findViewById(R.id.textViewEnemyDefense);

        textViewStage = (TextView) findViewById(R.id.textViewStage);

        xpBar = (ProgressBar) findViewById(R.id.xpBar);
    }

    private void getPlayerData(){
        currentPlayer = databaseAccessor.playerDM.findFirst();
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
        currentStage = PlayerUtil.getCurrentPlayerStage();
    }
}
