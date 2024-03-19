import java.awt.*;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Plotter {
    public static void plot(Graphics g, Shape shape) {
        g.setColor(shape.getColor());
        Set<Edge> edges = shape.getEdges();
        edges.forEach(edge -> drawLine(g, edge));
    }

    private static void drawLine(Graphics g,Edge edge) {
        Vertice v1 = edge.getVertice1();
        Vertice v2 = edge.getVertice2();
        int x1 = v1.getCoordinates().getIntX();
        int x2 = v2.getCoordinates().getIntX();
        int y1 = v1.getCoordinates().getIntY();
        int y2 = v2.getCoordinates().getIntY();
        g.drawLine(x1, y1, x2, y2);
    }
}
