# Design Recipe 流程总结（面向对象Java版本）

基于你提供的课程材料，这是从 Functional Java 扩展到面向对象 Java 的 Design Recipe。

---

## 完整流程（6个步骤）

### **Step 1: Problem Analysis and Data Design（问题分析与数据设计）**

**针对类（Classes）的调整**：
- 类本质上类似于 records
- `@param` 注释放在构造函数上
- 每个字段仍需要自己的解释
- 模板类似于 record 模板，即字段列表

**实例：Counter 类**
```java
/** 
 * Represents a monotonically increasing counter.
 * Examples: Counter(5), Counter(10532)
 * @implSpec Invariant: the counter value only ever increases.
 */
class Counter {
    /** The current value of the counter >= 0 */
    int counterValue;

    /**
     * Creates a new Counter
     * @param startValue – the starting value of the counter >= 0
     */
    Counter(int startValue) {
        counterValue = startValue;
    }
}

// Template:
// { ... counter.counterValue ... }
```

**关键点**：
- 必须包含类的整体解释
- 提供示例
- 使用 `@implSpec` 说明不变量（Invariant）
- 每个字段需要独立的注释说明

---

### **Step 2: Purpose Statement & Signature（目的声明与签名）**

**适用于**：
- 实例方法（Instance methods）
- 静态方法（Static methods）
- 构造函数（Constructors）

**特别重要**：
- **Effects（效果）**：说明方法对可变状态的影响
- **Preconditions（前置条件）**：调用者必须满足的条件
- **Postconditions（后置条件）**：方法保证的结果
- **Invariants（不变量）**：始终保持的条件

**构造函数特殊规则**：
- 没有 `@return` 说明
- `@param` 放在构造函数上

**实例：带可变状态的方法**
```java
/**
 * Increments the counter by the given amount
 * @param amount - the amount to increment, must be > 0
 * @return the new counter value after incrementing
 * Effects: increases counterValue by amount
 * @implSpec Precondition: amount > 0
 *           Postcondition: counterValue increases by exactly amount
 */
int increment(int amount) {
    counterValue += amount;
    return counterValue;
}
```

---

### **Step 3: Examples（示例）**

**与 Functional Java 相同**，但有特殊规则：

**构造函数的特殊规则**：
- 如果构造函数只是直接从参数赋值给字段，**不需要示例**

**需要示例的方法**：
```java
/**
 * Returns the current counter value
 * Examples:
 * - given: Counter with counterValue = 5
 *   expect: returns 5
 * - given: Counter with counterValue = 100
 *   expect: returns 100
 * Design Strategy: Simple Expression
 * @return the current counter value
 */
int getValue() {
    return counterValue;
}
```

**带可变状态的示例**：
```java
/**
 * Doubles the counter value
 * Examples:
 * - given: Counter with counterValue = 5
 *   expect: counterValue becomes 10, returns 10
 * - given: Counter with counterValue = 7
 *   expect: counterValue becomes 14, returns 14
 * Design Strategy: Simple Expression
 * Effects: multiplies counterValue by 2
 * @return the new counter value
 */
int doubleValue() {
    counterValue *= 2;
    return counterValue;
}
```

---

### **Step 4: Design Strategy（设计策略）**

**新增策略：Iteration（迭代）**

除了原有的四种策略（Simple Expression、Combining Functions、Case Distinction、Template Application），现在可以使用：

#### **5. Iteration（迭代）**

**两种迭代形式**：

**计数循环**：
```java
/**
 * Increments the counter n times by 1
 * @param n - number of times to increment, >= 0
 * Design Strategy: Iteration
 * Effects: increases counterValue by n
 */
void incrementNTimes(int n) {
    for(int i = 0; i < n; i++) {
        counterValue++;
    }
}
```

**遍历数据结构**：
```java
/**
 * Prints all names in the list
 * @param names - list of names to print
 * Design Strategy: Iteration
 * Effects: prints each name to console
 */
void printNames(List<String> names) {
    for(String name : names) {
        System.out.println(name);
    }
}
```

#### **Case Distinction 的放松**

**现在允许**：
- 嵌套的情况区分（但要合理程度）
- 分支中可以不包含 return 语句
- 可以在 if 语句后继续执行
- 可以省略 else 分支

```java
/**
 * Design Strategy: Case Distinction
 */
void processValue(int value) {
    if (value < 0) {
        System.out.println("Negative");
        return;
    }
    if (value == 0) {
        System.out.println("Zero");
    }
    // 继续执行，没有 else
    System.out.println("Processing complete");
}
```

---

### **Step 5: Implementation（实现）**

**基本不变**，但有新的语言特性可用：
- 可以使用循环
- 可以使用可变状态
- 可以嵌套控制流结构

**实例**：
```java
/**
 * Adds all positive numbers in the array to the counter
 * @param numbers - array of integers
 * Design Strategy: Iteration
 * Effects: increases counterValue by the sum of all positive numbers
 */
void addPositiveNumbers(int[] numbers) {
    for (int num : numbers) {
        if (num > 0) {
            counterValue += num;
        }
    }
}
```

---

### **Step 6: Tests（测试）**

**使用 JUnit 进行测试**（而非之前的 Testing Package）

**要求**：
- 使用 `org.junit.jupiter:junit-jupiter:5.9.0` 或更高版本
- 将示例转换为测试
- 测试可变状态时需要设置初始状态

**实例：使用 JUnit**
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CounterTest {
    /**
     * Tests that a new counter starts with the given value
     */
    @Test
    void testCounterConstruction() {
        Counter c = new Counter(5);
        assertEquals(5, c.getValue());
    }

    /**
     * Tests incrementing the counter
     */
    @Test
    void testIncrement() {
        Counter c = new Counter(10);
        assertEquals(15, c.increment(5));
        assertEquals(15, c.getValue());
    }

    /**
     * Tests doubling the counter value
     */
    @Test
    void testDoubleValue() {
        Counter c = new Counter(7);
        assertEquals(14, c.doubleValue());
        assertEquals(14, c.getValue());
    }
}
```

---

## 关键差异总结

| 方面 | Functional Java | OO Java |
|------|----------------|---------|
| 数据定义 | records, sealed interfaces | classes |
| 字段 | 不可变 | 可以可变 |
| 模板 | 基于数据类型 | 字段列表 |
| Effects | 无 | 必须文档化 |
| 设计策略 | 4种 | 5种（新增 Iteration） |
| 控制流 | 严格限制 | 更灵活（可嵌套、可省略分支） |
| 测试 | Testing Package | JUnit |
| 构造函数 | N/A | 简单赋值不需要示例 |