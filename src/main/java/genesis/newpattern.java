package genesis;

import java.util.regex.Pattern;

public class newpattern {

	private Pattern pattern;
	public newpattern() {
		pattern = Pattern.compile("^([0-9]+)$");
	}
	
	public boolean doesMatch(String s) {
		return pattern.matcher(s).matches();
	}
	public static void main(String[] args) {
		newpattern p = new newpattern();
		System.out.println(p.doesMatch("9\na"));
	}

}
