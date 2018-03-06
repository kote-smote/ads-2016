package dataStructures;

import java.util.*;
import unit.Edge;

/**
 * @author Martin Kotevski
 * Date: 12/16/16
 * Undirected Weighted Graph implemented with adjacency list.
 */
public class UWGraph<E> {
    private int V;
    private Node<E>[] adjList;

    @SuppressWarnings("unchecked")
    public UWGraph(int V, E[] vertexInfos) {
        this.V = V;
        adjList = new Node[V];
        for (int i = 0; i < V; i++) {
            adjList[i] = new Node<>(i, vertexInfos[i]);
        }
    }

    @SuppressWarnings("unchecked")
    public UWGraph(int V) {
        this.V = V;
        adjList = new Node[V];
    }

    public int countVertices() {
        return V;
    }

    public boolean areAdjacent(int x, int y) {
        return adjList[x].hasNeighbour(adjList[y]);
    }

    public void addEdge(int x, int y, float weight) {
        if (adjList[x] == null)
            adjList[x] = new Node<>(x, null);
        if (adjList[y] == null)
            adjList[y] = new Node<>(y, null);

        if (areAdjacent(x, y)) {
            adjList[x].updateNeighbourWeight(adjList[y], weight);
            adjList[y].updateNeighbourWeight(adjList[x], weight);
        } else {
            adjList[x].addNeighbour(adjList[y], weight);
            adjList[y].addNeighbour(adjList[x], weight);
        }
    }

    public boolean deleteEdge(int x, int y) {
        // always returns (true && true) or (false && false)
        return adjList[x].removeNeighbour(adjList[y])
                && adjList[y].removeNeighbour(adjList[x]);
    }

    public void setInfo(int index, E info) {
        if (adjList[index] == null)
            adjList[index] = new Node<>(index, info);
        else
            adjList[index].info = info;
    }

    public List<Edge> getEdges() {
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            int v1 = adjList[i].index;
            for (Node.NodeNeighbour neighbour : adjList[i].neighbours) {
                int v2 = neighbour.node.index;
                float weight = neighbour.weight;
                Edge e = new Edge(v1, v2, weight);
                // Graph is undirected, take every edge only once
                if (v2 > v1)
                    edges.add(e);
            }
        }
        return edges;
    }

    public List<Edge> prim(int index) {
        List<Edge> edgesMST = new ArrayList<>();
        boolean[] visited = new boolean[V];

        visited[index] = true;

        for (int i = 0; i < V - 1; i++) {
            Edge e = this.minWeightEdge(visited);
            if (e == null) // No more edges to add
                break;
            visited[e.v1] = true;
            visited[e.v2] = true;
            edgesMST.add(e);
        }
        return edgesMST;
    }

    private Edge minWeightEdge(boolean[] visited) {
        float minWeight = Float.MAX_VALUE;
        int v1 = 0; int v2 = 0;

        for (int i = 0; i < visited.length; i++)
            if (visited[i]) {
                Node<E> node = adjList[i];
                for (Node.NodeNeighbour n : node.neighbours)
                    if (!visited[n.node.index] && n.weight < minWeight) {
                        v1 = i;
                        v2 = n.node.index;
                        minWeight = n.weight;
                    }
            }

        return minWeight == Float.MAX_VALUE ? null :
                new Edge(v1, v2, minWeight);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Node<E> n : adjList) {
            builder.append(n);
            builder.append("\n");
        }
        return builder.toString();
    }

    private class Node<T> {
        int index;
        T info;
        List<NodeNeighbour> neighbours;

        Node(int index, T info) {
            this.index = index;
            this.info = info;
            neighbours = new LinkedList<>();
        }

        public boolean equals(Object object) {
            if (object == null)
                return false;
            if (this == object)
                return true;
            if (this.getClass() != object.getClass())
                return false;
            @SuppressWarnings("unchecked")
            Node<T> other = (Node<T>) object;
            return this.info.equals(other.info);
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(info.toString());
            builder.append(": ");
            builder.append(neighbours);
            builder.append("\n");
            return builder.toString();
        }

        void addNeighbour(Node<T> node, float weight) {
            NodeNeighbour tmp = new NodeNeighbour(node, weight);
            neighbours.add(tmp);
        }

        boolean removeNeighbour(Node<T> node) {
            return neighbours.remove(new NodeNeighbour(node));
        }

        boolean hasNeighbour(Node<T> node) {
            return neighbours.contains(new NodeNeighbour(node));
        }

        void updateNeighbourWeight(Node<T> node, float weight) {
            neighbours.forEach(n -> {
                if (n.node.equals(node))
                    n.weight = weight;
            });
        }

        private class NodeNeighbour {
            Node<T> node;
            float weight;

            NodeNeighbour(Node<T> node, float weight) {
                this.node = node;
                this.weight = weight;
            }

            NodeNeighbour(Node<T> node) {
                this.node = node;
                this.weight = 0;
            }

            @Override
            public boolean equals(Object o) {

                if (o == null)
                    return false;
                if (this == o)
                    return true;
                if (this.getClass() != o.getClass())
                    return false;
                @SuppressWarnings("unchecked")
                NodeNeighbour other = (NodeNeighbour) o;
                return this.node.equals(other.node);
            }

            @Override
            public String toString() {
                return node.index + ": " + node.info.toString();
            }
        }
    }
}
