package dataStructures;

import adt.*;

import java.util.*;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Created by martin on 12/13/16.
 * Directed Weighted Graph
 * - implementation using adjacency list
 */
public class DWGraph<E> {

    private int V; // number of nodes
    private Node<E>[] adjList;

    public DWGraph(int V, E[] list) {
        this.V = V;
        adjList = new Node[V];
        for (int i = 0; i < V; i++)
            adjList[i] = new Node<>(i, list[i]);
    }

    public DWGraph(int V) {
        this.V = V;
        adjList = new Node[V];
    }

    public boolean areAdjacent(int x, int y) {
        return adjList[x].containsNeighbour(adjList[y]);
    }

    public void addEdge(int x, int y, float weight) {
        if (adjList[x] == null)
            adjList[x] = new Node<>(x, null);
        if (adjList[y] == null)
            adjList[y] = new Node<>(y, null);
        if (adjList[x].containsNeighbour(adjList[y]))
            adjList[x].updateNeighbourWeight(adjList[y], weight);
        else
            adjList[x].addNeighbour(adjList[y], weight);
    }

    public boolean deleteEdge(int x, int y) {
        return adjList[x].removeNeighbour(adjList[y]);
    }

    public void setInfo(int index, E info) {
        if (adjList[index] == null)
            adjList[index] = new Node<E>(index, info);
        else
            adjList[index].info = info;
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
        int index; // reden broj na temeto vo grafot
        T info;
        LinkedList<NodeNeighbour> neighbours;

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
            return index == other.index && info.equals(other.info);
        }

        List<Node<T>> getNeighbours() {
            return neighbours.stream()
                    .map(n -> n.node)
                    .collect(Collectors.toList());
        }

        boolean containsNeighbour(Node<T> n) {
            return neighbours.contains(new NodeNeighbour(n));
        }

        void addNeighbour(Node<T> node, float weight) {
            NodeNeighbour tmp = new NodeNeighbour(node, weight);
            neighbours.add(tmp);
        }

        boolean removeNeighbour(Node<T> n) {
            return neighbours.remove(new NodeNeighbour(n));
        }

        void updateNeighbourWeight(Node<T> node, float weight) {
            neighbours.forEach(n -> {
                if (n.node.equals(node))
                    n.weight = weight;
            });
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(info.toString());
            builder.append(": ");
            builder.append(neighbours);
            return builder.toString();
        }

        // Private class inside Node class
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
                return node.equals(o);
            }

            public String toString() {
                return node.info.toString();
            }
        }
    }
}

