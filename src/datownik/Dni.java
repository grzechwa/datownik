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

public class Dni {

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
		
		// Dni swiateczne umieszone w tablicy + pierwszy przypadek testowy
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
		swieta[9] = wielka((GregorianCalendar)calendar);
		swieta[10] = wielka((GregorianCalendar)calendar);
		swieta[10].add(Calendar.DAY_OF_YEAR, 59);
		
		// zmienne do ustawiania dnia miesiaca w petli for + 
		// ustawinie poczatkowego dnia
        int dzien = 1;
        int mies = calendar.get(calendar.MONTH)+2;
        int rok = calendar.get(Calendar.YEAR);

        calendar.set(rok, mies, 1);

		for(int i = 1; i <= daysInMonth((GregorianCalendar)calendar); i++){
			
			// Ustawiam kalendarz, z ktorego bede korzystal
			// TODO: zautomatyzowac ustawianie kalendarza
			calendar.set(rok, mies, i);
			
			// Przygotowuje wlasny format daty do zapisu w liscie
			dzien = calendar.get(calendar.DAY_OF_MONTH);
			
			// Potrzebna nazwa dnia, do usuniecia go z "kalendarza"
			String res = sdfDay.format(calendar.getTime());
			
			// Dodaje do listy dni bez weekendow
			// TODO: dodac wyjatki zwiazane z wielkanoca
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
		
		// System.out.println("---------" + wielka((GregorianCalendar)calendar));
		wielka((GregorianCalendar)calendar);
		
		

		
		
		
		
		/*
		if(data+60 > 31)
			System.out.println(data%31 + " czerwca");
		else
			System.out.println(data + " marca");
			*/
	}
	
	
	// http://www.elektroda.pl/rtvforum/topic1244944.html
	public static int daysInMonth(GregorianCalendar c) {
        final int [] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        daysInMonths[1] += c.isLeapYear(c.get(GregorianCalendar.YEAR)) ? 1 : 0;
        return daysInMonths[c.get(GregorianCalendar.MONTH)];
   } 
	
	
	// wielkanoc itp
	public static Calendar wielka(GregorianCalendar rok){
		int a,b,c,d,e;
		a = rok.get(Calendar.YEAR) % 19;
		b = rok.get(Calendar.YEAR) % 4;
		c = rok.get(Calendar.YEAR) % 7;
		
		d = (a * 19 + 24) % 30;
		e = (2 * b + 4 * c + 6 * d + 5) % 7;
		
		// wyjatki
		if((d == 29 && e == 6) ||
		   (d == 28 && e == 6 )){ d -= 7;}
		
		GregorianCalendar wlk = new GregorianCalendar(2015, 5, 1);
		System.out.print("Wielkanoc przypada: ");
		
		int data = 22 + d + e;
		if(data > 31){
			System.out.println(data%31 + " kwietnia");
			wlk.set(rok.get(Calendar.YEAR),3, data%31);
		}
		else{
			System.out.println(data + " marca");
			wlk.set(rok.get(Calendar.YEAR), 2, data);
		}
		SimpleDateFormat sss = new SimpleDateFormat("dd.M.yyyy");
		
		System.out.print("BozeCialo przypada: ");
		// przesuwam o jeden dzien, bo niedziela i tak jest wolna
		// daltego zamiast 60 jest 59
		wlk.add(Calendar.DAY_OF_YEAR, 1);
			System.out.println(sss.format(wlk.getTime()));
		wlk.add(Calendar.DAY_OF_YEAR, 59);
			System.out.println(sss.format(wlk.getTime()));
		wlk.add(Calendar.DAY_OF_YEAR, -59);
			return wlk;
	}
}
	
	
	
	
	

