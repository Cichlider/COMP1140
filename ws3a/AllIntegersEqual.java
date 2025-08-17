

import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 





boolean allIntegersEqual(ConsList<Integer> lst) {
    if (IsEmpty(lst)) {
        return true;
    } else if (IsEmpty(Rest(lst))) {
        return true; 
    } else {
        return Equals(First(lst),(First(Rest(lst)))) && allIntegersEqual(Rest(lst));
    }
}

void main(){
    ConsList<String> a=MakeList("comp1110","comp1140","comp6710");
    ConsList<Double> b = MakeList(3.2, 4.5, 7.3);
    ConsList<Integer> c = MakeList(2,3,1,4,5,6,3,2,1);
    ConsList<Integer> list1 = MakeList(); 
    ConsList<Integer> list2 = MakeList(1); 
    ConsList<Integer> list3 = MakeList(1,1,1); 
    ConsList<Integer> list5 = MakeList(2,2,2,2,2,1); 
    ConsList<Integer> list7 = MakeList(2,2,2,3,2,2,2); 
    // println(b);
    // println(firststring(a));
    // println(lengthDoubles(b));
    // println(concatenateStrings(a));
    // println(removeInteger(c,5));
    // println(largestInteger(c));
    println(allIntegersEqual(list2));
}
