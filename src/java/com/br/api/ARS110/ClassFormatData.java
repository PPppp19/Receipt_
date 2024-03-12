/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.ARS110;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Jilasak
 */
public class ClassFormatData {

    public String GetDateFormatSetsubmitacc(Date dateToset) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("ddMMyy", Locale.ENGLISH);
        String formatted = format1.format(dateToset);
        return formatted;
    }

    public static String SubDateForM3(String date) {
        String DateForM3 = date.trim();
        DateForM3 = DateForM3.substring(6, 8) + "" + DateForM3.substring(4, 6) + "" + DateForM3.substring(2, 4);
        return DateForM3;
    }

    public double Double2digitReturn(double number) {

        try {
            String numberBeforeconvert = String.valueOf(new DecimalFormat("##.##").format(Math.round(number * 100.0) / 100.0));
            double numberreturn = Double.parseDouble(numberBeforeconvert);
            return numberreturn;
        } catch (Exception e) {
            return 0;
        }

    }
}
