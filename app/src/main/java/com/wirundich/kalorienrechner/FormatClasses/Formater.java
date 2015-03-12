package com.wirundich.kalorienrechner.FormatClasses;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Matze on 23.02.2015.
 */
public class Formater {
public static String dateFormater(Date date){
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    return sdf.format(date);
}
    public static String timeFormater(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }
    public static String dayFormater(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("EEEEEEEEEEE");
        return sdf.format(date);
    }
}
