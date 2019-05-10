import processing.core.PApplet;

public abstract class Node {

    protected PApplet _world;
    protected int _x;
    protected int _y;
    protected int _r;
    protected NodeState _color;

    public Node(PApplet _world, int x, int y, int r) {
        this._world = _world;
        this._x = x;
        this._y = y;
        this._r = r;
        this._color = NodeState.WHITE;
    }

    public abstract void show();

    public void changeColor(NodeState color) {
        this._color = color;
    }

    public boolean clicked() {
        double distXFromCenter = this._world.mouseX - this._x;
        double distYFromCenter = this._world.mouseY - this._y;

        return Math.sqrt( Math.pow(distXFromCenter,2) + Math.pow(distYFromCenter, 2)) < this._r;
    }

//    Colors are 0xaarrggbb
    public int nodeColor(NodeState color) {
        switch (color) {
            case RED:
                return 0xffff0000;
            case BLUE:
                return 0xff0000ff;
            default:
                return 0xffffffff;
        }
    }

}
