public class Driver{
    public static void main(String[] args) {
        Integer[] v1 = {25, 10, 60, 55, 58, 56, 14, 63, 8, 50, 6, 9};
        Integer[] v2 = {200, 15, 3, 65, 83, 70, 90};


        Integer[] values = {10, 12, 7, 11, 14, 5, 9};
        Integer[] little = {20, 19, 21};

        Tree<Integer> tree = new Tree<>("Tree 1");
        Tree<Integer> tree2 = new Tree<>(values, "Tree 2");

        System.out.println(tree.toString());

        tree.balanceTree();
        System.out.println(tree.toString());




    }
}