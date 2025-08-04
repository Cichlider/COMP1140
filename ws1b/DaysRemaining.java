import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

int daysRemaining(int day){
    return (32-day);
}

void main(){
    println(daysRemaining(1));
}

void test() {
    runAsTest(this::testExercise6);
}

void testExercise6() {
    testEqual(daysRemaining(1), 31, "Special case: First day of the month");
    testEqual(daysRemaining(31), 1, "Special case: Last day of the month");
    testEqual(daysRemaining(7), 25, "General case: Day in the middle of the month");
}
