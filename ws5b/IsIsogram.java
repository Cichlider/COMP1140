import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*;


ConsList<Pair<Character,Integer>> characterCountCons(String str) {
    return characterCountConsHelper(str, 0, MakeConsMap());
}

Boolean characterCountConsHelper(String str, int index, ConsList<Pair<Character,Integer>> currentMap) {
    if (index >= Length(str)) {
        return true;
    } else {
        char c = GetCharAt(str, index);
        
        Maybe<Integer> currentCount = Get(currentMap, c);
        
        ConsList<Pair<Character,Integer>> updatedMap = switch(currentCount) {
            case Nothing<Integer>() -> true;
            case Something<Integer>(var count) -> false;
        };
        
        return characterCountConsHelper(str, index + 1, updatedMap);
    }
}



void main(){
    String a ="abcdefg";
    ConsList<Pair<Character,Integer>> b = characterCountCons(a);
    println(b);
}