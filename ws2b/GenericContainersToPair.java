import comp1110.lib.*;
import comp1110.lib.Date;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*;

//GenericContainersToPair.java
//GenericContainersToPair.java

record Container<T>(T thing){}

<T, U> Pair<T,U> genericContainersToPair(Container<T> a , Container<U> b){
    return new Pair<T,U> (a.thing(),b.thing());
}

void main(){
    Container<Integer> c1a = new Container<Integer>(1);
    Container<Integer> c1b = new Container<Integer>(2);
    println(genericContainersToPair(c1a, c1b));
}

void test(){
    runAsTest(this:: testExercise4);
}


void testExercise4() {
    Container<Integer> c1a = new Container<Integer>(1);
    Container<Integer> c1b = new Container<Integer>(2);
    Pair<Integer, Integer> p1 = new Pair<Integer,Integer>(1,2);
    testEqual(genericContainersToPair(c1a,c1b), p1, "General case: Combining containers of the same type");

    Container<String> c2a = new Container<String>("abc");
    Container<String> c2b = new Container<String>("def");
    Pair<String, String> p2 = new Pair<String,String>("abc","def");
    testEqual(genericContainersToPair(c2a,c2b), p2, "General case: Combining containers of the same type");

    Container<Character> c3a = new Container<Character>('x');
    Container<Character> c3b = new Container<Character>('y');
    Pair<Character, Character> p3 = new Pair<Character,Character>('x','y');
    testEqual(genericContainersToPair(c3a,c3b), p3, "General case: Combining containers of the same type");

    Container<Maybe<Integer>> c4a = new Container<Maybe<Integer>>(new Nothing<Integer>());
    Container<Integer> c4b = new Container<Integer>(9);
    Pair<Maybe<Integer>, Integer> p4 = new Pair<Maybe<Integer>,Integer>(new Nothing<Integer>(),9);
    testEqual(genericContainersToPair(c4a,c4b), p4, "General case: Combining containers of a different type");

    Container<Boolean> c5a = new Container<Boolean>(true);
    Container<Pair<Boolean,Boolean>> c5b = new Container<Pair<Boolean,Boolean>>(new Pair<Boolean,Boolean>(true, true));
    Pair<Boolean, Pair<Boolean,Boolean>> p5 = new Pair<Boolean,Pair<Boolean,Boolean>>(true,new Pair<Boolean,Boolean>(true,true));
    testEqual(genericContainersToPair(c5a,c5b), p5, "General case: Combining containers of a different type");
}
