import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

void main(){
    String number1 = readln("Giveme the first number: ");
    String number2 = readln("Giveme the second number: ");
    println("The sum is:" + (StringToInt(number1) + StringToInt(number2)));
}