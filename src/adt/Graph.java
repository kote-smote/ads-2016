package adt;

/**
 * Created by martin on 12/15/16.
 */
public interface Graph<E> {

    boolean areAdjacent(int x, int y);

    void addEdge(int x, int y);

    boolean deleteEdge(int x, int y);
}
