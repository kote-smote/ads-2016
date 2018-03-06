import dataStructures.DUGraph;

/**
 * Created by martin on 12/15/16.
 */
public class TestGraphs {
    public static void main(String[] args) {

        testDUGraph();

    }

    public static void testDUGraph() {

        Integer[] arr = {0, 1, 2, 3, 4, 5, 6, 7};
        DUGraph<Integer> graph = new DUGraph<>(8, arr);
        graph.addEdge(0, 3);
        graph.addEdge(0, 7);
        graph.addEdge(0, 5);
        graph.addEdge(1, 0);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(3, 2);
        graph.addEdge(4, 3);
        graph.addEdge(5, 1);
        graph.addEdge(5, 2);
        graph.addEdge(7, 1);
        graph.addEdge(7, 6);
        System.out.println(graph);

        System.out.format("%-20s: ", "dfs");
        graph.dfs(5);

        System.out.format("%-20s: ", "dfs recursive");
        graph.dfsR(5);

        System.out.format("%-20s: ", "bfs");
        graph.bfs(1);

        System.out.format("%-20s: ", "topological sort");
        graph.topolgicalSort();
    }

}
