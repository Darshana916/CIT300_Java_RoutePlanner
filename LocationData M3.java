import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


class Location {

    public String name;
    public int id;
    public List<String> roads; // Connected locations (Adjacency List)
    
    // BST/AVL attributes
    public Location left;
    public Location right;
    public int height;

    public Location(String name, int id) {
        this.name = name;
        this.id = id;
        this.roads = new ArrayList<>();
        this.height = 1;
        this.left = null;
        this.right = null;
    }

    public void addRoad(String destinationName) {
        if (!roads.contains(destinationName)) {
            roads.add(destinationName);
        }
    }

    public void removeRoad(String destinationName) {
        roads.remove(destinationName);
    }
}



class LocationBST {
    public Location root;

    public LocationBST() {
        this.root = null;
    }
    
    private int getHeight(Location node) {
        return (node == null) ? 0 : node.height;
    }

    public int getBalance(Location node) {
        return (node == null) ? 0 : (getHeight(node.left) - getHeight(node.right));
    }

    public Location insert(Location root, Location newLocation) {
        if (root == null) {
            return newLocation;
        }

        if (newLocation.name.compareToIgnoreCase(root.name) < 0) {
            root.left = insert(root.left, newLocation);
        } else if (newLocation.name.compareToIgnoreCase(root.name) > 0) {
            root.right = insert(root.right, newLocation);
        } else {
            return root; // Duplicate names not allowed
        }

        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        return root;
    }
    
    public void insert(Location newLocation) {
        this.root = insert(this.root, newLocation);
    }
    
    // Inorder Traversal for Requirement 6
    public void displayAllLocations() {
        System.out.println("\n--- All Locations (Sorted via Tree Structure) ---");
        inorderTraversal(this.root);
        System.out.println("--------------------------------------------------");
    }

    private void inorderTraversal(Location node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.println("ID:" + node.id + " | Name: " + node.name + " | Balance Factor: " + getBalance(node));
            inorderTraversal(node.right);
        }
    }
    
    public void remove(String name) {
        // Simple remove: For full functionality, implement full BST deletion logic.
    }
}
