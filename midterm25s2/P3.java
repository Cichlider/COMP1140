import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

/**
 * 题目3：二叉树最深路径查找
 * 
 * 题目描述：
 * 给定一个二叉树，编写函数 findDeepestPath 找到从根节点到最深叶子节点的路径。
 * 如果有多条相同深度的路径，任意返回一条即可。
 * 
 * 函数签名：
 * ConsList<String> findDeepestPath(Binarytree<String> tree)
 * 
 * 测试用例：
 * 1. 单个叶子节点 A → 输出：["A"]
 * 2. 简单树结构：
 *        B
 *       / \
 *      A   C
 *    输出：["B", "A"] or ["B" , "C"]
 * 
 * 3. 不平衡树：
 *        A
 *       / \
 *      B   C
 *         / \
 *        D   E
 *    输出：["A", "C", "D"] or ["A", "C", "E"]
 */


/**
 * 二叉树的数据定义 
 */
sealed interface Binarytree<String> permits Node , Leaf {}
record Node<String>(Binarytree<String> left, String value, Binarytree<String> right) implements Binarytree<String>{}
record Leaf<String>(String value) implements Binarytree<String> {}

/**
 * 测试接口函数
 */
Binarytree<String> returnNode(Binarytree<String> left, String value, Binarytree<String> right){
    Binarytree<String> l = new Node<String>(left,value,right);
    return l;
}

Binarytree<String> returnLeaf(String value){
    Binarytree<String> l = new Leaf<String>(value);
    return l;
}

/**
 * 主要实现
 */
ConsList<String> findDeepestPath(Binarytree<String> tree){
    ConsList<String> emp =new Nil<String>();
    ConsList<String> result = helper(tree,emp);
    return result;
}

ConsList<String> helper(Binarytree<String> tree , ConsList<String> lst){
    return switch(tree){
        case Leaf<String>(var value) -> Append(lst,new Cons<String>(value,new Nil<String>()));
        case Node<String>(var left,var value,var right) ->
            (depth(left)>=depth(right))?
            helper(left,Append(lst,new Cons<String>(value,new Nil<String>()))):
            helper(right,Append(lst,new Cons<String>(value,new Nil<String>())));
    };
}

int depth(Binarytree<String> tree){
    return switch(tree){
        case Leaf<String>(var value) -> 1;
        case Node<String>(var left,var value,var right) ->
            1+Max(depth(left),depth(right));
    };
}

int Max(int a,int b){
    return (a>=b) ? a : b;
}




void main() {
    // 测试1: 单个叶子节点
    Binarytree<String> leaf = returnLeaf("A");
    ConsList<String> result1 = findDeepestPath(leaf);
    println(result1);

    // 测试2: 简单树 - 左子树更深
    //     B
    //    / \
    //   A   C
    Binarytree<String> tree2 = returnNode(
        returnLeaf("A"),
        "B", 
        returnLeaf("C")
    );
    ConsList<String> result2 = findDeepestPath(tree2);
    println(result2);
    
    // 测试3: 不平衡树 - 右子树更深
    //     A
    //    / \
    //   B   C
    //      / \
    //     D   E
    Binarytree<String> tree3 = returnNode(
        returnLeaf("B"),
        "A",
        returnNode(returnLeaf("D"), "C", returnLeaf("E"))
    );
    ConsList<String> result3 = findDeepestPath(tree3);
    println(result3);

    
    // 测试4: 复杂树
    //       A
    //      / \
    //     B   C
    //    /   / \
    //   D   E   F
    //          /
    //         G
    Binarytree<String> tree4 = returnNode(
        returnNode(returnLeaf("D"), "B", new Leaf<>("X")),
        "A",
        returnNode(
            returnLeaf("E"), 
            "C", 
            returnNode(returnLeaf("G"), "F", new Leaf<>("H"))
        )
    );
    ConsList<String> result4 = findDeepestPath(tree4);
    println(result4);
}