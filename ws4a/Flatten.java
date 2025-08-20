import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

/**
 * A recursive data-type representing a binary tree.
 * 
 * A `BinaryTree` may be either:
 * - a `Leaf`, representing an empty tree
 * - or a `Node`, containing a value and two subtrees (left and right).
 * 
 * Examples:
 * - Leaf() [an empty tree]
 * - Node(Leaf(), 5, Leaf()) [a single node with value 5]
 * - Node(Node(Leaf(), 1, Leaf()), 3, Node(Leaf(), 4, Leaf())) [root 3, left child 1, right child 4]
 *
 */
sealed interface BinaryTree permits Leaf, Node {}
/**
 * ... switch(x) {
 *      case Leaf() -> ... 
 *      case Node(var l, var v, var r) -> 
 *          ...[left recursive call] ... [right recursive call] ...)
 * } ... ;
 */

/**
 * Represents an empty subtree in a `BinaryTree`.
 * 
 * Used as the base case in recursive tree operations.
 * 
 * Example:
 * - Leaf() [an empty binary tree]
 */
record Leaf() implements BinaryTree {}

/**
 * Represents a non-empty subtree in a `BinaryTree`, containing a value and two subtrees.
 * 
 * Example:
 * - Node(Leaf(), 7, Leaf()) [a single-node tree with value 7]
 * 
 * @param left  The left subtree (may be Leaf or Node)
 * @param value The value stored at this node
 * @param right The right subtree (may be Leaf or Node)
 */
record Node(BinaryTree left, int value, BinaryTree right) implements BinaryTree {}


// For diagrams denoting binary trees, `x` will indicate a leaf.

// x
BinaryTree leaf = new Leaf();

//   1
//  / \
// x   x
BinaryTree basicTree1 = new Node(leaf, 1, leaf);

//   5
//  / \
// x   x
BinaryTree basicTree2 = new Node(leaf, 5, leaf);

//     4
//    / \
//   5   x
//  / \
// x   x
BinaryTree mediumTree1 = new Node(basicTree2, 4, leaf);

//      2
//     / \
//    /   \
//   1     1
//  / \   / \
// x   x x   x
BinaryTree mediumTree2 = new Node(basicTree1, 2, basicTree1);

//
//               3
//              / \
//             /   \
//            /     \
//           /       \
//          /         \
//         /           \
//        2             4
//       / \           / \
//      /   \         /   \ 
//     1     1       5     x
//    / \   / \     / \ 
//   x   x x   x   x   x
BinaryTree complexTree = new Node(mediumTree2, 3, mediumTree1);

Boolean isPresent(BinaryTree tree, int a){
    return switch(tree){
        case Leaf() -> false;
        case Node(BinaryTree left, int value, BinaryTree right) -> 
                (value == a) || isPresent(left,a) || isPresent(right,a);
    };
}

void main(){
    testEqual(false, isPresent(leaf, 1), "leaf does not contain 1");

    testEqual(true, isPresent(basicTree1, 1), "basicTree1 contains 1");
    testEqual(false, isPresent(basicTree1, 2), "basicTree1 does not contain 2");

    testEqual(true, isPresent(basicTree2, 5), "basicTree2 contains 5");
    testEqual(false, isPresent(basicTree2, 3), "basicTree2 does not contain 3");

    testEqual(true, isPresent(mediumTree1, 4), "mediumTree1 contains 4");
    testEqual(true, isPresent(mediumTree1, 5), "mediumTree1 contains 5");
    testEqual(false, isPresent(mediumTree1, 1), "mediumTree1 does not contain 1");

    testEqual(true, isPresent(mediumTree2, 2), "mediumTree2 contains 2");
    testEqual(true, isPresent(mediumTree2, 1), "mediumTree2 contains 1");
    testEqual(false, isPresent(mediumTree2, 5), "mediumTree2 does not contain 5");

    testEqual(true, isPresent(complexTree, 3), "complexTree contains 3");
    testEqual(true, isPresent(complexTree, 5), "complexTree contains 5");
    testEqual(true, isPresent(complexTree, 1), "complexTree contains 1");
    testEqual(false, isPresent(complexTree, 99), "complexTree does not contain 99");

}