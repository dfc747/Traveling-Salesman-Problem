import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Alexander Kamsizoglou on 11-Nov-16.
 */
public final class utilities {
	private utilities() {
	}

		/* Load in a TSPLib instance. This example assumes that the Edge weight type is EUC_2D.
		// It will work for examples such as rl5915.tsp. Other files such as
		// fri26.tsp .To use a different format, you will have to modify the this code.
		//
		*/
	public static ArrayList<Point2D> loadTSPLib(String fName) {
		ArrayList<Point2D> result = new ArrayList<Point2D>();
		BufferedReader br = null;
		try {
			String currentLine;
			int dimension = 0;    //Hold the dimension of the problem
			boolean readingNodes = false;
			br = new BufferedReader(new FileReader(fName));
			while ((currentLine = br.readLine()) != null) {
				// Read the file until the end;
				if (currentLine.contains("EOF")) {
					// EOF should be the last line
					readingNodes = false;
					// Finished reading nodes
					if (result.size() != dimension) {
						// Check to see if the expected number of cities have been loaded
						System.out.println("Error loading cities");
						System.exit(-1);
					}
				}
				if (readingNodes) {
					// If reading in the node data
					String[] tokens = currentLine.trim().split("\\s+");
					// Split the line by spaces.
					// tokens[0] is the city id and not needed in this example
					float x = Float.parseFloat(tokens[1].trim());
					float y = Float.parseFloat(tokens[2].trim());
					// Use Java's built in Point2D type to hold a city
					Point2D city = new Point2D.Float(x, y);
					// Add this city into the arraylist
					result.add(city);
				}
				if (currentLine.contains("DIMENSION")) {
					// Note the expected problem dimension (number of cities)
					String[] tokens = currentLine.split(":");
					dimension = Integer.parseInt(tokens[1].trim());
				}
				if (currentLine.contains("NODE_COORD_SECTION")) {
					// Node data follows this line
					readingNodes = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	//Calculate the length of a TSP route held in an ArrayList as a set of Points
	public static double routeLength(ArrayList<Point2D> cities) {
		double result = 0;    // Holds the route length
		Point2D prev = cities.get(cities.size() - 1);
		// Set the previous city to the last city in the ArrayList as we need to measure the length of the entire loop
		for (Point2D city : cities) {
			// Go through each city in turn
			result += city.distance(prev);
			// get distanceDifference from the previous city
			prev = city;
			// current city will be the previous city next time
		}
		return result;
	}

	//	Checks if two ArrayLists of Point2D are the same.
	//	First it checks that the source ArrayList doesn't contain duplicate points
	//	because this would be an invalid TSP file. Then it checks that the target
	//  ArrayList doesn't contain duplicates. Then each element of the source
	//  ArrayList is checked that exists in the target ArrayList.
	public static boolean areSame(ArrayList<Point2D> source, ArrayList<Point2D> target) {

		if (source.size() != target.size()){
			return false;
		}

		Set<Point2D> setSource = new HashSet<Point2D>(source);
		if (setSource.size() != source.size()) {
			System.out.println("Error! The source file contains a duplicate...");
			return false;
		}

		Set<Point2D> setTarget = new HashSet<Point2D>(target);
		if (setTarget.size() != target.size()) {
			System.out.println("Error! The target file contains a duplicate");
			return false;
		}

		for (Point2D point : source){
			if (!target.contains(point)) {
				return false;
			}
		}

		return true;
	}
}
