package dataStructures;

import adt.Graph;
import adt.Stack;

import java.util.*;

/**
 * Created by martin on 12/13/16.
 * Directed Unweighted (Adjacency List) Graph
 * - implementation using adjacency list
 */
public class DUGraph<E> implements Graph<E> {

    private int V; // number of nodes / vertexes
    private Node<E>[] adjList;

    @SuppressWarnings("unchecked")
    public DUGraph(int V, E[] list) {
        this.V = V;
        adjList = new Node[V];
        for (int i = 0; i < V; i++)
            adjList[i] = new Node<>(i, list[i]);
    }

    @SuppressWarnings("unchecked")
    public DUGraph(int V) {
        this.V = V;
        adjList = new Node[V];
    }

    public boolean areAdjacent(int x, int y) {
        return adjList[x].containsNeighbour(adjList[y]);
    }

    public void addEdge(int x, int y) {
        if (adjList[x] == null)
            adjList[x] = new Node<>(x, null);
        if (adjList[y] == null)
            adjList[y] = new Node<>(y, null);
        adjList[x].addNeighbour(adjList[y]);
    }

    public boolean deleteEdge(int x, int y) {
        return adjList[x].removeNeighbour(adjList[y]);
    }

    public void setInfo(int index, E info) {
        if (adjList[index] == null)
            adjList[index] = new Node<>(index, info);
        else
            adjList[index].info = info;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Node<E> n : adjList)
            builder.append(n);
        return builder.toString();
    }

    public void dfs(int index) {
        boolean[] visited = new boolean[V];
        Stack<Node<E>> stack = new LinkedStack<>();

        Node<E> startNode = adjList[index];
        stack.push(startNode);

        while (!stack.isEmpty()) {
            Node<E> node = stack.pop();

            if (!visited[node.index]) {
                visited[node.index] = true;
                System.out.print(node.info + " ");
            }

            node.neighbours.forEach(n -> {
                if (!visited[n.index])
                    stack.push(n);
            });
        }
        System.out.println();
    }

    public void dfsR(int index) {
        boolean[] visited = new boolean[V];
        dfsR(index, visited);
        System.out.println();
    }

    private void dfsR(int index, boolean[] visited) {

        visited[index] = true;
        System.out.print(adjList[index].info + " ");

        for (Node<E> n : adjList[index].neighbours) {
            int i = n.index;
            if (!visited[i])
                dfsR(n.index, visited);
        }
    }

    public void bfs(int index) {
        boolean[] visited = new boolean[V];
        LinkedQueue<Node<E>> queue = new LinkedQueue<>();

        Node<E> current = adjList[index];
        System.out.print(current.info + " ");
        visited[current.index] = true;
        queue.enqueue(current);

        while (!queue.isEmpty()) {
            current = queue.dequeue();
            current.neighbours.forEach(n -> {
                if (!visited[n.index]) {
                    System.out.print(n.info + " ");
                    visited[n.index] = true;
                    queue.enqueue(n);
                }
            });
        }
        System.out.println();
    }

    // typical bfs
    public List<Integer> getConnectedComponent(int index) {
        boolean[] isConnected = new boolean[V];
        LinkedQueue<Node<E>> queue = new LinkedQueue<>();

        isConnected[index] = true;
        queue.enqueue(adjList[index]);

        while (!queue.isEmpty()) {
            Node<E> node = queue.dequeue();

            node.neighbours.forEach(n -> {
                if (!isConnected[n.index]) {
                    isConnected[n.index] = true;
                    queue.enqueue(n);
                }
            });
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < V; i++)
            if (isConnected[i])
                res.add(adjList[i].index);

        return res;
    }

    public void topolgicalSort() {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new LinkedStack<>();

        for (int i = 0; i < V; i++)
            dfsTopologicalR(i, visited, stack);

        while (!stack.isEmpty())
            System.out.print(adjList[stack.pop()].info + " ");
    }

    private void dfsTopologicalR(int index, boolean[] visited, Stack<Integer> stack) {
        if (!visited[index]) {
            visited[index] = true;
            for (Node<E> n : adjList[index].neighbours)
                dfsTopologicalR(n.index, visited, stack);
            stack.push(index);
        }
    }


    // slightly modified bfs algorithm

    /**
     * Finds the shortest distance between tho nodes in an unweighted graph
     *
     * @param start index of the node to start from
     * @param end   index of the node to end with
     * @return distance from node with index 'start' to node with index 'end',
     * or -1 if node with index 'end' does not exist in the graph
     */
    public int distance(int start, int end) {
        QueueList<Node<E>> queue = new QueueList<>();
        boolean[] visited = new boolean[V];
        // stores distance from start node to every node
        int[] distance = new int[V];

        if (adjList[start] == null)
            return 0;

        visited[start] = true;
        distance[start] = 0;
        queue.enqueue(adjList[start]);

        while (!queue.isEmpty()) {
            Node<E> node = queue.dequeue();

            for (Node<E> n : node.neighbours) {
                if (!visited[n.index]) {
                    visited[n.index] = true;
                    distance[n.index] = distance[node.index] + 1;
                    queue.enqueue(n);
                }
                if (n.index == end)
                    return distance[end];
            }
        }
        return -1; // node with index 'end' doesn't exist in the graph
    }

    // Graph Node
    private class Node<T> {
        int index; // reden broj na temeto vo grafot
        T info;
        LinkedList<Node<T>> neighbours;

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

        void addNeighbour(Node<T> node) {
            neighbours.add(node);
        }

        boolean containsNeighbour(Node<T> node) {
            return neighbours.contains(node);
        }

        boolean removeNeighbour(Node<T> node) {
            if (neighbours.contains(node)) {
                neighbours.remove(node);
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(info.toString());
            builder.append(": ");
            for (Node<T> n : neighbours) {
                builder.append(n.info.toString());
                builder.append(" -> ");
            }
            if (neighbours.size() > 0)
                builder.delete(builder.length() - 4, builder.length());
            builder.append("\n");
            return builder.toString();
        }
    }

}