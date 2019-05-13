package Input;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

public abstract class Button {

    private static PFont buttonFont;

    private PApplet _world;

    private int _x;
    private int _y;
    private int _w;
    private int _h;
    private int _color;
    private String _text;

    public Button(PApplet _world, int _x, int _y, int _w, int _h, int _color, String _text) {
        this._world = _world;
        this._x = _x;
        this._y = _y;
        this._w = _w;
        this._h = _h;
        this._color = _color;
        this._text = _text;

        buttonFont = _world.createFont("res/fonts/OldLondon.ttf", 20);
    }

    public void show() {
        _world.fill(_color);
        // Makes the (x,y) the center of the rectangle instead of the top left
        _world.rect(_x - (_w / 2), _y - (_h / 2), _w, _h, 10);

        // Write the Text of the button to the button

        _world.textAlign(PConstants.CENTER, PConstants.CENTER);
        _world.fill(0);
        _world.textFont(buttonFont);
        _world.textSize( _w / 3);
        _world.text(_text, _x, _y - (_w / 25));
    }

    public boolean clicked() {
        return _world.mouseX > _x && _world.mouseX < _x + _w && _world.mouseY > _y && _world.mouseY < _y + _h;
    }

    public void setText(String newText) {
        _text = newText;
    }

    public abstract void onClick();
}
