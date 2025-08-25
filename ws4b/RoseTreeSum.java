import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 


record RoseTree(ConsList<RoseTree> children, int value) {}

int roseTreeSum(RoseTree tree) {
    return tree.value() + Sum(Map((x -> roseTreeSum(x)), tree.children()));
}

int Sum(ConsList<Integer>lst){
    return switch(lst){
        case Nil<Integer>() -> 0;
        case Cons<Integer>(var elem , var rest) -> elem + Sum(rest);
    };
}

void main() {
    // Tree0: single node
    // 7
    RoseTree tree0 = new RoseTree(new Nil<RoseTree>(), 7);
    testEqual(7, roseTreeSum(tree0), "roseSum for tree with no children");

    // Tree1:
    //    5
    //   / \
    //  3   2
    RoseTree child1 = new RoseTree(new Nil<RoseTree>(), 3);
    RoseTree child2 = new RoseTree(new Nil<RoseTree>(), 2);
    RoseTree tree1 = new RoseTree(MakeList(child1, child2), 5);
    testEqual(10, roseTreeSum(tree1), "roseSum for tree with children");

    // Tree2:
    //       1
    //      / \
    //     3   7
    //    / \   \
    //   4   6   8
    RoseTree grand1 = new RoseTree(new Nil<RoseTree>(), 4);
    RoseTree grand2 = new RoseTree(new Nil<RoseTree>(), 6);
    RoseTree child3 = new RoseTree(MakeList(grand1, grand2), 3);
    RoseTree grand3 = new RoseTree(new Nil<RoseTree>(), 8);
    RoseTree child4 = new RoseTree(MakeList(grand3), 7);
    RoseTree tree2 = new RoseTree(MakeList(child3, child4), 1);
    testEqual(29, roseTreeSum(tree2), "roseSum for tree where all children have children");

    // Tree3:
    //    2
    //   / \
    // 10   3
    //     / \
    //    4   6
    RoseTree child5 = new RoseTree(new Nil<RoseTree>(), 10);
    RoseTree tree3 = new RoseTree(MakeList(child5, child3), 2);
    testEqual(25, roseTreeSum(tree3), "roseSum for mixed-child tree");

    // Tree4:
    // 1
    // |
    // 2
    // |
    // 3
    // |
    // 4
    // |
    // 5
    RoseTree gc4 = new RoseTree(new Nil<RoseTree>(), 5);
    RoseTree gc3 = new RoseTree(MakeList(gc4), 4);
    RoseTree gc2 = new RoseTree(MakeList(gc3), 3);
    RoseTree gc1 = new RoseTree(MakeList(gc2), 2);
    RoseTree tree4 = new RoseTree(MakeList(gc1), 1);
    testEqual(15, roseTreeSum(tree4), "roseSum for depth-4 tree");

    // Tree5: root 10 with five children [1, 2, 3, 4, 5]
    //        10
    //   /  / | \  \
    //  1  2  3  4  5
    RoseTree c1 = new RoseTree(new Nil<RoseTree>(), 1);
    RoseTree c2 = new RoseTree(new Nil<RoseTree>(), 2);
    RoseTree c3 = new RoseTree(new Nil<RoseTree>(), 3);
    RoseTree c4 = new RoseTree(new Nil<RoseTree>(), 4);
    RoseTree c5 = new RoseTree(new Nil<RoseTree>(), 5);
    RoseTree tree5 = new RoseTree(MakeList(c1,c2,c3,c4,c5),10);
    testEqual(25, roseTreeSum(tree5), "roseSum for node with five children");
}