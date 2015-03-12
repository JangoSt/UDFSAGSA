package com.wirundich.kalorienrechner.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.support.v4.app.Fragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;


import com.wirundich.kalorienrechner.FormatClasses.Formater;
import com.wirundich.kalorienrechner.R;
import com.wirundich.kalorienrechner.dataclasses.DataBus;
import com.wirundich.kalorienrechner.dataclasses.ItemCalorie;
import com.wirundich.kalorienrechner.dataclasses.OnItemChangedListener;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddItem extends Fragment {
    OnItemChangedListener onItemChangedListener;
        DataBus db;
        public final String ARG_FRAGITEM = "Add Item";
    Button setDate;
    Button setTime;
    ItemCalorie actItemCalorie;
    GregorianCalendar actDate;
    Calendar calendar;
    public static final String FRAG_NAME = "Add Item";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = (DataBus)getActivity().getApplication();
        calendar = Calendar.getInstance();
        actDate = new GregorianCalendar();
    }

    @Override
    public String toString() {
        return ARG_FRAGITEM;
    }

    public AddItem() { };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_item, container, false);
        Button addItem = (Button)rootView.findViewById(R.id.btnAddItem);
        setDate = (Button)rootView.findViewById(R.id.btnSetDate);
        setTime = (Button)rootView.findViewById(R.id.btnSetTime);
        updateValues();
        final EditText edtNotice = (EditText)rootView.findViewById(R.id.edtNotice);
        final EditText edtCalorie = (EditText)rootView.findViewById(R.id.edtCalorie);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtCalorie.getText().toString().equals("")){
                    Toast.makeText(db, "Bitte Kalorien eingeben", Toast.LENGTH_SHORT).show();
                }
                else {
                    actItemCalorie= new ItemCalorie(Integer.parseInt(edtCalorie.getText().toString()), new Date());
                    if (edtNotice.getText() == null)
                        actItemCalorie.setNotice("");
                    else
                        actItemCalorie.setNotice(edtNotice.getText().toString());
                    actItemCalorie.setDate(actDate.getTime());
                    db.addItem(actItemCalorie);
                    Log.d("DEBUG", "Item added" + actItemCalorie.toString());
                    edtCalorie.setText("");
                    edtNotice.setText("");
                    onItemChangedListener.onItemAdded();
                    getFragmentManager().beginTransaction().replace(R.id.pager, new ListExpandableItems()).commit();
                }
            }
        });
        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                DatePickerDialog datePicker = new DatePickerDialog(getActivity(),new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(year, monthOfYear, dayOfMonth);
                        actDate.set(GregorianCalendar.YEAR,year);
                        actDate.set(GregorianCalendar.MONTH, monthOfYear);
                        actDate.set(GregorianCalendar.DAY_OF_MONTH,dayOfMonth);
                        updateValues();
                    }
                }, c.get(Calendar.YEAR),c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                datePicker.setCancelable(true);
                datePicker.show();


            }
        });
        setTime.setOnClickListener(new View.OnClickListener() {
            Calendar c = Calendar.getInstance();
            @Override
            public void onClick(View v) {
                TimePickerDialog timePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        actDate.set(GregorianCalendar.HOUR,hourOfDay);
                        actDate.set(GregorianCalendar.MINUTE,minute);
                        updateValues();
                    }
                }, c.get(Calendar.HOUR),c.get(Calendar.MINUTE),true);
                timePicker.setCancelable(true);
                timePicker.show();
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
    public void updateValues(){
        setDate.setText(Formater.dateFormater(actDate.getTime()));
        setTime.setText(Formater.timeFormater(actDate.getTime()));
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            onItemChangedListener = (OnItemChangedListener) activity;
        }
        catch(ClassCastException e)
        {
            throw new ClassCastException(activity.toString()+"has to implement Listener Interface");
        }
    }
}
