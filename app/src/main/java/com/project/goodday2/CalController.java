package com.project.goodday2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalController {

    private Calendar cal;
    private String selectedYear;
    private String selectedMonth;

    public CalController(){
        String cd[] = getCurrentTime("yyyy:MM").split(":");
        selectedYear = cd[0];
        selectedMonth = cd [1];
    }

    public String getSelectedMonth() {
        return selectedMonth;
    }

    public String getSelectedYear() {
        return selectedYear;
    }

    public void setSelectedYear(String selectedYear) {
        this.selectedYear = selectedYear;
    }

    public void setSelectedMonth(String selectedMonth) {
        this.selectedMonth = selectedMonth;
    }

    public  String  getCurrentTime(String format){
        long cTime = System.currentTimeMillis();
        Date date = new Date(cTime);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public int getDaysOfMonth(){
        cal = new GregorianCalendar(Integer.parseInt(selectedYear), (Integer.parseInt(selectedMonth)-1),1);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }


}


