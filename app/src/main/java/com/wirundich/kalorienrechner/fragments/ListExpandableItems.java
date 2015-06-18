package com.wirundich.kalorienrechner.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.wirundich.kalorienrechner.FormatClasses.ExpandableDayItemAdapter;
import com.wirundich.kalorienrechner.R;
import com.wirundich.kalorienrechner.dataclasses.DataBus;
import com.wirundich.kalorienrechner.dataclasses.ItemCalorie;
import com.wirundich.kalorienrechner.dataclasses.ItemDay;
import com.wirundich.kalorienrechner.dataclasses.Listeners.DataBusListener;

import java.util.ArrayList;

public class ListExpandableItems extends Fragment implements DataBusListener{
    DataBus db;
    private ExpandableDayItemAdapter iAdapter;
    public final String FRAG_NAME = "ListExpandableItems";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db =DataBus.getInstance();
 }



    @Override
    public String toString() {
        return FRAG_NAME;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_expandablelist_items, container, false);
        ExpandableListView exList = (ExpandableListView)rootView.findViewById(R.id.lwItems);
        exList.setIndicatorBounds(5,5);
        iAdapter = new ExpandableDayItemAdapter(db.getItems(),getActivity().getApplication());
        exList.setAdapter(iAdapter);
        return rootView;
    }
    public static Fragment newInstance() {
        return new ListExpandableItems();
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
        iAdapter.notifyDataSetChanged();
    }

    @Override
    public void UserValuesChanged() {

    }
}

