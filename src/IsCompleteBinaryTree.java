import java.util.Random;
import java.util.Scanner;

/**
 * Created by martin on 12/21/16.
 * Za dadeno binarno prebaruvacko drvo cii sto jazli sodrzat prirodni broevi
 * da se proveri dali e kompletno.
 * *Kompletno binarno drvo - elementite od sekoe nivo, osven mozebi od posednoto,
 * imaat po dve deca, a decata od poslednoto nivo se levi deca.
 *
 * Vlez: Vo prviot red se vcituva brojot na jazli koi ke se vnesuvaat vo drvoto.
 * Ponataka vo sekoj red oddelno se dadeni vrednostite na jazlite od binarnoto
 * drvo. Otkako ke se kreira drvoto, se proveruva dali e kompletno ili ne.
 *
 * Izlez: Se pecati poraka dali drvoto e kompletno ili ne.
 *
 * Zabeleska: Da se iskoristi soodvetna podatocna struktura i proverkata za toa
 * dali drvoto e kompetno ili ne da se napravi vo posebna metoda koa sto e
 * del od implementacijata na podatocnata struktura.
 */
public class IsCompleteBinaryTree {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        int N = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(sc.nextLine());
            bst.insert(n);
        }

        if (bst.isComplete())
            System.out.println("Drvoto e kompletno");
        else
            System.out.println("Drvoto ne e kompletno");
    }

}

// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// Comparable find( x )   --> Return item that matches x
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 *
 * @author Mark Allen Weiss
 */
class BinarySearchTree<E extends Comparable<E>> {

    /**
     * The tree root.
     */
    private BNode<E> root;
    private int size;
    // stores number of elements at eatch level
    private int[] countAtLevel;

    /**
     * Construct the tree.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     *
     * @param x the item to insert.
     */
    public void insert(E x) {
        root = insert(x, root);
        size++;
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     *
     * @param x the item to remove.
     */
    public void remove(E x) {
        root = remove(x, root);
        size--;
    }

    /**
     * Find the smallest item in the tree.
     *
     * @return smallest item or null if empty.
     */
    public E findMin() {
        return elementAt(findMin(root));
    }

    /**
     * Find the largest item in the tree.
     *
     * @return the largest item of null if empty.
     */
    public E findMax() {
        return elementAt(findMax(root));
    }

    /**
     * Find an item in the tree.
     *
     * @param x the item to search for.
     * @return the matching item or null if not found.
     */
    public BNode<E> find(E x) {
        return find(x, root);
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty() {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree() {
        if (isEmpty()) {
            System.out.println("Empty tree");
        } else {
            printTree(root);
        }
    }

    /**
     * Find the max height of the tree
     *
     * @return the maximum height of this tree
     */
    public int height() {
        return height(this.root);
    }

    /**
     * Checks if the BinarySearchTree is complete.
     * A complete binary tree is a binary tree in which every level,
     * except possibly the last, is completely filled,
     * and all nodes in the last level are as far left as possible.
     *
     * @return true if this tree is complete, otherwise false
     */
    public boolean isComplete() {

        int height = this.height();
        this.countAtLevel = new int[height];
        // fills countsAtLevel array
        this.fillCounts(root, 0, height - 1);

        int[] expectedCountAtLevel = new int[height];

        // proveri gi site nivoa se do predposledniot
        for (int i = 0; i < height - 1; i++) {
            expectedCountAtLevel[i] = (int) Math.pow(2, i);
            if (countAtLevel[i] != expectedCountAtLevel[i])
                return false;
        }

        // proveri go poslednoto nivo
        if (this.lastLevelValidity())
            return true;
        return false;
    }


    /**
     * Internal method to get element field.
     *
     * @param t the node.
     * @return the element field or null if t is null.
     */
    private E elementAt(BNode<E> t) {
        if (t == null)
            return null;
        return t.info;
    }

    /**
     * Internal method to insert into a subtree.
     *
     * @param x the item to insert.
     * @param t the node that roots the tree.
     * @return the new root.
     */
    private BNode<E> insert(E x, BNode<E> t) {
        if (t == null) {
            t = new BNode<E>(x, null, null);
        } else if (x.compareTo(t.info) < 0) {
            t.left = insert(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            t.right = insert(x, t.right);
        } else ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     *
     * @param x the item to remove.
     * @param t the node that roots the tree.
     * @return the new root.
     */
    private BNode<E> remove(Comparable x, BNode<E> t) {
        if (t == null)
            return t;   // Item not found; do nothing

        if (x.compareTo(t.info) < 0) {
            t.left = remove(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) { // Two children
            t.info = findMin(t.right).info;
            t.right = remove(t.info, t.right);
        } else {
            if (t.left != null)
                return t.left;
            else
                return t.right;
        }
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     *
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    private BNode<E> findMin(BNode<E> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }
        return findMin(t.left);
    }

    /**
     * Internal method to find the largest item in a subtree.
     *
     * @param t the node that roots the tree.
     * @return node containing the largest item.
     */
    private BNode<E> findMax(BNode<E> t) {
        if (t == null) {
            return null;
        } else if (t.right == null) {
            return t;
        }
        return findMax(t.right);
    }

    /**
     * Internal method to find an item in a subtree.
     *
     * @param x is item to search for.
     * @param t the node that roots the tree.
     * @return node containing the matched item.
     */
    private BNode<E> find(E x, BNode<E> t) {
        if (t == null)
            return null;

        if (x.compareTo(t.info) < 0) {
            return find(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            return find(x, t.right);
        } else {
            return t;    // Match
        }
    }

    /**
     * Internal method to print a subtree in sorted order.
     *
     * @param t the node that roots the tree.
     */
    private void printTree(BNode<E> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.info);
            printTree(t.right);
        }
    }

    /**
     * Internal method to find the max height of the tree
     *
     * @param node the node to start from - usually the root
     * @return the maximum height of this tree
     */
    private int height(BNode<E> node) {
        if (node == null)
            return 0;
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return (leftHeight > rightHeight) ? leftHeight + 1 : rightHeight + 1;
    }

    /**
     * Internal method to find the number of elements at the specified level.
     * As a side effect it fills the countsAtLevel array up to the level
     * specified as desired level, so after the termination of this method,
     * the array will contain the number of elements at the first level,
     * second level, ... , desired level.
     *
     * @param node    the node to start from - usually the root
     * @param curr    current level at which the method operates
     * @param desired the desired  level at which the number of elements
     *                is will be calculated
     * @return number of elements at the specified level
     */
    private int fillCounts(BNode node, int curr, int desired) {
        if (node == null)
            return 0;
        if (curr == desired) {
            this.countAtLevel[curr]++;
            return 1;
        }
        this.countAtLevel[curr]++;
        return fillCounts(node.left, curr + 1, desired)
                + fillCounts(node.right, curr + 1, desired);
    }

    /**
     * Used in combination with isComplete().
     * Checks if all nodes in the last level are left children.
     *
     * @return true if all nodes in the last level are as far left
     * as possible, otherwise false
     */
    private boolean lastLevelValidity() {
        int penultimateLevel = this.height() - 1; // predposlediot level
        return lastLevelValidity(root, 0, penultimateLevel);
    }

    /**
     * Internal method used in combination with isComplete().
     * Checks if all nodes in the last level are as far left as possible.
     *
     * @param node         the node to start from - usually the root
     * @param curr         current level at which the method operates
     * @param prenultimate the prenultimate level of the tree
     * @return true if all nodes in the last level are left children,
     * otherwise false
     */
    private boolean lastLevelValidity(BNode<E> node, int curr, int prenultimate) {
        if (node == null)
            return false;
        if (curr == prenultimate) {
            if (node.right != null)
                return false;
            return true;
        }
        return lastLevelValidity(node.left, curr + 1, prenultimate) ||
                lastLevelValidity(node.right, curr + 1, prenultimate);
    }

    // Test program
    public static void testTree(String[] args) {
        int i, j, k;

        Random r = new Random(System.currentTimeMillis());
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();

        for (i = 0; i < 1000; i++)
            bst.insert(r.nextInt(1000000));

        bst.printTree();

    }
}

class BNode<E extends Comparable<E>> {

    public E info;
    public BNode<E> left;
    public BNode<E> right;

    public BNode(E info) {
        this.info = info;
        left = null;
        right = null;
    }

    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }

}