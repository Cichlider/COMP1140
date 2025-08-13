import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*;

<T,U> U getTestApply(Supplier<T> s, Predicate<T> p,Function<T,U> fa,Function<T,U> fb){
    T objection = s.get();
    return (p.test(objection) ? fa.apply(objection) : fb.apply(objection));
}

void main(){
    Supplier<Integer> s1 = () -> 5;
    Predicate<Integer> p1 = x -> x == 5;
    Function<Integer, Integer> f1a = x -> x + 5;
    Function<Integer, Integer> f1b = x -> 0;
    println(getTestApply(s1,p1,f1a,f1b));
}

void test(){
    runAsTest(this::testExercise5);
}


void testExercise5() {
    Supplier<Integer> s1 = () -> 5;
    Predicate<Integer> p1 = x -> x == 5;
    Function<Integer, Integer> f1a = x -> x + 5;
    Function<Integer, Integer> f1b = x -> 0;
    testEqual(getTestApply(s1,p1,f1a,f1b), 10, "General case: Selection of lambdas");
    testEqual(getTestApply(s1,p1,f1b,f1a), 0, "General case: Selection of lambdas");

    Supplier<String> s2 = () -> "Hello world!";
    Predicate<String> p2 = x -> x == "Hello world!";
    Function<String, String> f2a = x -> x + "5";
    Function<String, String> f2b = x -> "";
    testEqual(getTestApply(s2,p2,f2a,f2b), "Hello world!5", "General case: Selection of lambdas");
    testEqual(getTestApply(s2,p2,f2b,f2a), "", "General case: Selection of lambdas");
}
