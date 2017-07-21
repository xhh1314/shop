package shop.util;

import java.util.Calendar;
import java.util.Random;

import shop.bean.User;

public class OrderCode {
	
	public String getOrderCode(Calendar c,String userid){
		int mouth=c.get(Calendar.MONTH)+1;
		int day=c.get(Calendar.DAY_OF_MONTH);
		int minute=c.get(Calendar.MINUTE);
		int second=c.get(Calendar.SECOND);
		//生成一个月日分秒的时间戳
		String datestr=transform(mouth)+transform(day)+transform(minute)+transform(second);
		Random random=new Random();
		int randomNum=random.nextInt(90)+10;//生成一个两位数的随机数
		String UID=userid.substring(userid.length()-4);//获取用户uuid的后4位
		return datestr+randomNum+UID;
	}
	
	//补全2位数的日期
	private String transform(int str){
		if(str<10)
		{
			return 0+""+str;
		}else{
			
			return str+"";
		}
		
		
	}

}
