package shop.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import shop.bean.Product;
import shop.bean.ProductImage;
import shop.bean.wrap.ProductPropertyValue;
import shop.dao.ProductDao;
import shop.dao.ProductImageDao;
import shop.service.ProductService;
import shop.util.GetUUID;
import shop.util.PropertyUtil;

@Service("productService")
@Scope("prototype")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImageDao productImageDao;
	// 项目根目录
	//private static String rootPath = null;
	// 图片存储目录
	private static String imageRoot = PropertyUtil.getProperty("imageroot");
	@Override
	public boolean insert(Product product, MultipartFile image,HttpServletRequest request) throws IOException {
		// TODO Auto-generated method stub
		boolean flag = false;
		product.setUuid(GetUUID.getUuid());// 给uuid属性赋值
		String rootPath=request.getServletContext().getRealPath("")+File.separator+imageRoot;
		ProductImage pi = new ProductImage();
		pi.setPd_uuid(product.getUuid());
		pi.setValue(saveImage(image,rootPath));// 保存图片，并且将路径字符串返回保存
		// 这里创建一个图片类的list,实际上本案例只上传了一张图片，没有涉及多种图片上传的情况
		List<ProductImage> pis = new ArrayList<ProductImage>();
		pis.add(pi);

		if (pis != null)
			product.setProductImage(pis);

		productDao.insert(product);
		if (product.getProductImage() != null)// 产品的图像属性不为空才进行插入操作
			productImageDao.insert(product.getProductImage());
		flag = true;// 这里省略掉Product对象插入判断过程
		return flag;
	}

	/**
	 * @param image
	 * @return存储到数据库的路径字符串
	 * @throws IOException
	 */
	private String saveImage(MultipartFile image,String rootPath) throws IOException {
		String filename = image.getOriginalFilename();
		// 该路径是存储图片的根目录
		System.out.println("测试项目根目录对不对rootPath:" + rootPath);
		System.out.println("测试项目图片目录对不对iamgeRoot:" + imageRoot);
		// String rootPath = request.getServletContext().getRealPath("") +
		// File.separatorChar + "resource"+ File.separatorChar + "image";
		String database_local = imageRoot + "/"+makeCurrentPath(filename);// 前端引用image的路径
		String location = makePath(filename, rootPath);// 得到一个文件存储目录
		filename = makeFilename(filename);// 得到一个随机的文件名
		database_local = database_local + filename;// 该路径是存储到数据库的路径字符串
		File file = new File(location);
		if (!file.exists()) {// 如果存储目录不存在，则创建存储目录
			file.mkdirs();
		}
		location = location + filename;// 这是实际的存储目录+实际文件名
		byte[] temp = image.getBytes();
		OutputStream out = new FileOutputStream(location);
		out.write(temp);
		out.close();
		return database_local;// 把数据库的存储路径返回
	}

	/**
	 * 得到一个文件随机名字
	 * 
	 * @param filename
	 * @return
	 */
	private String makeFilename(String filename) {
		return UUID.randomUUID().toString() + "_" + filename;// 得到一个随机名字
	}

	/**
	 * 使用hash散列算法，算出文件存储路径（在要把文件存到硬盘里时调用该方法）
	 * 
	 * @param filename
	 * @param path
	 * @return
	 */
	private String makePath(String filename, String path) {
		int hashcode = filename.hashCode();
		int dir1 = hashcode & 0xf;
		int dir2 = (hashcode & 0xf0) >> 4;
		return path + File.separatorChar + dir1 + File.separatorChar + dir2 + File.separatorChar;
	}

	/**
	 * 获取存入到数据库的路径字符串
	 * 
	 * @param filename
	 * @return
	 */
	private String makeCurrentPath(String filename) {
		int hashcode = filename.hashCode();
		int dir1 = hashcode & 0xf;
		int dir2 = (hashcode & 0xf0) >> 4;
		return dir1 + "/" + dir2 + "/";

	}

	@Override
	public Product selectById(String uuid) {
		// TODO Auto-generated method stub
		return productDao.selectById(uuid);

	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return productDao.findAll();
	}
	public List<Product> selectBySubdivide(String Subdivideuuid){
		return productDao.selectBySubdivide(Subdivideuuid);
	}
	@Override
	public boolean update(Product product) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Product product) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ProductPropertyValue> selectProductPropertyValue(String uuid) {
		// TODO Auto-generated method stub
		List<ProductPropertyValue> productPropertyValues=null;
		if(uuid!=null){
			productPropertyValues=productDao.findProductPropertyValue(uuid);
			if(productPropertyValues==null)
				productPropertyValues=new ArrayList<ProductPropertyValue>();
		}
		return productPropertyValues;
	}

	@Override
	public List<Product> selectBykeys(String keys) {
		// TODO Auto-generated method stub
		if(keys==null || keys=="")
			return null;
		return productDao.selectByKeys(keys);
	}

}
