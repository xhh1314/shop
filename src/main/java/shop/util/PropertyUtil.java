package shop.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import shop.service.impl.ProductServiceImpl;

/**这个工具类目前有缺陷，当读取配置文件失败的时候，无法抛出checked异常
 * @author lh
 *
 */
public class PropertyUtil {

	private static Properties property = new Properties();
	
	static {
		try {
			// 注意这里toURI().getPath()的用法
			String srcpath=PropertyUtil.class.getClassLoader().getResource("").toURI().getPath();
			property.load(new FileInputStream(new File(srcpath + "application.properties")));
			// System.out.println("srcPath=" + srcpath);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			System.out.println("读取application.properties配置文件失败！");
			throw new RuntimeException(e);
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("读取application.properties配置文件失败！");
			throw new RuntimeException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("读取application.properties配置文件失败！");
			throw new RuntimeException(e);
		}
		
		System.out.println("读取application.properties配置文件成功");

	}

	public static String getProperty(String key) {
		//System.out.println("调用getProperty方法");
		return property.getProperty(key);
	}
}
