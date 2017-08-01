package shop.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 从ApplicationContext中获取通过@annotation方式实例化的Bean
 * 通过实现ApplicationContextAware接口，获取ApplicationContext实例
 * @author lh
 *
 */
@Component
public class SpringBeanUtil implements ApplicationContextAware {

	private static  ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		SpringBeanUtil.applicationContext=applicationContext;
		//System.out.println("实例化SpringBeanUtil");
	}
	public static <T> T getBeanInstance(Class<T> bean){
		return applicationContext.getBean(bean);
	} 
	

}
