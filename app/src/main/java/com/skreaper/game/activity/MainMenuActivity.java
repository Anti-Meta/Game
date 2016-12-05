package com.skreaper.game.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.skreaper.game.R;
import com.skreaper.game.adapter.MainMenuAdapter;
import com.skreaper.game.entity.MenuOption;
import com.skreaper.game.ormlite.DatabaseManager;
import com.skreaper.game.ormlite.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MainMenuActivity extends AppCompatActivity {
    private List<MenuOption> menuOptions = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        loadMenuItems();
        setMenuItems();

        /*Player player = new Player();
        player.setName("Darius");
        player.setLevel(1);

        Player player2 = new Player();
        player2.setName("Shervin");
        player2.setLevel(9000);*/

        //DatabaseManager<Player> playerDatabaseManager = new DatabaseManager<>(this, Player.class);
    }

    private void setMenuItems() {
        ListView listView = (ListView) findViewById(R.id.mainMenuList);
        MainMenuAdapter mainMenuAdapter = new MainMenuAdapter(this, menuOptions);
        listView.setAdapter(mainMenuAdapter);
    }

    private void loadMenuItems() {
        menuOptions.add(startMenuOption());
        menuOptions.add(optionsMenuOption());

    }

    private MenuOption startMenuOption() {
        MenuOption item = new MenuOption();
        item.setOrderNumber(1);
        item.setMainText("Start");
        item.setOptionClass(SplashActivity.class);
        item.setIcon(R.drawable.link_button);
        return item;
    }

    private MenuOption optionsMenuOption() {
        MenuOption item = new MenuOption();
        item.setOrderNumber(2);
        item.setMainText("Options");
        item.setOptionClass(SplashActivity.class);
        item.setIcon(R.drawable.black_wrench);
        return item;
    }

    public void onItemClick(int mPosition) {
        MenuOption value = menuOptions.get(mPosition);
        Intent intent = new Intent(this, value.getOptionClass());
        startActivity(intent);
    }
}
