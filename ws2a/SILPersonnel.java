import comp1110.lib.*;
import comp1110.lib.Date;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*;
//java --enable-preview 
//java --enable-preview comp1110.testing.Test 
//SILPersonnel.java

/**
 * The SIL offers the following four world's finest degrees:
 * Doctorate of Ideological Purity (DIP)
 * Master of Chancellor Thorne Thought (MCTT)
 * Bachelor of Unified Principles (BUP)
 * Bachelor of Agriculture and Farming (BFOOD)
 */
enum Degree{
    DIP,
    MCTT,
    BUP,
    BFOOD
}
// Template:
// {
//     return switch(Degree){
//             case DIP -> ...;
//             case MCTT -> ...;
//             case BUP -> ...;
//             case BFOOD -> ...
//         }...;
// }

/**
 * The SIL has three kinds of personnel:
 * - Students: We need to store the following information: Their name and their degree.
 * - Staff: Have a name, a monthly salary, and may or may not be casual.
 * - Security: Have a name. They also might be "secret", 
 * meaning that instead of wearing a uniform, 
 * they will pretend to be a student or staffer for the sake of surveillance.
 */
sealed interface Person 
    permits Students,Staff,Security {}

// Template:
// {
//     ...
//     return switch(Person){
//         case Students(String name , Degree degree) -> ...;
//         case Staff(String name,int salary,boolean casual) -> ...;
//         case Security(String name, Person real ) -> ...;
//     }...;
// }


/**
 * Students: We need to store the following information: Their name and their degree.
 * Examples:
 *  - Students("lisa",DIP) - A students named Lisa and Degree is DIP
 *  - Students("Tom",MCTT) - A students named Tom and Degree is MCTT
 * @param name None-empty String
 * @param degree The degree
 */
record Students(String name , Degree degree) implements Person{}

/**
 * Staff: Have a name, a monthly salary, and may or may not be casual.
 * Examples:
 *  - Staff("Cat",1102,True) - A staff named "Cat" with 1102 salary and is casual.
 *  - Staff("Tom",1202,False) - A staff named "Tom" with 1202 salary and is not casual.
 * @param name String
 * @param salary Int
 * @param casual Boolean
 */
record Staff(String name,int salary,boolean casual)implements Person{}


record Security(String name, Boolean bool )implements Person{}

/**
 * Due to economic difficulties, the SIL does not have the 
 * budget to afford this many personnel, and will have to 
 * downsize.
 * Example:
 *  - Given: Students("Lisa", BFOOD)
 *      expect: True
 *  - Given: Staff("Tom",50,True)
 *      expect: True 
 * Design Strategy: implement Template 
 * @param person
 * @return The result of getting expelled or not - a boolean
 */
Boolean isPersonnelRemoved(Person person){
    return switch(person){
        case Students(String name, Degree degree) -> degree == Degree.BFOOD;
        case Staff(String name, int salary, boolean casual) -> casual && salary > 4;
        case Security(String name, Boolean bool) -> false;
    };
}

void main(){
    println(isPersonnelRemoved(new Students("Lisa", Degree.BFOOD)));
    println(StartsWith("alex","a"));
}

void test(){
    runAsTest(this:: test1);
}

void test1(){
    testEqual(true, isPersonnelRemoved(new Students("Lisa", Degree.BFOOD)));
    testEqual(false, isPersonnelRemoved(new Students("Tom", Degree.DIP)));
    testEqual(true, isPersonnelRemoved(new Staff("Cat", 1102, true)));
    testEqual(false, isPersonnelRemoved(new Staff("Tom", 1202, false)));
    testEqual(false, isPersonnelRemoved(new Security("Bob", new Students("Alice", Degree .BFOOD))));
    testEqual(false, isPersonnelRemoved(new Security("Bob", new Staff("Alice", 1202, false))));
}