import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 


String concatenateStrings(ConsList<String> lst){
    return switch(lst){
        case Nil<String>() -> "";
        case Cons<String>(var elem,var rest) -> elem + concatenateStrings(rest);
    };
}



void main(){
    ConsList<String> a =MakeList("COMP1110", "COMP1140", "COMP6710");
    println(concatenateStrings(a));
}

void test(){
    runAsTest(this::testExercise10);
}

void testExercise10(){
    testEqual("COMP1110COMP1140COMP6710",concatenateStrings(MakeList("COMP1110", "COMP1140", "COMP6710")));
}