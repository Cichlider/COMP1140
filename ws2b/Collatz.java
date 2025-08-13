import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 
/**
 * Given an input integer number, halves the number if the number
 * is even. If it is odd, then it multiplies the number by three
 * and adds one.
 * 
 * Examples:
 *   - given 4:
 *        expect 2 
 *   - given 5
 *        expect 16 
 * 
 * */
int collatz(int n, Predicate<Integer> isEven, Function<Integer,Integer> two , Function<Integer,Integer> three) {
    if (isEven.test(n)) {
        return two.apply(n);
    } else {
        return three.apply(n);
    }
}


void main(){
    println(collatz(5,n -> (n%2==0), n -> n/2 , n -> n*3+1));
}