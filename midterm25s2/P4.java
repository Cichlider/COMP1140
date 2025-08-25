import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

/**
 * 题目4：基于条件的二叉树左右子树交换
 * 
 * 题目描述：
 * 编写一个函数 swapOnCondition，接收一个泛型二叉树和一个 Predicate<T>。
 * 遍历二叉树，对于每个内部节点（Node），如果该节点的值满足 predicate.test() 返回 true，
 * 则交换该节点的左右子树的位置（只是交换位置，不是连同子节点一起交换）。
 * 
 * 函数签名：
 * <T> BinaryTree<T> swapOnCondition(BinaryTree<T> tree, Predicate<T> predicate)
 * 
 * 测试用例：
 * 1. 对于字符串树，交换值为 "A" 的节点的左右子树：
 *    原树：     A          交换后：    A
 *             / \                   / \
 *            B   C                 C   B
 *    
 * 2. 对于整数树，交换偶数值节点的左右子树：
 *    原树：     2          交换后：    2
 *             / \                   / \
 *            1   4                 4   1
 *               / \                   / \
 *              3   5                 5   3
 * 
 * 3. 多层交换示例：
 *    如果多个节点都满足条件，则每个满足条件的节点都会交换其左右子树位置
 */


/**
 * 这是符合题目要求的数据类型定义
 * 直接复制即可
 * 但请注意，考试的时候是让你手搓的，而且还要求你自行编写
 * 测试接口函数，因此，你必须对如何使用该数据类型十分熟悉
 */

sealed interface BinTree<T> permits Leaf, Node{}

record Leaf<T>(T value) implements BinTree<T>{}

record Node<T>(BinTree<T> left, T value, BinTree<T> right) implements BinTree<T> {}

import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*;

/**
 * 题目4：基于条件的二叉树左右子树交换
 * 
 * 题目描述：
 * 编写一个函数 swapOnCondition，接收一个泛型二叉树和一个判断函数（Function<T, Boolean>）。
 * 遍历二叉树，对于每个内部节点（Node），如果该节点的值满足判断函数返回 true，
 * 则交换该节点的左右子树的位置。
 * 
 * 二叉树定义：
 * sealed interface BinaryTree<T> permits Node, Leaf {}
 * record Node<T>(BinaryTree<T> left, T value, BinaryTree<T> right) implements BinaryTree<T>{}
 * record Leaf<T>(T value) implements BinaryTree<T> {}
 * 
 * 要求：
 * - 使用泛型实现，支持任意类型 T
 * - 只有内部节点（Node）才能进行交换操作
 * - 叶子节点（Leaf）不受影响，直接返回原节点
 * - 交换是指：将左子树放到右边，右子树放到左边
 * - 需要递归处理所有子树
 * - 使用 Function<T, Boolean> 进行条件判断
 * 
 * 函数签名：
 * <T> BinaryTree<T> swapOnCondition(BinaryTree<T> tree, Function<T, Boolean> predicate)
 * 
 * 测试用例：
 * 1. 对于字符串树，交换值为 "A" 的节点的左右子树：
 *    原树：     A          交换后：    A
 *             / \                   / \
 *            B   C                 C   B
 *    
 * 2. 对于整数树，交换偶数值节点的左右子树：
 *    原树：     2          交换后：    2
 *             / \                   / \
 *            1   4                 4   1
 *               / \                   / \
 *              3   5                 5   3
 * 
 * 3. 多层交换示例：
 *    如果多个节点都满足条件，则每个满足条件的节点都会交换其左右子树位置
 */

/**
 * 二叉树的数据定义 
 */
sealed interface BinaryTree<T> permits Node, Leaf {}
record Node<T>(BinaryTree<T> left, T value, BinaryTree<T> right) implements BinaryTree<T>{}
record Leaf<T>(T value) implements BinaryTree<T> {}

/**
 * 测试辅助函数：创建节点和叶子
 */
<T> BinaryTree<T> createNode(BinaryTree<T> left, T value, BinaryTree<T> right) {
    return new Node<>(left, value, right);
}

<T> BinaryTree<T> createLeaf(T value) {
    return new Leaf<>(value);
}

/**
 * 主要实现：基于条件交换二叉树节点的左右子树
 * @param tree 要处理的二叉树
 * @param predicate 判断函数，接受类型T的值，返回Boolean
 * @return 处理后的二叉树
 */
<T> BinaryTree<T> swapOnCondition(BinaryTree<T> tree, Function<T, Boolean> predicate) {
    return switch(tree) {
        // 叶子节点：直接返回，不需要交换
        case Leaf<T>(var value) -> tree;
        
        // 内部节点：检查是否满足条件，决定是否交换左右子树
        case Node<T>(var left, var value, var right) -> {
            // 递归处理左右子树
            BinaryTree<T> processedLeft = swapOnCondition(left, predicate);
            BinaryTree<T> processedRight = swapOnCondition(right, predicate);
            
            // 如果当前节点满足条件，交换左右子树
            if (predicate.apply(value)) {
                yield new Node<>(processedRight, value, processedLeft); // 交换左右
            } else {
                yield new Node<>(processedLeft, value, processedRight); // 保持原序
            }
        }
    };
}

/**
 * 辅助函数：打印二叉树（用于测试验证）
 */
<T> void printTree(BinaryTree<T> tree, String prefix, boolean isLast) {
    if (tree != null) {
        println(prefix + (isLast ? "└── " : "├── ") + 
                switch(tree) {
                    case Leaf<T>(var value) -> "Leaf(" + value + ")";
                    case Node<T>(var left, var value, var right) -> "Node(" + value + ")";
                });
        
        if (tree instanceof Node<T>(var left, var value, var right)) {
            String newPrefix = prefix + (isLast ? "    " : "│   ");
            printTree(left, newPrefix, false);
            printTree(right, newPrefix, true);
        }
    }
}

<T> void printTree(BinaryTree<T> tree) {
    printTree(tree, "", true);
}

void main() {
    // 测试1: 字符串树，交换值为 "A" 的节点
    println("=== 测试1：字符串树，交换值为'A'的节点 ===");
    BinaryTree<String> stringTree = createNode(
        createLeaf("B"),
        "A",
        createLeaf("C")
    );
    
    println("原树：");
    printTree(stringTree);
    
    // 创建判断函数：检查是否等于"A"
    Function<String, Boolean> isA = s -> Equals(s, "A");
    BinaryTree<String> swappedStringTree = swapOnCondition(stringTree, isA);
    println("\n交换后：");
    printTree(swappedStringTree);
    
    // 测试2: 整数树，交换偶数值的节点
    println("\n=== 测试2：整数树，交换偶数值节点 ===");
    BinaryTree<Integer> intTree = createNode(
        createLeaf(1),
        2,
        createNode(
            createLeaf(3),
            4,
            createLeaf(5)
        )
    );
    
    println("原树：");
    printTree(intTree);
    
    // 创建判断函数：检查是否为偶数
    Function<Integer, Boolean> isEven = n -> Equals(n % 2, 0);
    BinaryTree<Integer> swappedIntTree = swapOnCondition(intTree, isEven);
    println("\n交换后：");
    printTree(swappedIntTree);
    
    // 测试3: 复杂树，多个节点满足条件
    println("\n=== 测试3：复杂树，交换所有大于5的节点 ===");
    BinaryTree<Integer> complexTree = createNode(
        createNode(
            createLeaf(3),
            6,
            createLeaf(4)
        ),
        10,
        createNode(
            createLeaf(2),
            8,
            createLeaf(1)
        )
    );
    
    println("原树：");
    printTree(complexTree);
    
    // 创建判断函数：检查是否大于5
    Function<Integer, Boolean> greaterThan5 = n -> GreaterThan(n, 5);
    BinaryTree<Integer> swappedComplexTree = swapOnCondition(complexTree, greaterThan5);
    println("\n交换后：");
    printTree(swappedComplexTree);
}