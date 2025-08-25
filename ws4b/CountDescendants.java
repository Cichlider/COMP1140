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



int countDescendants(Descendants tree) {
    return countDescendantsList(tree.children());
}

int countDescendantsList(ConsList<Descendants> trees) {
    return switch(trees) {
        case Nil<Descendants>() -> 0;
        case Cons<Descendants>(var elem, var rest) ->
            1 + countDescendants(elem) + countDescendantsList(rest);
    };
}

void main() {
    // Tree0: single person A with no children
    // A
    ConsList<Descendants> children0 = new Nil<Descendants>();
    Descendants tree0 = new Descendants(
        new Person("A", "x", 0, 0),
        children0
    );
    testEqual(0, countDescendants(tree0), "Tree0: A has 0 descendants");

    // Tree1: person A with three children B, C, D (no grandchildren)
    //     A
    //    /|\
    //   / | \
    //  B  C  D
    Descendants B = new Descendants(new Person("B", "x", 0, 0), new Nil<Descendants>());
    Descendants C = new Descendants(new Person("C", "x", 0, 0), new Nil<Descendants>());
    Descendants D = new Descendants(new Person("D", "x", 0, 0), new Nil<Descendants>());
    ConsList<Descendants> children1 = 
        new Cons<Descendants>(B,
        new Cons<Descendants>(C,
        new Cons<Descendants>(D, 
        new Nil<Descendants>())));
    Descendants tree1 = new Descendants(new Person("A", "x", 0, 0), children1);
    println(countDescendants(tree1));
    testEqual(3, countDescendants(tree1), "Tree1: A has 3 descendants");

    // Tree2: A with B (two children), C (no children), D1 (chain of grandchildren)
    //         A
    //        /|\
    //       / | \
    //      /  |  \
    //     B   C  D1
    //    / \      |
    //   B1 B2    D11
    //             |
    //            D111
    Descendants B1 = new Descendants(new Person("B1", "x", 0, 0), new Nil<Descendants>());
    Descendants B2 = new Descendants(new Person("B2", "x", 0, 0), new Nil<Descendants>());
    ConsList<Descendants> bChildren = new Cons<Descendants>(B1, new Cons<Descendants>(B2, new Nil<Descendants>()));
    Descendants B_root = new Descendants(new Person("B", "x", 0, 0), bChildren);

    Descendants C_root = new Descendants(new Person("C", "x", 0, 0), new Nil<Descendants>());

    Descendants D111 = new Descendants(new Person("D111", "x", 0, 0), new Nil<Descendants>());
    ConsList<Descendants> d11Children = new Cons<Descendants>(D111, new Nil<Descendants>());
    Descendants D11 = new Descendants(new Person("D11", "x", 0, 0), d11Children);

    ConsList<Descendants> d1Children = new Cons<Descendants>(D11, new Nil<Descendants>());
    Descendants D1 = new Descendants(new Person("D1", "x", 0, 0), d1Children);

    ConsList<Descendants> children2 = 
        new Cons<Descendants>(B_root,
        new Cons<Descendants>(C_root,
        new Cons<Descendants>(D1, 
        new Nil<Descendants>())));
    Descendants tree2 = new Descendants(new Person("A", "x", 0, 0), children2);
    testEqual(7, countDescendants(tree2), "Tree2: A has 7 descendants");
}
