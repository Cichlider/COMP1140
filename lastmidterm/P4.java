import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

/**
 * 这是符合题目要求的数据类型定义
 * 直接复制即可
 */

sealed interface BinTree<T> permits Leaf, Node{}

record Leaf<T>(T value) implements BinTree<T>{} // Leaf也有value！

record Node<T>(BinTree<T> left, T value, BinTree<T> right) implements BinTree<T> {}


/**
 * 这是 P4-1
 * Implement:
 *   BinTreeFold(BiFunction<T,T,T> agg, BinTree<T> tree)
 *
 * Example:
 *   agg = (a, b) -> a - b
 *
 *   Given tree:
 *
 *            n/a
 *           /   \
 *        n/a     n/a
 *       /  \    /   \
 *      1    2  3     4
 *
 *   Evaluation:
 *      ((1 - 2) - (3 - 4)) = 0
 *
 *   Expected return: 0
 */

<T> T BinTreeFold(BiFunction<T,T,T> agg, BinTree<T> tree) {
    return switch(tree) {
        case Leaf<T>(var value) -> value;
        case Node<T>(var left, var value, var right) -> 
            agg.apply(BinTreeFold(agg, left), BinTreeFold(agg, right));
    };
}



/**
 * 这是 P4-2
 * Implement:
 *   BinTreeMap(Function<T,T> mapper, BinTree<T> tree)
 *
 * Example:
 *   mapper = a -> Length(a)
 *
 *   Given tree:
 *
 *            n/a
 *           /   \
 *        n/a     n/a
 *       /  \    /   \
 *   "abc"  "x" "34"  ""
 *
 *   After mapping (string length):
 *
 *            n/a
 *           /   \
 *        n/a     n/a
 *       /  \    /   \
 *      3    1   2    0
 *
 *   Expected return: new mapped tree
 */

<T> BinTree<T> BinTreeMap(Function<T,T> mapper, BinTree<T> tree){
    return switch(tree){
        case Leaf<T>(var value) -> new Leaf<T>(mapper.apply(value));
        case Node<T>(var left, var value, var right) -> 
            new Node<T>(BinTreeMap(mapper, left), 
                       mapper.apply(value), 
                       BinTreeMap(mapper, right));
    };
}

void main(){

    //P4-1测试代码：
    BinTree<Integer> tree = new Node<>(
        new Node<>(new Leaf<>(1), 0, new Leaf<>(2)), 
        0,
        new Node<>(new Leaf<>(3), 0, new Leaf<>(4))
    );
    
    BiFunction<Integer, Integer, Integer> subtract = (a, b) -> a - b;
    println(BinTreeFold(subtract, tree)); 


    //P4-2测试代码：
    BinTree<String> stringTree = new Node<>(
        new Node<>(new Leaf<>("abc"), "dummy", new Leaf<>("x")),
        "dummy",
        new Node<>(new Leaf<>("34"), "dummy", new Leaf<>(""))
    );
    
    Function<String, String> lengthMapper = s -> String.valueOf(s.length());
    println(stringTree);
    println(BinTreeMap(lengthMapper, stringTree));
}


