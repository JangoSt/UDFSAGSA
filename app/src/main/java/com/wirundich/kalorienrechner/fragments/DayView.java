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
import com.wirundich.kalorienrechner.dataclasses.Listeners.DataBusListener;

public class DayView extends Fragment implements DataBusListener{
    View rootView ;
    ListView lwSimple;
    ActionMode mActionMode;
    FragmentPagerAdapter fragmentPagerAdapter;
    ListViewItemCalorieAdapter lwiC;

   public final String FRAG_NAME = "DayView";
    DataBus db;
    ItemDay itemDay;

    public static Fragment newInstance(){
        return new DayView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = DataBus.getInstance();
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
        lwiC = new ListViewItemCalorieAdapter(db.getActDay(), getActivity().getApplication());
        lwSimple.setAdapter(lwiC);
        lwSimple.setClickable(true);

        ViewPager pager = (ViewPager)rootView.findViewById(R.id.vpPager);
        pager.setAdapter(new SampleAdapter(getActivity(),getChildFragmentManager()));

        return rootView;
    }

    @Override
    public String toString() {
        return FRAG_NAME;
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
        lwiC.notifyDataSetChanged();
    }

    @Override
    public void UserValuesChanged() {

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
                return  (UserOverview.newInstance());
        }

        @Override
        public int getCount() {
            return 2;
        }
    }


}
