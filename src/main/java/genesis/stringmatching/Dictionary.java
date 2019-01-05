package genesis.stringmatching;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Dictionary class stores sets of dictionary words in buckets (sets) sorted by length. 
 * 
 * @author ckoec
 *
 */
public class Dictionary {

	private List<HashSet<String>> wordList;

	/**
	 * This isn't easy to adjust
	 * @param sizeFactor
	 * 			Adjust to change which bucket is allocated the most capacity
	 * @param sizeConstant
	 * 			Fixed constant to adjust the capacity of our buckets
	 * @param maxLength
	 * 			Number of buckets to initialize
	 */
	public Dictionary(float sizeFactor, int sizeConstant, int maxLength) {
		wordList = new ArrayList<HashSet<String>>(16);
		wordList.add(new HashSet<String>());
		for (int i = 1; i < maxLength; i++) {
			double capacity = Math.log(i)*(Math.max(maxLength-i*sizeFactor, 1))*sizeConstant+15;
			HashSet<String> set = new HashSet<String>((int)capacity, 0.75f);
			wordList.add(set);
		}
	}
	
	/**
	 * Constructs our dictionary from a resource stream.
	 * 
	 * @param resource
	 * 			path to our resource
	 * @throws IOException
	 * 			if resource can't be found
	 */
	public void buildDictionaryFromResourceStream(String resource) throws IOException {
		ClassLoader classLoader = StringMatching.class.getClassLoader();
		InputStream stream = classLoader.getResourceAsStream(resource);
		BufferedInputStream bStream = new BufferedInputStream(stream);
		BufferedReader reader = new BufferedReader(new InputStreamReader(bStream, "UTF-8"));
		String s;
		while(  (s = reader.readLine())  != null) {
	
			insertWord(s);
		
		}
		reader.close();
	}
	
	/**
	 * Construct the dictionary from a url, didn't have time to finish implementing this feauture
	 * @param url
	 */
	public void buildDictionaryFromURL(String url) {
		
	}
	
	/**
	 * 
	 * @param word to test
	 * @return
	 * 		True if the word is contained in the set, false otherwise
	 */
	public boolean contains(char[] word) {
		if (wordList.size() <= word.length)
			return false;
		return wordList.get(word.length).contains(String.valueOf(word));
	}
	
	/**
	 * 
	 * @param word to test
	 * @return
	 * 		True if the word is contained in the set, false otherwise
	 */
	public boolean contains(String word) {
		if (wordList.size() <= word.length())
			return false;
		return wordList.get(word.length()).contains(word);
	}
	
	/**
	 * Inserts a word into our dictionary
	 * @param word
	 * @return
	 * 		True if word added successfully, false otherwise
	 */
	public boolean insertWord(String word) {
		if (wordList.size() <= word.length()) {
			for (int i = 0; i <= word.length() - wordList.size(); i++) {
				wordList.add(new HashSet<String>());
			}
		}
		return wordList.get(word.length()).add(word);
	}
	
	/**
	 * Returns the max length of our dictionary
	 * @return
	 */
	public int getMaxLength() {
		return wordList.size()-1;
	}
	
	/**
	 * Get all words of a given length
	 * @param l
	 * 		length of the word set we want to get
	 * @return
	 * 		An unmodifiable set of all words of length l
	 */
	public Set<String> getWords(int l) {
		return Collections.unmodifiableSet(wordList.get(l));
	}
	
	/**
	 * Removes a word from the dictionary
	 * @param wordToRemove
	 * @return
	 * 		True if sucessfully removed, false otherwise
	 */
	public boolean removeWord(char[] wordToRemove) {
		return wordList.get(wordToRemove.length).remove(String.valueOf(wordToRemove));
	}
	
	/**
	 * Removes a word from the dictionary
	 * @param wordToRemove
	 * @return
	 * 		True if sucessfully removed, false otherwise
	 */
	public boolean removeWord(String wordToRemove) {
		return wordList.get(wordToRemove.length()).remove(wordToRemove);
	}
	
	
}
