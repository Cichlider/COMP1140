import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

//java --enable-preview 
//java --enable-preview comp1110.testing.Test 
//GPACalculator.java

enum Grade {
    /** Mark between 0 and 49 (both inclusive) */
    FAIL, 
    /** Mark between 50 and 59 (both inclusive) */
    PASS, 
    /** Mark between 60 and 69 (both inclusive) */
    CREDIT,
    /** Mark between 70 and 79 (both inclusive) */
    DISTINCTION, 
    /** Mark between 80 and 100 (both inclusive) */
    HD,
    // an error input
    ERROR;

}
Grade gpaCalculator (int score){
    if (score>=0 && score<=49){
        return Grade.FAIL;
    }else if (score>=50 && score<=59){
        return Grade.PASS;
    }else if (score>=60 && score<=69){
        return Grade.CREDIT;
    }else if (score>=70 && score<=79){
        return Grade.DISTINCTION;
    }else if (score>=80 && score<=100){
        return Grade.HD;
    }else{
        return Grade.ERROR;
    }
}

void main(){
    println(gpaCalculator(85));
}

void test() {
    runAsTest(this::testExercise9);
}

void testExercise9() {
    // The test cases for exercise 9 cover the boundary of every 
    // grade (e.g. 49/50 for fail/pass), the extremes of allowable
    // marks (e.g. 0 or 100), and a case in the middle of each grade 
    // range.
    testEqual(gpaCalculator(0),Grade.FAIL, "Edge case: 0 -> Fail");
    testEqual(gpaCalculator(49),Grade.FAIL, "Edge case: 49 -> Fail");
    testEqual(gpaCalculator(50),Grade.PASS, "Edge case: 50 -> Pass");
    testEqual(gpaCalculator(53),Grade.PASS, "General case: 53 -> Pass");
    testEqual(gpaCalculator(59),Grade.PASS, "Edge case: 59 -> Pass");
    testEqual(gpaCalculator(60),Grade.CREDIT, "Edge case: 60 -> Credit");
    testEqual(gpaCalculator(64),Grade.CREDIT, "General case: 64 -> Credit");
    testEqual(gpaCalculator(61),Grade.CREDIT, "General case: 61 -> Credit");
    testEqual(gpaCalculator(69),Grade.CREDIT, "Edge case: 69 -> Credit");
    testEqual(gpaCalculator(70),Grade.DISTINCTION, "Edge case: 70 -> Distinction");
    testEqual(gpaCalculator(77),Grade.DISTINCTION, "General case: 77 -> Distinction");
    testEqual(gpaCalculator(79),Grade.DISTINCTION, "Edge case: 79 -> Distinction");
    testEqual(gpaCalculator(80),Grade.HD, "Edge case: 80 -> HD");
    testEqual(gpaCalculator(85),Grade.HD, "General case: 85 -> HD");
    testEqual(gpaCalculator(100),Grade.HD, "Edge case: 100 -> HD");
}
