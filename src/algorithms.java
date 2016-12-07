import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Created by Alexander Kamsizoglou on 11-Nov-16.
 */
public class algorithms {

	/**
	 * Returns an ArrayList of Point2D that contains the solution
	 * of the Nearest Neighbour algorithm. This algorithm
	 * will select the first point of the ArrayList as a starting point,
	 * it will calculate the total distanceDifference and it will return the route
	 * with the minimum distanceDifference.
	 * @param source	an ArrayList of Point2D
	 * @return			the ArrayList with the minimum distanceDifference
	 */
	public static ArrayList<Point2D> NN(ArrayList<Point2D> source) {

		ArrayList<Point2D> cities = new ArrayList<>(source);
		ArrayList<Point2D> result = new ArrayList<>();

		Point2D currentCity = cities.remove(0);


		while (cities.size() > 0) {
			result.add(currentCity);
			cities.remove(currentCity);
			double distance = Double.POSITIVE_INFINITY;
			Point2D closest = new Point2D.Double();

			for (Point2D possibleCity : cities) {
				if (currentCity.distance(possibleCity) < distance) {
					closest = possibleCity;
					distance = currentCity.distance(possibleCity);
				}
			}

			currentCity = closest;
		}

		return result;
	}

	/**
	 * Returns an ArrayList of Point2D that contains the solution
	 * of the Repetitive Nearest Neighbour algorithm. This algorithm
	 * will select every point as a starting point, it will calculate
	 * the total distanceDifference using the Nearest Neighbour algorithm and it
	 * will return the route with the minimum distanceDifference.
	 * @param source	an ArrayList of Point2D
	 * @return			the ArrayList with the minimum distanceDifference
	 */
	public static ArrayList<Point2D> RNN(ArrayList<Point2D> source) {


		ArrayList<Point2D> result = new ArrayList<Point2D>();
		ArrayList<Point2D> minimumResult = new ArrayList<>();
		double smallestTour = Double.POSITIVE_INFINITY;
		int maxrun = source.size();

		/*
		if (maxrun > 40){ // limit to 40 runs for demonstration only.
			maxrun = 40;
		}
		*/

		for ( int i = 0; i < maxrun; i++){ // Try with all possible starting cities
			ArrayList<Point2D> cities = new ArrayList<>(source);
			result = new ArrayList<>();
			double distance;

			Point2D currentCity = cities.remove(i);

			while (cities.size()  > 0) {
				result.add(currentCity);
				cities.remove(currentCity);
				distance = Double.POSITIVE_INFINITY;
				Point2D closest = new Point2D.Double();

				for (Point2D possibleCity : cities) {
					if (currentCity.distance(possibleCity) < distance) {
						closest = possibleCity;
						distance = currentCity.distance(possibleCity);
					}
				}

				currentCity = closest;
			}

			double tourLength = utilities.routeLength(result);

			// Hold the shortest tour
			if (tourLength < smallestTour) {
				smallestTour = tourLength;
				minimumResult = result;
			}

		}

		return minimumResult;
	}

	// TwoOpt algorithm calculates if changing between two edges
	// will decrease route distance. If yes then it swaps the edges.
	public static ArrayList<Point2D> TwoOpt(ArrayList<Point2D> route){

		double best_distance = utilities.routeLength(route);
		double new_distance = Double.POSITIVE_INFINITY;

		boolean changed = true; // set it to true to begin while loop

		while (changed) {
			changed = false;
			for (int i = 0; i < route.size(); i++) {
				for (int k = i + 1; k < route.size() - 1; k++) {

					// makes the swap only when we cut some distance
					if (distanceDifference(route, i, k) > 0) { // distance is positive, swap the edges to decrease routeLength.
						swap(route, i, k);
						new_distance = utilities.routeLength(route);

						if (new_distance < best_distance) {
							best_distance = new_distance;	// hold the smallest distance
							changed = true;	// set to true to continue while loop until no change is made
						}
					}
				}
			}
		}
		return route;
	}

	// Reverse points between start and end
	public static ArrayList<Point2D> swap(ArrayList<Point2D> tour, int start, int end){

		int length = (end - start + 1); // The length of inner ArrayList between start and end inclusive

		for ( int i = 0; i < length / 2; i++ ){
			Point2D temp = tour.get(i + start); //save it to a temporary point
			tour.set(i + start , tour.get((start+length) - 1 - i)); //copy last element to the start
			tour.set(start + length - 1 - i, temp); //copy temp to end
		}

		return tour;
	}

	// Calculates the distance difference of edges
	// it is used for two-opt to check if we save any distanceDifference before we make the swap.
	// if distanceDifference is positive then the new distance will be smaller if we swap them.
	public static double distanceDifference(ArrayList<Point2D> tour, int i, int j){

		// if we have the first city then the previous is the last
		if ( i - 1 < 0) {
			i = tour.size() - 1;
		}

		// if we have the last city then the next is the first
		if ( j >= tour.size() - 1) {
			j = 0;
		}

		// the first edge is between nodes d1 and d2
		double d1 = tour.get(i - 1).distance(tour.get(i));
		double d2 = tour.get(j).distance(tour.get(j + 1));

		// the second edge is between nodes d3 and d4
		double d3 = tour.get(i - 1).distance(tour.get(j));
		double d4 = tour.get(i).distance(tour.get(j + 1));

		return	((d1+d2) - (d3+d4));
	}

}
