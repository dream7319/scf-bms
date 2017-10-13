package com.impl;

/**
 * @author lierl
 * @create 2017-10-13 15:09
 **/
public class B extends A{

	public String show(B obj){
		return ("B and B");
	}
	public String show(A obj){
		return ("B and A");
	}
}
