class CityGraph {

    private Map<String, Location> adjacencyList;
    private int nextId;

    public CityGraph() {
        this.adjacencyList = new HashMap<>();
        this.nextId = 1;
    }
    
    public Map<String, Location> getAdjacencyList() {
        return adjacencyList;
    }

    public boolean addLocation(Location location) {
        String name = location.name;
        if (!adjacencyList.containsKey(name)) {
            location.id = nextId++;
            adjacencyList.put(name, location);
            return true;
        }
        return false;
    }

    public boolean addRoad(String sourceName, String destinationName) {
        if (adjacencyList.containsKey(sourceName) && adjacencyList.containsKey(destinationName)) {
            Location source = adjacencyList.get(sourceName);
            Location destination = adjacencyList.get(destinationName);
            
            source.addRoad(destinationName);
            destination.addRoad(sourceName); // Bi-directional
            return true;
        }
        return false;
    }

    public boolean removeRoad(String sourceName, String destinationName) {
        if (adjacencyList.containsKey(sourceName) && adjacencyList.containsKey(destinationName)) {
            adjacencyList.get(sourceName).removeRoad(destinationName);
            adjacencyList.get(destinationName).removeRoad(sourceName); // Bi-directional
            return true;
        }
        return false;
    }

    public void displayConnections() {
        System.out.println("\n--- City Connections (Graph Edges) ---");
        if (adjacencyList.isEmpty()) {
            System.out.println("No locations added yet.");
            return;
        }

        for (Map.Entry<String, Location> entry : adjacencyList.entrySet()) {
            String locationName = entry.getKey();
            Location locationObj = entry.getValue();
            
            String roads = String.join(", ", locationObj.roads);
            System.out.println("[" + locationName + "] is connected to: [" + roads + "]");
        }
        System.out.println("--------------------------------------");
    }
}
    
