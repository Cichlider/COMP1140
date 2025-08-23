import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

String symmetricSequence(int n){
    return helperleft(n) + helperright(n,1);
}

String helperleft(int n){
    return switch(n){
        case 1 -> ToString(1);
        default -> ToString(n) + " " + helperleft(n-1);
    };
}

String helperright(int n, int a){
    return switch(n){
        case 0 -> "";
        default -> " " + ToString(a) + helperright(n-1,a+1);
    };
}

void main() 
{
  testEqual(symmetricSequence(1), "1 1", "Base case: n = 1");
  testEqual(symmetricSequence(2), "2 1 1 2", "Recursive case: n = 2");
  testEqual(symmetricSequence(3), "3 2 1 1 2 3", "Recursive case: n = 3");
  testEqual(symmetricSequence(5), "5 4 3 2 1 1 2 3 4 5", "Recursive case: n = 5");
  testEqual(symmetricSequence(10),"10 9 8 7 6 5 4 3 2 1 1 2 3 4 5 6 7 8 9 10", "Recursive case: n = 10");
}
