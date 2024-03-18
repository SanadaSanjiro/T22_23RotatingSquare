public class Edge {
    private final  Vertice v1, v2;

    public Edge(Vertice v1, Vertice v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Vertice getVertice1() {
        return v1;
    }

    public Vertice getVertice2() {
        return v2;
    }
}
