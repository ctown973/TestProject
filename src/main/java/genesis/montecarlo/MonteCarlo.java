package genesis.montecarlo;

import java.awt.Color;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Problem 3 - Monte Carlo test
 * 
 * To calculate the Expected value of draws to get the black ball without replacement, 
 * I imagined the 6 possibilities that could take place, that is draws of 
 * b w w w w w
 * w b w w w w
 * w w b w w w
 * w w w b w w
 * w w w w b w
 * w w w w w b
 * 
 * To calculate the expected number, of draws we can take the average of the number of draws.
 * (1+2+3+4+5+6)/ 6 = 3.5
 * 
 * For k-1 white balls, we can generalize the previous formula as ((1+2+3....+(k-1))+k)/k 
 * 
 * k-1
 * Σ	i
 * i=1
 * ______   +1
 * 
 * 	 k
 * 
 * 
 * With replacement, we can calculate expected value as the sum of the infinite series that represents the probability of 
 * k number of white balls being pulled before a black ball is pulled.
 * 
 * ∞
 * Σ    (5/6)^k
 * k=0
 * 
 * This series converges to 6, our expected value.  
 * 
 * Below is a monte carlo simulation test for confirmation.
 * @author ckoeck
 *
 */
public class MonteCarlo {

	private Jar<Ball> jar;
	
	private int numberOfWhiteBalls;
	
	public MonteCarlo() {
		jar = new Jar<Ball>();
	}
	
	
	/**
	 * Clears the "jar" and re-populates it with white balls and a black ball
	 * @param list
	 */
	public void populateList(Jar<Ball> jar) {
		jar.empty();
		for (int i =0; i < numberOfWhiteBalls; i++) {
			jar.addItem(new Ball(Ball.Color.white));
		}
		jar.addItem(new Ball(Ball.Color.black));
	}


	/**
	 * 
	 * @param doReplace
	 * 			Set to true if we are running the test with ball replacement, false otherwise
	 */
	public void runMonteCarloTest(boolean doReplace, int runs, int numOfWhiteBalls) {

		this.numberOfWhiteBalls = numOfWhiteBalls;
		populateList(jar);
		
		int totaldraws = 0;
		double avgdraw = 0;
		// We are going to run plenty of sample runs to get a good average
		for (int k = 1; k <= runs; k++) {
			// run the inner loop until the black ball is found, then we will breeak;
			for (int i = 0; ; i++)  {

				int random = (int)(Math.random()*100000*jar.size())%(jar.size());
				Ball choosenBall;
			
				if (doReplace) {
					 choosenBall = jar.getItem(random);
				} else {
					 choosenBall = jar.removeItem(random);
				}
				if (choosenBall.isBlack()) {
					// if we've choosen the black ball, add the number of draws we took to get here to the total draws pulled so far 
					// (don't forget the extra draw for the black ball)
					totaldraws += (i+1);
					break;
				}	
			}
			// calculate the current average then populate the list.  We could also wait until the end to calculate the average
			avgdraw = totaldraws/(double)(k);
			if (!doReplace)
				populateList(jar);
				
		}
		
		if (doReplace) {
			System.out.println("Expected number of draws until we pulled the black ball with replacement: "+avgdraw);
		} else {
			System.out.println("Expected number of draws until we pulled the black ball without replacement: "+avgdraw);
		}
	}
	

	static final class Jar<T> {
		/**
		 * Our representation of a jar of balls
		 */
		private List<T> list;
		
		public Jar() {
			
			list = new LinkedList<T>();
		}
		
		public void empty() {
			list.clear();
		}
		
		public void addItem(T item) {
			list.add(item);
		}
		
		public T getItem(int i) {
			return list.get(i);
		}
		
		public T removeItem(int i) {
			return list.remove(i);
		}
		
		public int size() {
			return list.size();
		}
	}
	
	static final class Ball {
		private Color color;
		private enum Color  {black, white};
		public Ball(Color color) {
			this.color = color;
		}
		
		public boolean isBlack() {
			if (color == Color.black) {
				return true;
			} else 
				return false;
		}
	}
	
}
