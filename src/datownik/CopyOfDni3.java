package datownik;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.jws.soap.SOAPBinding.Style;

public class CopyOfDni3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("dd.M.yyyy");
		SimpleDateFormat sdfDay = new SimpleDateFormat("EEEE");

		String date = sdf.format(new Date());
		Locale locale = new Locale("pl-PL");
		Calendar calendar = new GregorianCalendar();

        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection testData;
		List<String> list = new ArrayList();
		
        Calendar[] swieta = new GregorianCalendar[15];
		swieta[0] = new GregorianCalendar(2015, 3, 29);
		swieta[1] = new GregorianCalendar(2015, 0, 6);
		swieta[2] = new GregorianCalendar(2015, 4, 1);
		swieta[3] = new GregorianCalendar(2015, 4, 3);
		swieta[4] = new GregorianCalendar(2015, 7, 15);
		swieta[5] = new GregorianCalendar(2015, 10, 1);
		swieta[6] = new GregorianCalendar(2015, 10, 11);
		swieta[7] = new GregorianCalendar(2015, 11, 25);
		swieta[8] = new GregorianCalendar(2015, 11, 26);	
		
        int dzien = 1;
        int mies = calendar.get(calendar.MONTH);
        int rok = calendar.get(Calendar.YEAR);

        // test
        mies = calendar.get(Calendar.MONTH);
        System.out.println("STD: " + calendar.MONTH);
        calendar.set(rok, mies, 1);
        System.out.println("MY:  " + calendar.get(calendar.MONTH));

		for(int i = 1; i <= daysInMonth((GregorianCalendar)calendar); i++){
			
			// Ustawiam kalendarz, z ktorego bede korzystal
			// TODO: zautomatyzowac ustawianie kalendarza
			calendar.set(rok, mies, i);
			
			// Przygotowuje wlasny format daty do zapisu w liscie
			dzien = calendar.get(calendar.DAY_OF_MONTH);
			
			// Potrzebna nazwa dnia, do usuniecia go z "kalendarza"
			String res = sdfDay.format(calendar.getTime());
			
			
			// System.out.println(sdf.format(calendar.getTime()).toString().equals(sdf.format(swieta[0].getTime()).toString()));
			
			
			// Dodaje do listy dni bez weekendow
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
					sdf.format(calendar.getTime()).toString().equals(sdf.format(swieta[8].getTime()).toString())
					
					)) {
				
				String myKal = String.valueOf(dzien) + "-" + String.valueOf(mies+1) + "-" + String.valueOf(rok); 
				list.add(myKal +"\n");
				
			}
		}
		
		StringBuilder sb = new StringBuilder();
		Collections.reverse(list);

		for(String s : list){
			sb.insert(0, s);
		}
		
		System.out.println(sb);
		testData = new StringSelection(sb.toString());
		// doaje do shcowaka
		c.setContents(testData, testData);
		
		System.out.println("----" + list.size() + " -- " + calendar.get(Calendar.DAY_OF_YEAR));

		
		Calendar cal2 = new GregorianCalendar(2015, 3, 30);

		
		String d1 = sdf.format(calendar.getTime()).toString();
		String d2 = sdf.format(cal2.getTime()).toString();

        System.out.println(sdf.format(calendar.getTime()).toString().equals(sdf.format(swieta[0].getTime()).toString()));
        System.out.println(d1);
        System.out.println(d2);
	}
	
	
	// http://www.elektroda.pl/rtvforum/topic1244944.html
	public static int daysInMonth(GregorianCalendar c) {
        final int [] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        daysInMonths[1] += c.isLeapYear(c.get(GregorianCalendar.YEAR)) ? 1 : 0;
        return daysInMonths[c.get(GregorianCalendar.MONTH)];
   } 
	
	
	
	
	/*
	public static boolean wielkanoc(Calendar calendar){
		 // Obliczanie Wielkanocy wg metody Meeusa/Jonesa/Butchera
		if (calendar.get(calendar.MONTH) >= 3 || calendar.get(calendar.MONTH) <= 6)
		{
		int a = calendar.get(calendar.YEAR) % 19;
		int b = (int) Math.floor(calendar.get(calendar.YEAR)/100);
		int c = calendar.get(calendar.YEAR) % 100;
		int d = (int) Math.floor(b/4);
		int e = b % 4;
		int f = (int) Math.floor((b+8)/25);
		int g = (int) Math.floor((b-f+1)/3);
		int h = (19*a+b-d-g+15)%30;
		int i = (int) Math.floor(c/4);
		int k = c % 4;
		int l = (32+2*e+2*i-h-k) % 7;
		int m = (int) Math.floor((a+11*h+22*l)/451);
		int p = (h+l-7*m+114) % 31;
		int dzien = p + 1;
		int miesiac = (int) Math.floor((h+l-7*m+114)/31);
		Date Wielkanoc = new Date(calendar.get(calendar.YEAR), miesiac, dzien);
		// if (data==Wielkanoc || data == dodajDni(Wielkanoc, 1) || data == dodajDni(Wielkanoc, 49) || data == dodajDni(Wielkanoc, 60))
		return true;
		}
		return false;
	}
	*/
}
	
	
	
	
	

