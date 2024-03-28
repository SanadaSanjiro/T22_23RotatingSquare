import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Shape {
    private Color color; // цвет
    private Set<Vertice> vertices = new HashSet<>(); // грани фигуры
    private Set<Edge> edges = new HashSet<>(); // узлы фигуры
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

    public synchronized void setCentRot(Coordinates c) {
        centRot = c;
    }

    public synchronized Coordinates getCentRot() {
        return centRot;
    }

    public synchronized void rotate(int a) {
        angle = angle % 360 + a;
        System.out.println("Новый угол: " + angle);
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

    public synchronized void resize(int newSize) {
        int scaleChange = scale - newSize;
        scale = newSize;
        System.out.println("Новый масштаб: " + scale);
        vertices.forEach(v-> {
            Coordinates c = v.getCoordinates();
            // перемещаем центр вращения в начало отсчета
            double x = c.getX() - centRot.getX();
            double y = c.getY() - centRot.getY();
            // расчитываем новые координаты относительно 0;0
            double x1 = x * (100 - scaleChange)/100;
            double y1 = y * (100 - scaleChange)/100;
            System.out.println(v +  " :x1 = " + x1 + ", y1 = " + y1);
            // возвращаем центр координат на место
            x1 = x1 + centRot.getX();
            y1 = y1 + centRot.getY();
            System.out.println("x1 = " + x1 + ", y1 = " + y1);
            v.setCoordinates(new Coordinates(x1,y1));
        });
    }
}
