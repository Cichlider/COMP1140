import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

ConsList<String> f(String s, int index){
    if(index >= Length(s)){
        return new Nil<>();
    } else {
        return new Cons<>(ToString(GetCharAt(s,index)), f(s,index+1));
    }
}

int g(ConsList<String>lst){
    return switch(lst){
        case Nil<String>() -> 0;
        case Cons<String>(var elem,var rest) -> 
            (Equals(elem,"a")||Equals(elem,"e")||Equals(elem,"i")||Equals(elem,"o")||Equals(elem,"u")) 
            ? g(rest) 
            : 1+g(rest);
    };
}

int countConsonants(String s){
    ConsList<String> lst = f(s,0);
    int result = g(lst);
    return result;
}

void main(){
    String s ="hello";
    println(countConsonants(s));
}