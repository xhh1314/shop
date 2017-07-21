package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import org.junit.Test;

public class DateFormateTest {
	
	@Test
	public void test1() throws ParseException{
		Calendar c=Calendar.getInstance();
		System.out.println(c.get(Calendar.MONTH)+1+"月"+c.get(Calendar.DAY_OF_MONTH)+"日"+c.get(Calendar.MINUTE)+"分"+c.get(Calendar.SECOND)+"秒");
		Date date=c.getTime();
		System.out.println("date:"+date.toString());
		
	}
	@Test
	public void test2(){
		
		Random random=new Random();
		for(int i=0;i<10;i++)
		{
			System.out.println("float:"+random.nextFloat());
			System.out.println("double"+random.nextDouble());
			
			
		}
		
	}

}
