package com.greg;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

public class Datownik {

    public static void main(String[] args) {
	// write your code here
        // zmienne na formatowanie dat
        SimpleDateFormat sdf = new SimpleDateFormat("dd.M.yyyy");
        SimpleDateFormat sdfDay = new SimpleDateFormat("EEEE");

        Calendar calendar = new GregorianCalendar();
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection testData;
        List<String> list = new ArrayList<String>();

        // Dni swiateczne umonthzone w tablicy + pierwszy przypadek testowy
        Calendar[] swieta = new GregorianCalendar[15];
        swieta[0] = new GregorianCalendar(2016, 0, 1);
        swieta[1] = new GregorianCalendar(2016, 0, 6);
        swieta[2] = new GregorianCalendar(2016, 4, 1);
        swieta[3] = new GregorianCalendar(2016, 4, 3);
        swieta[4] = new GregorianCalendar(2016, 7, 15);
        swieta[5] = new GregorianCalendar(2016, 10, 1);
        swieta[6] = new GregorianCalendar(2016, 10, 11);
        swieta[7] = new GregorianCalendar(2016, 11, 25);
        swieta[8] = new GregorianCalendar(2016, 11, 26);
        swieta[9] = easter((GregorianCalendar)calendar);
        swieta[10] = easter((GregorianCalendar)calendar);
        swieta[10].add(Calendar.DAY_OF_YEAR, 59);

        // zmienne do ustawiania dnia monthiaca w petli for +
        // ustawinie poczatkowego dnia
        // TODO: dodac przesuniecie daty
        int day = 1;

        if (args.length == 0) day = 1;
        else if (args.length == 1
                && String.valueOf(args[0]).matches("[0-9]+"))
        day = Integer.parseInt(args[0]);

        int month 	= calendar.get(Calendar.MONTH);
        int year 	= calendar.get(Calendar.YEAR);

        // inicjalizacja kalendarze do przetwarzania
        calendar.set(year, month, 1);

        for(int i = day; i <= daysInMonth((GregorianCalendar)calendar); i++){

            // Ustawiam kalendarz, z ktorego bede korzystal
            // TODO: zautomatyzowac ustawianie kalendarza
            calendar.set(year, month, i);

            // Przygotowuje wlasny format daty do zapisu w liscie
            day = calendar.get(calendar.DAY_OF_MONTH);

            // Potrzebna nazwa dnia, do usuniecia go z "kalendarza"
            String res = sdfDay.format(calendar.getTime());

            // Dodaje do listy dni bez weekendow
            // TODO: skrocic zapis
            if(!(res.equals("niedziela") ||
                    res.equals("sobota") ||
                    sdf.format(calendar.getTime()).toString().equals(sdf.format(swieta[0].getTime()).toString()) ||
                    sdf.format(calendar.getTime()).toString().equals(sdf.format(swieta[1].getTime()).toString()) ||
                    sdf.format(calendar.getTime()).toString().equals(sdf.format(swieta[2].getTime()).toString()) ||
                    sdf.format(calendar.getTime()).toString().equals(sdf.format(swieta[3].getTime()).toString()) ||
                    sdf.format(calendar.getTime()).toString().equals(sdf.format(swieta[4].getTime()).toString()) ||
                    sdf.format(calendar.getTime()).toString().equals(sdf.format(swieta[5].getTime()).toString()) ||
                    sdf.format(calendar.getTime()).toString().equals(sdf.format(swieta[6].getTime()).toString()) ||
                    sdf.format(calendar.getTime()).toString().equals(sdf.format(swieta[7].getTime()).toString()) ||
                    sdf.format(calendar.getTime()).toString().equals(sdf.format(swieta[8].getTime()).toString()) ||
                    sdf.format(calendar.getTime()).toString().equals(sdf.format(swieta[9].getTime()).toString()) ||
                    sdf.format(calendar.getTime()).toString().equals(sdf.format(swieta[10].getTime()).toString())
            )) {

                String myKal = String.valueOf(day) + "-" + String.valueOf(month+1) + "-" + String.valueOf(year);
                list.add(myKal +"\n");

            }
        }

        StringBuilder sb = new StringBuilder();
        Collections.reverse(list);

        for(String s : list)
            sb.insert(0, s);

        System.out.println(sb);
        testData = new StringSelection(sb.toString());
        // doaje do shcowaka
        c.setContents(testData, testData);
    }

    // http://www.elektroda.pl/rtvforum/topic1244944.html
    public static int daysInMonth(GregorianCalendar c) {
        final int [] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        daysInMonths[1] += c.isLeapYear(c.get(GregorianCalendar.YEAR)) ? 1 : 0;
        return daysInMonths[c.get(GregorianCalendar.MONTH)];
    }

    // easternoc itp
    public static Calendar easter(GregorianCalendar year){
        int a,b,c,d,e;
        a = year.get(Calendar.YEAR) % 19;
        b = year.get(Calendar.YEAR) % 4;
        c = year.get(Calendar.YEAR) % 7;

        d = (a * 19 + 24) % 30;
        e = (2 * b + 4 * c + 6 * d + 5) % 7;

        // wyjatki
        if((d == 29 && e == 6) ||
                (d == 28 && e == 6 )){ d -= 7;}

        GregorianCalendar wlk = new GregorianCalendar(2015, 5, 1);

        int data = 22 + d + e;
        // TODO: skrocic zapis
        if(data > 31){
            wlk.set(year.get(Calendar.YEAR),3, data%31);
        }
        else{
            wlk.set(year.get(Calendar.YEAR), 2, data);
        }

        SimpleDateFormat sss = new SimpleDateFormat("dd.M.yyyy");
        // przesuwam o jeden day, bo niedziela i tak jest wolna
        // dlatego zamiast 60 jest 59
        wlk.add(Calendar.DAY_OF_YEAR, 1);
        wlk.add(Calendar.DAY_OF_YEAR, 59);
        wlk.add(Calendar.DAY_OF_YEAR, -59);
        return wlk;
    }
}
