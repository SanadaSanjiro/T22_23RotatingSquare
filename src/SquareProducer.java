import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SquareProducer {
    public static Shape getSquare(int x, int y, int edgeLenght, Color color) {
        Coordinates c1, c2, c3, c4;
        c1 = new Coordinates(x, y);
        c2 = new Coordinates(x, y + edgeLenght);
        c3 = new Coordinates(x + edgeLenght, y);
        c4 = new Coordinates(x + edgeLenght, y + edgeLenght);

        Vertice v1, v2, v3, v4;
        v1 = new Vertice(c1);
        v2 = new Vertice(c2);
        v3 = new Vertice(c3);
        v4 = new Vertice(c4);
        Set<Vertice> vertices = new HashSet<>(Arrays.asList(new Vertice[]{v1,v2,v3,v4}));

        Edge e1, e2, e3, e4;
        e1 = new Edge(v1, v2);
        e2 = new Edge(v2, v4);
        e3 = new Edge(v4, v3);
        e4 = new Edge(v3, v1);
        Set<Edge> edges = new HashSet<>(Arrays.asList(new Edge[]{e1, e2, e3, e4}));

        Shape square = new Shape();
        vertices.forEach(v->square.addVertice(v));
        edges.forEach(e->square.addEdge(e));
        square.setColor(color);
        return square;
    }
}
