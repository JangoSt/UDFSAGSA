package com.wirundich.kalorienrechner.fragments;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wirundich.kalorienrechner.R;
import com.wirundich.kalorienrechner.dataclasses.DataBus;
import com.wirundich.kalorienrechner.dataclasses.ItemDay;
import com.wirundich.kalorienrechner.dataclasses.ItemUser;
import com.wirundich.kalorienrechner.dataclasses.Listeners.DataBusListener;


public class DayViewOverview extends Fragment implements DataBusListener{
    DataBus db;
    private static final String KEY_POSITION = "position";
    TextView dayField;
    TextView dateField;
    TextView actCalorie;
    TextView maxCalorie;
    ImageButton editUser;
    ProgressBar calories;
//    OnDayOverViewCalling onDayOverViewCalling;
    BroadcastReceiver broadcastReceiver;

    static  public DayViewOverview newInstance(int postion){
        DayViewOverview frag = new DayViewOverview();
        Log.d(frag.getClass().getName(), "new Instance created");
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, postion);
        frag.setArguments(args);
        return frag;
    }
    static public String getTitle(Context ctx, int position){
        return (new DayViewOverview().getClass().getName()+position+"");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = DataBus.getInstance();
        View rootView = inflater.inflate(R.layout.fragment_day_view_overview, container, false);
        dayField = (TextView)rootView.findViewById(R.id.txt_dayView_day);
        dateField = (TextView)rootView.findViewById(R.id.txt_dayView_date);
        actCalorie = (TextView)rootView.findViewById(R.id.txtCalorieAct);
        maxCalorie = (TextView)rootView.findViewById(R.id.txtCalorieMax);
        calories = (ProgressBar)rootView.findViewById(R.id.prog_dayview_calorie);
        return rootView;
    }
    public void updateView(){
        calories.setMax(db.getUser().getMaxCalorie());
        calories.setProgress(db.getActDay().getCaloriesDay());
        dayField.setText(db.getActDay().getDay());
        dateField.setText(db.getActDay().getDate());
        actCalorie.setText(db.getActDay().getCaloriesDay() + "");
        maxCalorie.setText(db.getUser().getMaxCalorie()+"");
    }

    @Override
    public void onResume() {
        super.onResume();
        db.addListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        db.removeListener(this);
    }

    @Override
    public void ItemInListChanged() {
        updateView();
    }

    @Override
    public void UserValuesChanged() {

    }
}
