package JavaVisitorPattern;

import java.util.*;

enum Color {
    RED, GREEN
}

abstract class Tree {

    private int value;
    private Color color;
    private int depth;

    public Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public int getDepth() {
        return depth;
    }

    public abstract void accept(TreeVis visitor);
}

class TreeNode extends Tree {

    public ArrayList<Tree> children = new ArrayList<>();

    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitNode(this);

        for (Tree child : children) {
            child.accept(visitor);
        }
    }

    public void addChild(Tree child) {
        children.add(child);
    }
}

class TreeLeaf extends Tree {

    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}

abstract class TreeVis {
    public abstract int getResult();

    public abstract void visitNode(TreeNode node);

    public abstract void visitLeaf(TreeLeaf leaf);

}

class SumInLeavesVisitor extends TreeVis {
    private int sum = 0;

    public int getResult() {
        return sum;
    }

    public void visitNode(TreeNode node) {
        return;
    }

    public void visitLeaf(TreeLeaf leaf) {
        sum += leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
    private long product = 1;

    public int getResult() {
        return (int) product;
    }

    public void visitNode(TreeNode node) {
        if (node.getColor() == Color.RED) {
            product = (product * node.getValue()) % 1000000007;
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
        if (leaf.getColor() == Color.RED) {
            product = (product * leaf.getValue()) % 1000000007;
        }
    }
}

class FancyVisitor extends TreeVis {
    private int sum1 = 0;
    private int sum2 = 0;

    public int getResult() {
        return java.lang.Math.abs(sum1 - sum2);
    }

    public void visitNode(TreeNode node) {
        if (node.getDepth() % 2 == 0) {
            sum1 += node.getValue();
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
        if (leaf.getColor() == Color.GREEN) {
            sum2 += leaf.getValue();
        }
    }
}

public class Solution {
    private static Map<Integer, Set<Integer>> edges = new HashMap<>();
    private static int[] values;
    private static Color[] colors;

    public static Tree solve() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        values = new int[n];
        colors = new Color[n];
        Tree[] tree = new Tree[n];
        Tree root = null;
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            colors[i] = Color.values()[scanner.nextInt()];
        }
        if (n == 1) {
            root = new TreeLeaf(values[0], colors[0], 0);
        } else {
            for (int i = 0; i < n - 1; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                addEdges(u, v);
            }
            TreeNode node = new TreeNode(values[0], colors[0], 0);
            root = node;
            Set<Integer> children = edges.get(1);
            for (int child : children) {
                edges.get(child).remove(1);
                node.addChild(addTree(child, root));
            }
        }
        scanner.close();
        return root;
    }

    private static Tree addTree(int u, Tree parent) {
        if (!edges.get(u).isEmpty()) {
            // node has children
            TreeNode node = new TreeNode(values[u - 1], colors[u - 1], parent.getDepth() + 1);
            Set<Integer> children = edges.get(u);
            for (int child: children) {
                // children
                edges.get(child).remove(u);
                node.addChild(addTree(child, node));
            }
            return node;
        } else {
            return new TreeLeaf(values[u - 1], colors[u - 1], parent.getDepth() + 1);
        }
    }

    private static void addEdges(int u, int v) {
        addEdge(u, v);
        addEdge(v, u);
    }

    private static void addEdge(int u, int v) {
        if (!edges.containsKey(u)) {
            edges.put(u, new HashSet<>());
        }
        edges.get(u).add(v);
    }


    public static void main(String[] args) {
        Tree root = solve();
        SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
        ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
        FancyVisitor vis3 = new FancyVisitor();

        root.accept(vis1);
        root.accept(vis2);
        root.accept(vis3);

        int res1 = vis1.getResult();
        int res2 = vis2.getResult();
        int res3 = vis3.getResult();

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }
}

/*
                if (tree[u - 1] != null) {
                    if (!(tree[u - 1] instanceof TreeNode)) {
                        tree[u - 1] = new TreeNode(tree[u - 1].getValue(), tree[u - 1].getColor(), tree[u - 1].getDepth());
                    }
                } else {
                    tree[u - 1] = new TreeNode(values[u - 1], colors[u - 1], 0);
                }
                tree[v - 1] = new TreeLeaf(values[v - 1], colors[v - 1], tree[u - 1].getDepth() + 1);
                if (edges.containsKey(u - 1)) {
                    edges.get(u - 1).add(v - 1);
                } else {
                    HashSet<Integer> set = new HashSet<>();
                    set.add(v - 1);
                    edges.put(u - 1, set);
                }

*/