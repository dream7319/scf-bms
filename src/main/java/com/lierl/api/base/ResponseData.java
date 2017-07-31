package com.lierl.api.base;

import com.baomidou.mybatisplus.plugins.Page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lierl
 * @create 2017-07-31 10:54
 **/
public class ResponseData<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<T> records;

	// 传参或指定
	private int num; // 当前页号, 采用自然数计数 1,2,3,...
	private int size; // 页面大小:一个页面显示多少个数据

	// 需要从数据库中查找出
	private long rowCount;// 数据总数：一共有多少个数据

	// 可以根据上面属性：num,size,rowCount计算出
	private int pageCount; // 页面总数
	private int startRow;// 当前页面开始行, 第一行是0行
	private int first = 1;// 第一页 页号
	private int last;// 最后页 页号
	private int next;// 下一页 页号
	private int prev;// 前页 页号
	private int start;// 页号式导航, 起始页号
	private int end;// 页号式导航, 结束页号
	private int numCount = 10;// 页号式导航, 最多显示页号数量为numCount+1;这里显示11页。
	private List<String> showPages = new ArrayList<String>(); // 要显示的页号

	public ResponseData(Page<T> page) {
		this.records = page.getRecords();

		this.num = page.getCurrent();
		if(this.num <= 0){
			num = 1;
		}
		this.size = page.getSize();
		this.rowCount = page.getTotal();
		this.pageCount = page.getPages();
		this.num = Math.min(this.num, this.pageCount);
		this.num = Math.max(1, this.num);
		this.startRow = (this.num - 1) * this.size;
		this.last = this.pageCount;
		this.next = Math.min(this.pageCount, this.num + 1);
		this.prev = Math.max(1, this.num - 1);

		// 计算page 控制
		start = Math.max(this.num - numCount / 2, first);
		end = Math.min(start + numCount, last);
		if (end - start < numCount) {
			start = Math.max(end - numCount, 1);
		}
		for (int i = start; i <= end; i++) {
			showPages.add(String.valueOf(i));
		}
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getRowCount() {
		return rowCount;
	}

	public void setRowCount(long rowCount) {
		this.rowCount = rowCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public int getPrev() {
		return prev;
	}

	public void setPrev(int prev) {
		this.prev = prev;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getNumCount() {
		return numCount;
	}

	public void setNumCount(int numCount) {
		this.numCount = numCount;
	}

	public List<String> getShowPages() {
		return showPages;
	}

	public void setShowPages(List<String> showPages) {
		this.showPages = showPages;
	}
}
