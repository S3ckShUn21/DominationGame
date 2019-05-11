import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

// Main class
public class Runner extends PApplet {

    // Used to generate all random numbers
    private Random rand = new Random();

    // Background of all the nodes which will be on the screen
    // All three types
    private ArrayList<Node> nodes = new ArrayList<>();

    private ArrayList<Edge> edges = new ArrayList<>();

    private int overlapRadius = 100;

    // Runs the processing main method
    public static void main(String[] args) {
        PApplet.main("Runner");
    }

    // Run before setup
    // Creates window
    public void settings() {
        fullScreen();
    }

    // Run once before draw
    public void setup() {
        // Adds ten nodes to screen
        int numToAdd = 60;
        while (numToAdd >= 0) {
            int tempX = rand.nextInt(width);
            int tempY = rand.nextInt(height);

            // Checks to see if this node i
            boolean add = true;
            for (Node n : nodes) {
                if (Util.distance(tempX, tempY, n.get_x(), n.get_y()) < overlapRadius) {
                    add = false;
                }
            }
            if (add) {
                nodes.add(new Village(this, tempX, tempY));
                numToAdd--;
            }
        }
    }

    // Run all the time
    public void draw() {
        // Draw each edge
        edges.forEach(Edge::show);
        // Draw each node after the edges because we want these to show on top of the edge lines
        nodes.forEach(Node::show);
    }

    public void mouseClicked() {
        // Runs through each of the Nodes
        for (Node n : nodes) {
            // If THIS node was clicked then change ITS color specifically
            if (n.clicked()) {
                n.changeColor(NodeState.RED);
            }
        }
    }

}
