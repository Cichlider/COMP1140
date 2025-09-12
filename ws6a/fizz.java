import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*;

String fizzWizzBuzzRizz(int x){
    String s = "";
    if (x % 3 == 0 && (s == "")){
        s += "Fizz ";
    }

    if (x % 5 == 0 && (s == "")){
        s += "Wizz ";
    }else if (x % 5 == 0){
        s += "wizz ";
    }

    if (x % 7 == 0 && (s == "")){
        s += "Buzz ";
    }else if (x % 7 == 0){
        s += "buzz ";
    }

    if (x % 11 == 0 && (s == "")){
        s += "Rizz ";
    }else if (x % 11 == 0){
        s += "rizz ";
    }
    
    s = Trim(s);

    if (s!=""){
        s += "!";
    }
    
    return s;
}

void main() {
    // Single-word cases
    String expected1 = "Fizz!";
    String actual1 = fizzWizzBuzzRizz(3);
    testEqual(expected1, actual1, "Divisible by 3 only -> Fizz!");

    String expected2 = "Wizz!";
    String actual2 = fizzWizzBuzzRizz(5);
    testEqual(expected2, actual2, "Divisible by 5 only -> Wizz!");

    String expected3 = "Buzz!";
    String actual3 = fizzWizzBuzzRizz(7);
    testEqual(expected3, actual3, "Divisible by 7 only -> Buzz!");

    String expected4 = "Rizz!";
    String actual4 = fizzWizzBuzzRizz(11);
    testEqual(expected4, actual4, "Divisible by 11 only -> Rizz!");

    // No word
    String expected5 = "";
    String actual5 = fizzWizzBuzzRizz(13);
    testEqual(expected5, actual5, "Divisible by none -> empty string");

    // All of the words (3 * 5 * 7 * 11 = 1155)
    String expected6 = "Fizz wizz buzz rizz!";
    String actual6 = fizzWizzBuzzRizz(1155);
    testEqual(expected6, actual6, "Divisible by 3,5,7,11 -> all words");

    // Some but not all
    String expected7 = "Fizz wizz!";
    String actual7 = fizzWizzBuzzRizz(15);
    testEqual(expected7, actual7, "Divisible by 3 & 5 -> Fizz wizz!");

    // Some but not all
    String expected8 = "Wizz rizz!";
    String actual8 = fizzWizzBuzzRizz(55);
    testEqual(expected8, actual8, "Divisible by 5 & 11 -> Wizz rizz!");
}

