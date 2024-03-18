public class Coordinates {
    private final double x;
    private final double y;

    public Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(int x, int y) {
        this.x = Double.valueOf(x);
        this.y = Double.valueOf(y);
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