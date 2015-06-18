package com.wirundich.kalorienrechner.FormatClasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.wirundich.kalorienrechner.Components.Indicator;
import com.wirundich.kalorienrechner.R;
import com.wirundich.kalorienrechner.dataclasses.DataBus;
import com.wirundich.kalorienrechner.dataclasses.ItemCalorie;
import com.wirundich.kalorienrechner.dataclasses.ItemDay;
import com.wirundich.kalorienrechner.dataclasses.Listeners.DataBusListener;


import java.util.ArrayList;

/**
 * Created by Matze on 24.02.2015.
 */
public class ListViewItemCalorieAdapter extends BaseAdapter {
    ArrayList<ItemCalorie> calorieList;
    Context ctx;

   public ListViewItemCalorieAdapter(ItemDay day, Context ctx) {
       calorieList = day.getCalorieItems();
       this.ctx = ctx;
   }

    @Override
    public int getCount() {
        return calorieList.size();
    }

    @Override
    public Object getItem(int position) {
        return calorieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return calorieList.get(position).hashCode();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ItemCalorie itemCalorie = calorieList.get(position);
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)ctx.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.costum_item_row, parent, false);
        }
        TextView header =(TextView) v.findViewById(R.id.txtHeader);
        TextView noticeText =(TextView) v.findViewById(R.id.txtNoticeText);
        TextView txtCalorie =(TextView) v.findViewById(R.id.txtCalorie);
        ImageButton iButton = (ImageButton)v.findViewById(R.id.menuButtonCostumChildRow);
        Indicator indicator = (Indicator) v.findViewById(R.id.item_indicator);
        indicator.setProgress((int) (100.0/(DataBus.getInstance().getUser().getMaxCalorie()/3)*itemCalorie.getCalorie()));
        TextView indicatorProc = (TextView) v.findViewById(R.id.item_txt_proc);
        indicatorProc.setText(indicator.getProgress()/3+" %");
        iButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(ctx, v);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_deleteItem:
                                calorieList.remove(position);
                                DataBus.getInstance().notifyItemAdded();
                        }
                        return false;
                    }
                });
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.calorie_item_menu,popupMenu.getMenu());
                popupMenu.show();
            }
        });
        header.setText(itemCalorie.getTime());
        txtCalorie.setText(itemCalorie.getCalorie()+" kcal");

        if(itemCalorie.getNotice().equals(""))
            noticeText.setText("keine Beschreibung");
        else
            noticeText.setText(itemCalorie.getNotice());

        return v;

    }
}
