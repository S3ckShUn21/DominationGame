package com;

import com.Graph.Fortress;
import com.Graph.Graph;
import com.Graph.Node;
import com.Graph.Village;
import com.Input.Button;
import com.Input.InputHandler;
import com.Utilities.ActionState;
import com.Utilities.NodeState;
import com.Utilities.Util;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PShape;

import java.util.Random;

// Main class
public class Runner extends PApplet {

    // Used to generate all random numbers
    private Random rand = new Random();

    private ActionState actionState = ActionState.NONE;
    private NodeState colorState = NodeState.WHITE;

    // This holds all the buttons, and deals with them being clicked
    InputHandler inputHandler = new InputHandler();

    // Create the buttons
    private Button red, blue, guard, attack;

    private PShape integral;

    private Graph map;

    private int squareButtonWidth = 120;

    public static int BLUE_COLOR = 0xff2929bb;

    public static int RED_COLOR = 0xffbb1111;

    public static int DARK_RED_COLOR = 0xff441111;

    public static int DARK_BLUE_COLOR = 0xff111144;

    private int winPercent = 65;

    private int borderSize = 50;

    private int bottomBorder = 225;

    private int overlapRadius = 100;

    private int buttonBottomHeight = 75;

    public static PFont MEDIEVAL_FONT;

    // Runs the processing main method
    public static void main(String[] args) {
        PApplet.main("com.Runner");
    }

    // Run before setup
    // Creates window
    public void settings() {
        fullScreen();
    }

    // Run once before draw
    public void setup() {

        MEDIEVAL_FONT = createFont("res/fonts/OldLondon.ttf", 20);

        map = new Graph(this, 60, 20, 250);

//        integral = loadShape("res/killme.svg");

        // Adds sixty nodes to screen
        fillMap();

        red = new Button(this, width / 10, height - buttonBottomHeight, squareButtonWidth,
                squareButtonWidth, RED_COLOR, "") {
            @Override
            public void onClick() {
                colorState = NodeState.RED;
                System.out.println("Red Was Clicked");
            }
        };

        blue = new Button(this, width - (width / 10), height - buttonBottomHeight, squareButtonWidth, squareButtonWidth,
                BLUE_COLOR, "") {
            @Override
            public void onClick() {
                colorState = NodeState.BLUE;
                System.out.println("Blue Was Clicked");
            }
        };

        guard = new Button(this, width - (width / 3), height - buttonBottomHeight, 200, 100, 0xff777777,
                "Guard") {
            @Override
            public void onClick() {
                actionState = ActionState.GUARD;
            }
        };

        attack = new Button(this, width / 3, height - buttonBottomHeight, 200, 100, 0xff777777, "Attack") {
            @Override
            public void onClick() {
                actionState = ActionState.ATTACK;
            }
        };

        inputHandler.addButton(red);
        inputHandler.addButton(blue);
        inputHandler.addButton(guard);
        inputHandler.addButton(attack);
    }

    // Run all the time
    public void draw() {
        background(170);
        map.show();
//        shape(integral, 100, 100, 50, 50);
        // Draw GUI over top of everything
        inputHandler.show();
        measurePlayerNodeCount();
        checkDomination();
    }

    public void mouseClicked() {
        // Actually start check things
        inputHandler.run();
        // Runs through each of the Nodes
        for (Node n : map.get_nodes()) {
            // If THIS node was clicked then change ITS color specifically
            if (n.clicked()) {
                n.changeColor(colorState);
            }
        }
    }

    public void keyPressed() {
        //Reset
        if (key == 'r') {
            do {
                map.reset();
                fillMap();
            } while (!map.isFullyConnected());
        }
        // Check if connected graph
//        else if ( key == 'c') {
//            if( map.isFullyConnected() ) {
//                guard.setText("Yes");
//            } else {
//                guard.setText("No");
//            }
//        }
    }

    private void fillMap() {

        // Create 40 villages
        int numToAdd = 40;
        while (numToAdd >= 0) {
            int tempX = rand.nextInt(width - (borderSize * 2)) + borderSize;
            int tempY = rand.nextInt(height - bottomBorder) + borderSize;

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

        // Create 20 Fortresses
        numToAdd = 20;
        while (numToAdd >= 0) {
            int tempX = rand.nextInt(width - (borderSize * 2)) + borderSize;
            int tempY = rand.nextInt(height - bottomBorder) + borderSize;

            // Checks to see if this node i
            boolean add = true;
            for (Node n : map.get_nodes()) {
                if (Util.distance(tempX, tempY, n.get_x(), n.get_y()) < overlapRadius) {
                    add = false;
                }
            }
            if (add) {
                map.addNode(new Fortress(this, tempX, tempY));
                numToAdd--;
            }
        }

//        System.out.println(map.get_nodes().size());

        map.connectNodes();
    }

    private void measurePlayerNodeCount() {
        // Get the total
        double numNodes = map.get_nodes().size();
        // Get num of each color
        double numRed = 0, numBlue = 0;
        for (Node n : map.get_nodes()) {
            if (n.getColor() == NodeState.BLUE) {
                numBlue++;
            } else if (n.getColor() == NodeState.RED) {
                numRed++;
            }
        }
        // Format the percentage of the colors
        String percentRed = String.format("%.1f", numRed / numNodes * 100) + "%";
        String percentBlue = String.format("%.1f", numBlue / numNodes * 100) + "%";
        textAlign(CENTER, CENTER);
        textSize(50);
        //Red Color
        fill(DARK_RED_COLOR);
        text(percentRed, red.get_x(), red.get_y());
        //Blue Color
        fill(DARK_BLUE_COLOR);
        text(percentBlue, blue.get_x(), blue.get_y());
    }

    private void checkDomination() {
        // Get the total
        double numNodes = map.get_nodes().size();
        // Get num of each color
        double numRed = 0, numBlue = 0;
        for (Node n : map.get_nodes()) {
            if (n.getColor() == NodeState.BLUE) {
                numBlue++;
            } else if (n.getColor() == NodeState.RED) {
                numRed++;
            }
        }
        textAlign(CENTER, CENTER);
        textSize(30);
        //Check to see if either has passed the win percent amount
        if (numRed / numNodes * 100 >= winPercent) {
            //Red Color
            fill(DARK_RED_COLOR);
            text(String.format("Red has dominated %d%% of the field", winPercent), width / 2,
                    height - buttonBottomHeight);
        } else if (numBlue / numNodes * 100 >= winPercent) {
            //Blue Color
            fill(DARK_BLUE_COLOR);
            text(String.format("Blue has dominated %d%% of the field", winPercent), width / 2,
                    height - buttonBottomHeight);
        }

    }
}
