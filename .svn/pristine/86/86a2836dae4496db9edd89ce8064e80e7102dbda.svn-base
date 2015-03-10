// Source File Name:   DateUtil.java

package com.ctgu.util;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil
{

    public DateUtil()
    {
    }

    public static Date now()
    {
        return new Date();
    }

    public static String nowString(String pattern)
    {
        return format(now(), pattern);
    }

    public static String nowString()
        throws SQLException
    {
        return format(now(), "yyyy-MM-dd");
    }

    public static int getYear(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(1);
    }

    public static int getMonth(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(2) + 1;
    }

    public static int getDay(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(5);
    }

    public static int getHour(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(10);
    }

    public static int getMinute(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(12);
    }

    public static int getSecond(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(13);
    }

    public static Date addYear(Date date, int ammount)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(1, ammount);
        return c.getTime();
    }

    public static Date addMonth(Date date, int ammount)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(2, ammount);
        return c.getTime();
    }

    public static Date addDay(Date date, int ammount)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(5, ammount);
        return c.getTime();
    }

    public static int beforeYears(Date beforeDate, Date afterDate)
    {
        int before = getYear(beforeDate);
        int after = getYear(afterDate);
        return after - before;
    }

    public static int beforeMonths(Date beforeDate, Date afterDate)
    {
        Calendar beforeCalendar = Calendar.getInstance();
        beforeCalendar.setTime(beforeDate);
        beforeCalendar.set(5, 1);
        beforeCalendar.set(10, 0);
        beforeCalendar.set(13, 0);
        beforeCalendar.set(12, 0);
        Calendar afterCalendar = Calendar.getInstance();
        afterCalendar.setTime(afterDate);
        afterCalendar.set(5, 1);
        afterCalendar.set(10, 0);
        afterCalendar.set(13, 0);
        afterCalendar.set(12, 0);
        boolean positive = true;
        if(beforeDate.after(afterDate))
            positive = false;
        int beforeMonths = 0;
        do
        {
            boolean yearEqual = beforeCalendar.get(1) == afterCalendar.get(1);
            boolean monthEqual = beforeCalendar.get(2) == afterCalendar.get(2);
            if(!yearEqual || !monthEqual)
            {
                if(positive)
                {
                    beforeMonths++;
                    beforeCalendar.add(2, 1);
                } else
                {
                    beforeMonths--;
                    beforeCalendar.add(2, -1);
                }
            } else
            {
                return beforeMonths;
            }
        } while(true);
    }

    public static int beforeDays(Date beforeDate, Date afterDate)
    {
        Calendar beforeCalendar = Calendar.getInstance();
        beforeCalendar.setTime(beforeDate);
        beforeCalendar.set(10, 0);
        beforeCalendar.set(13, 0);
        beforeCalendar.set(12, 0);
        Calendar afterCalendar = Calendar.getInstance();
        afterCalendar.setTime(afterDate);
        afterCalendar.set(10, 0);
        afterCalendar.set(13, 0);
        afterCalendar.set(12, 0);
        boolean positive = true;
        if(beforeDate.after(afterDate))
            positive = false;
        int beforeDays = 0;
        do
        {
            boolean yearEqual = beforeCalendar.get(1) == afterCalendar.get(1);
            boolean monthEqual = beforeCalendar.get(2) == afterCalendar.get(2);
            boolean dayEqual = beforeCalendar.get(5) == afterCalendar.get(5);
            if(!yearEqual || !monthEqual || !dayEqual)
            {
                if(positive)
                {
                    beforeDays++;
                    beforeCalendar.add(5, 1);
                } else
                {
                    beforeDays--;
                    beforeCalendar.add(5, -1);
                }
            } else
            {
                return beforeDays;
            }
        } while(true);
    }

    public static int beforeRoundYears(Date beforeDate, Date afterDate)
    {
        Date bDate = beforeDate;
        Date aDate = afterDate;
        boolean positive = true;
        if(beforeDate.after(afterDate))
        {
            positive = false;
            bDate = afterDate;
            aDate = beforeDate;
        }
        int beforeYears = beforeYears(bDate, aDate);
        int bMonth = getMonth(bDate);
        int aMonth = getMonth(aDate);
        if(aMonth < bMonth)
            beforeYears--;
        else
        if(aMonth == bMonth)
        {
            int bDay = getDay(bDate);
            int aDay = getDay(aDate);
            if(aDay < bDay)
                beforeYears--;
        }
        if(positive)
            return beforeYears;
        else
            return (new BigDecimal(beforeYears)).negate().intValue();
    }

    public static int beforeRoundAges(Date beforeDate, Date afterDate)
    {
        Date bDate = beforeDate;
        Date aDate = afterDate;
        boolean positive = true;
        if(beforeDate.after(afterDate))
        {
            positive = false;
            bDate = afterDate;
            aDate = beforeDate;
        }
        int beforeYears = beforeYears(bDate, aDate);
        int bMonth = getMonth(bDate);
        int aMonth = getMonth(aDate);
        if(aMonth < bMonth)
            beforeYears--;
        if(positive)
            return beforeYears;
        else
            return (new BigDecimal(beforeYears)).negate().intValue();
    }

    public static int beforeRoundMonths(Date beforeDate, Date afterDate)
    {
        Date bDate = beforeDate;
        Date aDate = afterDate;
        boolean positive = true;
        if(beforeDate.after(afterDate))
        {
            positive = false;
            bDate = afterDate;
            aDate = beforeDate;
        }
        int beforeMonths = beforeMonths(bDate, aDate);
        int bDay = getDay(bDate);
        int aDay = getDay(aDate);
        if(aDay < bDay)
            beforeMonths--;
        if(positive)
            return beforeMonths;
        else
            return (new BigDecimal(beforeMonths)).negate().intValue();
    }

    public static Date getDate(int year, int month, int date)
    {
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, date);
        c.set(10, 0);
        c.set(12, 0);
        c.set(13, 0);
        c.set(14, 0);
        return c.getTime();
    }

    public static String format(Date date, String pattern)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static String format(Date date)
    {
        return format(date, "yyyy-MM-dd");
    }

    public static Date parse(String dateStr, String pattern)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try
        {
            return sdf.parse(dateStr);
        }
        catch(ParseException e)
        {
            return null;
        }
    }

    public static Date parse(String dateStr)
    {
        return parse(dateStr, "yyyy-MM-dd");
    }

    public static String toUTC(Date date)
    {
        return toUTC(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String toUTC(Date date, String pattern)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(date);
    }

    public static final String YEAR_MONTH_DAY_PATTERN = "yyyy-MM-dd";
    public static final String HOUR_MINUTE_SECOND_PATTERN = "HH:mm:ss";
    public static final String YMDHMS_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_YEAR = "yyyy";
    public static final String PATTERN_MONTH = "MM";
    public static final String PATTERN_DATE = "dd";
    public static final String PATTERN_HOUR = "hh";
    public static final String PATTERN_HOUR24 = "HH";
    public static final String PATTERN_MINUTE = "mm";
    public static final String PATTERN_SECOND = "ss";
    public static final String PATTERN_MILLISECOND = "SSS";
}
