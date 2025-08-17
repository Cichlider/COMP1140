import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 


ConsList<Integer> removeEvenIntegerList (ConsList<Integer>lst){
    return Filter(x -> x%2!=0  , lst);
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

    ConsList<Integer> result1 = MakeList(1);
    ConsList<Integer> result2 = MakeList(3);
    ConsList<Integer> result3 = MakeList(1,3);
    ConsList<Integer> result4 = MakeList(5);
    ConsList<Integer> result5 = MakeList(7,9);
    ConsList<Integer> result6 = MakeList(-1,3);

    testEqual(new Nil<Integer>(), new Nil<Integer>(), "Empty list doesn't do anything");
    testEqual(removeEvenIntegerList(justOne), result1, "Singleton list");
    testEqual(removeEvenIntegerList(twoElements), result2, "List with two elements");
    testEqual(removeEvenIntegerList(oneTwoThree), result3, "Three elements, [1,2,3]");
    testEqual(removeEvenIntegerList(fourFiveSix), result4, "Three elements, [4,5,6]");
    testEqual(removeEvenIntegerList(sevenEightNine), result5, "Three elements, [7,8,9]");
    testEqual(removeEvenIntegerList(MakeList(-1,-2,3,0)), result6, "Negative elements and zero");

}
