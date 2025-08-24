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

String reverseString(String s){
    ConsList<String> lst = f(s,0);
    return FoldLeft((x,y)-> x+y,"",lst);
}

void main(){
    String s ="abcd";
    println(reverseString(s));
}
