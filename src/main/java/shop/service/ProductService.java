package shop.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import shop.bean.Product;
import shop.bean.wrap.ProductPropertyValue;

public interface ProductService {
	
	public boolean insert(Product product,MultipartFile image,HttpServletRequest request) throws IOException;
	public Product selectById(String uuid);
	public List<Product> findAll();
	public boolean update(Product product);
	public boolean delete(Product product);
	public List<Product> selectBySubdivide(String Subdivideuuid);
	public List<ProductPropertyValue> selectProductPropertyValue(String uuid);
	public List<Product> selectBykeys(String keys);
	
}
