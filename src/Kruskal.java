import dataStructures.UWGraph;
import unit.Edge;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by martin on 12/16/16.
 * Implements Kruskal's Algorithm for finding Minimum Spanning Tree
 * in an Undirected Weighted Graph.
 */
public class Kruskal {

    public static <E> List<Edge> apply(UWGraph<E> graph) {
        List<Edge> edgesMST = new ArrayList<>(); // edges in the MST tree
        List<Edge> edges = graph.getEdges();
        edges.sort(Comparator.comparing(Edge::getWeight));
        int[] labels = new int[graph.countVertices()]; // Stores labels for each subtree

        // At the beginning, there are V different subtrees
        for (int i = 0; i < graph.countVertices(); i++)
            labels[i] = i;

        for (Edge e : edges) {
            int v1 = e.v1;
            int v2 = e.v2;
            // if edge vertices are in different subtree,
            // i.e adding the edge won't create loop
            if (labels[v1] != labels[v2]) {
                edgesMST.add(e);
                mergeSubTrees(v1, v2, labels);
            }
        }
        return edgesMST;
    }

    private static void mergeSubTrees(int label1, int label2, int[] labels) {
        // by convention, merged tree labels take the min(label1, label2)
        int searchFor = Math.max(label1, label2);
        int replaceWith = Math.min(label1, label2);

        for (int i = 0; i < labels.length; i++)
            if (labels[i] == searchFor)
                labels[i] = replaceWith;
    }

}
