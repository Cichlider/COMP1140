import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

Maybe<Integer> safeDivide(int a, int b) {
    if (b == 0) {
        return new Nothing<Integer>();("Division by zero is not allowed");
    } else {
        return Maybe.just(a / b);
    }
}
