package com.skreaper.game.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.skreaper.game.R;
import com.skreaper.game.adapter.MainMenuAdapter;
import com.skreaper.game.entity.MenuOption;
import com.skreaper.game.ormlite.DatabaseAdapter;
import com.skreaper.game.ormlite.DatabaseManager;
import com.skreaper.game.ormlite.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainMenuActivity extends AppCompatActivity {
    private List<MenuOption> menuOptions = new ArrayList<>();
    private DatabaseAdapter databaseAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        databaseAdapter = new DatabaseAdapter(this);
        loadMenuItems();
        setMenuItems();

    }

    private void setMenuItems() {
        ListView listView = (ListView) findViewById(R.id.mainMenuList);
        TextView textView = (TextView) findViewById(R.id.textViewMainMenu);
        MainMenuAdapter mainMenuAdapter = new MainMenuAdapter(this, menuOptions);
        listView.setAdapter(mainMenuAdapter);

        Player currentPlayer = databaseAdapter.playerDM.findFirst();
        textView.setText("Hello " + currentPlayer.getName());
    }

    private void loadMenuItems() {
        menuOptions.add(startMenuOption());
        menuOptions.add(optionsMenuOption());
    }

    private MenuOption startMenuOption() {
        MenuOption item = new MenuOption();
        item.setOrderNumber(1);
        item.setMainText("Start");
        item.setOptionClass(StartActivity.class);
        return item;
    }

    private MenuOption optionsMenuOption() {
        MenuOption item = new MenuOption();
        item.setOrderNumber(2);
        item.setMainText("Options");
        item.setOptionClass(SplashActivity.class);
        return item;
    }

    public void onItemClick(int mPosition) {
        MenuOption value = menuOptions.get(mPosition);
        Intent intent = new Intent(this, value.getOptionClass());
        startActivity(intent);
    }
}
