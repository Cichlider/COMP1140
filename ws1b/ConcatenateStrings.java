import comp1110.lib.*;
// This import grants access to the functional Java standard library functions.
import static comp1110.lib.Functions.*;
// This import is necessary for the tests below to compile and run
import static comp1110.testing.Comp1110Unit.*;


String concatenateStrings( String str1, String str2) {
    // Concatenate two strings and return the result
    return str1 + str2;
}

void main (String... args) {
    String result = concatenateStrings (args[0],args[1]);
    println(result); 
}

void test() {
    runAsTest(this::testExercise2b);
}

void testExercise2b() {
    testEqual(concatenateStrings("",""), "", "Special case: Concatenation of two empty strings");
    testEqual(concatenateStrings("ab",""), "ab", "Special case: Argument 2 is empty");
    testEqual(concatenateStrings("","abc"), "abc", "Special case: Argument 1 is empty");
    testEqual(concatenateStrings("abc","defg"), "abcdefg", "General case: Concatenation of two strings");
}
