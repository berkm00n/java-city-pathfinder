# City Path Finder

A Java Swing application built as a team project for COMP 201 — Data Structures and Algorithms at MEF University.

Select two cities to display routes, total distances, and execution times from a recursive DFS search and a queue-based search. The project uses an adjacency matrix, a custom stack, and a custom queue.

## Running

The original city-distance CSV is missing from this repository. To run the application, provide a compatible CSV and update the file path in `Main2.java`. City names must match the buttons in the interface.

The CSV needs a header row and one row per city: the city name followed by integer distances, with rows and columns in the same city order. Use `99999` for missing connections, `0` on the diagonal, and positive weights for other connections.

With a JDK installed, run from the repository root:

```sh
mkdir -p out
javac -encoding UTF-8 -d out src/classActivities/*.java
java -cp out classActivities.Main2
```

Click **Reset** between searches.

## Algorithm note

The original code calls the queue-based search “BFS,” but it updates weighted distances and requeues cities when a shorter route is found. It is not standard unweighted BFS. DFS explores alternative paths with backtracking. Displayed execution times are individual measurements, not a benchmark.
