import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

// This is a simple Java program that prints "Hello world!" to the console.


int max_speed = 100;
void main (String... args){
    int speed = StringToInt(args[0]);
    if (speed < max_speed){
        println(speed);
    }else{
        println(max_speed);
    }
}