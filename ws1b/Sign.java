import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

int sign(int a){
    if (a>=0){
        return a;
    }else{
        return (-a);
    }
}

void main(){
    println(sign(0));
    println(sign(-1));
    println(sign(1));
    println(sign(-5));
}

void test() {
    runAsTest(this::testExercise5);
}

void testExercise5() {
    testEqual(sign(0), 0, "Special case: Absolute value of 0");
    testEqual(sign(5), 5, "General case: Absolute value of a positive number");
    testEqual(sign(-2), 2, "General case: Absolute value of a negative number");
}
