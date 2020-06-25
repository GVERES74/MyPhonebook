/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author VERESG
 */
public class DateCallFuncs {
 
    private String yearnow, yearbefore, currentdate, currentyear, currenttime;
    
    public DateCallFuncs(){
    
        Calendar year_now = new GregorianCalendar();
        Date currentdate = new Date();
        Date currenttime = new Date();
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat timeformat = new SimpleDateFormat("hh:mm");
        this.currenttime = timeformat.format(currenttime);
        this.currentdate = dateformat.format(currentdate);
        this.yearnow = ""+year_now.get(Calendar.YEAR);
        year_now.add(Calendar.YEAR, -1);
        this.yearbefore = ""+year_now.get(Calendar.YEAR);
        
    }

    public String getYearnow() {
        return yearnow;
    }

    public String getYearbefore() {
        return yearbefore;
    }

    public String getCurrentdate() {
        return currentdate;
    }

    public String getCurrentyear() {
        return currentyear;
    }

    public String getCurrenttime() {
        return currenttime;
    }
    
    
}
