import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

/**
 * Represents a person with basic information.
 * 
 * Examples:
 * - Person("Alice Smith", "female", 1950, 2020)
 * - Person("Bob Jones", "male", 1980, 0) [0 indicates still alive]
 * 
 * @param name   The full name of the person
 * @param gender The gender of the person ("male", "female", etc.)
 * @param birth  The year of birth
 * @param death  The year of death [0 if still alive]
 */
record Person(String name, String gender, int birth, int death) {}
/**
 * ... x.name() ... x.gender() ... x.birth() ... x.death() ...
 */

/**
 * A recursive data-type representing a person and all of their descendants.
 * 
 * @param person    The `Person` instance at this node
 * @param children  A `ConsList<Descendants>` representing all direct children
 * 
 * Examples:
 * - Descendants(Person("Alice", ...), []) [Alice with no children]
 * - Descendants(Person("Bob", ...), [Descendants(Person("John", ...),[]),Descendants(Person("Amanda", ...),[])]) [Bob with two children, John and Amanda]
 */
record Descendants(Person person, ConsList<Descendants> children) {}
/**
 * ... x.person() ... [ConsList function](x.children()) ...
 */


