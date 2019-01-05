package genesis;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

import genesis.montecarlo.MonteCarlo;
import genesis.stringmatching.Dictionary;
import genesis.stringmatching.StringMatching;

public class GenesisTest {
	private  int test;
	private Object lock = new Object();
	private ReentrantLock reentrantLock = new ReentrantLock();
	public GenesisTest() {
		test = 0;
	}

	
	public void voidStartThreads() {
		Thread t  = new Thread(()-> {
			 for (int i = 0; i < 100000000; i++) {
				// synchronized(lock) {
					 reentrantLock.lock();
					 test++;
					 reentrantLock.unlock();
				// }
			 }
		});
	
		Thread t2  = new Thread(()-> {
			 for (int i = 0; i < 100000000; i++) {
				// synchronized(lock) {
					 reentrantLock.lock();
					 test++;
					 reentrantLock.unlock();
			//	 }
			 }
		});
		t.start();
		t2.start();
		try {
			t.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("TEST INT: "+test);
	}
	public static void main(String[] args) {
		
		
		GenesisTest test = new GenesisTest();
		test.voidStartThreads();
		System.out.println("Hi, please enter 1 to run the solution to the 1st problem, our longest string matching algorithm. Enter 3 to run our monte carlo solution. \nEnter \"quit\" to exit \n");
		
		String command;
		
		Scanner in = new Scanner(System.in);
		command = in.next();
		while (!command.equals("quit")) {
			if (command.equals("1")) {
				long startTime = System.currentTimeMillis();
				Dictionary dictionary = new Dictionary(0.8f, 5000, 15);
				try {
					dictionary.buildDictionaryFromResourceStream("sowpods.txt");
				} catch (IOException e) {
				
					e.printStackTrace();
				}
				StringMatching stringMatching = new StringMatching(dictionary);
				stringMatching.findLargestString();
				long endTime = System.currentTimeMillis();
				long runTime = endTime - startTime;
				System.out.println("total runtime: "+runTime);
			} else if (command.equals("3")) {
				MonteCarlo carlo = new MonteCarlo();
				System.out.println("please enter the number of simulations to run and white balls to use for the monte carlo test. \n Example: <NumOfSimulations>,<NumOfWhiteBalls> \n");
				boolean isEntered = false;
				while (!isEntered) {
					command = in.next();
				
					try {
						String[] tokens = command.split(",");
						
						carlo.runMonteCarloTest(true, Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
	
						carlo.runMonteCarloTest(false, Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
						isEntered = true;
					} catch (RuntimeException e) {
						System.err.println("improperly entered parameters, please enter integers in the format <NumOfSimulations>,<NumOfWhiteBalls>\n" );
					}
				}
			}
			command = in.next();
		}
		
	 
		// run monte carlo test with replacement, then without replacement
	

	}

}
