package unit;

/**
 * Created by martin on 12/16/16.
 */
public class Edge {
    public final int v1; // vertex 1
    public final int v2; // vertex 2
    private float weight;

    public Edge(int v1, int v2, float weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }
}