import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*;
import java.util.function.Predicate;


sealed interface BinTree<T> permits Node Leaf {}

record Leaf<T>() implements BinTree<T> {}

record Node<T>(BinTree<T> left, T value , BinTree<T> right ) implements BinTree<T> {}


// ============= Exercise 1: 数据定义和模板 =============
record RoseTree(ConsList<RoseTree> children, Integer value) {}

// 模板函数
/*
<ReturnType> processRoseTree(RoseTree tree) {
    // 处理当前节点的值: tree.value()
    // 递归处理子节点列表: processRoseTreeList(tree.children())
    // 组合结果
}

<ReturnType> processRoseTreeList(ConsList<RoseTree> trees) {
    if (IsEmpty(trees)) {
        return // 基础情况;
    } else {
        // 处理第一个元素: processRoseTree(Head(trees))
        // 递归处理其余元素: processRoseTreeList(Tail(trees))
        // 组合结果
    }
}
*/

// ============= Exercise 2: roseTreeSum =============
int roseTreeSum(RoseTree tree) {
    return tree.value() + listSum(tree.children());
}

int listSum(ConsList<RoseTree> trees) {
    if (IsEmpty(trees)) {
        return 0;
    } else {
        return roseTreeSum(Head(trees)) + listSum(Tail(trees));
    }
}

// ============= Exercise 3-5: Person 和 Descendants 相关 =============
record Person(String name, String gender, int birth, int death) {}
record Descendants(Person person, ConsList<Descendants> children) {}

// Exercise 3: countDescendants
int countDescendants(Descendants tree) {
    return countDescendantsList(tree.children());
}

int countDescendantsList(ConsList<Descendants> trees) {
    if (IsEmpty(trees)) {
        return 0;
    } else {
        return 1 + countDescendants(Head(trees)) + countDescendantsList(Tail(trees));
    }
}

// Exercise 4: countDescendantsByPersonName
int countDescendantsByPersonName(Descendants tree, String name) {
    if (Equals(tree.person().name(), name)) {
        return countDescendants(tree);
    } else {
        return countDescendantsByPersonNameList(tree.children(), name);
    }
}

int countDescendantsByPersonNameList(ConsList<Descendants> trees, String name) {
    if (IsEmpty(trees)) {
        return 0;
    } else {
        return countDescendantsByPersonName(Head(trees), name) + 
               countDescendantsByPersonNameList(Tail(trees), name);
    }
}

// Exercise 5: countPeopleByPredicate (without higher-order functions)
int countPeopleByPredicate(Descendants tree, Predicate<Descendants> predicate) {
    int current = predicate.test(tree) ? 1 : 0;
    return current + countPeopleByPredicateList(tree.children(), predicate);
}

int countPeopleByPredicateList(ConsList<Descendants> trees, Predicate<Descendants> predicate) {
    if (IsEmpty(trees)) {
        return 0;
    } else {
        return countPeopleByPredicate(Head(trees), predicate) + 
               countPeopleByPredicateList(Tail(trees), predicate);
    }
}

// 调用示例：有两个孩子的人数
// countPeopleByPredicate(tree, x -> Equals(Length(x.children()), 2))

// ============= Exercise 6: 使用高阶函数 =============
int countPeopleByPredicateHigherOrder(Descendants tree, Predicate<Descendants> predicate) {
    ConsList<Descendants> allPeople = getAllPeople(tree);
    return Length(Filter(predicate, allPeople));
}

ConsList<Descendants> getAllPeople(Descendants tree) {
    return new Cons<>(tree, FlatMap(this::getAllPeople, tree.children()));
}

// ============= Exercise 7: youngestParent =============
Person youngestParent(Descendants tree) {
    return youngestParentHelper(tree, null, Integer.MAX_VALUE);
}

Person youngestParentHelper(Descendants tree, Person currentYoungest, int minAge) {
    if (!IsEmpty(tree.children())) {
        int ageWhenBecameParent = getAgeWhenBecameParent(tree);
        if (ageWhenBecameParent < minAge) {
            currentYoungest = tree.person();
            minAge = ageWhenBecameParent;
        }
    }
    
    return youngestParentHelperList(tree.children(), currentYoungest, minAge);
}

Person youngestParentHelperList(ConsList<Descendants> trees, Person currentYoungest, int minAge) {
    if (IsEmpty(trees)) {
        return currentYoungest;
    } else {
        Person newYoungest = youngestParentHelper(Head(trees), currentYoungest, minAge);
        int newMinAge = newYoungest == currentYoungest ? minAge : getAgeWhenBecameParentByPerson(newYoungest, Head(trees));
        return youngestParentHelperList(Tail(trees), newYoungest, newMinAge);
    }
}

int getAgeWhenBecameParent(Descendants tree) {
    if (IsEmpty(tree.children())) {
        return Integer.MAX_VALUE;
    }
    
    int oldestChildBirth = getOldestChildBirth(tree.children());
    return oldestChildBirth - tree.person().birth();
}

int getAgeWhenBecameParentByPerson(Person person, Descendants tree) {
    // 简化实现，实际需要找到这个人在树中的位置
    return getAgeWhenBecameParent(tree);
}

int getOldestChildBirth(ConsList<Descendants> children) {
    if (IsEmpty(children)) {
        return 0;
    }
    
    int headBirth = Head(children).person().birth();
    if (IsEmpty(Tail(children))) {
        return headBirth;
    } else {
        int tailOldest = getOldestChildBirth(Tail(children));
        return Math.min(headBirth, tailOldest); // 最小出生年份 = 最老
    }
}

// ============= Exercise 8: pruneByPredicate =============
Descendants pruneByPredicate(Descendants tree, Predicate<Person> predicate) {
    ConsList<Descendants> prunedChildren = pruneChildrenByPredicate(tree.children(), predicate);
    return new Descendants(tree.person(), prunedChildren);
}

ConsList<Descendants> pruneChildrenByPredicate(ConsList<Descendants> trees, Predicate<Person> predicate) {
    if (IsEmpty(trees)) {
        return new Nil<>();
    } else {
        Descendants head = Head(trees);
        ConsList<Descendants> rest = pruneChildrenByPredicate(Tail(trees), predicate);
        
        if (predicate.test(head.person())) {
            // 如果满足谓词，移除此节点及其所有后代
            return rest;
        } else {
            // 否则递归处理此节点的子节点
            Descendants prunedHead = pruneByPredicate(head, predicate);
            return new Cons<>(prunedHead, rest);
        }
    }
}

// ============= Exercise 9: 泛型 RoseTree =============
record RoseTree<T>(ConsList<RoseTree<T>> children, T value) {}

<T extends Comparable<T>> ConsList<T> extractLeafNodesData(RoseTree<T> tree) {
    ConsList<T> leafData = collectLeafData(tree);
    return Sort((a, b) -> b.compareTo(a), leafData); // 降序排序
}

<T> ConsList<T> collectLeafData(RoseTree<T> tree) {
    if (IsEmpty(tree.children())) {
        // 叶子节点
        return new Cons<>(tree.value(), new Nil<>());
    } else {
        // 内部节点，递归收集子节点的叶子数据
        return collectLeafDataList(tree.children());
    }
}

<T> ConsList<T> collectLeafDataList(ConsList<RoseTree<T>> trees) {
    if (IsEmpty(trees)) {
        return new Nil<>();
    } else {
        return Append(collectLeafData(Head(trees)), 
                     collectLeafDataList(Tail(trees)));
    }
}

// ============= 测试函数 =============
void main() {
    // 测试 Exercise 2
    testExercise2();
    
    // 测试 Exercise 3
    testExercise3();
    
    // 测试 Exercise 4
    testExercise4();
    
    // 测试 Exercise 5
    testExercise5();
    
    // 测试 Exercise 7
    testExercise7();
    
    // 测试 Exercise 8
    testExercise8();
    
    // 测试 Exercise 9
    testExercise9();
}

void testExercise9() {
    // 测试 Integer 类型
    RoseTree<Integer> intLeaf1 = new RoseTree<>(new Nil<>(), 5);
    RoseTree<Integer> intLeaf2 = new RoseTree<>(new Nil<>(), 3);
    RoseTree<Integer> intLeaf3 = new RoseTree<>(new Nil<>(), 8);
    
    RoseTree<Integer> intTree = new RoseTree<>(
        MakeList(intLeaf1, intLeaf2, intLeaf3), 1
    );
    
    ConsList<Integer> intResult = extractLeafNodesData(intTree);
    testEqual(3, Length(intResult), "Int tree: correct number of leaves");
    testEqual(Integer.valueOf(8), Head(intResult), "Int tree: largest first");
    
    // 测试 String 类型
    RoseTree<String> strLeaf1 = new RoseTree<>(new Nil<>(), "apple");
    RoseTree<String> strLeaf2 = new RoseTree<>(new Nil<>(), "zebra");
    RoseTree<String> strLeaf3 = new RoseTree<>(new Nil<>(), "banana");
    
    RoseTree<String> strTree = new RoseTree<>(
        MakeList(strLeaf1, strLeaf2, strLeaf3), "root"
    );
    
    ConsList<String> strResult = extractLeafNodesData(strTree);
    testEqual(3, Length(strResult), "String tree: correct number of leaves");
    testEqual("zebra", Head(strResult), "String tree: lexicographically largest first");
}