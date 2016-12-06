package com.skreaper.game.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.skreaper.game.R;
import com.skreaper.game.activity.MainMenuActivity;
import com.skreaper.game.entity.MenuOption;

import java.util.List;

public class MainMenuAdapter extends BaseAdapter {
    private Activity activity;
    private List<MenuOption> menuOptionList;
    private static LayoutInflater inflater = null;

    public MainMenuAdapter(MainMenuActivity activity, List<MenuOption> menuOptionList){
        this.activity = activity;
        this.menuOptionList = menuOptionList;
        inflater = ( LayoutInflater )activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if(menuOptionList.size()<=0)
            return 1;
        return menuOptionList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewDisplayValue {
        public TextView mainText;
    }

    //TODO
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewDisplayValue value;

        if(convertView == null){
            view = inflater.inflate(R.layout.menu_option_item, null);

            value = new ViewDisplayValue();
            value.mainText = (TextView) view.findViewById(R.id.textView);

            view.setTag(value);
        }
        else{
            value = (ViewDisplayValue) view.getTag();
        }

        if(menuOptionList.size() <= 0){
            value.mainText.setText("No Data");
        }
        else{
            MenuOption selectedMenuOption = menuOptionList.get(position);

            value.mainText.setText(selectedMenuOption.getMainText());
        }

        view.setOnClickListener(new OnItemClickListener(position));
        return view;
    }

    private class OnItemClickListener  implements View.OnClickListener {
        private int mPosition;

        OnItemClickListener(int position){
            mPosition = position;
        }

        @Override
        public void onClick(View arg0) {
            MainMenuActivity mainMenuActivity = (MainMenuActivity) activity;
            mainMenuActivity.onItemClick(mPosition);
        }
    }
}
