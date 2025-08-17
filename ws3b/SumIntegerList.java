import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

int sumIntegerList (ConsList<Integer>lst){
    return Fold((x,y)->x+y,0,lst);
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

    // [-1,-2,3,0]
    ConsList<Integer> negativeAndZero = MakeList(-1,-2,3,0);

    testEqual(sumIntegerList(new Nil<Integer>()), 0, "Empty list doesn't do anything");
    testEqual(sumIntegerList(justOne), 1, "Singleton list");
    testEqual(sumIntegerList(twoElements), 5, "List with two elements");
    testEqual(sumIntegerList(oneTwoThree), 6, "Three elements, [1,2,3]");
    testEqual(sumIntegerList(fourFiveSix), 15, "Three elements, [4,5,6]");
    testEqual(sumIntegerList(sevenEightNine), 24, "Three elements, [7,8,9]");
    testEqual(sumIntegerList(MakeList(-1,-2,3)), 0, "Negative elements");

}
