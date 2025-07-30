import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*;






void test() {
  runAsTest(this::testExercise2a);
}

void testExercise2a() {
   testEqual(addIntegerNumbers(1,3), 4, "General case: Addition of positive numbers");
   testEqual(addIntegerNumbers(2,0), 2, "Special case: Addition of 0");
   testEqual(addIntegerNumbers(5,-2), 3, "Special case: Addition of a negative number");
   testEqual(addIntegerNumbers(-6, -3), -9, "Special case: Addition of two negative numbers");
}
Cichlider