import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Random;

public class Runner extends PApplet {

    private Random rand = new Random();
    private ArrayList<Node> nodes = new ArrayList<>();

    public static void main(String[] args) {
        PApplet.main("Runner");
    }

    public void settings() {
        size(800, 800);
    }

    public void setup() {
        for (int i = 0; i < 10; i++) {
            nodes.add(new Village(this, rand.nextInt(width), rand.nextInt(height)));
        }
    }

    public void draw() {
        nodes.forEach(Node::show);
    }

    public void mouseClicked() {
        for( Node n : nodes) {
            n.changeColor(NodeState.RED);
        }
    }

}
