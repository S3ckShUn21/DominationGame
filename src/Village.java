import processing.core.PApplet;

public class Village extends Node {

    public Village(PApplet world, int x, int y) {
        super(world, x, y, 30);
    }

    @Override
    public void show() {
        _world.strokeWeight(3);
        _world.fill(nodeColor(this._color));
        _world.circle(this._x, this._y, this._r);
    }

    public int get_x() {
        return _x;
    }

    public void set_x(int _x) {
        this._x = _x;
    }

    public int get_y() {
        return _y;
    }

    public void set_y(int _y) {
        this._y = _y;
    }

    public NodeState get_color() {
        return _color;
    }

}
