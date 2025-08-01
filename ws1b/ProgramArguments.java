import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 
// This is a simple program that adds two integers provided as command line arguments
// It is not necessary to modify this program for the exercise.
void main(String[] args){
    println(StringToInt(args[0]) + StringToInt(args[1]));
}