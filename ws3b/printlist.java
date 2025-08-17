import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 


<T, U> ConsList<U> myMap (ConsList<T> lst, Function<T, U> f) {
    return switch(lst) {
        case Nil<T>() -> new Nil<U>();
        case Cons<T>(var element, var rest) -> new Cons<U> (f.apply(element), myMap(rest, f));
    };
}


void main() {
    ConsList<Integer> a = MakeList(1, 2, 3);
    println(myMap(a, x -> x*x));
}

