import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*;

/**
 * Returns a cons-list-based map that maps each character
 * in the given String to how often the character appears
 * in the String.
 * Examples:
 *   Given: "aaab"; Expect: {'a': 3, 'b': 1}
 *   Given: "COMP1110"; Expect: {'C': 1, 'O': 1, 'M': 1, 'P': 1, '1': 3, '0': 1}
 */
ConsList<Pair<Character,Integer>> characterCountCons(String str) {
    return characterCountConsHelper(str, 0, MakeConsMap());
}

// 辅助递归函数
ConsList<Pair<Character,Integer>> characterCountConsHelper(String str, int index, ConsList<Pair<Character,Integer>> currentMap) {
    if (index >= Length(str)) {
        return currentMap;
    } else {
        char c = GetCharAt(str, index);
        
        // 获取当前字符的计数
        Maybe<Integer> currentCount = Get(currentMap, c);
        
        // 更新计数
        ConsList<Pair<Character,Integer>> updatedMap = switch(currentCount) {
            case Nothing<Integer>() -> Put(currentMap, c, 1);
            case Something<Integer>(var count) -> Put(currentMap, c, count + 1);
        };
        
        return characterCountConsHelper(str, index + 1, updatedMap);
    }
}

/**
 * Returns a stateful hash map that maps each character
 * in the given String to how often the character appears
 * in the String.
 * Examples: see characterCountCons examples
 */
Map<Character,Integer> characterCountHash(String str) {
    Map<Character,Integer> result = MakeHashMap();
    characterCountHashHelper(str, 0, result);
    return result;
}

// 辅助递归函数
void characterCountHashHelper(String str, int index, Map<Character,Integer> currentMap) {
    if (index < Length(str)) {
        char c = GetCharAt(str, index);
        
        // 获取当前字符的计数
        Maybe<Integer> currentCount = Get(currentMap, c);
        
        // 更新计数（直接修改map）
        switch(currentCount) {
            case Nothing<Integer>() -> Put(currentMap, c, 1);
            case Something<Integer>(var count) -> Put(currentMap, c, count + 1);
        }
        
        characterCountHashHelper(str, index + 1, currentMap);
    }
}


void main(){
    String s1 = "";
    ConsList<Pair<Character,Integer>> countStateless1 = MakeConsMap();
    Map<Character,Integer> countStateful1 = MakeHashMap();

    String s2 = "a";
    Pair<Character,Integer> p2a = new Pair<>('a',1);
    ConsList<Pair<Character,Integer>> countStateless2 = MakeConsMap(p2a);
    Map<Character,Integer> countStateful2 = MakeHashMap(p2a);

    String s3 = "aaab";
    Pair<Character,Integer> p3a = new Pair<>('a',3);
    Pair<Character,Integer> p3b = new Pair<>('b',1);
    ConsList<Pair<Character,Integer>> countStateless3 = MakeConsMap(p3a, p3b);
    Map<Character,Integer> countStateful3 = MakeHashMap(p3a, p3b);

    String s4 = "COMP1110";
    Pair<Character,Integer> p4C = new Pair<>('C',1);
    Pair<Character,Integer> p4O = new Pair<>('O',1);
    Pair<Character,Integer> p4M = new Pair<>('M',1);
    Pair<Character,Integer> p4P = new Pair<>('P',1);
    Pair<Character,Integer> p41 = new Pair<>('1',3);
    Pair<Character,Integer> p40 = new Pair<>('0',1);
    ConsList<Pair<Character,Integer>> countStateless4 = MakeConsMap(p4C, p4O, p4M, p4P, p41, p40);
    Map<Character,Integer> countStateful4 = MakeHashMap(p4C, p4O, p4M, p4P, p41, p40);

    String s5 = "sghjkhjbfhdxetrdhj";
    Pair<Character,Integer> p5s = new Pair<>('s',1);
    Pair<Character,Integer> p5g = new Pair<>('g',1);
    Pair<Character,Integer> p5h = new Pair<>('h',4);
    Pair<Character,Integer> p5j = new Pair<>('j',3);
    Pair<Character,Integer> p5k = new Pair<>('k',1);
    Pair<Character,Integer> p5b = new Pair<>('b',1);
    Pair<Character,Integer> p5f = new Pair<>('f',1);
    Pair<Character,Integer> p5d = new Pair<>('d',2);
    Pair<Character,Integer> p5x = new Pair<>('x',1);
    Pair<Character,Integer> p5e = new Pair<>('e',1);
    Pair<Character,Integer> p5t = new Pair<>('t',1);
    Pair<Character,Integer> p5r = new Pair<>('r',1);
    ConsList<Pair<Character,Integer>> countStateless5 = MakeConsMap(
        p5s, p5g, p5h, p5j, p5k, p5b, p5f, p5d, p5x, p5e, p5t, p5r
    );
    Map<Character,Integer> countStateful5 = MakeHashMap(
        p5s, p5g, p5h, p5j, p5k, p5b, p5f, p5d, p5x, p5e, p5t, p5r
    );

    String s6 = "skoop da woop";
    Pair<Character,Integer> p6s = new Pair<>('s',1);
    Pair<Character,Integer> p6k = new Pair<>('k',1);
    Pair<Character,Integer> p6o = new Pair<>('o',4);
    Pair<Character,Integer> p6p = new Pair<>('p',2);
    Pair<Character,Integer> p6d = new Pair<>('d',1);
    Pair<Character,Integer> p6a = new Pair<>('a',1);
    Pair<Character,Integer> p6w = new Pair<>('w',1);
    Pair<Character,Integer> p6Space = new Pair<>(' ',2);
    ConsList<Pair<Character,Integer>> countStateless6 = MakeConsMap(
        p6s, p6k, p6o, p6p, p6d, p6a, p6w, p6Space
    );
    Map<Character,Integer> countStateful6 = MakeHashMap(
        p6s, p6k, p6o, p6p, p6d, p6a, p6w, p6Space
    );

    String s7 = "aabaa";
    Pair<Character,Integer> p7a = new Pair<>('a',4);
    Pair<Character,Integer> p7b = new Pair<>('b',1);
    ConsList<Pair<Character,Integer>> countStateless7 = MakeConsMap(p7a, p7b);
    Map<Character,Integer> countStateful7 = MakeHashMap(p7a, p7b);

    String s8 = "abc";
    Pair<Character,Integer> p8a = new Pair<>('a',1);
    Pair<Character,Integer> p8b = new Pair<>('b',1);
    Pair<Character,Integer> p8c = new Pair<>('c',1);
    ConsList<Pair<Character,Integer>> countStateless8 = MakeConsMap(p8a, p8b, p8c);
    Map<Character,Integer> countStateful8 = MakeHashMap(p8a, p8b, p8c);

    String s9 = "aaa";
    Pair<Character,Integer> p9a = new Pair<>('a',3);
    ConsList<Pair<Character,Integer>> countStateless9 = MakeConsMap(p9a);
    Map<Character,Integer> countStateful9 = MakeHashMap(p9a);

    testEqual(characterCountCons(s1), countStateless1, "Empty string");
    testEqual(characterCountHash(s1), countStateful1, "Empty string");
    
    testEqual(characterCountCons(s2), countStateless2, "String with 1 letter");
    testEqual(characterCountHash(s2), countStateful2, "String with 1 letter");

    testEqual(characterCountCons(s3), countStateless3, "\"aaab\": same letter multiple times in a row");
    testEqual(characterCountHash(s3), countStateful3, "\"aaab\": same letter multiple times in a row");

    testEqual(characterCountCons(s4), countStateless4, "\"COMP1110\": Some letters equal");
    testEqual(characterCountHash(s4), countStateful4, "\"COMP1110\": Some letters equal");

    testEqual(characterCountCons(s5), countStateless5, "\"sghjkhjbfhdxetrdhj\": keyboard mash");
    testEqual(characterCountHash(s5), countStateful5, "\"sghjkhjbfhdxetrdhj\": keyboard mash");

    testEqual(characterCountCons(s6), countStateless6, "\"skoop da woop\": Some letters equal");
    testEqual(characterCountHash(s6), countStateful6, "\"skoop da woop\" Some letters equal");

    testEqual(characterCountCons(s7), countStateless7, "\"aabaa\": Equal letters separated by unequal letters");
    testEqual(characterCountHash(s7), countStateful7, "\"aabaa\": Equal letters separated by unequal letters");

    
    testEqual(characterCountCons(s8), countStateless8, "\"abc\": All letters unequal");
    testEqual(characterCountHash(s8), countStateful8, "\"abc\": All letters unequal");

    testEqual(characterCountCons(s9), countStateless9, "\"aaa\": All letters equal");
    testEqual(characterCountHash(s9), countStateful9, "\"aaa\": All letters equal");

}




