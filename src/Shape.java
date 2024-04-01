import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Геометрическая фигура, состоящую из вершин и соединяющих их граней
 */
public class Shape {
    private Color color; // цвет
    private final Set<Vertice> vertices = new HashSet<>(); // грани фигуры
    private final Set<Edge> edges = new HashSet<>(); // узлы фигуры
    private Coordinates centRot = new Coordinates(0,0); // координаты центра вращения
    private int angle; //угол наклона фигуры в градусах
    private int scale=100; // масштаб в процентах

    public synchronized Color getColor() {
        return color;
    }

    public synchronized void setColor(Color color) {
        this.color = color;
    }

    public synchronized Set<Vertice> getVertices() {
        return vertices;
    }

    public synchronized void addVertice(Vertice vertice) {
        vertices.add(vertice);
    }

    public synchronized Set<Edge> getEdges() {
        return edges;
    }

    public synchronized void addEdge(Edge edge) {
        if (vertices.contains(edge.getVertice1()) && vertices.contains(edge.getVertice2())) {
            edges.add(edge);
        } else throw new IllegalArgumentException("Вершины данной грани не принадлежат фигуре!");
    }

    /**
     * Задает центр вращения фигуры
     * @param c координаты центра вращения
     */
    public synchronized void setCentRot(Coordinates c) {
        centRot = c;
    }

    public synchronized Coordinates getCentRot() {
        return centRot;
    }

    /**
     * Поворачивает фигуру на a градусов относительно ее центра вращения
     * @param a градус, на который нужно повернуть фигуру
     */
    public synchronized void rotate(int a) {
        angle = angle % 360 + a;
        vertices.forEach(v-> {
            Coordinates c = v.getCoordinates();
            // перемещаем центр вращения в начало отсчета
            double x = c.getX() - centRot.getX();
            double y = c.getY() - centRot.getY();
            // расчитываем новые координаты относительно 0;0
            double x1 = x * Math.cos(a*Math.PI/180.0) - y * Math.sin(a*Math.PI/180.0);
            double y1 = x * Math.sin(a*Math.PI/180.0) + y * Math.cos(a*Math.PI/180.0);
            // возвращаем центр координат на место
            x1 = x1 + centRot.getX();
            y1 = y1 + centRot.getY();
            v.setCoordinates(new Coordinates(x1,y1));
        });
    }

    /**
     * Масштабирует фигуру относительно центра вращения
     * @param newScale масштаб, не должен быть равным 0, иначе координаты "схлопнуться"
     */
    public synchronized void resize(int newScale) {
        assert newScale!=0;
        vertices.forEach(v-> {
            Coordinates c = v.getCoordinates();
            // перемещаем центр вращения в начало отсчета
            double x = c.getX() - centRot.getX();
            double y = c.getY() - centRot.getY();
            // расчитываем новые координаты относительно 0;0
            x=x*100/scale;
            y=y*100/scale;
            double x1 = x * newScale/100;
            double y1 = y * newScale/100;
            // возвращаем центр координат на место
            x1 = x1 + centRot.getX();
            y1 = y1 + centRot.getY();
            v.setCoordinates(new Coordinates(x1,y1));
        });
        scale = newScale;
    }
}
