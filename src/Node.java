import processing.core.PApplet;

import java.util.Objects;

public abstract class Node {

    // Allows to use processing functions
    protected PApplet _world;
    // Position on the screen
    protected int _x;
    protected int _y;
    // Radius of the node
    protected int _r;
    // Who owns the node at the current time
    protected NodeState _color;

    public Node(PApplet _world, int x, int y, int r) {
        this._world = _world;
        this._x = x;
        this._y = y;
        this._r = r;
        this._color = NodeState.WHITE;
    }

    // Will be implemented by Villages, Fortresses, Castles individually
    // Each one should have their own picture
    // Also they will deal with how to show their "guarded" state
    public abstract void show();

    public void changeColor(NodeState color) {
        this._color = color;
    }

    // Determines if the mouse was clicked on them specifically
    public boolean clicked() {
        double distXFromCenter = this._world.mouseX - this._x;
        double distYFromCenter = this._world.mouseY - this._y;

        return Math.sqrt(Math.pow(distXFromCenter, 2) + Math.pow(distYFromCenter, 2)) < this._r;
    }

    // Gives a basic output color based on the "owner" of the node
    public int nodeRGBColor() {
        //    Colors are 0xaarrggbb
        switch (this._color) {
            case RED:
                return 0xffff0000;
            case BLUE:
                return 0xff0000ff;
            default:
                return 0xffffffff;
        }
    }

    public boolean withinRadius(Node n, int radius) {
        return Util.distance(this._x, this._y, n.get_x(), n.get_y()) < radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return _x == node._x &&
                _y == node._y &&
                _r == node._r &&
                Objects.equals(_world, node._world) &&
                _color == node._color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_world, _x, _y, _r, _color);
    }

    public int get_x() {
        return _x;
    }

    public int get_y() {
        return _y;
    }

    public int get_r() {
        return _r;
    }
}
