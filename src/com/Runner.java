package com;

import com.Graph.Fortress;
import com.Graph.Graph;
import com.Graph.Node;
import com.Graph.Village;
import com.Input.Button;
import com.Input.InputHandler;
import com.Utilities.ActionState;
import com.Utilities.ColorState;
import com.Utilities.NodeState;
import com.Utilities.Util;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PShape;

import java.util.ArrayList;
import java.util.Random;

// Main class
public class Runner extends PApplet {

    // Used to generate all random numbers
    private Random rand = new Random();

    private ActionState actionState = ActionState.NONE;
    private ColorState colorState = ColorState.NONE;

    // This holds all the buttons, and deals with them being clicked
    InputHandler inputHandler = new InputHandler();

    // Create the buttons
    private Button red, blue, guard, attack;

    private PShape integral;

    private Graph map;

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

        red = new Button(this, width / 10, height - buttonBottomHeight, 100, 100, 0xffbb0000, "") {
            @Override
            public void onClick() {
                colorState = ColorState.RED;
            }
        };

        blue = new Button(this, width - (width / 10), height - buttonBottomHeight, 100, 100, 0xff0000bb, "") {
            @Override
            public void onClick() {
                colorState = ColorState.BLUE;
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
        //Reset
        if (key == 'r') {
            do {
                map.reset();
                fillMap();
            } while( !map.isFullyConnected() );
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

}
