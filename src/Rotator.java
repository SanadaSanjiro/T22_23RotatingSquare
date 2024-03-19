import java.util.Set;

public class Rotator {
    public static void rotate(Shape shape, double angle) {
        Coordinates centRot = shape.getCentRot();
        Set<Vertice> vertices = shape.getVertices();

        vertices.forEach(v-> {
            Coordinates c = v.getCoordinates();
            // перемещаем центр вращения в начало отсчета
            double x = c.getX() - centRot.getX();
            double y = c.getY() - centRot.getY();
            // расчитываем новые координаты относительно 0;0
            x = x * Math.cos(angle*Math.PI/180) - y * Math.sin(angle*Math.PI/180);
            y = x * Math.sin(angle*Math.PI/180) + y * Math.cos(angle*Math.PI/180);
            // возвращаем центр координат на место
            x = x + centRot.getX();
            y = y + centRot.getY();
            System.out.println("x =" + x + "; y = " + y );
            v.setCoordinates(new Coordinates(x,y));
        });
    }
}
