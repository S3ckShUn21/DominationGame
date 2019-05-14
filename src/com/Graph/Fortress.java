package com.Graph;

import com.Runner;
import processing.core.PApplet;
import processing.core.PConstants;

public class Fortress extends Node {

    public Fortress(PApplet _world, int x, int y) {
        super(_world, x, y, 40);
    }

    @Override
    public void show() {
        _world.strokeWeight(3);
        _world.fill(nodeRGBColor());
        _world.circle(this._x, this._y, this._r * 2);
        _world.fill(0);
        _world.textFont(Runner.MEDIEVAL_FONT);
        _world.textSize(this._r);
        _world.textAlign(PConstants.CENTER, PConstants.CENTER);
        _world.text("F", this._x, this._y - (this._r / 10));
    }
}
