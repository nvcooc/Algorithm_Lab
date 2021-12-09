package TreeTopic;

public class BinarySearchTree {
    Node root;

    public static Boolean isBST(Node root, int pre) {
        if (root == null) {
            return true;
        }
        System.out.print(" "+root.value+" ");
        if (!isBST(root.left, pre)) {
            return false;
        }
        if (root.value < pre) {
            return false;
        }
        pre = root.value;
        return isBST(root.right, pre);
    }

    public void addNode(Node node, Node root) {

        if (root == null) {
            this.root = node;
            return;
        }
        if (node.value <= root.value) {
            if (root.left == null) {
                root.left = node;
            } else {
                addNode(node, root.left);
            }
        } else {
            if (root.right == null) {
                root.right = node;
            } else {
                addNode(node, root.right);
            }
        }
    }

    public static void main(String[] args) {
        BinarySearchTree t = new BinarySearchTree();
        Node n1 = new Node(5);
        t.addNode(n1, t.root);
        Node n2 = new Node(1);
        t.addNode(n2, t.root);
        Node n3 = new Node(3);
        t.addNode(n3, t.root);
        Node n4 = new Node(6);
        t.addNode(n4, t.root);
        Node n5 = new Node(4);
        t.addNode(n5, t.root);
        System.out.print("先序遍历");
        System.out.println(BinarySearchTree.isBST(t.root, Integer.MIN_VALUE));
    }
}

class Node {
    int value;
    Node left;
    Node right;

    Node(int value) {
        this.value = value;
    }
}