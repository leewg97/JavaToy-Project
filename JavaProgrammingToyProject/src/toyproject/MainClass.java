package toyproject;

import java.io.IOException;

public class MainClass {

	public static void main(String[] args) throws Exception{
		
		System.out.println("============================");
		System.out.println("==== 회원 관리 프로그램 START ====");
		System.out.println("============================");
		
		MemberManager manager = new MemberManager();
		manager.readMenu();
				
		System.out.println("============================");
		System.out.println("===== 회원 관리 프로그램 END =====");
		System.out.println("============================");
		
		
		
	}
	
}
