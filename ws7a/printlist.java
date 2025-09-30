/**
 * Represents a monotonically increasing counter.
 * Examples:Counter(5),Counter(10532)
 * @implSpec Invariant: the counter value only ever
 * increases.
 */
class Counter{
    /**The current value of the counter >= 0 */
    int counterValue;
    /**
     * Creates a new Counter
     * @param startValue - the starting value of the counter >=0
     */
    Counter(int startValue){
        counterValue = startValue;
    }

}

/** same as for functions, for all of
 * - (instance) methods
 * - Static methods
 * - Constructors
 * Note : Constructors don't have an @return spec.
 * Particularly important: effects, pre/postconditions, invariants
 */

