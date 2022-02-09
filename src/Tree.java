import java.util.*;

public class Tree<E extends Comparable<? super E>> {
    private BinaryTreeNode root;  // Root of tree
    private String name;     // Name of tree

    /**
     * Create an empty tree
     *
     * @param label Name of tree
     */
    public Tree(String label) {
        name = label;
    }

    /**
     * Create BST from ArrayList
     *
     * @param arr   List of elements to be added
     * @param label Name of tree
     */
    public Tree(ArrayList<E> arr, String label) {
        name = label;
        for (E key : arr) {
            insert(key);
        }
    }

    /**
     * Create BST from Array
     *
     * @param arr   List of elements to be added
     * @param label Name of  tree
     */
    public Tree(E[] arr, String label) {
        name = label;
        for (E key : arr) {
            insert(key);
        }
    }



    /**
     * Return a string containing the tree contents as a tree with one node per line
     */
    @Override
    public String toString() {
        String s = this.name;
        if (root == null) {
            return s + ": Empty tree";
        } else{
            s += "\n";
            }
        s = toString(this.root, 0, s);

        return s;
    }

    private String toString(BinaryTreeNode node, int depth, String s){
        if (node == null){
            return s;
        }
        s = toString(node.right, depth + 1, s);

        s += formatNode(node, depth);

        s = toString(node.left, depth + 1, s);

        return s;
    }

    private String formatNode(BinaryTreeNode currentNode, int depth){

        // String builder suggested by IntelliJ
        StringBuilder formatted = new StringBuilder();

        // <String>.repeat() suggested by IntelliJ
        formatted.append("\t".repeat(Math.max(0, depth)));

        formatted.append(currentNode.element);

        if (currentNode.parent == null){
            formatted.append(" [No parent]");
        } else {
            formatted.append(" [").append(currentNode.parent.element).append("]");
        }


        return formatted + "\n";

    }

    /**
     * Return a string containing the tree contents as a single line
     */
    public String inOrderToString() {

        String s = this.name + ":";

        if (root == null){
            return s + " Empty tree";
        }

        return inOrderToString(root, s);

    }

    private String inOrderToString(BinaryTreeNode node, String s){

        if (node == null){
            return s;
        }

        s = inOrderToString(node.left, s);

        s += " " + node.element;

        s = inOrderToString(node.right, s);

        return s;
    }

    /**
     * Balance the tree
     */
    public void balanceTree() {
        // TODO: come back and implement

        ArrayList<E> valueList;
        valueList = grabValue(root, new ArrayList<>());

        if(root != null){
            if (root.right != null){
                this.root.right = null;
            }

            if (root.left != null){
                this.root.left = null;
            }

            this.root = null;
        }



        binaryInsert(valueList, 0, valueList.size() - 1);

    }

    private ArrayList<E> grabValue(BinaryTreeNode node, ArrayList<E> valueList){
        if (node == null){
            return valueList;
        }

        valueList = grabValue(node.left, valueList);
        valueList.add(node.element);
        valueList = grabValue(node.right, valueList);

        return valueList;

    }

    private void binaryInsert(ArrayList<E> valueList, int low, int high){
        if (low > high){
            return;
        }

        int mid = (low + high) / 2;
        insert(valueList.get(mid));
        binaryInsert(valueList, low, mid - 1);
        binaryInsert(valueList, mid + 1, high);

    }



    /**
     * reverse left and right children recursively
     */
    public void flip() {
        flip(root);
    }

    private void flip(BinaryTreeNode node){
        if (node == null){
            return;
        }

        BinaryTreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;

        flip(node.left);
        flip(node.right);

    }

    /**
     * Returns a BinaryTreeNode with the value of key
     * @param key The value at the node
     */
    public BinaryTreeNode getByKey(E key) {
        return getByKey(key, root);
    }

    private BinaryTreeNode getByKey(E key, BinaryTreeNode node){
        if (node == null){
            return null;
        }
        if (node.element == key){
            return node;
        } else if (key.compareTo(node.element) < 0){
            return getByKey(key, node.left);
        } else{
            return getByKey(key, node.right);
        }

    }

    /**
     * Counts number of nodes in specified level
     *
     * @param level Level in tree, root is zero
     * @return count of number of nodes at specified level
     */
    public int nodesInLevel(int level) {
        return nodesInLevel(root, level, 0);
    }

    private int nodesInLevel(BinaryTreeNode node, int level, int total){

        if (node == null){
            return total;
        }
        if (level == 0){
            return total + 1;
        } else {
            total = nodesInLevel(node.left, level - 1, total);
            total = nodesInLevel(node.right, level - 1, total);
        }

        return total;
    }

    /**
     * Print all paths from root to leaves
     */
    public void printAllPaths() {
        printAllPaths(root);
    }

    private void printAllPaths(BinaryTreeNode node){
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null){
            BinaryTreeNode printNode = this.root;
            while (printNode != null){
                System.out.printf("%s ", printNode.element);

                int compResult = node.element.compareTo(printNode.element);
                if (compResult < 0){
                    printNode = printNode.left;
                }else {
                    printNode = printNode.right;
                }
            }
            System.out.print("\n");
        } else {
            printAllPaths(node.right);
            printAllPaths(node.left);
        }
    }

    /**
     * Returns the in-order successor of the specified node
     * @param node node from which to find the in-order successor
     */
    public BinaryTreeNode inOrderSuccessor(BinaryTreeNode node){
        if (node != null) {
            return inOrderSuccessor(node, node.element);
        } else {
            return null;
        }
    }

    private BinaryTreeNode inOrderSuccessor(BinaryTreeNode node, E value){

        if (node == null){
            return null;
        }
        if (node.element.compareTo(value) == 0) {
            if (node.right != null) {
                return inOrderSuccessor(node.right, value);
            }
            if (node.parent == null) {
                return null;
            } else if (node.parent.element.compareTo(node.element) > 0) {
                return node.parent;
            } else {
                return inOrderSuccessor(node.parent, value);
            }
        } else if (node.element.compareTo(value) > 0){
            if (node.left == null){
                return node;
            } else{
                return inOrderSuccessor(node.left, value);
            }
        } else{
            if (node.parent == null){
                return null;
            }
            if (node.parent.element.compareTo(value) > 0){
                return node.parent;
            } else{
                return inOrderSuccessor(node.parent, value);
            }
        }
    }

    /**
     * Counts all non-null binary search trees embedded in tree
     *
     * @return Count of embedded binary search trees
     */
    public int countBST() {
        return countBST(root, 0);
    }

    private int countBST(BinaryTreeNode node, int total){
        if (node == null){
            return total;
        }
        if (isBST(node)){
            total += 1;
        }
        total = countBST(node.right, total);
        total = countBST(node.left, total);

        return total;
    }

    private boolean isBST(BinaryTreeNode node){
        if (node == null){
            return true;
        }

        if (node.right != null && node.element.compareTo(node.right.element) > 0){
            return false;
        }
        if (node.left != null && node.element.compareTo(node.left.element) < 0){
            return false;
        }

        return (isBST(node.right) && isBST(node.left));
    }


    /**
     * Insert into a bst tree; duplicates are allowed
     *
     * @param x the item to insert.
     */
    public void insert(E x) {
        root = insert(x, root, null);
    }

    /**
     * Internal method to insert into a subtree.
     * In tree is balanced, this routine runs in O(log n)
     *
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryTreeNode insert(E x, BinaryTreeNode t, BinaryTreeNode parent) {
        if (t == null) return new BinaryTreeNode(x, null, null, parent);

        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = insert(x, t.left, t);
        } else {
            t.right = insert(x, t.right, t);
        }

        return t;
    }


    /**
     * Internal method to find an item in a subtree.
     * This routine runs in O(log n) as there is only one recursive call that is executed and the work
     * associated with a single call is independent of the size of the tree: a=1, b=2, k=0
     *
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     *          SIDE EFFECT: Sets local variable curr to be the node that is found
     * @return node containing the matched item.
     */
    private boolean contains(E x, BinaryTreeNode t) {
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            return contains(x, t.left);
        else if (compareResult > 0)
            return contains(x, t.right);
        else {
            return true;    // Match
        }
    }

    // Basic node stored in unbalanced binary trees
    public class BinaryTreeNode {
        E element;            // The data in the node
        BinaryTreeNode left;   // Left child
        BinaryTreeNode right;  // Right child
        BinaryTreeNode parent; //  Parent node

        // Constructors
        BinaryTreeNode(E theElement) {
            this(theElement, null, null, null);
        }

        BinaryTreeNode(E theElement, BinaryTreeNode lt, BinaryTreeNode rt, BinaryTreeNode pt) {
            element = theElement;
            left = lt;
            right = rt;
            parent = pt;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Node:");
            sb.append(element);
            if (parent == null) {
                sb.append("<>");
            } else {
                sb.append("<");
                sb.append(parent.element);
                sb.append(">");
            }

            return sb.toString();
        }
    }
}
