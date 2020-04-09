package com.jorgegiance.chess_timer.util;

public class Utils {

    public static int string2Hours(String time){
        if(time.length() == 8){
            return Integer.parseInt(time.substring(0, 2));
        }else{
            return -1;
        }

    }

    public static int string2Minutes(String time){

        if(time.length() == 8){
            return Integer.parseInt(time.substring(3, 5));
        }else if (time.length() == 5){
            return Integer.parseInt(time.substring(0, 2));
        }else{
            return -1;
        }


    }

    public static int string2Seconds(String time){

        if(time.length() == 8){
            return Integer.parseInt(time.substring(6, 8));
        }else if (time.length() == 5){
            return Integer.parseInt(time.substring(3, 5));
        }else{
            return -1;
        }


    }

    public static String time2String(int hours, int minutes, int seconds){

        String h;
        String m;
        String s;

        if(hours < 10){
            h = "0" + Integer.toString(hours);
        }else{
            h = Integer.toString(hours);
        }

        if(minutes < 10){
            m = "0" + Integer.toString(minutes);
        }else{
            m = Integer.toString(minutes);
        }

        if(seconds < 10){
            s = "0" + Integer.toString(seconds);
        }else{
            s = Integer.toString(seconds);
        }

        return h + ":" + m + ":" + s;
    }

    public static String time2String(int minutes, int seconds){

        String m;
        String s;

        if(minutes < 10){
            m = "0" + Integer.toString(minutes);
        }else{
            m = Integer.toString(minutes);
        }

        if(seconds < 10){
            s = "0" + Integer.toString(seconds);
        }else{
            s = Integer.toString(seconds);
        }

        return m + ":" + s;
    }
}
