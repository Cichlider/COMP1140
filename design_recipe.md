# Java Design Recipe 完整指南

本指南整合了函数式编程到面向对象编程的完整 Design Recipe 流程，适用于 Java 开发中的各种场景。

---

## 核心六步流程

### Step 1: Problem Analysis & Data Design（问题分析与数据设计）

#### 1.1 类（Classes）的数据设计

**必需元素**：
- 类的整体解释（第一行）
- 典型示例（Examples）
- 不变量声明（`@implSpec Invariants`）
- 每个字段的独立注释
- Template（模板）

```java
/**
 * A DoublyLinkedList is a list implementation using doubly-linked nodes
 * Examples: empty list, [1,2,3], ["a","b"]
 * 
 * @implSpec Invariants:
 *   1. first always points to the first element, or null if empty
 *   2. size always equals the number of elements in the list
 *   3. all next/previous links are consistent and bidirectional
 *   4. circular structure: first.previous points to last node
 */
public class DoublyLinkedList<T> {
    /** Reference to the first node, or null if list is empty */
    private Node<T> first;
    
    /** The number of elements currently in the list, >= 0 */
    private int size;
    
    /**
     * Creates a new empty DoublyLinkedList
     * @implSpec Postcondition: size = 0, first = null
     */
    public DoublyLinkedList() {
        this.first = null;
        this.size = 0;
    }
}

// Template for DoublyLinkedList:
// { ... this.first ... this.size ... }
```

#### 1.2 内部类（Nested Classes）

内部类同样需要完整的文档：

```java
/**
 * Node holds an element with bidirectional links
 * Examples: Node(5, ref_to_prev, ref_to_next)
 * 
 * @param <T> - the type of element stored
 * @implSpec Invariant: if previous is not null, previous.next == this
 *                      if next is not null, next.previous == this
 */
private static class Node<T> {
    /** The element stored in this node */
    T value;
    
    /** Reference to the previous node in the list */
    Node<T> previous;
    
    /** Reference to the next node in the list */
    Node<T> next;
    
    /**
     * Creates a new Node
     * @param value - the element to store
     * @param previous - reference to previous node
     * @param next - reference to next node
     */
    Node(T value, Node<T> previous, Node<T> next) {
        this.value = value;
        this.previous = previous;
        this.next = next;
    }
}

// Template for Node:
// { ... node.value ... node.previous ... node.next ... }
```

#### 1.3 Records（记录类型）

Records 的文档较为简洁：

```java
/**
 * A Point represents a coordinate in 2D space
 * Examples: Point(0,0), Point(3.5, -2.1)
 * 
 * @param x - the x-coordinate
 * @param y - the y-coordinate
 */
public record Point(double x, double y) {}

// Template:
// { ... point.x ... point.y ... }
```

#### 1.4 接口（Interfaces）

```java
/**
 * Shape represents a geometric shape with computable area
 * Examples: Circle, Rectangle, Triangle
 * 
 * @implSpec All implementations must provide non-negative area
 */
public interface Shape {
    /**
     * Computes the area of this shape
     * @return the area in square units, >= 0
     */
    double getArea();
}
```

---

### Step 2: Purpose Statement & Signature（目的声明与签名）

#### 2.1 完整方法文档结构

```java
/**
 * [一句话描述方法的目的]
 * 
 * Examples:
 * - [示例 1]
 * - [示例 2]
 * 
 * Design Strategy: [策略名称]
 * 
 * [Effects: 副作用描述（如有）]
 * 
 * @param [参数名] - [参数描述]
 * @return [返回值描述]
 * @throws [异常类型] [抛出条件]
 * @implSpec [前置/后置条件]
 */
```

#### 2.2 实例方法（Instance Methods）

**无副作用的方法**：

```java
/**
 * Returns the current size of the list
 * 
 * Examples:
 * - given: empty list
 *   expect: returns 0
 * - given: [1,2,3]
 *   expect: returns 3
 * 
 * Design Strategy: Simple Expression
 * 
 * @return the number of elements in the list
 */
public int size() {
    return this.size;
}
```

**有副作用的方法**：

```java
/**
 * Appends the specified element to the end of this list
 * 
 * Examples:
 * - given: empty list, add 5
 *   expect: list becomes [5], returns true
 * - given: [1,2], add 3
 *   expect: list becomes [1,2,3], returns true
 * 
 * Design Strategy: Case Distinction
 * 
 * Effects: adds element to end of list; increases size by 1
 * 
 * @param t - element to be appended to this list
 * @return true if the element was added successfully
 * @implSpec Postcondition: size increases by exactly 1
 *           Postcondition: t is now the last element
 */
public boolean add(T t) { ... }
```

#### 2.3 静态方法（Static Methods）

```java
/**
 * Creates a new list containing the given elements
 * 
 * Examples:
 * - given: no arguments
 *   expect: returns empty list
 * - given: 1, 2, 3
 *   expect: returns [1,2,3]
 * 
 * Design Strategy: Iteration
 * 
 * @param elements - the elements to include in the list
 * @return a new list containing all given elements
 * @param <T> - the type of elements
 */
@SafeVarargs
public static <T> DoublyLinkedList<T> of(T... elements) { ... }
```

#### 2.4 构造函数（Constructors）

**简单构造函数**（仅赋值字段）：

```java
/**
 * Creates a new Counter with the specified starting value
 * 
 * @param startValue - the initial counter value, must be >= 0
 * @implSpec Precondition: startValue >= 0
 *           Postcondition: counterValue = startValue
 */
public Counter(int startValue) {
    this.counterValue = startValue;
}
```

**注意**：简单构造函数可省略 Examples，但复杂逻辑需要示例。

#### 2.5 关键注解说明

**Effects（副作用）**：
- 说明方法对对象状态的所有修改
- 说明对外部系统的影响（I/O、数据库等）
- 命令式编程中必需

**Preconditions（前置条件）**：
- 调用者必须保证的条件
- 如果违反，方法行为未定义

**Postconditions（后置条件）**：
- 方法执行后保证的状态
- 返回值的性质

**Invariants（不变量）**：
- 在所有公共方法执行前后都成立的条件
- 通常在类级别声明

---

### Step 3: Examples（示例）

#### 3.1 示例格式

**纯函数风格**：

```java
/**
 * Examples:
 * - isEmpty() on [] returns true
 * - isEmpty() on [1,2,3] returns false
 */
```

**带状态变化**：

```java
/**
 * Examples:
 * - given: Counter with counterValue = 5, increment(3)
 *   expect: counterValue becomes 8, returns 8
 * - given: Counter with counterValue = 0, increment(10)
 *   expect: counterValue becomes 10, returns 10
 */
```

#### 3.2 示例数量要求

- **最少 2 个示例**
- 覆盖主要场景和边界情况
- 每个 Case Distinction 分支至少 1 个示例
- 复杂方法增加示例数量

#### 3.3 特殊情况

**简单构造函数**：可省略示例

```java
/**
 * Creates a new Point
 * @param x - the x-coordinate
 * @param y - the y-coordinate
 */
public Point(double x, double y) {
    this.x = x;
    this.y = y;
}
```

**Getter 方法**：需要示例

```java
/**
 * Returns the x-coordinate
 * 
 * Examples:
 * - given: Point(3.0, 4.0)
 *   expect: returns 3.0
 * - given: Point(-1.5, 2.0)
 *   expect: returns -1.5
 * 
 * Design Strategy: Simple Expression
 * 
 * @return the x-coordinate
 */
public double getX() {
    return this.x;
}
```

---

### Step 4: Design Strategy（设计策略）

选择以下五种策略之一，并在文档中明确声明。

#### 策略 1: Simple Expression（简单表达式）

**适用场景**：
- 直接返回字段值
- 简单的算术运算
- 单一表达式实现

```java
/**
 * Returns the current counter value
 * 
 * Examples:
 * - given: Counter with counterValue = 5
 *   expect: returns 5
 * 
 * Design Strategy: Simple Expression
 * 
 * @return the current counter value
 */
public int getValue() {
    return this.counterValue;
}
```

#### 策略 2: Combining Functions（组合函数）

**适用场景**：
- 调用其他已有方法
- 组合多个函数结果

```java
/**
 * Checks if the list is empty
 * 
 * Examples:
 * - given: empty list
 *   expect: returns true
 * - given: [1,2,3]
 *   expect: returns false
 * 
 * Design Strategy: Combining Functions
 * 
 * @return true if the list contains no elements
 */
public boolean isEmpty() {
    return this.size() == 0;
}
```

#### 策略 3: Case Distinction（情况区分）

**适用场景**：
- 需要根据条件执行不同逻辑
- 处理不同的数据状态

**OO Java 放松的规则**：
- 允许嵌套的 if 语句（但保持合理深度）
- 不必在所有分支都有 return
- 可以省略 else 分支
- 可以在 if 后继续执行代码

```java
/**
 * Removes the first occurrence of the specified element
 * 
 * Examples:
 * - given: empty list, remove 5
 *   expect: list unchanged, returns false
 * - given: [1,2,3], remove 2
 *   expect: list becomes [1,3], returns true
 * - given: [1,2,3], remove 5
 *   expect: list unchanged, returns false
 * 
 * Design Strategy: Case Distinction
 * 
 * Effects: if found, removes element and decreases size by 1
 * 
 * @param o - element to be removed
 * @return true if element was found and removed
 */
public boolean remove(Object o) {
    if (isEmpty()) {
        return false;
    }
    
    Node<T> current = first;
    for (int i = 0; i < size; i++) {
        if (current.value.equals(o)) {
            // 找到元素，执行删除
            removeNode(current);
            return true;
        }
        current = current.next;
    }
    
    return false;  // 未找到
}
```

**带嵌套的示例**：

```java
/**
 * Design Strategy: Case Distinction
 */
public void processValue(int value) {
    if (value < 0) {
        System.out.println("Negative");
        return;
    }
    
    if (value == 0) {
        System.out.println("Zero");
    } else {
        System.out.println("Positive");
    }
    
    // 继续执行，适用于 value >= 0 的情况
    System.out.println("Processing complete");
}
```

#### 策略 4: Apply Template（应用模板）

**适用场景**：
- 处理自定义数据类型
- 需要访问数据结构的各个部分

```java
/**
 * Computes the distance from origin
 * 
 * Examples:
 * - given: Point(3, 4)
 *   expect: returns 5.0
 * - given: Point(0, 0)
 *   expect: returns 0.0
 * 
 * Design Strategy: Apply Template - Point
 * 
 * @return the Euclidean distance from origin
 */
public double distanceFromOrigin() {
    // Using Point template: { ... this.x ... this.y ... }
    return Math.sqrt(this.x * this.x + this.y * this.y);
}
```

**处理节点的示例**：

```java
/**
 * Updates all node values using the given function
 * 
 * Design Strategy: Iteration over nodes + Apply Template - Node
 * 
 * Effects: modifies the value field of each node
 * 
 * @param mapper - function to apply to each value
 */
private void updateAllNodes(Function<T, T> mapper) {
    if (isEmpty()) return;
    
    Node<T> current = first;
    for (int i = 0; i < size; i++) {
        // Using Node template: { ... current.value ... current.next ... }
        current.value = mapper.apply(current.value);
        current = current.next;
    }
}
```

#### 策略 5: Iteration（迭代）

**适用场景**：
- 遍历集合或数组
- 执行重复操作

**两种迭代形式**：

**计数循环**：

```java
/**
 * Increments the counter n times by 1
 * 
 * Examples:
 * - given: Counter with counterValue = 5, incrementNTimes(3)
 *   expect: counterValue becomes 8
 * - given: Counter with counterValue = 0, incrementNTimes(0)
 *   expect: counterValue remains 0
 * 
 * Design Strategy: Iteration
 * 
 * Effects: increases counterValue by n
 * 
 * @param n - number of times to increment, must be >= 0
 * @implSpec Precondition: n >= 0
 */
public void incrementNTimes(int n) {
    for (int i = 0; i < n; i++) {
        this.counterValue++;
    }
}
```

**遍历数据结构**：

```java
/**
 * Checks if this list contains the specified element
 * 
 * Examples:
 * - given: [1,2,3], contains(2)
 *   expect: returns true
 * - given: [1,2,3], contains(5)
 *   expect: returns false
 * - given: empty list, contains(1)
 *   expect: returns false
 * 
 * Design Strategy: Iteration
 * 
 * @param o - element whose presence is to be tested
 * @return true if this list contains the specified element
 */
public boolean contains(Object o) {
    if (isEmpty()) {
        return false;
    }
    
    Node<T> current = first;
    for (int i = 0; i < size; i++) {
        if (current.value.equals(o)) {
            return true;
        }
        current = current.next;
    }
    
    return false;
}
```

**增强 for 循环**：

```java
/**
 * Prints all names in the list with line numbers
 * 
 * Examples:
 * - given: ["Alice", "Bob"]
 *   expect: prints "1. Alice\n2. Bob"
 * 
 * Design Strategy: Iteration
 * 
 * Effects: prints each name to System.out
 * 
 * @param names - list of names to print
 */
public void printNames(List<String> names) {
    int index = 1;
    for (String name : names) {
        System.out.println(index + ". " + name);
        index++;
    }
}
```

#### 策略选择指南

| 场景 | 推荐策略 |
|------|---------|
| 返回字段值 | Simple Expression |
| 简单计算（无循环/分支） | Simple Expression |
| 调用已有方法组合 | Combining Functions |
| 根据条件分支处理 | Case Distinction |
| 处理自定义类型的字段 | Apply Template |
| 遍历集合/数组 | Iteration |
| 重复执行操作 n 次 | Iteration |
| 混合场景 | 组合多个策略，明确主要策略 |

---

### Step 5: Implementation（实现）

#### 5.1 基本原则

- **避免魔法数字**：使用有意义的常量
- **保持函数简洁**：每个方法专注单一职责
- **注释关键逻辑**：复杂算法需要内联注释
- **遵循策略**：实现必须匹配声明的设计策略

#### 5.2 使用常量

```java
public class DoublyLinkedList<T> {
    /** Maximum recommended size before performance warning */
    private static final int MAX_RECOMMENDED_SIZE = 10000;
    
    /** Default initial capacity for internal operations */
    private static final int DEFAULT_CAPACITY = 16;
    
    public boolean add(T t) {
        if (size >= MAX_RECOMMENDED_SIZE) {
            System.err.println("Warning: list size exceeds recommended limit");
        }
        // ... 实现
    }
}
```

#### 5.3 辅助方法

将复杂逻辑拆分为私有辅助方法：

```java
/**
 * Removes the specified node from the list
 * 
 * Design Strategy: Case Distinction
 * 
 * Effects: removes node from list; updates links; decreases size by 1
 * 
 * @param node - the node to remove, must not be null
 * @implSpec Precondition: node is in this list
 *           Postcondition: node is no longer in list, size decreases by 1
 */
private void removeNode(Node<T> node) {
    if (size == 1) {
        // 唯一节点
        first = null;
    } else {
        // 更新链接
        node.previous.next = node.next;
        node.next.previous = node.previous;
        
        if (node == first) {
            first = node.next;
        }
    }
    
    size--;
}
```

#### 5.4 处理边界情况

```java
public T get(int index) {
    // 参数验证
    if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException(
            "Index: " + index + ", Size: " + size
        );
    }
    
    // 空列表已经被上面的检查处理
    Node<T> current = first;
    for (int i = 0; i < index; i++) {
        current = current.next;
    }
    
    return current.value;
}
```

---

### Step 6: Tests（测试）

#### 6.1 测试框架

使用 **JUnit 5**（Jupiter）：

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {
    private DoublyLinkedList<Integer> list;
    
    /**
     * Sets up a fresh list before each test
     */
    @BeforeEach
    void setUp() {
        list = new DoublyLinkedList<>();
    }
    
    // 测试方法...
}
```

#### 6.2 测试结构

**基本测试**：

```java
/**
 * Tests that a new list is empty
 */
@Test
void testNewListIsEmpty() {
    assertTrue(list.isEmpty());
    assertEquals(0, list.size());
}

/**
 * Tests adding a single element
 */
@Test
void testAddSingleElement() {
    assertTrue(list.add(5));
    assertEquals(1, list.size());
    assertFalse(list.isEmpty());
}
```

**测试状态变化**：

```java
/**
 * Tests that size increases correctly when adding elements
 */
@Test
void testAddMultipleElements() {
    list.add(1);
    assertEquals(1, list.size());
    
    list.add(2);
    assertEquals(2, list.size());
    
    list.add(3);
    assertEquals(3, list.size());
}
```

**测试异常**：

```java
/**
 * Tests that get throws IndexOutOfBoundsException for negative index
 */
@Test
void testGetNegativeIndex() {
    list.add(1);
    assertThrows(IndexOutOfBoundsException.class, () -> {
        list.get(-1);
    });
}

/**
 * Tests that get throws IndexOutOfBoundsException for index >= size
 */
@Test
void testGetIndexTooLarge() {
    list.add(1);
    assertThrows(IndexOutOfBoundsException.class, () -> {
        list.get(1);
    });
}
```

#### 6.3 覆盖率要求

- **所有示例转为测试**
- **每个分支至少一个测试**
- **边界情况**：空、单元素、多元素
- **异常情况**：无效输入、前置条件违反
- **状态验证**：检查副作用是否正确

#### 6.4 完整测试示例

```java
class DoublyLinkedListTest {
    private DoublyLinkedList<Integer> list;
    
    @BeforeEach
    void setUp() {
        list = new DoublyLinkedList<>();
    }
    
    // 构造函数和初始状态
    @Test
    void testNewListIsEmpty() {
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }
    
    // 添加元素
    @Test
    void testAddToEmptyList() {
        assertTrue(list.add(5));
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
    }
    
    @Test
    void testAddMultipleElements() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.size());
    }
    
    // 包含检查
    @Test
    void testContainsExistingElement() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertTrue(list.contains(2));
    }
    
    @Test
    void testContainsNonExistingElement() {
        list.add(1);
        list.add(2);
        assertFalse(list.contains(5));
    }
    
    @Test
    void testContainsOnEmptyList() {
        assertFalse(list.contains(1));
    }
    
    // 删除元素
    @Test
    void testRemoveExistingElement() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertTrue(list.remove(2));
        assertEquals(2, list.size());
        assertFalse(list.contains(2));
    }
    
    @Test
    void testRemoveNonExistingElement() {
        list.add(1);
        assertFalse(list.remove(5));
        assertEquals(1, list.size());
    }
    
    @Test
    void testRemoveFromEmptyList() {
        assertFalse(list.remove(1));
        assertEquals(0, list.size());
    }
    
    // 索引访问
    @Test
    void testGetValidIndex() {
        list.add(10);
        list.add(20);
        list.add(30);
        assertEquals(20, list.get(1));
    }
    
    @Test
    void testGetNegativeIndex() {
        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, 
            () -> list.get(-1));
    }
    
    @Test
    void testGetIndexTooLarge() {
        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, 
            () -> list.get(1));
    }
}
```

---

## 接口实现的特殊规则

### 文档分工

#### 接口层级

```java
public interface List<E> {
    /**
     * Appends the specified element to the end of this list
     * 
     * Examples:
     * - empty list, add(5) -> [5], returns true
     * - [1,2], add(3) -> [1,2,3], returns true
     * 
     * Effects: adds element to end of list
     * 
     * @param e - element to be appended
     * @return true if the element was added
     * @implSpec Postcondition: size increases by 1
     */
    boolean add(E e);
}
```

#### 实现类层级

```java
public class DoublyLinkedList<T> implements List<T> {
    /**
     * Design Strategy: Case Distinction
     */
    @Override
    public boolean add(T t) {
        if (isEmpty()) {
            // 空列表情况
            Node<T> newNode = new Node<>(t, null, null);
            newNode.next = newNode;
            newNode.previous = newNode;
            first = newNode;
        } else {
            // 非空列表情况
            Node<T> last = first.previous;
            Node<T> newNode = new Node<>(t, last, first);
            last.next = newNode;
            first.previous = newNode;
        }
        size++;
        return true;
    }
}
```

**关键点**：
- Purpose、Examples、Effects 写在接口中
- Design Strategy 写在实现类中
- 实现类不重复接口文档

### Liskov 替换原则

**定义**：子类（实现类）必须能够替换其父类（接口）而不破坏程序正确性。

#### 规则 1：不能加强前置条件

```java
interface Calculator {
    /**
     * @param value - any integer
     * @return value multiplied by 2
     */
    int doubleValue(int value);
}

class RestrictedCalculator implements Calculator {
    // ❌ 错误：加强了前置条件（value > 0）
    @Override
    int doubleValue(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Must be positive");
        }
        return value * 2;
    }
}

class CorrectCalculator implements Calculator {
    // ✅ 正确：接受任何整数
    @Override
    int doubleValue(int value) {
        return value * 2;
    }
}
```

#### 规则 2：不能放松后置条件

```java
interface Shape {
    /**
     * @return the area in square units, must be >= 0
     * @implSpec Postcondition: result >= 0
     */
    double getArea();
}

class BrokenShape implements Shape {
    // ❌ 错误：可能返回负数，放松了后置条件
    @Override
    double getArea() {
        return -5.0;
    }
}

class CorrectCircle implements Shape {
    private double radius;
    
    // ✅ 正确：保证返回非负值
    @Override
    double getArea() {
        return Math.PI * radius * radius;  // 总是 >= 0
    }
}
```

#### 规则 3：不能削弱不变量

```java
/**
 * @implSpec Invariant: size always equals the actual number of elements
 */
interface Collection<E> {
    int size();
    boolean add(E e);
}

class BrokenCollection<E> implements Collection<E> {
    private int size = 0;
    
    // ❌ 错误：add 后 size 不增加，违反不变量
    @Override
    public boolean add(E e) {
        // 添加元素但忘记增加 size
        return true;
    }
    
    @Override
    public int size() {
        return size;
    }
}
```

---

## 快速检查清单

### Step 1: Data Design
- [ ] 类/接口的一句话描述
- [ ] 提供典型示例（Examples）
- [ ] 每个字段都有独立注释
- [ ] `@implSpec` 声明所有不变量
- [ ] 编写完整的 Template
- [ ] 内部类也有完整文档

### Step 2: Purpose & Signature
- [ ] 一句话 Purpose statement
- [ ] 所有 `@param` 都有描述
- [ ] `@return` 说明（非 void 方法）
- [ ] **Effects** 说明所有副作用（如有）
- [ ] `@implSpec` 前置/后置条件（如适用）
- [ ] `@throws` 说明异常情况

### Step 3: Examples
- [ ] 至少 2 个示例
- [ ] 覆盖主要场景和边界情况
- [ ] 每个 Case Distinction 分支至少 1 个示例
- [ ] 格式：`given: ... expect: ...`
- [ ] 简单构造函数可省略

### Step 4: Design Strategy
- [ ] 选择了五种策略之一
- [ ] 在注释中写明