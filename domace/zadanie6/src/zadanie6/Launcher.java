package zadanie6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Launcher {

	public static void main(String[] args) {
		File subor = new File("C:\\Users\\rasti\\eclipse-workspace\\zadanie6\\src\\zadanie6\\subor.txt");
		
		FileTurtle rasto = new FileTurtle();
//		
//		try(Scanner sc = new Scanner(subor)){
//			while(sc.hasNext()) {
//				
//				System.out.println(sc.next());
//			}
//			
//			
//		}catch(FileNotFoundException e) {
//			System.out.println("nenasiel som subor kokoti");
//		}
//		System.out.println(rasto.isWinner("Biden", "C:\\Users\\rasti\\eclipse-workspace\\zadanie6\\src\\zadanie6\\subor.txt"));
	
		
		System.out.println(Arrays.toString(rasto.vycitanka(4, 7, "C:\\Users\\rasti\\eclipse-workspace\\zadanie6\\src\\zadanie6\\subor.txt")));
	
//	String skuska = "aaaaa12gdgdg36gdge54";
//	
//	String skuska1 = skuska;
//	
//	System.out.println(skuska1.toUpperCase());
//	
		
		
		String haha = "asjfiaofnlas";
		
		System.out.println(haha.equals(null));
		
		//rasto.renameVariables("C:\\Users\\rasti\\eclipse-workspace\\zadanie6\\src\\zadanie6\\subor.txt", "C:\\Users\\rasti\\eclipse-workspace\\zadanie6\\src\\zadanie6\\vystup.txt");
	}

}
