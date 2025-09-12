import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*;

void main() {
    int x = 2;
    int y = 7;
    
    println("x: " + x);
    println("y: " + y);

    int z = y;
    y = x;
    x = z;


    println("x: " + x);
    println("y: " + y);
}


