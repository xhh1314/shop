package shop.bean.comparator;

import java.util.Comparator;

import shop.bean.Product;

//单例模式
public class ProductAscending implements Comparator<Product> {
	private ProductAscending() {
	}

	private static ProductAscending productAscending;

	public synchronized static ProductAscending getInstance() {
		if (productAscending == null)
			return new ProductAscending();
		else
			return productAscending;
	}

	@Override
	public int compare(Product o1, Product o2) {
		// TODO Auto-generated method stub
		int flag = 0;
		if (o1.getPromotePrice() < o2.getOriginalPrice())
			flag = -1;
		if (o1.getPromotePrice() == o2.getOriginalPrice())
			flag = 0;
		if (o1.getPromotePrice() > o2.getOriginalPrice())
			flag = 1;
		return flag;
	}

}
