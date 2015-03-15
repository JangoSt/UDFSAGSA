package com.wirundich.kalorienrechner.dataclasses;

import java.util.Date;

/**
 * Created by Matze on 23.02.2015.
 */
public class ItemUser {
    private int maxCalorie = 3000;

    private int weight, height, gender, sports,birthday;
    public void setMaxCalorie(int maxCalorie) {
        this.maxCalorie = maxCalorie;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getSports() {
        return sports;
    }

    public void setSports(int sports) {
        this.sports = sports;
    }



    public ItemUser(int weight, int height, int gender, int sports, int birthday) {
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.sports = sports;
        this.birthday = birthday;

    }

    public void calcMaxCalorie() {
        double tempSport = 1.0;
        for (int i = 0; i < sports; i++) {
            tempSport += 0.2;
        }
        if (gender == 0) {
            maxCalorie = (int) ((double) 655 + (9.5 * weight) + (1.9 * height) - (4.7 *(new Date().getYear()-birthday)));
        } else {
            maxCalorie = (int)((double)66+(13.8 *weight)+(5.0 *height)-(6.8*(new Date().getYear()-birthday)));
        }
        maxCalorie = (int)(maxCalorie*tempSport);
    }

    public int getMaxCalorie() {
        return maxCalorie;
    }
}
