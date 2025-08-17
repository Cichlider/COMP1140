import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 


int evaluateFunctionComposition(Function<Integer,Integer> f , Function<Integer,Integer> g, int a){
    return (f.apply(g.apply(a)));
}

void main(){
    Function<Integer, Integer> f1 = x -> x;
    Function<Integer, Integer> f2 = x -> x + 2;
    Function<Integer, Integer> f3 = x -> x / 3;
    Function<Integer, Integer> f4 = x -> x * x;
    Function<Integer, Integer> f5 = x -> x - 5;
    Function<Integer, Integer> f6 = y -> y*y;
    Function<Integer, Integer> f7 = z -> 2-z;

    testEqual(evaluateFunctionComposition(f1, f1, 0), 0, "Identity and identity");
    testEqual(evaluateFunctionComposition(f2, f1, 1), 3, "Identify and other function");
    testEqual(evaluateFunctionComposition(f1, f3, -3), -1, "Other function and identity");
    testEqual(evaluateFunctionComposition(f2, f2, -1), 3, "f2(f2(-1)) = 3");
    testEqual(evaluateFunctionComposition(f3, f2, 0), 0, "f3(f2(0)) = 0");
    testEqual(evaluateFunctionComposition(f4, f5, 2), 9, "f4(f5(2)) = 9");
    testEqual(evaluateFunctionComposition(f5, f4, 5), 20, "f5(f4(5)) = 20");
    testEqual(evaluateFunctionComposition(f2, f3, 10), 5, "f2(f3(10)) = 6");
    testEqual(evaluateFunctionComposition(f3, f5, -10), -5, "f3(f5(-10)) = -5");
    testEqual(evaluateFunctionComposition(f4, f4, 20), 160000, "f4(f4(20)) = 8000");
    testEqual(evaluateFunctionComposition(f6, f7, 10), 64, "f6(f7(10)) = 64");

}