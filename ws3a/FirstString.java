import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

Maybe<String> firstString(ConsList<String> lst){
    return switch(lst){
        case Nil<String>() -> new Nothing<String>();
        case Cons<String>(var element, var rest) -> new Something<String> (element); 
    };
}

void main(){
    ConsList<String> list3 = MakeList("COMP1110","COMP1140","COMP6710");
    println(firstString(list3));
}

void test(){
    runAsTest(this:: testExercise2);
}

void testExercise2() {
    ConsList<String> list1 = MakeList();
    testEqual(firstString(list1), new Nothing<String>(), "Edge case: empty list");

    ConsList<String> list2 = MakeList("COMP1110");
    testEqual(firstString(list2), new Something<String>("COMP1110"), "Edge case: singleton list");

    ConsList<String> list3 = MakeList("COMP1110","COMP1140","COMP6710");
    testEqual(firstString(list3), new Something<String>("COMP1110"), "General case: a list with several elements");
}
