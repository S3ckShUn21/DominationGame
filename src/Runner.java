import Graph.Edge;
import Graph.Graph;
import Graph.Node;
import Graph.Village;
import Input.InputHandler;
import Utilities.NodeState;
import Utilities.Util;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Random;

// Main class
public class Runner extends PApplet {

    // Used to generate all random numbers
    private Random rand = new Random();

    // This holds all the buttons, and deals with them being clicked
    InputHandler inputHandler = new InputHandler();

    // Background of all the nodes which will be on the screen
    // All three types
    private ArrayList<Node> nodes = new ArrayList<>();

    private Graph map;

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

        map = new Graph(this, 60, 20, 250);

        // Adds sixty nodes to screen
        fillMap();
    }

    // Run all the time
    public void draw() {
        background(170);
        map.show();
    }

    public void mouseClicked() {
        inputHandler.run();
        // Runs through each of the Nodes
        for (Node n : map.get_nodes()) {
            // If THIS node was clicked then change ITS color specifically
            if (n.clicked()) {
                n.changeColor(NodeState.RED);
            }
        }
    }

    public void keyPressed() {
        if( key == 'r' ) {
            map.reset();

            // Adds sixty nodes to screen
            fillMap();

        }
    }

    private void fillMap() {
        int numToAdd = 60;
        while (numToAdd >= 0) {
            int tempX = rand.nextInt(width);
            int tempY = rand.nextInt(height);

            // Checks to see if this node i
            boolean add = true;
            for (Node n : map.get_nodes()) {
                if (Util.distance(tempX, tempY, n.get_x(), n.get_y()) < overlapRadius) {
                    add = false;
                }
            }
            if (add) {
                map.addNode(new Village(this, tempX, tempY));
                numToAdd--;
            }
        }

//        System.out.println(map.get_nodes().size());

        map.connectNodes();
    }

}
