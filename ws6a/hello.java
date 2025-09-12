import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*;

void updateInputArgument(int x) {
    x = 3;
}

void main() { 
    int x = 2;
    updateInputArgument(x);
    println("The value of x after calling updateInputArgument(x) is: " + x);
}
