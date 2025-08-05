import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

sealed interface Maybe<T> permits Nothing, Something {}
record Nothing<T>() implements Maybe<T> {}
record Something<T>(T elem) implements Maybe<T> {}


Maybe <Integer> safeDivide(int a, int b) {
    if (b == 0) {
        return new Nothing<Integer>(); 
    } else {
        return new Something<Integer>(a / b);
    }
}


void main(){
    println(safeDivide(3,1));
}