package shop.bean.comparator;

import java.util.Comparator;

import shop.bean.Product;

/**
 * 使用内部内方式实现单例模式
 * 
 * @author lh
 *
 */
public class ProductDescending implements Comparator<Product> {

	private ProductDescending() {
	}

	static class ProductDescendingInner {
		static ProductDescending pd = new ProductDescending();
	}

	public static ProductDescending getInstance() {

		return ProductDescendingInner.pd;

	}

	@Override
	public int compare(Product o1, Product o2) {
		// TODO Auto-generated method stub
		int flag = 0;
		if (o1.getPromotePrice() > o2.getOriginalPrice())
			flag = -1;
		if (o1.getPromotePrice() == o2.getOriginalPrice())
			flag = 0;
		if (o1.getPromotePrice() < o2.getOriginalPrice())
			flag = 1;
		return flag;
	}

}
