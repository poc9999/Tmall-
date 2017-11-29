package tmall.util;

import java.util.List;

/**
 * 分页工具类
 * @author poc999
 *
 * @since 2017.10.28
 */
public class Page <T>{

	//当前显示的页数
	private int currentPage=1;
	//每页显示几条记录
	private int size=0;
	//一共有多少条记录
	private int totalNum=0;
	//根据计算得出的总页数
	private int totalPage=0;
	//返回的分页信息list
	private List<T> data;
	
	public Page(int size){
		this.size=size;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	//设置页数
	public void setCurrentPage(int currentPage) {
		
		if(currentPage<1){
			this.currentPage=1;
		}else if(currentPage>this.totalPage){
			this.currentPage=this.totalPage;
		}else{
			this.currentPage = currentPage;
		}
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getTotalNum() {
		return totalNum;
	}
	
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
		
		if(totalNum%this.size==0){
			this.totalPage = totalNum/this.size;
		}else if(totalNum%this.size!=0){
			this.totalPage = totalNum/this.size+1;
		}
		
	}
	public int getTotalPage() {
		return totalPage;
	}
	
	
	
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
	
	
}
