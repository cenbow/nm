package com.hs.loan.acctplus;

/** 
 * <li>ClassName:SubClass <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2017年1月13日 <br/> 
 * <li>@author   zzy       
 */
public class SubClass extends StaticTets{
	
	@Override
	public void bb() {
		System.out.println("SubClass bb");
	}
	
	public static void aa(){
		
		System.out.println("SubClass aa");
	}
	
	public static void main(String[] args) {
		StaticTets s = new SubClass();
		s.aa();
		s.bb();
		
		
	}
}
