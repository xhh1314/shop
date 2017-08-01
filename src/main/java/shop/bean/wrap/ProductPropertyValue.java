package shop.bean.wrap;

/**该类用来封装前端传送过来的json数组数据
 * @author lh
 *
 */
public class ProductPropertyValue {
	private String productUuid;
	private String productName;
	private String propertyName;
	private String propertyValue;
	
	public ProductPropertyValue(String productUuid, String productName, String propertyName, String propertyValue) {
		this.productUuid = productUuid;
		this.productName = productName;
		this.propertyName = propertyName;
		this.propertyValue = propertyValue;
	}
	public String getProductUuid() {
		return productUuid;
	}
	public String getProductName() {
		return productName;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public String getPropertyValue() {
		return propertyValue;
	}
	
	
	

}
