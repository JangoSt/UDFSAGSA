package com.wirundich.kalorienrechner.fragments;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wirundich.kalorienrechner.FormatClasses.ListViewItemCalorieAdapter;
import com.wirundich.kalorienrechner.R;
import com.wirundich.kalorienrechner.dataclasses.DataBus;
import com.wirundich.kalorienrechner.dataclasses.ItemDay;

public class DayView extends Fragment {
    View rootView ;
    ListView lwSimple;
    ActionMode mActionMode;
    FragmentPagerAdapter fragmentPagerAdapter;


   public final String FRAG_NAME = "DayView";
    DataBus db;
    ItemDay itemDay;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = (DataBus)getActivity().getApplication();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_day_view, container, false);

        lwSimple = (ListView) rootView.findViewById(R.id.lwSimple);
        lwSimple.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplication(),"FUUU",Toast.LENGTH_SHORT).show();
            }
        });
        ListViewItemCalorieAdapter lwiC = new ListViewItemCalorieAdapter(db.getActDay(), getActivity().getApplication());
        lwSimple.setAdapter(lwiC);
        lwSimple.setClickable(true);
        updateValues();


        ViewPager pager = (ViewPager)rootView.findViewById(R.id.vpPager);
        pager.setAdapter(new SampleAdapter(getActivity(),getChildFragmentManager()));

        return rootView;
    }
    public void updateValues(){
//        itemDay = db.getActDay();
//        calories.setMax(db.getUser().getMaxCalorie());
//        calories.setProgress(itemDay.getCaloriesDay());
//        actCalorie.setText(itemDay.getCaloriesDay() + "");
//        maxCalorie.setText(db.getUser().getMaxCalorie()+"");
//        dayField.setText(itemDay.getDay());
//        dateField.setText(itemDay.getDate());

    }
    @Override
    public String toString() {
        return FRAG_NAME;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateValues();

    }
    public class SampleAdapter extends FragmentPagerAdapter{
        Context ctx;
        public SampleAdapter(Context ctx, FragmentManager mgr){
            super(mgr);
            this.ctx = ctx;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0)
            return (DayViewOverview.newInstance(position));
            else
                return  (UserOverview.newInstance(position));
        }

        @Override
        public int getCount() {
            return 2;
        }
    }


}
