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

    // MEMBER 4: MAIN MENU AND INTEGRATION (Requirement 6)
    public static void main(String[] args) {
                


}