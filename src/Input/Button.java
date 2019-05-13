package Input;

import processing.core.PApplet;

public abstract class Button {

    private PApplet _world;

    private int _x;
    private int _y;
    private int _w;
    private int _h;
    private int _color;

    public Button(PApplet _world, int _x, int _y, int _w, int _h, int _color) {
        this._world = _world;
        this._x = _x;
        this._y = _y;
        this._w = _w;
        this._h = _h;
        this._color = _color;
    }

    public void show() {
        _world.fill(_color);
        _world.rect(_x, _y, _w, _h, 10);
    }

    public boolean clicked() {
        return _world.mouseX > _x && _world.mouseX < _x + _w && _world.mouseY > _y && _world.mouseY < _y + _h;
    }

    public abstract void onClick();
}
