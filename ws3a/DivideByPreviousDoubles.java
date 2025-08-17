import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 


ConsList<Double> divideByPreviousDoubles(ConsList<Double>lst){
    if(IsEmpty(lst)){
        return lst;
    }else if (Length(lst)==1){
        return new Nil<Double>();
    } else {
        return new Cons<Double>( First(Rest(lst)) / First(lst) , divideByPreviousDoubles(Rest(lst)));
    }
}

void main(){
    ConsList<Double> list3 = MakeList(1.0,2.0,3.0,4.0);
    println(divideByPreviousDoubles(list3));
}
