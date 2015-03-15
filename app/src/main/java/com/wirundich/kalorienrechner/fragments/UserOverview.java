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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.wirundich.kalorienrechner.fragments.UserOverview.OnFragmentUserOverViewInteraction} interface
 * to handle interaction events.
 * Use the {@link UserOverview#newInstance} factory method to
 * create an instance of this fragment.
 */

public class UserOverview extends Fragment {
    ImageButton editUser;
    static String KEY_POSITION = "position";
    TextView maxCalore;
    boolean editMode;
    DataBus db;
    static  public UserOverview newInstance(int postion){

        UserOverview frag = new UserOverview();
        Log.d(frag.getClass().getName(), "new Instance created");
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, postion);
        frag.setArguments(args);

        return frag;
    }

    private OnFragmentUserOverViewInteraction mListener;



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
        db = (DataBus) getActivity().getApplicationContext();
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

                mListener.onEditButtonPressed(editMode);

            }
        });
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentUserOverViewInteraction) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public void updateView(){
        maxCalore.setText(db.getUser().getMaxCalorie()+"");
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentUserOverViewInteraction {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
        public void onEditButtonPressed(boolean editMode);
    }

}
