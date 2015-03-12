package com.wirundich.kalorienrechner.CalorieItemListener;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.wirundich.kalorienrechner.R;

/**
 * Created by Matze on 27.02.2015.
 */
public class CalorieItemListener implements ListView.OnItemClickListener{

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PopupMenu popup = new PopupMenu(parent.getContext(),view);
        MenuInflater menuInflater = popup.getMenuInflater();
        menuInflater.inflate(R.menu.calorie_item_menu, popup.getMenu());
        popup.show();
    }
}
