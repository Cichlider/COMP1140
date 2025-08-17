import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

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

void main(){
    ConsList<String> a=MakeList("comp1110","comp1140","comp6710");
    ConsList<Double> b = MakeList(3.2, 4.5, 7.3);
    ConsList<Integer> c = MakeList(1,2,3,4);
    // println(b);
    // println(firststring(a));
    // println(lengthDoubles(b));
    // println(concatenateStrings(a));
    println(removeInteger(c,5));
}
