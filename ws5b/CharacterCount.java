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
ConsList<Pair<Character,Integer>> characterCountCons(String str){
    
}

/**
 * Returns a stateful hash map that maps each character
 * in the given String to how often the character appears
 * in the String.
 * Examples: see characterCountCons examples
 */
Map<Character,Integer> characterCountHash(String str)
