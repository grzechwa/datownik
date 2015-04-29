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
 * 
<<<<<<< HEAD
 * sssssasssssss
=======
 * sssssasss
>>>>>>> parent of 6fae1ec... 004-chybaaa ok
 */
public class CopyOfDni1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("dd.M.yyyy");
		String date = sdf.format(new Date());
		System.out.println(date);

		
		Locale locale = new Locale("pl-PL");
		Calendar calendar = new GregorianCalendar();
		System.out.println(sdf.format(calendar.getTime()));
		// System.out.println(new SimpleDateFormat().format(calendar.getTime()));
		System.out.println(calendar.getWeeksInWeekYear());
		System.out.println(
				sdf.format(
                        // calendar.getTime())
						calendar.get(Calendar.YEAR))
                );
		System.out.println(
				// calendar.getFirstDayOfWeek()
                calendar.get(Calendar.DAY_OF_WEEK)
				);
		
		Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection testData;
		List<String> list = new ArrayList();
		
		
		// calendar = new GregorianCalendar(2015,0, 22); // ustawienie testowej daty
		for(int i = 1; i <= calendar.getActualMaximum(calendar.DAY_OF_MONTH); i++){
			System.out.print(i + " ");			
			calendar.set(2015, Calendar.MONTH+1,i);

			// System.out.print(sdf.format(calendar.getTime())+ " ");
			
			// if (i%7==0) System.out.println();
			// list.add(calendar.getDisplayName(Calendar.DAY_OF_WEEK, calendar.LONG, locale)+"\n");
			
			
			
			// dodaje calosc kalendarza
			list.add(sdf.format(calendar.getTime())+"\n");
			
			// bez niedzieli
			if(Calendar.DAY_OF_WEEK !=7 ){
				System.out.println(Calendar.DAY_OF_WEEK + " --- ");
				// System.out.print(calendar.getDisplayName(Calendar.DAY_OF_WEEK, calendar.SHORT, locale)+" ");	
			}
		}
		
		System.out.println();
		
		StringBuilder sb = new StringBuilder();
		/*
		for(int i = 0; i < list.size(); i++){
			sb.insert(0, list.get(i));
		}
		*/
		
		
		for(int i = list.size()-1; i >= 0; i--){
			sb.insert(0, list.get(i));
		}
		
		
		testData = new StringSelection(sb.toString());
		// doaje do shcowaka
		c.setContents(testData, testData);
		
		
	}

}
