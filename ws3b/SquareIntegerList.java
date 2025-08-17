import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 


<T, U> ConsList<U> myMap (ConsList<T> lst, Function<T, U> f) {
    return switch(lst) {
        case Nil<T>() -> new Nil<U>();
        case Cons<T>(var element, var rest) -> new Cons<U> (f.apply(element), myMap(rest, f));
    };
}

ConsList<Integer> squareIntegerList(ConsList<Integer>lst){
    return myMap(lst,x->x*x);
}

void main(){
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

    ConsList<Integer> result1 = MakeList(1);
    ConsList<Integer> result2 = MakeList(9,4);
    ConsList<Integer> result3 = MakeList(1,4,9);
    ConsList<Integer> result4 = MakeList(16,25,36);
    ConsList<Integer> result5 = MakeList(49,64,81);
    ConsList<Integer> result6 = MakeList(1,4,9,0);

    
    testEqual(new Nil(), new Nil(), "Empty list doesn't do anything");
    testEqual(squareIntegerList(justOne), result1, "Singleton list");
    testEqual(squareIntegerList(twoElements), result2, "List with two elements");
    testEqual(squareIntegerList(oneTwoThree), result3, "Three elements, [1,2,3]");
    testEqual(squareIntegerList(fourFiveSix), result4, "Three elements, [4,5,6]");
    testEqual(squareIntegerList(sevenEightNine), result5, "Three elements, [7,8,9]");
    testEqual(squareIntegerList(negativeAndZero), result6, "Negative elements and zero");

}