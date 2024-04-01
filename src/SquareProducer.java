import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс предоставляет статический метод, генерирующий фигуру в форме квадрата
 */
public class SquareProducer {
    /**
     * Генерирует фигуру в форме квадрата с размером стороны = size. Центр вращения помещается в центр квадрата
     * с координатами (x+size/2, y+size/2), первая вершина - в точку (x, y), длина стороны = size
     * @param x координата, задающее смещение фигуры от начала отсчета по оси x
     * @param y координата, задающее смещение фигуры от начала отсчета по оси y
     * @param size задает длину стороны квадрата
     * @param color цвет фигуры
     * @return возвращает фигуру в форме квадрата со сторонами, ориентированными вертикально и горизонтально
     */
    public static Shape getSquare(int x, int y, int size, Color color) {
        Coordinates c1, c2, c3, c4;
        c1 = new Coordinates(x, y);
        c2 = new Coordinates(x, y + size);
        c3 = new Coordinates(x + size, y + size);
        c4 = new Coordinates(x + size, y);

        Vertice v1, v2, v3, v4;
        v1 = new Vertice(c1);
        v2 = new Vertice(c2);
        v3 = new Vertice(c3);
        v4 = new Vertice(c4);
        Set<Vertice> vertices = new HashSet<>(Arrays.asList(v1,v2,v3,v4));

        Edge e1, e2, e3, e4;
        e1 = new Edge(v1, v2);
        e2 = new Edge(v2, v3);
        e3 = new Edge(v3, v4);
        e4 = new Edge(v4, v1);
        Set<Edge> edges = new HashSet<>(Arrays.asList(e1, e2, e3, e4));

        Shape square = new Shape();
        vertices.forEach(square::addVertice);
        edges.forEach(square::addEdge);
        square.setColor(color);

        Coordinates centRot = new Coordinates(x+size/2.0, y+size/2.0);
        square.setCentRot(centRot);
        return square;
    }
}
