import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 


sealed interface Tree<T> permits Leaf, Node{}

record Leaf<T>() implements Tree<T>{}

record Node<T>(Tree<T> left, T value, Tree<T> right) implements Tree<T> {}


/**
 * A filter function for your binary tree.
 * The predicate evaluates the value of each inner node. If the predicate
 * returns true, then the inner node stays an inner node. If the predicate
 * returns false, then the inner node is turned into a leaf containing
 * the same value, and its children are removed from the tree.
 * For example, for the tree:
 *               4
 *              / \
 *             3   6
 *            / \  | \
 *           1  2  3  4
 * and the predicate: x -> x % 2 == 0
 * The result should be:
 *             4
 *            / \
 *           3   6
 *               | \
 *               3  4
 */

<T> Tree<T> treeFilter(Predicate<T> predicate, Tree<T> tree){
    return switch(tree){
        case Leaf<T>() -> new Leaf<T>();
        case Node<T>(var left, var value , var right) ->
            (predicate.test(value))
            ? new Node<>(treeFilter(predicate, left), value , treeFilter(predicate, right)) 
            : new Node<>(new Leaf<>(), value, new Leaf<>());
    };
}

void main(){
    Tree<Integer> tree =
            new Node<>(
                new Node<>(new Node<>(new Leaf<>(), 1, new Leaf<>()),
                           3,
                           new Node<>(new Leaf<>(), 2, new Leaf<>())),
                4,
                new Node<>(new Node<>(new Leaf<>(), 3, new Leaf<>()),
                           6,
                           new Node<>(new Leaf<>(), 4, new Leaf<>()))
            );

    Predicate<Integer> evenPredicate = x -> x % 2 == 0;
    println(treeFilter(evenPredicate,tree));
}