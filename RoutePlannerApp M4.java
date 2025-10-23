import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class RoutePlannerApp {

    // TRAVERSAL LOGIC (BFS using Queue - Requirement 4)
    public static void bfsTraversal(CityGraph graph, String startName) {
        String startLocationName = startName.trim();
        if (!graph.getAdjacencyList().containsKey(startLocationName)) {
            System.out.println("Start location not found.");
            return;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        queue.add(startLocationName);
        visited.add(startLocationName);
        
        System.out.println("\n--- BFS Traversal (Shortest Route Check) from " + startLocationName + " ---");

        while (!queue.isEmpty()) {
            String currentLocationName = queue.poll();
            System.out.print(currentLocationName + " -> ");
            
            Location currentLocationObj = graph.getAdjacencyList().get(currentLocationName);

            for (String neighborName : currentLocationObj.roads) {
                if (!visited.contains(neighborName)) {
                    visited.add(neighborName);
                    queue.add(neighborName);
                }
            }
        }
        System.out.println("END\nTraversal complete.");
    }

    // MAIN MENU AND INTEGRATION (Requirement 6)
    public static void main(String[] args) {
        CityGraph cityGraph = new CityGraph();
        LocationBST locationBST = new LocationBST();
        Scanner scanner = new Scanner(System.in);

        // Pre-populate data
        ManagementLogic.handleAddLocation(cityGraph, locationBST, "Colombo");
        ManagementLogic.handleAddLocation(cityGraph, locationBST, "Kandy");
        ManagementLogic.handleAddLocation(cityGraph, locationBST, "Galle");
        ManagementLogic.handleAddLocation(cityGraph, locationBST, "Jaffna");
        ManagementLogic.handleAddRoad(cityGraph, "Colombo", "Kandy");
        ManagementLogic.handleAddRoad(cityGraph, "Colombo", "Galle");
        ManagementLogic.handleAddRoad(cityGraph, "Kandy", "Jaffna");

        while (true) {
            System.out.println("\n" + "=".repeat(45));
            System.out.println("       --- Smart City Route Planner ---");
            System.out.println("=".repeat(45));
            System.out.println("1. Add a new location");
            System.out.println("2. Remove a location");
            System.out.println("3. Add a road between locations");
            System.out.println("4. Remove a road");
            System.out.println("5. Display all connections (Graph)");
            System.out.println("6. Display all locations (Tree)");
            System.out.println("7. Perform Route Traversal (BFS)");
            System.out.println("8. Exit");
            System.out.println("=".repeat(45));
            
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine();
            int choice;

            // Input Validation (Requirement 6)
            try {
                choice = Integer.parseInt(input);
                if (choice < 1 || choice > 8) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a number between 1 and 8.");
                continue;
            }
            
            switch (choice) {
                case 1:
                    System.out.print("Enter location name to add: ");
                    ManagementLogic.handleAddLocation(cityGraph, locationBST, scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Enter location name to remove: ");
                    ManagementLogic.handleRemoveLocation(cityGraph, locationBST, scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Enter Source location name: ");
                    String src = scanner.nextLine();
                    System.out.print("Enter Destination location name: ");
                    String dest = scanner.nextLine();
                    ManagementLogic.handleAddRoad(cityGraph, src, dest);
                    break;
                case 4:
                    System.out.print("Enter Source location name: ");
                    src = scanner.nextLine();
                    System.out.print("Enter Destination location name: ");
                    dest = scanner.nextLine();
                    ManagementLogic.handleRemoveRoad(cityGraph, src, dest);
                    break;
                case 5:
                    cityGraph.displayConnections();
                    break;
                case 6:
                    locationBST.displayAllLocations();
                    break;
                case 7:
                    System.out.print("Enter starting location for BFS traversal: ");
                    bfsTraversal(cityGraph, scanner.nextLine());
                    break;
                case 8:
                    System.out.println("Exiting Smart City Route Planner. Goodbye!");
                    scanner.close();
                    return;
            }
        }
    }
}
