package Graph;

import Utilities.Util;
import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graph {

    private PApplet _world;
    private List<Node> _nodes;
    private List<Edge> _edges;
    private int _maxNodes;
    private int _numFortresses;
    private int _nodeConnectionRadius;

    // For connecting nodes
    private Random rand;

    public Graph(PApplet world, int maxNodes, int numFortresses, int nodeConnectionRadius) {
        _world = world;

        _nodes = new ArrayList<>();
        _edges = new ArrayList<>();
        _maxNodes = maxNodes;
        _numFortresses = numFortresses;
        _nodeConnectionRadius = nodeConnectionRadius;

        rand = new Random();
    }

    public void connectNodes() {
        for (Node n : _nodes) {
            List<Node> closeNodes = getNodesInRadius(n, _nodeConnectionRadius);
            // The plus 2 makes sure it DOES have connections
            int numEdgesToAdd = rand.nextInt(closeNodes.size()) + 2;
            // Now here is where stuff gets weird
            // If I wanted to not overlap any edges, or many any duplicates then I would add in a
            // lot more code to check for that.
            // However, since this is mainly just for the visual, and duplicate edges will just
            // be drawn over each other, it doesn't really matter all that much.
            // Lastly, What I think I will implement is just deleting duplicates at the end.

            // This just creates a random number of edges that intersect with the current node
            // and one of the nodes in the radius around the given node
            while (numEdgesToAdd >= 0) {
                _edges.add(new Edge(_world, n, closeNodes.get(rand.nextInt(closeNodes.size()))));
                numEdgesToAdd--;
            }
        }
    }

    public void show() {
        // Draw each edge
        _edges.forEach(Edge::show);
        // Draw each node after the edges because we want these to show on top of the edge lines
        _nodes.forEach(Node::show);
    }

    // Used to setup the graph
    public boolean addNode(Node node) {
        if (_nodes.size() < _maxNodes) {
//            System.out.println("Added a node " + _nodes.size());
            _nodes.add(node);
            return true;
        }
        return false;
    }

    // Wont be used all that often because connectNodes will take care of the edges most of the
    // time. This is mainly here in case you want to explicitly add an edge.
    public boolean addEdge(Edge edge) {
        _edges.add(edge);
        return true;
    }

    private List<Node> getNodesInRadius(Node currentNode, int radius) {
        List<Node> list = new ArrayList<>();

        // Loops through all other nodes, if they are close enough to the specific node it will
        // add them to the list
        for (Node n : _nodes) {
            if (Util.distance(currentNode.get_x(), currentNode.get_y(), n.get_x(), n.get_y()) < radius) {
                list.add(n);
            }
        }

        return list;
    }

    public List<Node> get_nodes() {
        return _nodes;
    }

    public void reset() {
        _nodes = new ArrayList<>();
        _edges = new ArrayList<>();
    }
}
