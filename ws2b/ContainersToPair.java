import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 




/**
 * A container which can be used to wrap a value of any type. 
 * Used in the exercise below.
 * 
 * Examples:
 * Container(5)
 * Container("5")
 * Container(true)
 * 
 * @param thing the value wrapped in the container
 */
record Container<T>(T thing) {}


Pair<Integer,Double> containersToPair(Container<Integer> a , Container<Double> b){
    return new Pair<Integer, Double>(a.thing(), b.thing());
}

void main(){
    Container<Integer> container1 = new Container<Integer>(5);
    Container<Double> container2 = new Container<Double>(3.2);

    println(containersToPair(container1,container2));
}

void test(){
    runAsTest(this:: testExample);
}

void testExample() {
    Container<Integer> container1 = new Container<Integer>(5);
    Container<Double> container2 = new Container<Double>(3.2);
    testEqual(new Pair<Integer,Double>(5,3.2), containersToPair(container1,container2) );
}
