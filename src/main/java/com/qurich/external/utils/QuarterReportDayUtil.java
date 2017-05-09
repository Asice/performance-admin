package com.qurich.external.utils;

import java.util.Calendar;
import java.util.Date;

public class QuarterReportDayUtil {

    /** 
     * 当前季度的结束时间 
     * 
     * @return 
     */ 
	public static Date getCurrentQuarterEndTime() {  
        Calendar c = Calendar.getInstance();  
        int currentMonth = c.get(Calendar.MONTH) + 1;  
        Date now = null;  
        try {  
            if (currentMonth >= 1 && currentMonth <= 3) {  
                c.set(Calendar.MONTH, 2);  
                c.set(Calendar.DATE, 31);  
            } else if (currentMonth >= 4 && currentMonth <= 6) {  
                c.set(Calendar.MONTH, 5);  
                c.set(Calendar.DATE, 30);  
            } else if (currentMonth >= 7 && currentMonth <= 9) {  
                c.set(Calendar.MONTH, 8);  
                c.set(Calendar.DATE, 30);  
            } else if (currentMonth >= 10 && currentMonth <= 12) {  
                c.set(Calendar.MONTH, 11);  
                c.set(Calendar.DATE, 31);  
            }
            return c.getTime();
           // now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return now;  
    }  
	
	/** 
     * 当前季度的开始时间 
     * 
     * @return 
     */  
    public static Date getCurrentQuarterStartTime() {  
        Calendar c = Calendar.getInstance();  
        int currentMonth = c.get(Calendar.MONTH) + 1;  
        Date now = null;  
        try {  
            if (currentMonth >= 1 && currentMonth <= 3)  
                c.set(Calendar.MONTH, 0);  
            else if (currentMonth >= 4 && currentMonth <= 6)  
                c.set(Calendar.MONTH, 3);  
            else if (currentMonth >= 7 && currentMonth <= 9)  
                c.set(Calendar.MONTH, 4);  
            else if (currentMonth >= 10 && currentMonth <= 12)  
                c.set(Calendar.MONTH, 9);  
            	c.set(Calendar.DATE, 1);  
            return new Date(c.getTimeInMillis()-24*60*60*1000);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return now;  
    }  
	
	/*public static void main(String[] args) {
		System.out.println(AuthUtil.formatDateToString(getCurrentQuarterStartTime()));
	}
	
	//预告披露的季报标示
	public static Date getQuarterNoticeDay(){
		
		
		
		return null;
	}*/
	
}
