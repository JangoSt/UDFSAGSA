package com.wirundich.kalorienrechner.FormatClasses;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.wirundich.kalorienrechner.CalorieItemListener.CalorieItemListener;
import com.wirundich.kalorienrechner.R;
import com.wirundich.kalorienrechner.dataclasses.ItemCalorie;
import com.wirundich.kalorienrechner.dataclasses.ItemDay;

import java.util.ArrayList;

/**
 * Created by Matze on 24.02.2015.
 */
public class ExpandableDayItemAdapter extends BaseExpandableListAdapter implements View.OnCreateContextMenuListener {
        private ArrayList<ItemDay> dayList;

        Context ctx;
        public ExpandableDayItemAdapter(ArrayList<ItemDay> dayList, Context ctx) {

            this.dayList = dayList;
            this.ctx = ctx;
        }
        @SuppressWarnings("Only use getChildView as Method, other will throw NullPointer ! ")
         public ExpandableDayItemAdapter(ItemDay dayList, Context ctx) {
            this.ctx = ctx;
          }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return dayList.get(groupPosition).getCalorieItems().get(childPosition);
        }
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return dayList.get(groupPosition).getCalorieItems().get(childPosition).hashCode();
        }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {

        ListViewItemCalorieAdapter lwAdapter = new ListViewItemCalorieAdapter(dayList.get(groupPosition),ctx);
        return lwAdapter.getView(childPosition,convertView,parent);
        }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }



    @Override
        public int getChildrenCount(int groupPosition) {
            int size = dayList.get(groupPosition).getCalorieItems().size();
            Log.d("getChildrenCount", "Child for group [" + groupPosition + "] is [" + size + "]");
            return size;
        }
        @Override
        public Object getGroup(int groupPosition) {
            return dayList.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return dayList.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return dayList.get(groupPosition).hashCode();
        }
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 final View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater inflater = (LayoutInflater)ctx.getSystemService
                        (Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.costum_item_header, parent, false);
            }
            TextView dayPlainText = (TextView) v.findViewById(R.id.txt_header_day);
            TextView datePlainText = (TextView) v.findViewById(R.id.txt_header_date);

            ItemDay itemDay = dayList.get(groupPosition);
            dayPlainText.setText(itemDay.getDay());
            datePlainText.setText(itemDay.getDate());

            return v;
        }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

    }

}
