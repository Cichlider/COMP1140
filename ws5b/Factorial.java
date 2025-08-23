import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

int factorial(int a){
    return switch(a){
        case 0 -> 1;
        default -> a*factorial(a-1);
    };
}

void main()
{
  testEqual(factorial(1), 1   , "Base case: factorial of 1");
  testEqual(factorial(2), 2   , "Recursive case: factorial of 2");
  testEqual(factorial(4), 24  , "Recursive case: factorial of 4");  
  testEqual(factorial(6), 720 , "Recursive case: factorial of 6");
}
