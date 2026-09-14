package classActivities;


public class deptFirst {   
    private static int shortestDistance = Integer.MAX_VALUE; // declaring a integer max value to use in situations like no path between cities.
    private static CustomStack<String> shortestPath = new CustomStack<>();//declaring a shortest path stack.             

    // Depth-first search method to find all possible paths and update the shortest path.
    @SuppressWarnings("unchecked")
    private static void depthFirstSearch(int[][] matrix, String[] cityNames, CustomStack<Integer> stack, 
            CustomStack<String> path, boolean[] visited, int currentIndex, int endIndex, int currentDistance) {
        if (currentIndex == endIndex) {
            if (currentDistance < shortestDistance) {
                shortestDistance = currentDistance; // update the shortest distance after checking if it's longer than our currentDistance 
                shortestPath = (CustomStack<String>) path.clone(); // clone the current path into our shortest path.
            }
            return; // Exit recursion.
        }    

        if (currentDistance >= shortestDistance) { // If the current distance exceeds the shortest distance, stop exploring further.
            return;
        }

        visited[currentIndex] = true; // Mark the current city as visited.

        for (int nextIndex = 0; nextIndex < matrix.length; nextIndex++) {
            if (matrix[currentIndex][nextIndex] != Integer.MAX_VALUE && !visited[nextIndex]) { // Check if our dfs method can reach that index and also check if it's visited or not.
                stack.push(nextIndex); // Add city index to our stack.
                path.push(cityNames[nextIndex]); // Add city name to our stack.
                depthFirstSearch(matrix, cityNames, stack, path, visited, nextIndex, endIndex, currentDistance + matrix[currentIndex][nextIndex]); // Recursive call for backtracking.
                stack.pop(); // Backtracking by popping city index from our stack.
                path.pop(); // Backtracking by popping city name from our stack.
            }
        }
        visited[currentIndex] = false; // Mark them as not visited to explore alternative paths including that city.
    }        

    // Method to find the shortest path using DFS
    public static String findShortestPath(String firstCity, String endCity) {
        long startTime = System.nanoTime(); // declaring a start time variable to calculate execution time. 
        String[] cityNames = readToMatrix.getCityNames(); // Reading files via using our readToMatrix class.
        int[][] adjacencyMatrix = readToMatrix.getAdjacencyMatrix();

        int startIndex = getCityIndex(firstCity, cityNames); // Get first city index using our function getCityIndex.    
        int endIndex = getCityIndex(endCity, cityNames);

        if (startIndex == -1) {
            return "given" + firstCity + " is invalid."; // Invalid input check section with corresponding cityIndex output.
        }

        if (endIndex == -1) {
            return "given" + endCity + " is invalid"; // Invalid input check section with corresponding cityIndex output.
        }

        boolean[] visited = new boolean[cityNames.length]; // Declaring a boolean array to mark branches for backtracking.
        CustomStack<String> path = new CustomStack<>(); // Stack to store the current path during DFS.
        CustomStack<Integer> stack = new CustomStack<>(); // Stack to store city indices for traversal.
        int distanceSoFar = 0; // Variable to track the cumulative distance during traversal.

        depthFirstSearch(adjacencyMatrix, cityNames, stack, path, visited, startIndex, endIndex, distanceSoFar); // A call for depthFirstSearch method to explore all paths.
        StringBuilder result = new StringBuilder(); // Prepare a result to print the path on the terminal.

        if (!shortestPath.isEmpty()) {
            result.append("Shortest path from ").append(firstCity).append(" to ").append(endCity).append(" is:\n");
            CustomStack<String> correctOrderPath = new CustomStack<>();
            while (!shortestPath.isEmpty()) {
                correctOrderPath.push(shortestPath.pop()); // Since the stack uses First in Last out, we reverse the path stack with our custom pop function.
            }
            correctOrderPath.push(firstCity); // Push the start city to the end; now start city is our first city in the new branch.

            while (!correctOrderPath.isEmpty()) { 
                result.append(correctOrderPath.pop()).append(correctOrderPath.size() > distanceSoFar ? " -> " : ""); // Print the path from beginning to end.
            }
            result.append("\nTotal Distance:").append(shortestDistance);
        } else {
            result.append("\nThere are no paths between asked cities");
        }
        long endTime = System.nanoTime(); // Declaring an end time variable like startTime to use in our arithmetic equation.
        long timeDuration = (endTime - startTime); // Arithmetic equation to calculate timeDuration.
        result.append("\nExecution Time :").append(timeDuration).append(" nanoseconds"); // Add that to result.

        return result.toString();
    }

    private static int getCityIndex(String city, String[] cityNames) { // Small helper function for getting city indices.
        for (int i = 0; i < cityNames.length; i++) { // For loop to go through the city names array.
            if (cityNames[i].equals(city)) { // Check for an occurrence of city name given through UI to get the index of that city.
                return i; // Return index 
            }
        }
        return -1; // Return -1 to show that this is an invalid city name.
    }

    public static void clear() { //Clears the stack for the next search
        shortestDistance = Integer.MAX_VALUE;
        shortestPath.clear();
    }
    




}
