package datownik;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.jws.soap.SOAPBinding.Style;
/*
 * ss
 */
public class CopyOfDni2 {

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
		
		
		// calendar = new GregorianCalendar(2015,0, 22); // ustawienie testowej daty
		for(int i = 1; i <= calendar.getActualMaximum(calendar.DAY_OF_MONTH); i++){
			System.out.print(i + " ");			
			calendar.set(2015, Calendar.MONTH+1,i);
			
			
			
			
			int dzien = calendar.get(calendar.DAY_OF_MONTH);
			int mies = calendar.get(calendar.MONTH);
			int rok = calendar.get(calendar.YEAR);
			String res = sdfDay.format(calendar.getTime());
			// if(res.equals("niedziela") || res.equals("sobota")) System.out.println("niedziela lub sobota");
			if(!(res.equals("niedziela") || res.equals("sobota"))) {
			String myKal = String.valueOf(dzien) + "-" + String.valueOf(mies) + "-" + String.valueOf(rok); 
			// System.out.println(calendar.getDisplayName(calendar.MONTH, calendar.LONG, locale) );
			// System.out.println(calendar.get(calendar.DAY_OF_MONTH));
			// dodaje calosc kalendarza
			// list.add(sdf.format(calendar.getTime())+"\n");
			list.add(myKal +"\n");
			}
			
		}
		
        System.out.println("\nDane o dacie:\n" );
        /*
        int dzien = calendar.get(calendar.DAY_OF_MONTH);
        int mies = calendar.get(calendar.MONTH);
        int rok = calendar.get(calendar.YEAR);
        String myKal = String.valueOf(dzien) + "-" + String.valueOf(mies) + "-" + String.valueOf(rok); 
		*/
        System.out.println(
        				 
        		// myKal
        		/*
        		calendar.get(calendar.DAY_OF_MONTH) + " " + 
        		calendar.get(calendar.MONTH) + " " + 
        		calendar.get(calendar.YEAR)
        		
        		calendar.getActualMinimum(calendar.DAY_OF_MONTH) + " " +
        		calendar.getActualMaximum(calendar.DAY_OF_MONTH) + " " +
        		calendar.getDisplayName(calendar.MONTH, calendar.LONG, locale) + " " +
        		calendar.getDisplayName(calendar.MONTH, calendar.LONG, locale) 
        		*/
        		
        		);

		
		StringBuilder sb = new StringBuilder();
		for(int i = list.size()-1; i >= 0; i--){
			sb.insert(0, list.get(i));
			System.out.println(sb);
		}
		
		testData = new StringSelection(sb.toString());
		// doaje do shcowaka
		c.setContents(testData, testData);
         
        System.out.println(sdfDay.format(calendar.getTime()));
	}
}
