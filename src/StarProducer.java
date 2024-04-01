import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс предоставляет статический метод, генерирующий фигуру в форме звезды с произвольным количеством вершин.
 * Количество вершин звезды задается полем verticeNumber и должно быть не меньше 3 и не больше 90.
 */

public class StarProducer {
    /**
     *  Генерирует фигуру в форме звезды с произвольным количеством вершин.
     *  Количество вершин звезды задается полем verticeNumber и должно быть не меньше 3 и не больше 90.
     *  Центр вращения помещается в точку с координатами x + size/2 , y + size/2.
     *  Расстояние от центра до вершин равно size/2
     *  Первая вершина помещается строго над центром вращения на расстоянии size/2, остальные в точках,
     *  отстоящих от центра вращения на то же расстояние, повернутые на угол, равный 360 градусов / число вершин
     * @param x координата, задающее смещение фигуры от начала отсчета по оси x
     * @param y координата, задающее смещение фигуры от начала отсчета по оси y
     * @param size параметр, задающий размер фигуры
     * @param color цвет фигуры
     * @return возвращает фигуру в форме звезды
     */
    public static Shape getStar(int x, int y, int size, Color color) {
        Shape star = new Shape();
        // помещает центр вращения в середину фигуры
        Coordinates centRot = new Coordinates(x+size/2.0, y+size/2.0);
        star.setCentRot(centRot);

        int verticeNumber = 5;          //количество вершин звезды
        int angle = 360/verticeNumber;  //угол между вершинами звезды, 360 градусов разделить на число вершин

        Coordinates c = new Coordinates(centRot.getX(), y/2.0);  // координаты первой вершины над центром вращения
        Vertice[] vertices = new Vertice[verticeNumber];

        for (int i = 0; i < verticeNumber; i++) {
            // если вершина первая - оставляем заданные ранее координаты
            // если нет - вычисляем положение вершины в специальном методе
            c = i == 0 ?  c : getNextVertex(c, centRot, angle);
            Vertice v = new Vertice(c);
            vertices[i] = v;
        }

        // Заполняем множество граней, соединяя вершины друг с другом через одну
        Set<Edge> edges = new HashSet<>();
        for (int i = 0; i < verticeNumber; i++) {
            Edge e = new Edge(vertices[i], vertices[(i+2)%verticeNumber]);
            edges.add(e);
        }

        Arrays.asList(vertices).forEach(star::addVertice);
        edges.forEach(star::addEdge);
        star.setColor(color);
        return star;
    }

    /**
     * Расчитывает координаты следующей вершины звезды по предыдущей и углу между ними
     * с учетом положения центра вращения
     * @param previous координаты предыдущей вершины
     * @param center координаты центра вращения
     * @param angle угол между предыдцщей и текущей вершинами
     * @return возвращает координаты следующей вершины многоугольника
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