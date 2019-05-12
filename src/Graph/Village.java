package Graph;

import Graph.Node;
import Utilities.NodeState;
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
        // the circle function takes in a diameter, but we store a radius
        _world.circle(this._x, this._y, this._r * 2);

        if( _color == NodeState.RED ){
            // The connection radius
            _world.strokeWeight(0);
            _world.stroke(0x00000000);
            _world.fill(0x5500aa00);
            _world.circle(this._x, this._y, 500);
            // The not allowed to be here radius
            _world.fill(0x55dd0000);
            _world.circle(this._x, this._y, 200);
        }
    }

}
