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


public class DayViewOverview extends Fragment {
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
    public void updateView(ItemDay itemDay, ItemUser user){
        calories.setMax(user.getMaxCalorie());
        calories.setProgress(itemDay.getCaloriesDay());
        dayField.setText(itemDay.getDay());
        dateField.setText(itemDay.getDate());
                actCalorie.setText(itemDay.getCaloriesDay() + "");
        maxCalorie.setText(user.getMaxCalorie()+"");
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = (DataBus) getActivity().getApplicationContext();
        View rootView = inflater.inflate(R.layout.fragment_day_view_overview, container, false);
        dayField = (TextView)rootView.findViewById(R.id.txt_dayView_day);
        dateField = (TextView)rootView.findViewById(R.id.txt_dayView_date);
        actCalorie = (TextView)rootView.findViewById(R.id.txtCalorieAct);
        maxCalorie = (TextView)rootView.findViewById(R.id.txtCalorieMax);
        calories = (ProgressBar)rootView.findViewById(R.id.prog_dayview_calorie);
//        IntentFilter intentFilter = new IntentFilter(db.ITEM_CHANGED_FILTER);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("Broadcast reccieved",intent.toString());
                if(intent.getAction()== db.ITEM_CHANGED_FILTER) {
                    Log.d("Broadcast reccieved","update the view");
                    updateView(db.getActDay(), db.getUser());
                }
            }
        };

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver, new IntentFilter(db.ITEM_CHANGED_FILTER));


        updateView(db.getActDay(), db.getUser());
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
       //     onDayOverViewCalling = (OnDayOverViewCalling)activity;
        }catch(ClassCastException e){
            throw  new ClassCastException(activity.toString() + " must implement");
        }
    }
}
