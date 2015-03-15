package com.wirundich.kalorienrechner.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

import com.wirundich.kalorienrechner.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserSettings.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UserSettings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserSettings extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String GENDER = "gender";
    private static final String WEIGHT = "weight";
    private static final String HEIGHT = "height";
    private static final String SPORTS = "sports";
    private static final String YEAR = "year";
    RadioButton female, male;
    EditText edtAlter, edtWeight, edtSize;
    int weight, height, gender, sports;

    // TODO: Rename and change types of parameters



    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment UserSettings.
     */
    // TODO: Rename and change types and number of parameters
    public static UserSettings newInstance(int weight, int height, int gender, int sports, int year) {
        UserSettings fragment = new UserSettings();
        Bundle args = new Bundle();
        args.putInt(WEIGHT, weight);
        args.putInt(HEIGHT, height);
        args.putInt(GENDER, gender);
        args.putInt(SPORTS, sports);
        args.putInt(YEAR, year);


        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=  inflater.inflate(R.layout.fragment_user_settings, container, false);

        return rootView;
    }

    public UserSettings() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            weight = getArguments().getInt(WEIGHT, 0);
            height = getArguments().getInt(HEIGHT, 0);
            gender = getArguments().getInt(GENDER, 0);
            sports = getArguments().getInt(SPORTS, 0);


        }
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            //    mListener = (OnFragmentInteractionListener) activity;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
