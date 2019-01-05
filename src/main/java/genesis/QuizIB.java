package genesis;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.LongBuffer;
import java.util.BitSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class QuizIB {
	
	private Pattern pattern;
	private Matcher matcher;
	
	public QuizIB(String regularExpression) {
		 pattern = Pattern.compile(regularExpression);
		
	}
	private static final char sep = 0x20;
	private static final int message_type = 1;
	
	
	public boolean doesMatch(String testString) {
		return pattern.matcher(testString).matches();
	}

	public static int max (int a, int b) {
		return (a > b) ? a : b;
	}
	public static void filter(List<String> list)/* throws Exception */{
		if (list != null) {
			for (Iterator<String> itr = list.iterator(); itr.hasNext();) {
				if ("bad".equals(itr.next())) itr.remove();
			}
		}
	//d	throw new Exception("hi");
	}
	
	public static boolean checkIfPowerOfTwo(int d) {
		if (d == 1 || d == 0) 
			return false;
		int temp = d;
		while (temp % 2 == 0) {
			temp = temp/2;
		}
		boolean result = temp == 1;
		BitSet set = BitSet.valueOf(LongBuffer.wrap(new long[] {d}));
		System.out.println("d: "+d+ " "+set.cardinality());
		return result;
		
		
	}
	public static void main(String[] args) {
		List<String> stringList = new LinkedList<String>();
		
			filter(stringList);
			
			int a, b, c, d, e,f,g,h,i,j,k, l,m, n,p;
			a = 21;
			b = 16;
			c = 124;
			d = 1024;
			e = 2146;
			f = 1;
			g = 0;
			h = -1;
			i = -2;
			j = -3;
			k = -10;
			l = -1024;
			m = 2048;
			n = 64;
			p = 8;
			System.out.println("a: "+a+ " "+checkIfPowerOfTwo(a));
			System.out.println("b: "+b+ " "+checkIfPowerOfTwo(b));
			System.out.println("c: "+c+ " "+checkIfPowerOfTwo(c));
			System.out.println("d: "+d+ " "+checkIfPowerOfTwo(d));
			System.out.println("e: "+e+ " "+checkIfPowerOfTwo(e));
			System.out.println("f: "+f+ " "+checkIfPowerOfTwo(f));
			System.out.println("g: "+g+ " "+checkIfPowerOfTwo(g));
			System.out.println("h: "+h+ " "+checkIfPowerOfTwo(h));
			System.out.println("i: "+i+ " "+checkIfPowerOfTwo(i));
			System.out.println("j: "+j+ " "+checkIfPowerOfTwo(j));
			System.out.println("k: "+k+ " "+checkIfPowerOfTwo(k));
			System.out.println("l: "+l+ " "+checkIfPowerOfTwo(l));
			System.out.println("m: "+m+ " "+checkIfPowerOfTwo(m));
			System.out.println("n: "+n+ " "+checkIfPowerOfTwo(n));
			System.out.println("p: "+p+ " "+checkIfPowerOfTwo(p));
			
			
			
			
		
		
	}

}
