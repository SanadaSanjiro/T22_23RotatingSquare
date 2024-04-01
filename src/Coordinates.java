/**
 * Координаты на экране. Сохраняются в double, но могут возвращаться как int, соответственно
 * их значение должно лежать в пределах Integer.MAX_VALUE, Integer.MIN_VALUE
 */
public class Coordinates {
    private final double x;
    private final double y;

    public Coordinates(double x, double y) {
        assert x <= Integer.MAX_VALUE && x >= Integer.MIN_VALUE;
        assert y <= Integer.MAX_VALUE && y >= Integer.MIN_VALUE;
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getIntX() {
        return Double.valueOf(x).intValue();
    }

    public int getIntY() {
        return Double.valueOf(y).intValue();
    }
}
