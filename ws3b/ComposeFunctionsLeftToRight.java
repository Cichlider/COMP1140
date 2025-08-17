import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 


Function<Integer,Integer> composeFunctionsLeftToRight(ConsList <Function<Integer,Integer>> lst){
    return switch(lst){
        case Nil<Function<Integer,Integer>>() -> (x->x);
        case Cons<Function<Integer, Integer>>(var elem, var rest) ->
            x -> elem.apply(composeFunctionsLeftToRight(rest).apply(x));
    };
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
    
    // Becomes x -> x^2 + 2
    ConsList<Function<Integer,Integer>> functions3 = MakeList(f2,f3);

    // Becomes (x-5)^2 + 2
    ConsList<Function<Integer,Integer>> functions4 = MakeList(f1,f2,f3,f4);
    
    ConsList<Integer> numbers = MakeList(-5,-1,0,1,3,5);

    ConsList<Integer> result1 = numbers;
    ConsList<Integer> result2 = MakeList(25,1,0,1,9,25);
    ConsList<Integer> result3 = MakeList(27,3,2,3,11,27);
    ConsList<Integer> result4 = MakeList(102,38,27,18,6,2);

    testEqual(Map(composeFunctionsLeftToRight(functions1), numbers), result1, "Empty list");
    testEqual(Map(composeFunctionsLeftToRight(functions2), numbers), result2, "Singleton");
    testEqual(Map(composeFunctionsLeftToRight(functions3), numbers), result3, "Two functions");
    testEqual(Map(composeFunctionsLeftToRight(functions4), numbers), result4, "Four functions");

}
