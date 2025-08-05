import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 
//java --enable-preview 
//java --enable-preview comp1110.testing.Test 
//NumRoots.java

int numRoots (double a, double b, double c){
    double d = b*b - (4*a*c);
    if (d>0){
        return 2;
    }else if (d==0){
        return 1;
    }else{
        return 0;
    }
}

void main(){
    println(numRoots (1,2,1));
}

void test() {
    runAsTest(this::testExercise1);
}

void testExercise1() {
    // The test cases for rockPaperScissors cover all 9 possible 
    // combinations 
    testEqual(1, numRoots(1, 2, 1));
    testEqual(0, numRoots(1, 0, 1));
    testEqual(2, numRoots(1, 5, 1));
    testEqual(2, numRoots(-1, -3, -1));
}



