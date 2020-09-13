package com.example.android.popularnews.Utils;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.util.Log;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Utils {

    public static ColorDrawable[] vibrantLightColorList =
            {
                    new ColorDrawable(Color.parseColor("#ffeead")),
                    new ColorDrawable(Color.parseColor("#93cfb3")),
                    new ColorDrawable(Color.parseColor("#fd7a7a")),
                    new ColorDrawable(Color.parseColor("#faca5f")),
                    new ColorDrawable(Color.parseColor("#1ba798")),
                    new ColorDrawable(Color.parseColor("#6aa9ae")),
                    new ColorDrawable(Color.parseColor("#ffbf27")),
                    new ColorDrawable(Color.parseColor("#d93947"))
            };

    public static ColorDrawable getRandomDrawbleColor() {
        int idx = new Random().nextInt(vibrantLightColorList.length);
        return vibrantLightColorList[idx];
    }

    public static String DateToTimeFormat(String oldstringDate) {
        String isTime = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
            Date d = sdf.parse(oldstringDate.substring(0, oldstringDate.length()-5));

            return  ""+ android.text.format.DateFormat.format(" hh:mm:ss a dd-MM-yyyy ", d);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return isTime;
    }

    public static String stripHtml(String html) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY).toString();
        } else {
            return Html.fromHtml(html).toString();
        }
    }

    public static String DateFormat(String Timeinput) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
        Date currentTime = Calendar.getInstance().getTime();
        long now = currentTime.getTime();
        long input = now;
        try {
            Date d = sdf.parse(Timeinput.substring(0, Timeinput.length()-5));
            input = d.getTime();
        } catch (ParseException ex) {
            Log.v("Exception", ex.getLocalizedMessage());
        }

        if (now > input) {
            long spantime = now - input, phut = 60000;
            if (spantime < phut) {
                return "just now";
            } else if (spantime >= phut && spantime < 60 * phut) {
                return (int) spantime / (phut) + " minute ago";
            } else if (spantime >= 60 * phut && spantime < 24 * 60 * phut) {
                return (int) spantime / (60 * phut) + " hour ago";
            } else if (spantime >= 24 * 60 * phut && spantime < 2 * 24 * 60 * phut) {
                return "yesterday";
            } else {
                Date date = new Date(input);
                return "since " + android.text.format.DateFormat.format(" hh:mm:ss a dd-MM-yyyy ", date);
            }
        } else {
            long spantime = input - now, phut = 60000;
            if (spantime < phut) {
                return "just now";
            } else if (spantime >= phut && spantime < 60 * phut) {
                return (int) spantime / (phut) + " minute later";
            } else if (spantime >= 60 * phut && spantime < 24 * 60 * phut) {
                return (int) spantime / (60 * phut) + " hour later";
            } else if (spantime >= 24 * 60 * phut && spantime < 2 * 24 * 60 * phut) {
                return "tomorrow";
            } else {
                Date date = new Date(input);
                return "on " + android.text.format.DateFormat.format(" hh:mm:ss a dd-MM-yyyy ", date);
            }
        }

    }

    public static String getCountry() {
        Locale locale = Locale.getDefault();
        String country = String.valueOf(locale.getCountry());
        return country.toLowerCase();
    }
}
