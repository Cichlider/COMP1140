import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*;

int factorial (int a){
    int s =1;
    for (int i=1;i<a+1;i++){
        s=s*i;
    }
    return s;
}

void main(){
    println(factorial(5));
}