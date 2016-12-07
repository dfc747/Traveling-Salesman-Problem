import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Created by Alexander Kamsizoglou on 14-Oct-16.
 */
public class tsp {

	public static void main(String[] args) {
		//berlin52.tsp
		//rl5915.tsp

		final String file = "C://Users//dfc//Desktop//uni//Algorithms and Data Structures//Coursework//ALL_tsp//berlin52.tsp";

		ArrayList<Point2D> al = utilities.loadTSPLib(file); // load tsp file to arraylist
		ArrayList<Point2D> result = new ArrayList<>();

		long startTime, stopTime;
		startTime = System.currentTimeMillis();
		result = algorithms.RNN(al); // perform Repetitive Nearest Neighbour
		System.out.println("RNN Route length = " + utilities.routeLength(result));
		result = algorithms.TwoOpt(result); // TwoOpt optimisation of the result from RNN
		System.out.println("After TwoOpt Route length = " + utilities.routeLength(result));
		stopTime = System.currentTimeMillis();
		System.out.println(String.format("Completed in %d milliseconds", stopTime-startTime));

		// check that result is valid
		if (utilities.areSame(utilities.loadTSPLib(file),result)){
			System.out.println("Result is valid");
		}
		else {
			System.out.println("Invalid result!");
		}
	}
}
