package behavioral.nullobject;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Nice pattern. Why return a null list if you can return an empty list, for example? empty list can be iterated with no
 * problem, just nothing would happen here we have an EmptyNode that effectivelly does nothing but also has no null
 * pointer at the same time
 * <p>
 * Created by Arsen on 5/12/2017
 */
public class NullObjectApp {
    public static void main(String[] args) {
        Node n = new NormalNode().grow(3);
        System.out.println(n);
        System.out.println(n.size());
    }
}

interface Node {
    int size();

    Node grow(int deepness);

}

// normal object
class NormalNode implements Node {
    private Node left;
    private Node right;

    NormalNode() {
        left = new EmptyNode();
        right = new EmptyNode();
    }

    public int size() {
        return 1 + left.size() + right.size();
    }

    public Node grow(int deepness) {
        if (deepness > 0) {
            left = left.grow(deepness - 1);
            right = right.grow(deepness - 1);
        }
        return this;
    }

    public String toString() {
        return "NormalNode[" + left + ", " + right + ']';
    }
}

// null object. doesnt even have left and right nodes!!!
class EmptyNode implements Node {
    public int size() {
        return 0;
    }

    public Node grow(int deepness) {
        if (deepness > 0) {
            return new NormalNode().grow(deepness - 1);
        }
        return this;
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }
}
