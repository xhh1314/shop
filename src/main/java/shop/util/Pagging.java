package shop.util;


/**
 * @author lh
 *算法思路：
 *1、不根据当前页算起始页
 *2、当用户点击上一页或者下页的时候，才算出下一次需要显示的页码
 */
public class Pagging {
	
	private int begin;
	private int end;
	//private int current;
	//private int total;
	private int eachPage;
	
	public Pagging(int eachPage){
		
		this.eachPage=eachPage;
	}
	public void initialPageNumber(int totalPage){
		if(totalPage<=eachPage){
			this.begin=1;
			this.end=totalPage;
		}
		else{
			this.begin=1;
			this.end=this.eachPage;
		}
		
	}
	
	public void previousPage(int beginPage,int totalPage){
		if((beginPage-1)<=eachPage){
			this.begin=1;
			this.end=totalPage;
		}
		else{
			this.begin=beginPage-eachPage;
			this.end=beginPage-1;
		}
	}
	
	/**
	 * @param endPage
	 * @param totalPage
	 * 算出下一页显示的页码，如果遇到尾页，则保持尾页维持显示eachPage
	 */
	public void nextPage(int endPage,int totalPage){
		if((endPage+eachPage)>=totalPage){
			this.end=totalPage;
			this.begin=end-eachPage;
		}else{
		this.begin=endPage+1;
		this.end=begin+eachPage;
		}
	}

}
