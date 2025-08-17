import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 


<T, U> ConsList<U> myMap (ConsList<T> lst, Function<T, U> f) {
    return switch(lst) {
        case Nil<T>() -> new Nil<U>();
        case Cons<T>(var element, var rest) -> new Cons<U> (f.apply(element), myMap(rest, f));
    };
}


int sumIntegerList (ConsList<Integer>lst){
    return switch(lst){
        case Nil<Integer>() -> 0;
        case Cons<Integer>(var elem,var rest) -> elem + sumIntegerList(rest); 
    };
}

ConsList<Integer> removeEvenIntegerList (ConsList<Integer>lst){
    return Filter(x -> x%2==1 , lst);
}

String concatenateStrings(ConsList<String> lst){
    return Fold((x,y)->x+y,"",lst);
}

int largestInteger(ConsList<Integer>lst){
    return Fold((x,y)->x>y?x:y,0,lst);
}

int evaluateFunctionComposition(Function<Integer,Integer> f , Function<Integer,Integer> g, int a){
    return (f.apply(g.apply(a)));
}


Function<Integer,Integer> composeFunctionsRightToLeft(ConsList <Function<Integer,Integer>> lst){
    return switch(lst){
        case Nil<Function<Integer,Integer>>() -> (x->x);
        case Cons<Function<Integer, Integer>>(var elem, var rest) ->
            x -> elem.apply(composeFunctionsLeftToRight(rest).apply(x));
    };
}



Function<Integer,Integer> composeFunctionsLeftToRight(ConsList <Function<Integer,Integer>> lst){
    return Fold ((f,g) -> (x->g.apply(f.apply(x))),x->x,lst);
}

void main(){
    Function<Integer, Integer> f1 = x -> x;
    Function<Integer, Integer> f2 = x -> x + 2;
    Function<Integer, Integer> f3 = x -> x * x;
    Function<Integer, Integer> f4 = x -> x - 5;

    // Becomes x -> x
    ConsList<Function<Integer,Integer>> functions1 = MakeList();

    // Becomes x -> x*x
    ConsList<Function<Integer,Integer>> functions2 = MakeList(f3);
    
    // Becomes x -> (x+2)^2
    ConsList<Function<Integer,Integer>> functions3 = MakeList(f2,f3);
        new Cons(f2, new Cons(f3, new Nil()));

    // Becomes x -> (x+2)^2 - 5
    ConsList<Function<Integer,Integer>> functions4 = MakeList(f1,f2,f3,f4);
    
    ConsList<Integer> numbers = MakeList(-5,-1,0,1,3,5);

    ConsList<Integer> result1 = numbers;
    ConsList<Integer> result2 = MakeList(25,1,0,1,9,25);
    ConsList<Integer> result3 = MakeList(9,1,4,9,25,49);
    ConsList<Integer> result4 = MakeList(4,-4,-1,4,20,44);

    testEqual(Map(composeFunctionsRightToLeft(functions1), numbers), result1, "Empty list");
    testEqual(Map(composeFunctionsRightToLeft(functions2), numbers), result2, "Singleton");
    testEqual(Map(composeFunctionsRightToLeft(functions3), numbers), result3, "Two functions");
    testEqual(Map(composeFunctionsRightToLeft(functions4), numbers), result4, "Four functions");

}

