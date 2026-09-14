package classActivities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main2 {

    static String[] cities = {"Istanbul", "Ankara", "Izmir", "Bursa", "Adana", "Gaziantep", "Konya", "Diyarbakir",
        "Antalya", "Mersin", "Kayseri", "Urfa", "Malatya", "Samsun", "Denizli", "Batman", "Trabzon"
    };
    private static String firstCity = "";
    private static String secondCity = "";

    public static void main(String[] args) {

        // Load adjacency matrix and city names from CSV
        readToMatrix.readFromFile("C:\\Users\\Msı\\Desktop\\Turkish cities.csv");

        // Create the frame
        JFrame frame = new JFrame("City Path Finder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(new FlowLayout());

        // Create UI components
        
        JLabel selectedCitiesLabel = new JLabel("Selected Cities: None, None");
        JButton searchButton = new JButton("Find Shortest Path");
        searchButton.setEnabled(false);
        JButton resetButton = new JButton("Reset");
        JTextArea resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout());
        // City buttons
        JButton[] cityButtons = new JButton[cities.length];
        for (int i = 0; i < cities.length; i++) {
            cityButtons[i] = new JButton(cities[i]);
            final String city = cities[i];
            cityButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (firstCity.isEmpty()) {
                        firstCity = city;
                        selectedCitiesLabel.setText("Selected Cities: " + firstCity + ", None");
                    } else if (secondCity.isEmpty()) {
                        secondCity = city;
                        selectedCitiesLabel.setText("Selected Cities: " + firstCity + ", " + secondCity);
                    }
                    if (!firstCity.isEmpty() && !secondCity.isEmpty()) {
                        searchButton.setEnabled(true);
                    }
                }
            });
            frame.add(cityButtons[i]);
        }

        // Add UI components
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout()); // Layout to stack components horizontally
        buttonPanel.add(selectedCitiesLabel);  // Add label
        buttonPanel.add(searchButton);         // Add Find Shortest Path button
        buttonPanel.add(resetButton);          // Add Reset button
        textPanel.add(new JScrollPane(resultArea));
        // Add the buttonPanel to the frame
      
        frame.add(buttonPanel);
        frame.add(textPanel);
        
       
        

        // Action for search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (firstCity.isEmpty() || secondCity.isEmpty()) {
                    resultArea.setText("Please select both cities.");
                    return;
                }

                // Clear the previous results
                resultArea.setText("");

                // Call DFS and BFS to find the shortest path
                resultArea.append("DFS:\n");
                resultArea.append(deptFirst.findShortestPath(firstCity, secondCity));

                resultArea.append("\n\n");  // Add space between DFS and BFS

                resultArea.append("BFS:\n");
                resultArea.append(breadthFirst.findShortestPath(firstCity, secondCity));
                searchButton.setEnabled(false);
            }
        });

        // Action for reset button
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear selected cities
                firstCity = "";
                secondCity = "";
                selectedCitiesLabel.setText("Selected Cities: None, None");

                // Clear result area
                resultArea.setText("");

                // Reset DFS and BFS stacks/queues
                deptFirst.clear();  // Assuming deptFirst has a method to reset its state
                searchButton.setEnabled(false); 
            }
        });

        // Show the frame
        frame.setVisible(true);
    }
}