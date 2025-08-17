import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

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