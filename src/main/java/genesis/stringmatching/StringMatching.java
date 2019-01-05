package genesis.stringmatching;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Problem 1 - String matching problem 
 * 
 * This solution starts with the set of the largest words in our dictionary, and runs a recursive
 * algorithm to see if any substrings (where 1 character is removed from the original string)
 *  are valid words in our dictionary.  This algorithm stops when a match is found.
 *  
 *  The complexity of this algorithm is O(n*k) where n is the number of words in our dictionary
 *  and k is the average length.
 *  
 *  To determine this, I considered the worst possible dictionary we could give the algorithm,
 *  where every permutation of letters is a valid word, except there are no single letter valid words.
 *  
 *  This gives us a tree structure that looks like this at the top, 26^m where m is the longest length word
 *  This will generate substring checks of (26^m )*(m-1) and so on throughout the depth of our tree
 *   Substrings that aren't matches are removed from the dictionary, so this will limit the average depth
 *   of our search tree to the average length of the words in our dictionary, k.
 *   Therefore the total number of checks is bound by the total number of dictionary words multiplied
 *   by k.
 *   
 * @author ckoeck
 *
 */
public class StringMatching {

	
	static final int MIN_WORD_LENGTH = 1;
	
	private Dictionary dictionary;

	
	public StringMatching(Dictionary dictionary) {
		this.dictionary = dictionary;
		dictionary.insertWord("O");
		dictionary.insertWord("I");
		dictionary.insertWord("A");		
	}
	
	private HashSet<String> alreadySeen = new HashSet<String>();
	public boolean runMatcher(char[] testString) {
	
		// The recursive function reaches its end when there is only 1 character left
		// If this character is also in our dictionary, then the word satisties our requirement
		if (testString.length == MIN_WORD_LENGTH) {			
			return dictionary.contains(testString);
		}
		
		int index = 0;
		//iterate through all substrings and check to see if they are in our dictionary
		while (index < testString.length) {
			char[] charArray = new char[testString.length-1];
			System.arraycopy(testString, 0, charArray, 0, index);
			System.arraycopy(testString,index+1,charArray,index,testString.length-(index+1));

			index++;
			// If the string is in our dictionary, call runmatcher again to check all possible
			// substrings of this dictionary word
			if (doesMatch(charArray)) {
				boolean doesMatch = runMatcher(charArray);
				if (doesMatch)
					System.out.println(String.valueOf(charArray));
				// we may remove words that we know are not matches from our dictionary
				// This will allow us to avoid checking branches for duplicate substrings in the future
				// We don't remove the word if this is the first call to run matcher, it will cause 
				// a concurrent modification exception in the calling function, also it's unnecessary 
				if (!doesMatch)  {			
					dictionary.removeWord(charArray);
					
				} 
			
				return doesMatch;
				
			}
		}

		return false;
	}
	
	/**
	 * 
	 * @param substringToTest
	 *   			The substring of our dictionary word to test to see if it is also in the dictionary
	 * @return
	 * 		true if it is a match, false otherwise
	 */
	public boolean doesMatch(char[] substringToTest) {
		return dictionary.contains(substringToTest);
	}

	/**
	 * Finds and prints out the largest dictionary word that fits our requirements
	 */
	public void findLargestString() {
	
			
		
		long startTime = System.currentTimeMillis();
		boolean foundMatch = false;
		// Start with the largest bucket and try to find a match, iterating through all words in that bucket
		int maxLength = dictionary.getMaxLength();	
		while (maxLength > 0 && !foundMatch) {
				

//			String str = set.parallelStream().filter(stringToTest -> runMatcher(stringToTest.toCharArray(), stringToTest.length())).findAny().orElse(null);
//			if (str != null) {
//				System.out.println("found str: "+str);
//				break;
//			}
			
			Iterator<String> iter = dictionary.getWords(maxLength).iterator();
			while (iter.hasNext()) {
				String stringToTest = iter.next();
			//	System.out.println("testing string: "+stringToTest);
				boolean isMatch = runMatcher(stringToTest.toCharArray()); 
				
				if (isMatch) {
					System.out.println("FOUND match: "+stringToTest+" is the longest match");
					foundMatch = true;
					break;
				}
				
			}
			maxLength--;
		}
		long endTime = System.currentTimeMillis();
		long runTime = endTime-startTime;
		System.out.println("runtime is: "+runTime);
		
	
	}


}
