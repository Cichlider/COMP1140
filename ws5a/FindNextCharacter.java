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
int g(ConsList<String> lst, int index, int x, char c){
    return switch(lst){
        case Nil<String>() -> -1;
        case Cons<String>(var elem ,var rest) -> 
            (index > x && Equals(ToString(c),elem)) ? index : g(rest,index+1,x,c);
    };
}

int findNextCharacter(String s, int a, char c){
    ConsList<String> lst = f(s,0);
    int result = g(lst,0,a,c);
    return result;
}

void main(){
    testEqual(findNextCharacter("", 0,'a'), -1, "Edge case: Empty string");
    testEqual(findNextCharacter("k", 0,'b'), -1, "Edge case: Length = 1");
    testEqual(findNextCharacter("c",0,'c'), -1, "Edge case: Length = 1 and character present");
    testEqual(findNextCharacter("ko", 0,'d'), -1, "char not present");
    testEqual(findNextCharacter("korrh", 0,'e'), -1, "char not present");
    testEqual(findNextCharacter("kf", 0,'f'), 1, "char present after index");
    testEqual(findNextCharacter("korrhoho", 0,'o'), 1, "char present after index multiple times");
    testEqual(findNextCharacter("korrho", 3,'o'), 5, "char present before and after index"); 
    testEqual(findNextCharacter("korrh", 4,'o'), -1, "char present before and not after index"); 
    testEqual(findNextCharacter("korrh", 1,'o'), -1, "char present at and not after index");
    testEqual(findNextCharacter("korrho", 1,'o'), 5, "char present at and after index");
    testEqual(findNextCharacter("korrh", 5,'o'), -1, "Edge case: Index is at the end of the string");
}