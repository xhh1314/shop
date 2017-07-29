package shop.bean;


import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Product {
	private String uuid;
	private String name;
	private float originalPrice;
	private float promotePrice;
	private int stock;
	private String createTime;
	private Subdivide subdivide;
	private List<ProductImage> productImage;
	//第一张图片的存储路径
	private String fristImagePath;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public float getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(float originalPrice) {
		this.originalPrice = originalPrice;
	}
	public float getPromotePrice() {
		return promotePrice;
	}
	public void setPromotePrice(float promotePrice) {
		this.promotePrice = promotePrice;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public Subdivide getSubdivide() {
		return subdivide;
	}
	public void setSubdivide(Subdivide subdivide) {
		this.subdivide = subdivide;
	}
	public List<ProductImage> getProductImage() {
		return productImage;
	}
	public void setProductImage(List<ProductImage> productImage) {
		this.productImage = productImage;
		//初始化第一张图片路径的值
		//this.fristImagePath=productImage.get(0).getValue();
	}
	public String getFristImagePath() {
		return fristImagePath;
	}
	public void setFristImagePath(String fristImagePath) {
		this.fristImagePath = fristImagePath;
	}
	
	
	
	
	

}
