import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

int lengthDoubles(ConsList<Double> lst){
    return switch(lst){
        case Nil<Double>() -> 0;
        case Cons<Double>(var element,var rest) -> 1+lengthDoubles(rest);
    };
}


void main(){
    ConsList<Double> a = MakeList(3.2, 4.5, 7.3);
    println(lengthDoubles(a));
}