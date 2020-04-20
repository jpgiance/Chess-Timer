package com.jorgegiance.chess_timer.util;

public class Utils {

    public static int string2Hours(String time){
        if(time.length() == 8){
            return Integer.parseInt(time.substring(0, 2));
        }else if (time.length() == 5){
            return 0;
        }else {
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

    public static int string2TotalSeconds(String time){

        int sec = 0;
        int min = 0;
        int hour = 0;

        if(time.length() == 8){

            sec = Integer.parseInt(time.substring(6, 8));
            min = Integer.parseInt(time.substring(3, 5));
            hour = Integer.parseInt(time.substring(0, 2));

            return sec + min*60 + hour*3600;

        }else if (time.length() == 5){
            sec = Integer.parseInt(time.substring(3, 5));
            min = Integer.parseInt(time.substring(0, 2));

            return sec + min*60 + hour*3600;

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

    public static String time2String(int seconds){
        int sec = seconds % 60;
        int min = (seconds/60) % 60;
        int hour = (seconds/3600) % 60;

        if (hour > 0){
            return time2String(hour, min, sec);
        }else{
            return time2String(min, sec);
        }
    }
}
