import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Класс предоставляет статический метод, генерирующий фигуру в форме отрезка
 */
public class LineProducer {
    /**
     * Генерирует фигуру в форме отрезка. Отрезок помещается по координатам (x, 2*y ; x + size, 2*y)
     * Центр вращения отрезка помещается в центр отрезка по координатам (x+size/2, 2*y)
     * @param x координата, задающее смещение фигуры от начала отсчета по оси x
     * @param y координата, задающее смещение фигуры от начала отсчета по оси y
     * @param size параметр, задающий размер фигуры
     * @param color цвет фигуры
     * @return возвращает фигуру в форме отрезка
     */
    public static Shape getLine(int x, int y, int size, Color color) {
        Coordinates c1, c2;
        c1 = new Coordinates(x, 2*y);
        c2 = new Coordinates(x+ size, 2*y);
        Vertice v1, v2;
        v1 = new Vertice(c1);
        v2 = new Vertice(c2);
        Set<Vertice> vertices = new HashSet<>(Arrays.asList(v1,v2));
        Edge e1;
        e1 = new Edge(v1, v2);
        Set<Edge> edges = new HashSet<>(List.of(e1));
        Shape line = new Shape();
        vertices.forEach(line::addVertice);
        edges.forEach(line::addEdge);
        line.setColor(color);
        Coordinates centRot = new Coordinates(x+size/2.0, 2*y);
        line.setCentRot(centRot);
        return line;
    }
}
