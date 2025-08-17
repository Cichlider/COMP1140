import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

Maybe<String> firststring(ConsList<String> lst){
    return switch(lst){
            case Nil<String>() -> new Nothing<String>();
            case Cons<String>(var elem,var rest) -> new Something<String>(elem);
    };
}


int lengthDoubles(ConsList<Double> lst){
    return switch(lst){
        case Nil<Double>() -> 0;
        case Cons<Double>(var elem, var rest) -> 1 + lengthDoubles(rest); 
    };
}

String concatenateStrings(ConsList<String>lst){
    return switch(lst){
        case Nil<String>() -> "";
        case Cons<String> (var elem,var rest) -> elem + concatenateStrings(rest); 
    };
}

ConsList<Integer> removeInteger(ConsList<Integer>lst,int a){
    if (IsEmpty(lst)) {
        return lst;
    }else {
        if (a == 1){
            return Rest(lst);
        }else if (a==0){
            return lst;
        }else{
            return new Cons<Integer> (First(lst), removeInteger(Rest(lst),a-1));
        }
    }
}

int largestInteger(ConsList<Integer>lst){
    ConsList<Integer> a = Sort(lst);
    return Nth(a,Length(a)-1);
}

boolean allIntegersEqual(ConsList<Integer> lst) {
    if (IsEmpty(lst)) {
        return true;
    } else if (IsEmpty(Rest(lst))) {
        return true; 
    } else {
        return Equals(First(lst),(First(Rest(lst)))) && allIntegersEqual(Rest(lst));
    }
}

ConsList<Double> divideByPreviousDoubles(ConsList<Double>lst){
    if(IsEmpty(lst)){
        return lst;
    }else if (Length(lst)==1){
        return new Nil<Double>();
    } else {
        return new Cons<Double>( First(Rest(lst)) / First(lst) , divideByPreviousDoubles(Rest(lst)));
    }
}

ConsList<Integer>multiplyByNextIntegers(ConsList<Integer> lst) {
    if (IsEmpty(lst)) {
        return lst;
    } else if (Length(lst) == 1) {
        return new Nil<Integer>();
    } else {
        return new Cons<Integer>(First(lst) * First(Rest(lst)), multiplyByNextIntegers(Rest(lst)));
    }
}

void main(){
    ConsList<Integer> list3 = MakeList(1,2,3,4);
    println(multiplyByNextIntegers(list3));
}


