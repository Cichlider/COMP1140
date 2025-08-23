import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

Boolean isPresent2DListIntegers(ConsList<ConsList<Integer>>lst, int a){
    return switch(lst) {
        case Nil<ConsList<Integer>>() -> false;
        case Cons<ConsList<Integer>>(var elem,var rest) -> 
            contain(elem,a) || isPresent2DListIntegers(rest,a);
    };
}

Boolean contain(ConsList<Integer>lst, int a ){
    return switch(lst){
        case Nil<Integer>() -> false;
        case Cons<Integer>(var elem,var rest) -> 
            (a==elem) || contain(rest,a);
    };
}

void main() {
    // [1]
    ConsList<Integer> justOne = MakeList(1);
    // [3,2]
    ConsList<Integer> twoElements = MakeList(3,2);
    // [1,2,3]
    ConsList<Integer> oneTwoThree = MakeList(1,2,3);
    // [4,5,6]
    ConsList<Integer> fourFiveSix = MakeList(4,5,6);
    // [7,8,9]
    ConsList<Integer> sevenEightNine = MakeList(7,8,9);
    
    // []
    ConsList<ConsList<Integer>> emptyList = new Nil<ConsList<Integer>>();
    // [[],[],[]]
    ConsList<ConsList<Integer>> emptySublists = MakeList(MakeList(),MakeList(),MakeList());
         
    // [[1,2,3]]
    ConsList<ConsList<Integer>> singleton = MakeList(MakeList(1,2,3));
    
    // [[1,2,3],[4,5,6],[7,8,9]]
    ConsList<ConsList<Integer>> multipleSublists = MakeList(oneTwoThree,fourFiveSix,sevenEightNine);

    // [[1,6],[],[891,91,33,1,3],[1],[1,6]]
    ConsList<ConsList<Integer>> disuniformList = 
         MakeList(MakeList(1,6),MakeList(),MakeList(891,91,33,1,3),MakeList(1,6));
        
    // [[1,2,3],[1,2,3],[1,2,3]]
    ConsList<ConsList<Integer>> repeatedList = MakeList(oneTwoThree,oneTwoThree,oneTwoThree);
    
    // [[1],[1],[1]]
    ConsList<ConsList<Integer>> oneElement = MakeList(justOne, justOne, justOne);


    testFalse(isPresent2DListIntegers(emptySublists, 2), "List of empty lists");

    testTrue(isPresent2DListIntegers(singleton, 1), "Single list (first element)");
    testTrue(isPresent2DListIntegers(singleton, 2), "Single list (middle element)");
    testTrue(isPresent2DListIntegers(singleton, 3), "Single list (last element)");
    testFalse(isPresent2DListIntegers(singleton, 4), "Single list (absent)");

    testTrue(isPresent2DListIntegers(multipleSublists, 1), "Multiple sublists (inside first list)");
    testTrue(isPresent2DListIntegers(multipleSublists, 5), "Multiple sublists (inside middle list)");
    testTrue(isPresent2DListIntegers(multipleSublists, 9), "Multiple sublists (inside last list)");
    testFalse(isPresent2DListIntegers(multipleSublists, 0), "Multiple sublists (absent)");

    testTrue(isPresent2DListIntegers(disuniformList, 1), "Disuniform list (present)");
    testTrue(isPresent2DListIntegers(disuniformList, 91), "Disuniform list (present)");
    testTrue(isPresent2DListIntegers(disuniformList, 6), "Disuniform list (present)");
    testFalse(isPresent2DListIntegers(disuniformList, 99), "Disuniform list (absent)");

    testTrue(isPresent2DListIntegers(repeatedList, 2), "Repeated list (present)");
    testFalse(isPresent2DListIntegers(repeatedList, -1), "Repeated list (absent)");

    testTrue(isPresent2DListIntegers(oneElement, 1), "One element repeated (present)");
    testFalse(isPresent2DListIntegers(oneElement, 0), "One element repeated (absent)");
}
