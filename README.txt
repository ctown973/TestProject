Building the project can now be done with one command "mvn package".  This will create an executable jar in the target directory.  

I made a few significant changes to the String Matching algorithm that made it significantly faster.  First, I separated out my data structure into its own class called
Dictionary, which allows the developer more control over the initial capacity of the Sets.  I set mine to a high enough capacity that no rehashes never take place, and
key collisions are unlikely.  Also the the dictionary is built using a resource stream or alternatively, a URL to download the data.  I didn't have time to implement 
the URL download method.  The next major performance improvement I made is instead of creating two substrings and concatenating them, I create a character array and 
copy the substrings into the array.  This cut the runtime of the algorithm significantly.  I also cleaned up the recursive loop, only passing one argument instead of two.  
I experimented with solving the problem with Stream filters, this gave an elegant solution to the problem but the performance was worse, I believe the overhead of the
Stream on our small data set cancelled out any potential performance gains.  

For the Monte Carlo solution, I abstracted our data structure into a "jar" class and parameterized the number of simulations and number of white balls for our test. I 
changed the main class to allow for user input to set these variables.  