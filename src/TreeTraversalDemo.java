import java.util.*;

public class TreeTraversalDemo {

    // --- 1. Node definition ---
    static class Node {
        String val;
        Node left, right;
        Node(String val) { this.val = val; }
    }

    // --- 2. Traversal methods ---

    // Pre-order: Node, Left, Right
    static void preOrder(Node n) {
        if (n == null) return;
        System.out.print(n.val + " ");
        preOrder(n.left);
        preOrder(n.right);
    }

    // In-order: Left, Node, Right
    static void inOrder(Node n) {
        if (n == null) return;
        inOrder(n.left);
        System.out.print(n.val + " ");
        inOrder(n.right);
    }

    // Post-order: Left, Right, Node
    static void postOrder(Node n) {
        if (n == null) return;
        postOrder(n.left);
        postOrder(n.right);
        System.out.print(n.val + " ");
    }

    // Level-order (BFS)
    static void levelOrder(Node root) {
        if (root == null) return;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node n = q.poll();
            System.out.print(n.val + " ");
            if (n.left != null) q.add(n.left);
            if (n.right != null) q.add(n.right);
        }
    }


    // --- 3. Build the example LinkedIn tree ---
    static Node buildLinkedInTree() {
        // Level 0
        Node faithPost = new Node("Faith W. (original)");
        // Level 1
        Node nathan = new Node("Nathan W.");
        Node phil   = new Node("Phil K.");
        faithPost.left  = nathan;
        faithPost.right = phil;
        // Level 2 under Nathan
        Node greg = new Node("Greg S.");
        nathan.right = greg;
        // Level 3: Faith replies to Greg
        Node faithReply = new Node("Faith W. (reply)");
        greg.right = faithReply;
        return faithPost;
    }

    // --- 4. Solution 1: Backtracking with List<String> ---
    static List<String> binaryTreePaths_Backtrack(Node root) {
        List<String> results = new ArrayList<>();
        if (root != null) dfsBacktrack(root, new ArrayList<>(), results);
        return results;
    }
    private static void dfsBacktrack(Node node, List<String> path, List<String> results) {
        path.add(node.val);
        if (node.left == null && node.right == null) {
            results.add(String.join("->", path));
        } else {
            if (node.left  != null) dfsBacktrack(node.left,  path, results);
            if (node.right != null) dfsBacktrack(node.right, path, results);
        }
        path.removeLast();
    }

    // --- 5. Solution 2: Build strings on-the-fly ---
    static List<String> binaryTreePaths_StringBuild(Node root) {
        List<String> results = new ArrayList<>();
        if (root != null) dfsBuild(root, "", results);
        return results;
    }
    private static void dfsBuild(Node node, String current, List<String> results) {
        current = current.isEmpty() ? node.val : current + "->" + node.val;
        if (node.left == null && node.right == null) {
            results.add(current);
            return;
        }
        if (node.left  != null) dfsBuild(node.left,  current, results);
        if (node.right != null) dfsBuild(node.right, current, results);
    }


    // --- 6. main with test cases ---
    public static void main(String[] args) {
        System.out.println("=== Test: LinkedIn Tree ===");
        Node post = buildLinkedInTree();
        System.out.print("InOrder:  ");   inOrder(post);    System.out.println();
        System.out.println();



        // Example tree (non-BST):
//           A
//         /   \
//        B     C
//       / \   / \
//      D   E F   G
        Node root = new Node("A");
        root.left        = new Node("B");
        root.right       = new Node("C");
        root.left.left   = new Node("D");
        root.left.right  = new Node("E");
        root.right.left  = new Node("F");
        root.right.right = new Node("G");

        System.out.println("=== Slide 25: Paths (Solution 1, Backtrack) ===");
        List<String> paths1 = binaryTreePaths_Backtrack(root);
        paths1.forEach(System.out::println);
        System.out.println();

        System.out.println("=== Slide 26: Paths (Solution 2, String Build) ===");
        List<String> paths2 = binaryTreePaths_StringBuild(root);
        paths2.forEach(System.out::println);
    }
}
