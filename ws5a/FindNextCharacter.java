import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

int findNextCharacter(String s, int index, char c){
    String b = ToString(c);
    String a = SubString(s, index + 1, Length(s));
    if (Contains(a, b)){
        return (index + 1) + helperf(a, 0, b);
    } else {
        return -1;
    }
}

int helperf(String a, int n, String b){
    if(Equals(SubString(a, 0, 1), b)){
        return n;
    } else {
        return helperf(SubString(a, 1, Length(a)), n + 1, b);
    }
}


void main() {
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
