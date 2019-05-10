import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Random;

// Main class
public class Runner extends PApplet {

    // Used to generate all random numbers
    private Random rand = new Random();

    // Background of all the nodes which will be on the screen
    // All three types
    private ArrayList<Node> nodes = new ArrayList<>();

    // Runs the processing main method
    public static void main(String[] args) {
        PApplet.main("Runner");
    }

    // Run before setup
    // Creates window
    public void settings() {
        size(800, 800);
    }

    // Run once before draw
    public void setup() {
        // Adds ten nodes to screen
        for (int i = 0; i < 10; i++) {
            nodes.add(new Village(this, rand.nextInt(width), rand.nextInt(height)));
        }
    }

    // Run all the time
    public void draw() {
        // Draw each node
        nodes.forEach(Node::show);
        // Draw each edge
    }

    public void mouseClicked() {
        // Runs through each of the Nodes
        for (Node n : nodes) {
            // If THIS node was clicked then change ITS color specifically
            if( n.clicked() ) {
                n.changeColor(NodeState.RED);
            }
        }
    }

}
