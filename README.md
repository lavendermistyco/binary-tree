# TreeTraversalDemo

A Java demonstration of binary tree traversals and root-to-leaf path generation. Includes:

* **Pre-order**, **In-order**, **Post-order** (DFS variants)
* **Level-order** (BFS)
* **Two solutions** for listing all root-to-leaf paths:

    1. Backtracking with a mutable `List<String>`
    2. Building path strings on-the-fly

## Project Structure

```
TreeTraversalDemo.java   # Main class with all code examples
README.md                # This file
```

## Getting Started

1. **Clone** this repository (or download as ZIP)
2. **Compile** the Java source:

   ```bash
   javac TreeTraversalDemo.java
   ```
3. **Run** the demo:

   ```bash
   java TreeTraversalDemo
   ```

You’ll see console output for:

* In-order traversal of the generic example tree
* Paths for a generic binary tree (nodes A–G) using both path-generation solutions

## Code Highlights

### 1. Node Definition

```java
static class Node {
    String val;
    Node left, right;
    Node(String val) { this.val = val; }
}
```

### 2. Traversal Methods

* **preOrder**: `Node → Left → Right`
* **inOrder**: `Left → Node → Right`
* **postOrder**: `Left → Right → Node`
* **levelOrder**: Breadth-first using a `Queue<Node>`

### 3. Path Generation Solutions

#### Solution 1: Backtracking

```java
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
        if (node.left != null)  dfsBacktrack(node.left,  path, results);
        if (node.right != null) dfsBacktrack(node.right, path, results);
    }
    path.remove(path.size() - 1);
}
```

#### Solution 2: String Build

```java
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
    if (node.left != null)  dfsBuild(node.left,  current, results);
    if (node.right != null) dfsBuild(node.right, current, results);
}
```

## Example Usage

### Generic Binary Tree (A–G)

Construct a simple example:

```java
//      A
//     / \
//    B   C
//   / \ / \
//  D  E F  G
Node root = new Node("A");
root.left        = new Node("B");
root.right       = new Node("C");
root.left.left   = new Node("D");
root.left.right  = new Node("E");
root.right.left  = new Node("F");
root.right.right = new Node("G");
```

Invoke paths generation:

```java
List<String> paths1 = binaryTreePaths_Backtrack(root);
List<String> paths2 = binaryTreePaths_StringBuild(root);
paths1.forEach(System.out::println);
paths2.forEach(System.out::println);
```

**Expected output:**

```
A->B->D
A->B->E
A->C->F
A->C->G
```

## License

This project is provided **for educational purposes**. Feel free to use and adapt it.
