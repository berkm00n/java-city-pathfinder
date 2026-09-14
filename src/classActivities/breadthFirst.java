package classActivities;

import java.util.*;

//Contains method for finding the shortest path between cities.
public class breadthFirst {

    // Finds the shortest path between two cities using Breadth-First Search (BFS) algorithm.
    public static String findShortestPath(String startCity, String endCity) {
        // Records the start time of the method for performance tracking.
        long startTime = System.nanoTime();
        // Retrieves the adjacency matrix that representing city connections.
        int[][] adjacencyMatrix = readToMatrix.getAdjacencyMatrix();
        // Gets the list of city names corresponding to matrix indices.
        String[] cityNames = readToMatrix.getCityNames();
        // Finds the indices of start and end cities in the city names array.
        int start = Arrays.asList(cityNames).indexOf(startCity);
        int end = Arrays.asList(cityNames).indexOf(endCity);

        // Validates that both start and end cities exist in the matrix.
        if (start == -1 || end == -1) {
            return "Start or end city name does not exist.";
        }

        // Creates an array to track the previous city in the shortest path.
        int[] previous = new int[cityNames.length];
        // Initially fills with -1 to indicate no previous city.
        Arrays.fill(previous, -1);

        // Creates a queue to manage cities to be visited.
        CustomQueue<Integer> citiesToVisit = new CustomQueue<>();
        // This ensures that we explore cities in order of their distance from the start.
        citiesToVisit.enqueue(start);

        // Initializes distance array to track the number of edges from start city.
        int[] distance = new int[cityNames.length];
        // Initially sets to Integer.MAX_VALUE (unreachable large number) to represent unvisited.
        Arrays.fill(distance, Integer.MAX_VALUE);
        // Sets distance to start city is 0.
        distance[start] = 0;

        // Starts the main Breadth-First Search (BFS) loop.
        while (!citiesToVisit.isEmpty()) {
            // Removes and processes the next city from the queue.
            int currentCity = citiesToVisit.dequeue();

            // Explores all neighboring cities.
            for (int neighbor = 0; neighbor < adjacencyMatrix[currentCity].length; neighbor++) {
                // Checks if there's a valid connection between current and neighbor cities.
                // A valid connection is non-zero and non-Integer.MAX_VALUE (unreachable large number).
                if (adjacencyMatrix[currentCity][neighbor] != Integer.MAX_VALUE && adjacencyMatrix[currentCity][neighbor] != 0) {
                    // Calculates the distance to the neighbor through the current city.
                    int newDistance = distance[currentCity] + adjacencyMatrix[currentCity][neighbor];
                    // Updates the distance if a shorter path is found.
                    if (newDistance < distance[neighbor]) {
                        distance[neighbor] = newDistance;
                        // Records the current city as the previous city for path reconstruction.
                        previous[neighbor] = currentCity;
                        // Adds the neighbor to the queue for further exploration.
                        citiesToVisit.enqueue(neighbor);
                    }
                }
            }
        }

        // Creates a StringBuilder to efficiently build the result string.
        StringBuilder result = new StringBuilder();
        // Checks if no path was found between start and end cities.
        if (distance[end] == Integer.MAX_VALUE) {
            // Appends a message indicating no path exists between the start and end cities.
            result.append("No path found from ").append(startCity).append(" to ").append(endCity);
        }

        // If a path is found, reconstructs the path.
        else {
            List<String> path = new ArrayList<>();
            // Traces back from the end city to the start city using the previous array.
            // This builds the path in reverse order.
            for (int newEnd = end; newEnd != -1; newEnd = previous[newEnd]) {
                // Adds each city in the path to the list.
                path.add(cityNames[newEnd]);
            }

            // Reverses the path to get the correct order.
            Collections.reverse(path);

            // Appends the path description.
            result.append("Shortest path from ").append(startCity).append(" to ").append(endCity).append(" is:\n");
            // Joins to the cities in the path with " -> " between them.
            result.append(String.join(" -> ", path));
            // Appends the total distance.
            result.append("\nTotal distance: ").append(distance[end]);
        }

        // Calculates the execution time.
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        // Appends the execution time to the result.
        result.append("\nExecution time: ").append(duration).append(" nanoseconds");

        // Returns the final result as a string.
        return result.toString();
    }
}