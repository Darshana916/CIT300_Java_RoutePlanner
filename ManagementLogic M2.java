import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

class ManagementLogic {

    public static void handleAddLocation(CityGraph graph, LocationBST bst, String name) {
        String locationName = name.trim();
        if (locationName.isEmpty()) {
            System.out.println("❌ Location name cannot be empty.");
            return;
        }
        
        Location newLocation = new Location(locationName, 0);
        
        bst.insert(newLocation); 
        
        if (graph.addLocation(newLocation)) {
            System.out.println("✅ Location '" + locationName + "' (ID: " + newLocation.id + ") added successfully.");
        } else {
            System.out.println("❌ Error: Location '" + locationName + "' already exists.");
        }
    }

    public static void handleRemoveLocation(CityGraph graph, LocationBST bst, String name) {
        String locationName = name.trim();
        if (!graph.getAdjacencyList().containsKey(locationName)) {
            System.out.println("❌ Location '" + locationName + "' not found.");
            return;
        }

        for (Location locObj : graph.getAdjacencyList().values()) {
            locObj.removeRoad(locationName);
        }
        
        graph.getAdjacencyList().remove(locationName);
        bst.remove(locationName);
        
        System.out.println("✅ Location '" + locationName + "' and all associated roads removed.");
    }

    public static void handleAddRoad(CityGraph graph, String source, String destination) {
        String src = source.trim();
        String dest = destination.trim();

        if (src.equalsIgnoreCase(dest)) {
            System.out.println("❌ Source and destination cannot be the same.");
            return;
        }

        if (graph.addRoad(src, dest)) {
            System.out.println("✅ Road successfully added between " + src + " and " + dest + ".");
        } else {
            System.out.println("❌ Error: One or both locations do not exist or road already exists.");
        }
    }

    public static void handleRemoveRoad(CityGraph graph, String source, String destination) {
        String src = source.trim();
        String dest = destination.trim();

        if (graph.removeRoad(src, dest)) {
            System.out.println("✅ Road successfully removed between " + src + " and " + dest + ".");
        } else {
            System.out.println("❌ Error: One or both locations do not exist or the road does not exist.");
        }
    }
}
