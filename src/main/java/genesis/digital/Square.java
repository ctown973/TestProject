package genesis.digital;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

import genesis.Car;

public class Square {
	
	

	public static void main(String[] args) {
		String str = "3992";
		Car car;
		
		int test = Integer.parseInt(str);
		StringBuilder builder = new StringBuilder();
		int result = (int)(test / 1000);
		System.out.println("result: "+result);
		for (int i = 0; i < (int)result; i++) {
			builder.append("M");
		}
		test = test - 1000*result;
		result = (int)(test / 500);
		System.out.println("test now: "+test);
		System.out.println("mod: "+(test % 100));
		if ((int)(test / 100) == 9 ) {
			builder.append("CM");
		} else {
	
		System.out.println("result: "+result);
		for (int i = 0; i < (int)result; i++) {
			builder.append("D");
		}
		test = test - 500*result;
		System.out.println("test now: "+test);
		result = (int) (test / 100);
		for (int i = 0; i < (int)result; i++) {
			builder.append("C");
		}
		}
		System.out.println("result: "+result);
		test = test - 500*result;
		System.out.println("test now: "+test);
		result = (int) (test / 100);
		for (int i = 0; i < (int)result; i++) {
			builder.append("C");
		}
		test = test - 100*result;
		System.out.println("test now 4: "+test);
		result = (int) (test / 50);
		int tens = str.charAt(1) - '0';
		if (tens == 4) {
			builder.append("XL");
			test = test -40 ;
		}
		if (tens == 9) {
			builder.append("XC");
			test = test -90;
		} else {
		
			
			for (int i = 0; i < (int)result; i++) {
				builder.append("L");
			}
			test = test - 50*result;
			System.out.println("test now: "+test);
			result = (int) (test / 10);
			for (int i = 0; i < (int)result; i++) {
				builder.append("X");
			}
			test = test - 10*result;
			
			result = (int) (test / 5);
			for (int i = 0; i < (int)result; i++) {
				builder.append("V");
			}
			test = test - 5*result;
		}
		
	
		System.out.println("test now 5: "+test);
		result = (int) (test / 5);
		for (int i = 0; i < (int)result; i++) {
			builder.append("V");
		}
		test = test - 5*result;
	
		System.out.println("test now: "+test);
		
		result = (int) (test /1);
		for (int i = 0; i < (int)result; i++) {
			builder.append("I");
		}
	
		System.out.println("test now: "+test);
		result = (int) (test / 1);
		System.out.println("result: "+result);
		System.out.println(builder.toString());
	}

}
