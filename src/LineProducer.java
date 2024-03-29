import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LineProducer {
    public static Shape getLine(int x, int y, int edgeLenght, Color color) {
        Coordinates c1, c2;
        c1 = new Coordinates(x, 2*y);
        c2 = new Coordinates(x+ edgeLenght, 2*y);
        Vertice v1, v2;
        v1 = new Vertice(c1);
        v2 = new Vertice(c2);
        Set<Vertice> vertices = new HashSet<>(Arrays.asList(new Vertice[]{v1,v2}));
        Edge e1;
        e1 = new Edge(v1, v2);
        Set<Edge> edges = new HashSet<>(Arrays.asList(new Edge[]{e1}));
        Shape line = new Shape();
        vertices.forEach(v->line.addVertice(v));
        edges.forEach(e->line.addEdge(e));
        line.setColor(color);
        Coordinates centRot = new Coordinates(x+edgeLenght/2, 2*y);
        line.setCentRot(centRot);

        return line;
    }
}
