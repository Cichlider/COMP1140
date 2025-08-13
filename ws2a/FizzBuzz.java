import comp1110.lib.*;
import comp1110.lib.Date;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*;


String fizzBuzz(int a){
    if(a%3==0 && a%5==0){
        return ("fizzbuzz");
    }else if (a%3==0){
        return ("fizz");
    }else if (a%5==0){
        return ("buzz");
    }else{
        return ("");
    }   
}

/**
 * This function make the first characters of the String become UpperCase
 * Examples:
 *  - Given: ""
 *      - Expect: ""
 *  - Given: "hello"
 *      - Expect: "Hello"
 * design strategy: case distinction
 * @param a a string
 * @return a string
 */
String firstToUpper(String a){
    if (Equals(a,"")) {
        return "";
    }else{
        return (UpperCase(SubString(a,0,1)) +SubString(a,1));
    }
}

String finalToBang(String a){
    if (Equals(a,"")){
        return "";
    }else{
        return (SubString(a,0,Length(a)-1)+"!");
    }
}

String FizzBuzzPopSkibidiToyLet(int a){
    return finalToBang(firstToUpper((a%3==0 ? "fizz " :"")
                                    +(a%5==0 ? "buzz " :"")
                                    +(a%7==0 ? "skibidi " :"")
                                    +(a%11==0 ? "toy " :"")
                                    +(a%13==0 ? "let " :"")));
}



void main(){
    println(FizzBuzzPopSkibidiToyLet(2));
    println(firstToUpper("hello"));
}