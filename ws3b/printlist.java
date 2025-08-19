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

// ConsList<Pair<Integer,Integer>> combine(ConsList<Integer> list1,ConsList<Integer> list2){
//     ConsList<Integer> list3 = Sort(list1);
//     ConsList<Integer> list4 = Sort(list2);
//     int len1 = Length(list4);
//     int len2 = Length(list3);
//     ConsList<Integer> list5 = concreate(Map(x->BuildList(len1,x),list3));
//     ConsList<Pair<Integer,Integer>> list6 = Zip(list5,list2);
//     return list6;
// }

// ConsList<Integer> 


// ConsList<Integer> concreate(ConsList<ConsList<Integer>>lst){
//     return switch(lst){
//         case Nil<ConsList<Integer>>() -> new Nil <Integer> ();
//         case Cons<ConsList<Integer>>(var elem ,var rest) -> Append(elem,concreate(rest));
//     };
// }


ConsList<Pair<Integer,Integer>> combine(ConsList<Integer> list1, ConsList<Integer> list2) {
    return switch(list1) {
        case Nil<Integer>() -> new Nil<Pair<Integer,Integer>>();
        case Cons<Integer>(var elem, var rest) -> 
            Append(
                Map(x -> new Pair<>(elem, x), list2),
                combine(rest, list2)
            );
    };
}

ConsList<Pair<Integer,Integer>> f(ConsList<Integer> list1, ConsList<Integer> list2){
    ConsList<Integer> list3 = Sort(list1);
    ConsList<Integer> list4 = Sort(list2);

    return combine(list3,list4);
}


void main(){
    int len = 2;
    ConsList<Integer> a = MakeList(11,5,7);
    ConsList<Integer> b = MakeList(2,8);
    println(f(a,b));
}





