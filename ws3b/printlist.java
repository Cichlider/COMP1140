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




/**
 * 这是第一题
 * please check ws3a exercise "ConcatenateStrings"
 * 可以去查看一下 ws3a的练习题 “ConcatenateStrings”
 * totalLength() Give you a ConsList<String> strings
 * you need return sum of all strings
 */
String totalLength(ConsList<String>lst){
    return switch(lst){
        case Nil<String>() -> "";
        case Cons<String> (var elem,var rest) -> elem + totalLength(rest);
    };
}


/**
 * 这是第二题
 * Average() Give you a ConsList<Integer> numbers.
 * you need return average of all numbers (return floattype)
 */
int sum(ConsList<Integer>list){
    return switch(list){
        case Nil<Integer>()-> 0;
        case Cons<Integer>(var elem,var rest) -> elem+sum(rest);
    };
}

/**
 * 这是第三题
 * AllPair() Give two ConsList<lnteger>, 
 * return all Pairof these two lists 
 * (follow lexicographical order,means need to sort)
 * Example：
 *  -Given [11,5,7] ,[2,8]
 *      -Return [(5,2),(5,8),(7,2),(7,8),(11,2),(11,8)]
 */
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

ConsList<Pair<Integer,Integer>> AllPair(ConsList<Integer> list1, ConsList<Integer> list2){
    ConsList<Integer> list3 = Sort(list1);
    ConsList<Integer> list4 = Sort(list2);

    return combine(list3,list4);
}






float Average(ConsList<Integer>list){
    int value = sum(list);
    int len = Length(list);
    return((float) value/ (float)len);
}






void main(){
    int len = 2;
    ConsList<Integer> a = MakeList(11,5,7);
    ConsList<Integer> b = MakeList(2,8);
    ConsList<String> c = MakeList("helloworld","genius","I");
    println(Average(a));
    println(totalLength(c));
    // println(f(a,b));
}





