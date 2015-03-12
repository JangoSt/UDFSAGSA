package com.wirundich.kalorienrechner.dataclasses;

import android.content.BroadcastReceiver;

import com.wirundich.kalorienrechner.FormatClasses.Formater;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by Matze on 23.02.2015.
 */
public class ItemDay implements Serializable, Comparable<ItemDay>{
    Date date;


    ArrayList<ItemCalorie>items;
    public ItemDay(Date date){
        items = new ArrayList<ItemCalorie>();
        this.date = date;
    }
    public void addCalorieItem(ItemCalorie calorieItem){
        items.add(calorieItem);
        Collections.sort(items);
    }
    public boolean contains(ItemCalorie itemCalorie){
        return items.contains(itemCalorie);
    }

    public String getDate() {
        return Formater.dateFormater(date);
    }
    public Date getRealDate(){
        return date;
    }

    public ArrayList<ItemCalorie> getCalorieItems() {
        return items;
    }
    public String getDay(){
        return Formater.dayFormater(date);
    }

    @Override
    public String toString() {
        return "DAYITEM "+date.toString() +" ";
    }
    public int getCaloriesDay(){
        int result =0;
        for(ItemCalorie iCalorie:this.items){
            result+= iCalorie.getCalorie();
        }
        return result;
    }


    @Override
    public int compareTo(ItemDay another) {
        return another.getRealDate().compareTo(this.getRealDate());
    }
}
