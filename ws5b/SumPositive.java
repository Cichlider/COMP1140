import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

int sumPositive(int n){
    return switch(n){
        case 0 -> 0 ;
        default -> sumPositive(n-1) + n;
    };
}

void main() 
{
  testEqual(sumPositive(1),  1,  "Base case: Sum of the first positive integers");
  testEqual(sumPositive(2),  3,  "Recursive case: Sum of the first 2 positive integers is 3");
  testEqual(sumPositive(3),  6,  "Recursive case: Sum of the first 3 positive integers is 6");
  testEqual(sumPositive(5),  15, "Recursive case: Sum of the first 5 positive integers is 15");
  testEqual(sumPositive(10), 55, "Recursive case: Sum of the first 10 positive integers is 55");
}
