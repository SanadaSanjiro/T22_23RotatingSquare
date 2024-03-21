public class Vertice {
    private Coordinates coordinates;

    public Vertice(Coordinates c) {
        coordinates = c;
        System.out.println("Создана вершина с координатами x = " + c.getX() + ", y = " + c.getY());
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
