import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

String recursive(String s, int n){
    return switch(n){
        case 0 -> "";
        default -> s + recursive(s, n-1);
    };
}

void main()
{
  testEqual(recursive("wow",0), 
            "", 
            "Base case: n = 0");
  testEqual(recursive("comp1110",1), 
            "comp1110", 
            "Recursive case: n = 1");
  testEqual(recursive("",3), 
            "", 
            "Edge case: Input string is empty");
  testEqual(recursive("hello",3), 
            "hellohellohello", 
            "Recursive case: n = 3");
  testEqual(recursive("comp1110/1140/6710 ",5), 
            "comp1110/1140/6710 comp1110/1140/6710 comp1110/1140/6710 " +
            "comp1110/1140/6710 comp1110/1140/6710 ", 
            "Recursive case: n = 5");
  testEqual(recursive("around the world ", 144),
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " + 
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " + 
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " + 
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " + 
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " + 
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " + 
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " + 
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " + 
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " + 
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " + 
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " + 
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " +
            "around the world around the world around the world around the world " + 
            "around the world around the world around the world around the world ",
            "Edge case: Daft Punk");
}
