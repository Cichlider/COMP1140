# Java Core Concepts Code Walkthrough

## 1. Overload vs Override (Method Overloading vs Method Overriding)

Let me show you the key differences through code:

```java
// ========== OVERLOAD Example ==========
// Overloading: Same method name, different parameters IN THE SAME CLASS
class Calculator {
    // Method 1: two integers
    public int add(int a, int b) {
        return a + b;
    }
    
    // Method 2: three integers - this is OVERLOADING
    // Same name "add", but different number of parameters
    public int add(int a, int b, int c) {
        return a + b + c;
    }
    
    // Method 3: two doubles - also OVERLOADING
    // Same name "add", but different parameter types
    public double add(double a, double b) {
        return a + b;
    }
}

// When you call: calc.add(5, 3) - compiler decides which method at COMPILE TIME
// This is called "static binding" or "compile-time polymorphism"
```

**English explanation**: Overloading means you have multiple methods with the same name but different parameter lists in the same class. The compiler decides which method to call based on the arguments you provide.

```java
// ========== OVERRIDE Example ==========
// Overriding: Subclass redefines parent's method with EXACT SAME SIGNATURE
class Animal {
    public void makeSound() {
        System.out.println("Some generic animal sound");
    }
    
    public void eat() {
        System.out.println("Animal is eating");
    }
}

class Dog extends Animal {
    // This is OVERRIDING - same signature as parent
    @Override
    public void makeSound() {
        System.out.println("Woof! Woof!");
    }
    
    // Notice: we DON'T have to override eat() method
    // Subclasses inherit parent methods but don't HAVE to override all of them
}

class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow~");
    }
}

// Usage:
Animal myDog = new Dog();
myDog.makeSound();  // Prints "Woof! Woof!" - decided at RUNTIME
// This is "dynamic binding" or "runtime polymorphism"
```

**English explanation**: Overriding means a subclass provides its own implementation of a method that already exists in the parent class. The method signature must be exactly the same. The actual method called is determined at runtime based on the object type.

---

## 2. Four Types of Loops in Java

```java
public class LoopExamples {
    
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        
        // ========== Loop 1: FOR LOOP ==========
        // Best for: when you know exactly how many iterations you need
        System.out.println("FOR LOOP:");
        for (int i = 0; i < 5; i++) {
            System.out.println("Iteration: " + i);
        }
        
        // ========== Loop 2: WHILE LOOP ==========
        // Best for: when you don't know how many iterations, check condition FIRST
        System.out.println("\nWHILE LOOP:");
        int count = 0;
        while (count < 5) {
            System.out.println("Count: " + count);
            count++;
        }
        
        // ========== Loop 3: DO-WHILE LOOP ==========
        // Key difference: executes AT LEAST ONCE, checks condition AFTER
        System.out.println("\nDO-WHILE LOOP:");
        int num = 0;
        do {
            System.out.println("Number: " + num);
            num++;
        } while (num < 5);
        
        // Example showing do-while executes at least once:
        int x = 10;
        do {
            System.out.println("This prints once even though x = " + x);
        } while (x < 5);  // Condition is false, but body still executed once
        
        // ========== Loop 4: ENHANCED FOR LOOP (for-each) ==========
        // Best for: iterating through arrays or collections
        // Cannot modify the array, no index access
        System.out.println("\nENHANCED FOR LOOP:");
        for (int number : numbers) {
            System.out.println("Value: " + number);
        }
    }
}
```

**English explanation**: 
- **For loop**: Use when you know the exact number of iterations
- **While loop**: Use when condition is checked before execution
- **Do-while loop**: Use when you need at least one execution
- **Enhanced for loop**: Use for simple iteration over collections without needing index

---

## 3. What is an Interface? + Three Common Interfaces

```java
// ========== WHAT IS AN INTERFACE? ==========
// An interface is a CONTRACT - it defines WHAT methods a class must have,
// but not HOW to implement them (until Java 8+)

// Interface 1: Comparable - for sorting objects
interface Comparable<T> {
    int compareTo(T other);  // Abstract method - no implementation
}

// Interface 2: Runnable - for threading
interface Runnable {
    void run();
}

// Interface 3: Serializable - marker interface for object serialization
interface Serializable {
    // No methods - just marks the class as serializable
}

// ========== Example: Implementing Comparable ==========
class Student implements Comparable<Student> {
    private String name;
    private int grade;
    
    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }
    
    // We MUST implement compareTo because Student implements Comparable
    @Override
    public int compareTo(Student other) {
        // Compare by grade: negative if this < other, 0 if equal, positive if this > other
        return this.grade - other.grade;
    }
    
    public String toString() {
        return name + ": " + grade;
    }
}

// ========== Example: Implementing Runnable ==========
class MyTask implements Runnable {
    private String taskName;
    
    public MyTask(String taskName) {
        this.taskName = taskName;
    }
    
    // We MUST implement run() because MyTask implements Runnable
    @Override
    public void run() {
        System.out.println("Task " + taskName + " is running");
    }
}

// ========== Example: Custom Interface ==========
interface Drawable {
    void draw();  // Abstract method
    
    // Since Java 8: can have default methods with implementation
    default void display() {
        System.out.println("Displaying the drawing");
    }
}

class Circle implements Drawable {
    // We MUST implement draw(), but display() is optional (it has default)
    @Override
    public void draw() {
        System.out.println("Drawing a circle");
    }
}

// Usage example:
public class InterfaceDemo {
    public static void main(String[] args) {
        // Comparable example
        Student s1 = new Student("Alice", 85);
        Student s2 = new Student("Bob", 92);
        System.out.println(s1.compareTo(s2));  // Negative number (Alice < Bob)
        
        // Runnable example
        Runnable task = new MyTask("DataProcessing");
        Thread thread = new Thread(task);
        thread.start();
    }
}
```

**English explanation**: An interface is a contract that defines what methods a class must implement. It's like a blueprint. Classes that implement an interface MUST provide implementations for all abstract methods. Common Java interfaces include Comparable (for sorting), Runnable (for threading), and Serializable (for saving objects).

---

## 4. Generics - Which Data Types CANNOT be used?

```java
// ========== GENERICS CANNOT USE PRIMITIVE TYPES ==========
// Primitive types: int, double, char, boolean, byte, short, long, float

public class GenericsExample<T> {
    private T value;
    
    public void setValue(T value) {
        this.value = value;
    }
    
    public T getValue() {
        return value;
    }
}

public class GenericDemo {
    public static void main(String[] args) {
        // ❌ WRONG - cannot use primitive types
        // GenericsExample<int> box1 = new GenericsExample<>();  // COMPILE ERROR!
        
        // ✅ CORRECT - use wrapper classes instead
        GenericsExample<Integer> box1 = new GenericsExample<>();  // Integer not int
        box1.setValue(100);
        
        GenericsExample<Double> box2 = new GenericsExample<>();   // Double not double
        box2.setValue(3.14);
        
        GenericsExample<Boolean> box3 = new GenericsExample<>();  // Boolean not boolean
        box3.setValue(true);
        
        GenericsExample<String> box4 = new GenericsExample<>();
        box4.setValue("Hello");
        
        // Why? Because generics work with OBJECTS, and primitives are NOT objects
        // Primitives are stored directly in memory, objects are references
        
        // Wrapper classes for each primitive:
        // int    -> Integer
        // double -> Double
        // char   -> Character
        // boolean -> Boolean
        // byte   -> Byte
        // short  -> Short
        // long   -> Long
        // float  -> Float
    }
}

// ========== Generic Method Example ==========
class ArrayUtil {
    // This method works with any object type T
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.println(element);
        }
    }
    
    public static void main(String[] args) {
        String[] names = {"Alice", "Bob", "Charlie"};
        Integer[] numbers = {1, 2, 3, 4, 5};
        
        printArray(names);     // Works with String
        printArray(numbers);   // Works with Integer
        
        // ❌ WRONG - cannot use primitive array directly
        // int[] primitives = {1, 2, 3};
        // printArray(primitives);  // Won't work!
    }
}
```

**English explanation**: Generics CANNOT use primitive data types like int, double, boolean, etc. You must use their wrapper classes instead: Integer, Double, Boolean, etc. This is because generics work with objects (reference types), and primitives are not objects.

---

## 5. trim() vs strip() - String Methods

```java
public class StringCleaningDemo {
    public static void main(String[] args) {
        // ========== trim() - OLD METHOD (before Java 11) ==========
        String str1 = "   Hello World   ";
        String trimmed = str1.trim();
        System.out.println("Original: [" + str1 + "]");
        System.out.println("After trim(): [" + trimmed + "]");
        // Output: [Hello World]
        
        // trim() only removes ASCII spaces (character code 32 and below)
        // Removes: space, tab \t, newline \n, carriage return \r
        
        // ========== strip() - NEW METHOD (Java 11+) ==========
        String str2 = "   Hello World   ";
        String stripped = str2.strip();
        System.out.println("After strip(): [" + stripped + "]");
        // Output: [Hello World]
        
        // ========== KEY DIFFERENCE: Unicode whitespace ==========
        // strip() handles ALL Unicode whitespace characters, trim() doesn't
        
        String unicode = "\u2000\u2001Hello\u2002World\u2003";  // Unicode spaces
        
        System.out.println("\nUnicode string test:");
        System.out.println("trim():  [" + unicode.trim() + "]");
        // trim() might NOT remove these unicode spaces
        
        System.out.println("strip(): [" + unicode.strip() + "]");
        // strip() WILL remove all unicode whitespace
        
        // ========== Additional strip methods ==========
        String str3 = "   Hello World   ";
        
        // stripLeading() - removes only from the start
        System.out.println("\nstripLeading(): [" + str3.stripLeading() + "]");
        // Output: [Hello World   ]
        
        // stripTrailing() - removes only from the end
        System.out.println("stripTrailing(): [" + str3.stripTrailing() + "]");
        // Output: [   Hello World]
        
        // strip() - removes from both ends
        System.out.println("strip(): [" + str3.strip() + "]");
        // Output: [Hello World]
    }
}
```

**English explanation**: 
- **trim()**: Old method that only removes ASCII whitespace (spaces, tabs, newlines with character codes ≤ 32)
- **strip()**: Newer method (Java 11+) that removes ALL Unicode whitespace characters. It's more comprehensive and handles international characters better. strip() also has stripLeading() and stripTrailing() variants.

---

## 6. Do Subclasses HAVE TO Override All Parent Methods?

```java
// ========== NO - Subclasses DON'T have to override everything ==========

class Vehicle {
    // Method 1
    public void start() {
        System.out.println("Vehicle is starting");
    }
    
    // Method 2
    public void stop() {
        System.out.println("Vehicle is stopping");
    }
    
    // Method 3
    public void honk() {
        System.out.println("Beep beep!");
    }
}

// ========== Subclass 1: Override only what you need ==========
class Car extends Vehicle {
    // We override ONLY start() method
    @Override
    public void start() {
        System.out.println("Car engine starts: Vroom!");
    }
    
    // We DON'T override stop() - Car will inherit parent's stop()
    // We DON'T override honk() - Car will inherit parent's honk()
}

// ========== Subclass 2: Override nothing ==========
class Bicycle extends Vehicle {
    // We don't override ANY methods
    // Bicycle will use all of Vehicle's methods as-is
    
    // But we can ADD new methods
    public void ringBell() {
        System.out.println("Ring ring!");
    }
}

// ========== Subclass 3: Override multiple methods ==========
class Motorcycle extends Vehicle {
    @Override
    public void start() {
        System.out.println("Motorcycle engine roars!");
    }
    
    @Override
    public void honk() {
        System.out.println("BEEP BEEP BEEP!");
    }
    
    // We DON'T override stop() - will use parent's version
}

// ========== EXCEPTION: Abstract Methods MUST be overridden ==========
abstract class Animal {
    // Abstract method - NO implementation
    public abstract void makeSound();
    
    // Regular method - has implementation
    public void sleep() {
        System.out.println("Animal is sleeping");
    }
}

class Dog extends Animal {
    // We MUST override makeSound() because it's abstract
    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }
    
    // We DON'T have to override sleep() - it's not abstract
}

// ========== INTERFACES: Must implement ALL abstract methods ==========
interface Flyable {
    void fly();        // Abstract - must implement
    void land();       // Abstract - must implement
    
    // Default method (Java 8+) - optional to override
    default void takeOff() {
        System.out.println("Taking off...");
    }
}

class Bird implements Flyable {
    // MUST implement fly()
    @Override
    public void fly() {
        System.out.println("Bird is flying");
    }
    
    // MUST implement land()
    @Override
    public void land() {
        System.out.println("Bird is landing");
    }
    
    // takeOff() is optional - it has a default implementation
}

// ========== Demo ==========
public class OverrideDemo {
    public static void main(String[] args) {
        Car car = new Car();
        car.start();  // Uses overridden version: "Car engine starts: Vroom!"
        car.stop();   // Uses inherited version: "Vehicle is stopping"
        car.honk();   // Uses inherited version: "Beep beep!"
        
        Bicycle bike = new Bicycle();
        bike.start();     // Uses inherited version: "Vehicle is starting"
        bike.ringBell();  // Uses its own new method
        
        System.out.println("\n=== Summary ===");
        System.out.println("Regular methods: OPTIONAL to override");
        System.out.println("Abstract methods: MUST override");
        System.out.println("Interface abstract methods: MUST implement");
        System.out.println("Interface default methods: OPTIONAL to override");
    }
}
```

**English explanation**: 
- **Regular methods**: Subclasses do NOT have to override them - they automatically inherit them
- **Abstract methods**: Subclasses MUST override these (if parent is abstract class)
- **Interface abstract methods**: MUST implement all of them
- **Interface default methods**: Optional to override (Java 8+)

You only override methods when you want to change the behavior. Otherwise, you just inherit the parent's implementation.

---

## Quick Summary in English

1. **Overload vs Override**: Overload = same name, different parameters, same class. Override = redefine parent's method in subclass
2. **Four Loops**: for, while, do-while, enhanced for (for-each)
3. **Interfaces**: Comparable, Runnable, Serializable - they define contracts that classes must follow
4. **Generics**: Cannot use primitives (int, double, etc) - must use wrapper classes (Integer, Double, etc)
5. **trim() vs strip()**: trim() removes ASCII spaces, strip() removes all Unicode whitespace
6. **Override requirement**: No - only override what you need to change. Exception: abstract methods MUST be overridden