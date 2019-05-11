package Graph;

import Graph.Node;
import processing.core.PApplet;

public class Village extends Node {

    // Sets the village radius to be 30
    // Will probably change this later
    public Village(PApplet world, int x, int y) {
        super(world, x, y, 30);
    }

    // Will probably add in a picture of a small house instead of just circle
    @Override
    public void show() {
        _world.strokeWeight(3);
        _world.fill(nodeRGBColor());
        _world.circle(this._x, this._y, this._r);
    }

}
