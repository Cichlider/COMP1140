import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

Maybe<Double> SafeDivision (double a , double b)
{
    if(b==0){
        return new Nothing<>();
    }else{
        return new Something<>(a/b);
    }
}


void main(){
    println(SafeDivision(3,4));
}