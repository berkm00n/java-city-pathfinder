package classActivities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class readToMatrix {


        String path = "C:\\Users\\Msı\\Desktop\\Turkish cities.csv"; // Replace with your file path
        public static int[][] adjacencyMatrix;
        public static String[] cityNames;
        public static void readFromFile(String path) {
        	  String line = "";
              try {
                  @SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(new FileReader(path));

                 
                  br.readLine(); // Skip the header
                  line = br.readLine();
                  String[] firstRow = line.split(",");
                  int size = firstRow.length - 1; // The first element is a city name, so subtract one
                  adjacencyMatrix = new int[size][size];
                  cityNames = new String[size];

                  // Process the first row
                  cityNames[0] = firstRow[0];
                  for (int col = 1; col < firstRow.length; col++) {
                      adjacencyMatrix[0][col - 1] = parseDistance(firstRow[col]);
                  }

                  // Read the rest of the rows
                  int row = 1;
                  while ((line = br.readLine()) != null && row < size) {
                      String[] values = line.split(",");
                      cityNames[row] = values[0]; // The first element is the city name

                      // Parse each distance value
                      for (int col = 1; col < values.length; col++) {
                          adjacencyMatrix[row][col - 1] = parseDistance(values[col]);
                      }
                      row++;
                  }
           // Print the city names
      /*      for (String cityName : cityNames) {
                System.out.print(cityName + "\t");
            }
            System.out.println();

            // Print the adjacency matrix
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (adjacencyMatrix[i][j] == Integer.MAX_VALUE) {
                        System.out.print("∞\t");
                    } else {
                        System.out.print(adjacencyMatrix[i][j] + "\t");
                    }
                }
                System.out.println();
            }
*/
        }catch(IOException e) {
        	System.out.println("Error reading the file: " + e.getMessage());
        }
      
        }
        private static int parseDistance(String distance) {
            return distance.trim().equals("99999") ? Integer.MAX_VALUE : Integer.parseInt(distance.trim());
        }
        
        public static int[][] getAdjacencyMatrix() {
            return adjacencyMatrix;
        }
        public static String[] getCityNames() {
        	return cityNames;
        }

   
}
