package com.wirundich.kalorienrechner.dataclasses;

import com.wirundich.kalorienrechner.FormatClasses.Formater;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Matze on 22.02.2015.
 */
public class ItemCalorie implements Serializable, Comparable<ItemCalorie>{
    private int calorie;
    private String notice;
    Date date;
    public ItemCalorie(int calorie, Date date){
        this.date = date;
        this.calorie = calorie;
        this.notice = notice;
    }

    @Override
    public String toString() {
        return "CALORIEITEM "+calorie+"  "+date.toString();
    }

    public String getDate() {
        return Formater.dateFormater(date);
    }
    public String getTime(){return Formater.timeFormater(date);}
    public Date getRealDate(){
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCalorie() {
        return calorie;
    }
    public void setNotice(String notice){
        this.notice = notice;
    }
    public String getNotice() {
        return notice;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null)
            return false;
        if(getClass() != o.getClass()){
            return false;
        }
        final ItemCalorie other = (ItemCalorie)o;
        if((this.getDate()==null) ? (other.getDate()==null):!this.getDate().equals(other.getDate())){
            return false;
        }
        if(this.getDate() != other.getDate())
            return false;
        return true;
    }

    @Override
    public int compareTo(ItemCalorie another) {
        return another.getRealDate().compareTo(this.getRealDate());
    }
}
