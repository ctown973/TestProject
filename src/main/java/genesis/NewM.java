package genesis;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewM {
	private final Character m_value = 'a';
	public String toString() { return ""+m_value; }
	
	public static void main(String[] args) {
//	final List<Integer> list = new ArrayList<Integer>();
//	Collections.addAll(list, 1, 5, 2, 3, 7, 3, 8, 9);
//	final Integer pos = Integer.valueOf(3);
//	list.remove(pos);
//	System.out.println(list);
		int[] i = new int[] {1,7,4,3,10,2,11,8,6,5,9,13,12};
				Merge m = new Merge(i);
				m.sort(i, 0, i.length-1);
				
		for (int j : i) {
			System.out.print(" "+j);
		}
		System.out.println("");
	}
	
	class Person implements Comparable<Person>{
		
		String m_firstName;
		String m_lastName;
		@Override
		public int compareTo(Person p) {
			return (m_lastName.compareTo(p.m_lastName)+m_firstName.compareTo(p.m_firstName));
		}
		
	}

}
