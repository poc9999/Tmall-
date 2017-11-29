package tmall.util;

import java.util.List;

/**
 * ��ҳ������
 * @author poc999
 *
 * @since 2017.10.28
 */
public class Page <T>{

	//��ǰ��ʾ��ҳ��
	private int currentPage=1;
	//ÿҳ��ʾ������¼
	private int size=0;
	//һ���ж�������¼
	private int totalNum=0;
	//���ݼ���ó�����ҳ��
	private int totalPage=0;
	//���صķ�ҳ��Ϣlist
	private List<T> data;
	
	public Page(int size){
		this.size=size;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	//����ҳ��
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
