import java.awt.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class StarProducer {
    public static Shape getStar(int x, int y, int edgeLenght, Color color) {
        Shape star = new Shape();
        Coordinates centRot = new Coordinates(x+edgeLenght/2, y+edgeLenght/2);
        star.setCentRot(centRot);
        System.out.println("Центр вращения. x = " + centRot.getX() + " y = " + centRot.getY());
        int verticeNumber = 6;          //количество вершин звезды
        int angle = 360/verticeNumber;              //угол между вершинами звезды, 360 градусов делить на чилсо вершин

        Coordinates c = new Coordinates(centRot.getX(), y/2);
        System.out.println("Первая вершина. x = " + c.getX() + " y = " + c.getY());
        Vertice[] vertices = new Vertice[verticeNumber];

        for (int i = 0; i < verticeNumber; i++) {
            c = i == 0 ?  c : getNextVertex(c, centRot, angle);
            Vertice v = new Vertice(c);
            vertices[i] = v;
        }

        Set<Edge> edges = new HashSet<>();
        for (int i = 0; i < verticeNumber; i++) {
            Edge e = new Edge(vertices[i], vertices[(i+2)%verticeNumber]);
            edges.add(e);
        }

        Arrays.asList(vertices).forEach(v->star.addVertice(v));
        edges.forEach(e->star.addEdge(e));
        star.setColor(color);
        return star;
    }

    /**
     * Расчитывает координаты следующей вершины звезды по предыдущей и углу между ними
     * @param: Координаты предыдушей вершины
     * @param angle Угол между вершинами
     * @return Возвращает координаты следующей вершины многоугольника
     */
    private static Coordinates getNextVertex(Coordinates previous, Coordinates center, int angle) {
        double x, y;
        x = previous.getX() - center.getX();
        y = previous.getY() - center.getY();
        double x1 = x * Math.cos(angle*Math.PI/180.0) - y * Math.sin(angle*Math.PI/180.0);
        double y1 = x * Math.sin(angle*Math.PI/180.0) + y * Math.cos(angle*Math.PI/180.0);
        return new Coordinates(x1 + center.getX(), y1 + center.getY());
    }
}
