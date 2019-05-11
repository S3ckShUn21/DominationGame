package Graph;

import processing.core.PApplet;

public class Edge {

    private PApplet _world;
    private Node _start;
    private Node _end;

    public Edge(PApplet world, Node start, Node end) {
        _world = world;
        _start = start;
        _end = end;
    }


    public void show() {
        _world.strokeWeight(4);
        _world.stroke(0);
        _world.line(_start.get_x(), _start.get_y(), _end.get_x(), _end.get_y());
    }
}
