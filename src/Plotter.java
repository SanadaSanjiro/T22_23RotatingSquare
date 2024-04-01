import java.awt.*;
import java.util.Set;

/**
 * Предоставляет метод для отрисовки фигуры
 */
public class Plotter {
    /**
     * Отрисовывает форму.
     * @param g Объект Graphics
     * @param shape Фигура, которую требуется изобразить
     */
    public static void plot(Graphics g, Shape shape) {
        g.setColor(shape.getColor());
        shape.getEdges().forEach(edge -> drawLine(g, edge));
    }

    /**
     * Отрисовывает линию
     * @param g Объект Graphics
     * @param edge линия (грань фигуры) которую требуется отрисовать
     */
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
