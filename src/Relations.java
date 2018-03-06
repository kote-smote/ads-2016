import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BBNode<E> {

    public E info;
    public BBNode<E> left;
    public BBNode<E> right;
    char ltag;
    char rtag;
    static int LEFT = 1;
    static int RIGHT = 2;

    public BBNode(E info) {
        this.info = info;
        left = null;
        right = null;
        ltag = '-';
        rtag = '-';
    }
}

class BTree<E> {

    public BBNode<E> head;

    public BTree() {
        head = new BBNode<E>(null);
        // po definicija ako nema koren, t.e. ako stebloto e prazno
        head.left = head;
        head.ltag = '-';
        // kaj vodacot sekogas desnata vrska pokazuva kon samiot sebe
        head.right = head;
        head.rtag = '+';
    }

    public BBNode<E> makeRoot(E elem) {
        BBNode<E> tmp = new BBNode<E>(elem);
        head.left = tmp;
        head.ltag = '+';

        tmp.left = head;
        tmp.ltag = '-';
        tmp.right = head;
        tmp.rtag = '-';

        return tmp;
    }

    public BBNode<E> makeRootNode(BBNode<E> tmp) {
        head.left = tmp;
        head.ltag = '+';

        tmp.left = head;
        tmp.ltag = '-';
        tmp.right = head;
        tmp.rtag = '-';

        return tmp;
    }

    public BBNode<E> addChild(BBNode<E> node, int where, E elem) {
        BBNode<E> tmp = new BBNode<E>(elem);

        if (where == BBNode.LEFT) {

            if (node.ltag == '+') // veke postoi element
            {
                return null;
            }

            tmp.left = node.left;
            tmp.ltag = '-';
            tmp.right = node;
            tmp.rtag = '-';
            node.left = tmp;
            node.ltag = '+';
        } else {

            if (node.rtag == '+') // veke postoi element
            {
                return null;
            }

            tmp.right = node.right;
            tmp.rtag = '-';
            tmp.left = node;
            tmp.ltag = '-';
            node.right = tmp;
            node.rtag = '+';
        }

        return tmp;
    }

    public BBNode<E> addChildNode(BBNode<E> node, int where, BBNode<E> tmp) {

        if (where == BBNode.LEFT) {

            if (node.ltag == '+') // veke postoi element
            {
                return null;
            }

            tmp.left = node.left;
            tmp.ltag = '-';
            tmp.right = node;
            tmp.rtag = '-';
            node.left = tmp;
            node.ltag = '+';
        } else {

            if (node.rtag == '+') // veke postoi element
            {
                return null;
            }

            tmp.right = node.right;
            tmp.rtag = '-';
            tmp.left = node;
            tmp.ltag = '-';
            node.right = tmp;
            node.rtag = '+';
        }

        return tmp;
    }

    public BBNode<E> insertRight(BBNode<E> parent, E info) {

        BBNode<E> child = new BBNode<E>(info);

        child.ltag = '-';
        child.left = parent;
        child.rtag = parent.rtag;
        child.right = parent.right;

        parent.right = child;
        parent.rtag = '+';

        if (child.rtag == '+') {
            BBNode<E> temp = child.right;
            while (temp.ltag == '+') {
                temp = temp.left;
            }
            temp.left = child;
        }

        return child;
    }

    public BBNode<E> predecessorInorder(BBNode<E> node) {

        if (node.ltag == '-') {
            return node.left;
        }

        BBNode<E> p = node.left;
        while (p.rtag == '+') {
            p = p.right;
        }

        return p;
    }

    public BBNode<E> successorInorder(BBNode<E> node) {

        if (node.rtag == '-') {
            return node.right;
        }

        BBNode<E> p = node.right;
        while (p.ltag == '+') {
            p = p.left;
        }

        return p;
    }

    public int getNumberOfRelations() {
        if (head.left == head)
            return 0;
        BBNode<E> tmp = head.left;
        while (tmp.left != null)
            tmp = tmp.left;
        int count = 0;
        BBNode<E> tmp2 = successorInorder(tmp);
        while (tmp2.right != head) {
            BBNode<E> curr = tmp2;
            tmp2 = successorInorder(tmp2);
            if (tmp2 == tmp.left.left)
                count++;
            else if (tmp2 == tmp.left.right)
                count++;
            else if (tmp2 == tmp.right.right)
                count++;
            else if (tmp2 == tmp.right.left)
                count++;
            tmp = curr;
        }

        return count;
    }
}

public class Relations {

    public static void main(String[] args) throws Exception {
        int i, j, k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        BBNode<Integer> nodes[] = new BBNode[N];
        BTree<Integer> tree = new BTree<Integer>();

        for (i = 0; i < N; i++) {
            nodes[i] = null;
        }

        for (i = 0; i < N; i++) {
            String line = br.readLine();
            st = new StringTokenizer(line);
            int index = Integer.parseInt(st.nextToken());
            nodes[index] = new BBNode<Integer>(Integer.parseInt(st.nextToken()));
            String action = st.nextToken();
            if (action.equals("LEFT")) {
                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BBNode.LEFT, nodes[index]);
            } else if (action.equals("RIGHT")) {
                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BBNode.RIGHT, nodes[index]);
            } else {
                // this node is the root
                tree.makeRootNode(nodes[index]);
            }
        }

        br.close();

        // vasiot kod ovde
        System.out.println(tree.getNumberOfRelations());

    }
}
