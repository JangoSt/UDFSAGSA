package com.wirundich.kalorienrechner.dataclasses;

import android.app.Application;
import android.util.Log;

import com.wirundich.kalorienrechner.FormatClasses.Formater;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by Matze on 22.02.2015.
 *
 * Handels ActItem, No need to set actual Date in other classes
 */
public  class DataBus extends Application {
    public final String ITEM_CHANGED_FILTER = "ItemHasChanged";
    private final String ARRLIST_ITEMS = "ArrayListItems";
    private File file;
    //private ArrayList<ItemCalorie> items = new ArrayList<ItemCalorie>();
    private String[] fragments= {"Add Items", "List"};
    private ArrayList<ItemDay> items = new ArrayList<ItemDay>();
    private ItemUser user;
    private ItemDay actDay;
    OnItemChangedListener onItemChangedListener;



    @Override
    public void onCreate() {
        super.onCreate();
        initSingletons();
        file = new File (getApplicationContext().getFilesDir(), "Data");
        if(loadData())
            Log.d("Items", "loading was successfull");
        else
            Log.e("Items","loading error, new List will be created");
        user = new ItemUser();
        setActDayItem(new Date());

    }

    private void initSingletons(){

    }
    public boolean saveData() {
        try {
            FileOutputStream fos = new FileOutputStream(file.getPath());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(items);
            oos.close();
            Log.d("Items","Data successfull saved");
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("Items","Data save error");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Items","Data save error");
            return false;
        }


    }
    public boolean loadData(){

        try {
            FileInputStream fis = new FileInputStream(file.getPath());
            ObjectInputStream ois = new ObjectInputStream(fis);
            items = (ArrayList<ItemDay>)ois.readObject();
            ois.close();
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }
    public void addItem(ItemCalorie i){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(!setActDayItem(i.getRealDate())){
           items.add(actDay);
        };
        actDay.addCalorieItem(i);
        Collections.sort(items);
        Log.d("List changed", "Item added, List contains now " + items.size() + " items");
       saveData();
        printItems();


    }

    public ArrayList<ItemDay> getItems() {
        return items;
    }

    public String[] getFragments() {
        return fragments;
    }
    public void printItems(){
        for(ItemDay iDay :items){
            Log.w("PRINTITEMS","--> "+iDay.toString());
            for(ItemCalorie iCol:iDay.getCalorieItems()){
                Log.w("PRINTITEMS","-----> "+iCol.toString());
            }
        }
    }
    public boolean setActDayItem(Date date){
        for(ItemDay iDay: items){
            if(Formater.dateFormater(date).equals(iDay.getDate())){
                actDay=iDay;
                return true;
            }
        }
        actDay = new ItemDay(date);
        return false;
    }

    public ItemUser getUser() {
        return user;
    }

    public ItemDay getActDay() {
        setActDayItem(new Date());
        return actDay;
    }

}

