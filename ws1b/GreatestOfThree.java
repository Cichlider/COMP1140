import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

int greatestOfThree(int a, int b, int c){
    if(a>=b && a>=c){
        return a;
    }else{
        if (b>=a &&b>=c){
            return b;
        }else{
            return c;
        }
    }
}

void main(){
    println(greatestOfThree(1,2,3));
}

void test() {
    runAsTest(this::testExercise7);
}

void testExercise7() {
    testEqual(greatestOfThree(0,0,0), 0, "Special: Every number is equal and zero");
    testEqual(greatestOfThree(-1,0,1), 1, "General: Contains a negative number");
    testEqual(greatestOfThree(-1,-2,-3), -1, "General: Contains only negative numbers");
    testEqual(greatestOfThree(1,3,2), 3, "General: Contains 3 non-equal numbers");
    testEqual(greatestOfThree(2,1,2), 2, "Special: Two numbers have the greatest value");
}
