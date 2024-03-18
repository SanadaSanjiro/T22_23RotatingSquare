import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Shape {
    private Color color;
    private Set<Vertice> vertices = new HashSet<>();
    private Set<Edge> edges = new HashSet<>();

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Set<Vertice> getVertices() {
        return vertices;
    }

    public void addVertice(Vertice vertice) {
        vertices.add(vertice);
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public void addEdge(Edge edge) {
        if (vertices.contains(edge.getVertice1()) && vertices.contains(edge.getVertice2())) {
            edges.add(edge);
        } else throw new IllegalArgumentException("Вершины данной грани не принадлежат фигуре!");
    }
}
