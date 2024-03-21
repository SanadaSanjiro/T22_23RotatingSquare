import java.util.Set;

public class Rotator {
    public static void rotate(Shape shape, double angle) {
        Coordinates centRot = shape.getCentRot();
        Set<Vertice> vertices = shape.getVertices();
        System.out.println("Координаты центра вращения: x = " + centRot.getX() + ", y = " + centRot.getY());

        vertices.forEach(v-> {
            Coordinates c = v.getCoordinates();
            // перемещаем центр вращения в начало отсчета
            double x = c.getX() - centRot.getX();
            double y = c.getY() - centRot.getY();
            System.out.println("x =" + x + "; y = " + y );
            // расчитываем новые координаты относительно 0;0
            double x1 = x * Math.cos(angle*Math.PI/180.0) - y * Math.sin(angle*Math.PI/180.0);
            double y1 = x * Math.sin(angle*Math.PI/180.0) + y * Math.cos(angle*Math.PI/180.0);
            System.out.println("x =" + x + "; y = " + y );
            // возвращаем центр координат на место
            x1 = x1 + centRot.getX();
            y1 = y1 + centRot.getY();
            System.out.println("x =" + x1 + "; y = " + y1 );
            v.setCoordinates(new Coordinates(x1,y1));
        });
    }
}
