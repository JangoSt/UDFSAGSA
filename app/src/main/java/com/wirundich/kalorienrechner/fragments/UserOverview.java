package com.wirundich.kalorienrechner.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wirundich.kalorienrechner.R;
import com.wirundich.kalorienrechner.dataclasses.DataBus;
import com.wirundich.kalorienrechner.dataclasses.Listeners.DataBusListener;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.wirundich.kalorienrechner.fragments.UserOverview.OnFragmentUserOverViewInteraction} interface
 * to handle interaction events.
 * Use the {@link UserOverview#newInstance} factory method to
 * create an instance of this fragment.
 */

public class UserOverview extends Fragment implements DataBusListener {
    ImageButton editUser;

    TextView maxCalore;
    boolean editMode;
    DataBus db;
    static  public UserOverview newInstance(){

        UserOverview frag = new UserOverview();
        Log.d(frag.getClass().getName(), "new Instance created");
        Bundle args = new Bundle();

        frag.setArguments(args);

        return frag;
    }





    public UserOverview() {
        // Required empty public constructor
        editMode = false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_user_overview, container, false);
        maxCalore = (TextView) rootView.findViewById(R.id.txtViewMaxCalorieUser);
        db = DataBus.getInstance();
        maxCalore.setText(db.getUser().getMaxCalorie()+"");

        editUser = (ImageButton) rootView.findViewById(R.id.imgBtnEditUser);
        editUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editMode) {
                    editMode = false;
                    editUser.setImageDrawable(getResources().getDrawable(R.drawable.icon_edit));
                }
                else {
                    editMode = true;
                    editUser.setImageDrawable(getResources().getDrawable(R.drawable.icon_ok));
                }

            }
        });
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event





    public void updateView(){
        maxCalore.setText(db.getUser().getMaxCalorie()+"");
    }

    @Override
    public void ItemInListChanged() {

    }

    @Override
    public void UserValuesChanged() {
        updateView();
    }


}
