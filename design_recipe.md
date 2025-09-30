# Design Recipe 流程整理

根据文档，Design Recipe 包含 **6 个步骤**。以下是每个步骤的详细说明和实例：

---

## 步骤 1: Problem Analysis and Data Design（问题分析和数据设计）

### 目的
定义类的数据结构，包括字段、不变量和基本文档。

### 要点
- 类本质上是记录（records）
- 每个字段需要解释说明
- `@param` 注解放在构造函数上
- 需要说明不变量（invariants）

### 实例：Counter 类

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
```

### 实例：GameItem 类（来自你的作业）

```java
/**
 * GameItem represents an item in a role-playing game with:
 * - name: String - the name of the item (non-empty)
 * - value: int - the value in gold pieces (>= 0)
 * - weight: int - the weight in kilograms (>= 0) 
 * - attackBonus: int - attack bonus (can be positive, negative, or zero)
 * - agilityBonus: int - agility bonus (can be positive, negative, or zero)
 * - defenseBonus: int - defense bonus (can be positive, negative, or zero)
 *
 * Examples:
 * - GameItem("Heavy Sword", 250, 15, 10, -5, 0)
 * - GameItem("Elven Cloak", 120, 3, 0, 3, 1)
 *
 * @implSpec Invariants:
 *   - name must be non-empty
 *   - value >= 0
 *   - weight >= 0
 */
public class GameItem {
    private final String name;
    private final int value;
    private final int weight;
    private final int attackBonus;
    private final int agilityBonus;
    private final int defenseBonus;
}
```

---

## 步骤 2: Purpose Statement & Signature（目的说明和签名）

### 目的
为所有方法（实例方法、静态方法、构造函数）编写目的说明和签名。

### 要点
- 说明方法的作用
- 包括前置条件（preconditions）和后置条件（postconditions）
- 说明副作用（effects）
- 构造函数不需要 `@return` 说明

### 实例：Counter 类的方法

```java
/**
 * Increments the counter by the given amount.
 * @param amount the amount to increment by (must be > 0)
 * @implSpec Precondition: amount > 0
 *           Postcondition: counterValue increases by amount
 */
void increment(int amount) {
    // implementation
}

/**
 * Returns the current value of the counter.
 * @return the current counter value
 */
int getValue() {
    return counterValue;
}
```

### 实例：GameItem 的方法

```java
/**
 * Returns the name of the item (derived from the INI section header).
 * @return the item name
 */
public String getName() {
    return name;
}

/**
 * Given a valid and non-empty INI file of the format described above,
 * reads all item definitions from the sections and returns an array
 * containing them.
 * @param file the INI file to read from
 * @return an array of GameItem objects parsed from the file
 * @implSpec Precondition: file must be a valid INI file
 *           Postcondition: returns array of parsed items,
 *           or empty array if file not found
 */
public static GameItem[] readItems(File file) {
    // implementation
}
```

---

## 步骤 3: Examples（示例）

### 目的
为方法提供具体的输入输出示例，帮助理解方法行为。

### 要点
- 为所有方法（实例方法、静态方法、构造函数）提供示例
- 直接赋值字段的构造函数不需要示例
- 示例应该覆盖典型情况和边界情况

### 实例：Counter 类

```java
/**
 * Increments the counter by the given amount.
 * Examples:
 * - Given: Counter(5), increment(3) → counterValue becomes 8
 * - Given: Counter(10), increment(1) → counterValue becomes 11
 * @param amount the amount to increment by (must be > 0)
 */
void increment(int amount) {
    counterValue += amount;
}
```

### 实例：GameItem 辅助方法

```java
/**
 * Checks if a line is a section header (enclosed in square brackets).
 * Examples:
 * - Given: "[Heavy Sword]" → Expect: true
 * - Given: "[Elven Cloak]" → Expect: true  
 * - Given: "Value=250" → Expect: false
 * - Given: "" → Expect: false
 * @param line the line to check
 * @return true if the line is a section header, false otherwise
 */
private static boolean isSectionHeader(String line) {
    return line.startsWith("[") && line.endsWith("]");
}

/**
 * Extracts the section name from a section header line.
 * Examples:
 * - Given: "[Heavy Sword]" → Expect: "Heavy Sword"
 * - Given: "[Elven Cloak]" → Expect: "Elven Cloak"
 * @param line the section header line
 * @return the section name without brackets
 */
private static String extractSectionName(String line) {
    return line.substring(1, line.length() - 1);
}
```

---

## 步骤 4: Design Strategy（设计策略）

### 目的
选择合适的设计策略来实现方法。

### 可用的设计策略
1. **Simple Expressions**（简单表达式）- 直接计算
2. **Case Distinction**（情况区分）- 使用 if/switch
3. **Combining Functions**（组合函数）- 调用辅助方法
4. **Iteration**（迭代）- 使用循环
5. **Recursion**（递归）

### 实例 1: Simple Expressions

```java
/**
 * Returns the total value of all items.
 * Design Strategy: Simple expressions - sum the values
 */
int getTotalValue() {
    return value1 + value2 + value3;
}
```

### 实例 2: Case Distinction

```java
/**
 * Parses a key-value pair line and updates the item attributes accordingly.
 * Examples:
 * - Given: "Value=250", item with value=0 → item with value=250
 * - Given: "Type=Weapon", item unchanged → item unchanged (ignored key)
 * Design Strategy: Case distinction - switch on key name
 */
private static void parseKeyValuePair(String line, ItemBuilder itemBuilder) {
    String[] parts = line.split("=", 2);
    String key = parts[0].trim();
    String valueStr = parts[1].trim();
    
    switch (key) {
        case "Value":
            itemBuilder.setValue(Integer.parseInt(valueStr));
            break;
        case "Weight":
            itemBuilder.setWeight(Integer.parseInt(valueStr));
            break;
        // ... other cases
    }
}
```

### 实例 3: Combining Functions

```java
/**
 * Reads all items from an INI file.
 * Design Strategy: Combining Functions - use helper functions 
 *                  to parse each line type
 */
public static GameItem[] readItems(File file) {
    ArrayList<GameItem> items = new ArrayList<>();
    
    try (Scanner scanner = new Scanner(file)) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            
            if (isSectionHeader(line)) {
                String name = extractSectionName(line);
                // process section
            } else if (isKeyValuePair(line)) {
                parseKeyValuePair(line, builder);
            }
        }
    }
    
    return items.toArray(new GameItem[0]);
}
```

### 实例 4: Iteration

```java
/**
 * Computes the total strength of a character.
 * Design Strategy: Iteration - sum bonuses from all items
 */
int computeTotalStrength() {
    int total = baseStrength;
    for (GameItem item : inventory) {
        total += item.getAttackBonus();
    }
    return total;
}
```

### For 循环的两种形式

```java
// 形式 1: 索引循环
// i 不在循环体内赋值，但在每次迭代时递增
for (int i = 0; i < 10; i++) {
    System.out.println(i);
}

// 形式 2: 增强 for 循环（遍历有限数据结构）
for (String name : names) {
    System.out.println(name);
}
```

---

## 步骤 5: Implementation（实现）

### 目的
根据设计策略编写实际代码。

### 要点
- 遵循设计策略
- 可以嵌套情况区分（但不要过度复杂）
- 可以在 if 语句后继续执行，省略 else 分支
- 如果代码太复杂，创建辅助函数

### 实例：完整实现

```java
public static GameItem[] readItems(File file) {
    ArrayList<GameItem> items = new ArrayList<>();
    
    try (Scanner scanner = new Scanner(file)) {
        ItemBuilder currentBuilder = null;
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            
            // Empty line indicates section separator
            if (line.isEmpty()) {
                if (currentBuilder != null) {
                    items.add(currentBuilder.build());
                    currentBuilder = null;
                }
                continue;
            }
            
            // Check if this is a section header
            if (isSectionHeader(line)) {
                if (currentBuilder != null) {
                    items.add(currentBuilder.build());
                }
                String itemName = extractSectionName(line);
                currentBuilder = new ItemBuilder(itemName);
                continue;
            }
            
            // Process key-value pairs
            if (currentBuilder != null && isKeyValuePair(line)) {
                parseKeyValuePair(line, currentBuilder);
            }
        }
        
        // Don't forget the last item
        if (currentBuilder != null) {
            items.add(currentBuilder.build());
        }
        
    } catch (FileNotFoundException e) {
        return new GameItem[0];
    }
    
    return items.toArray(new GameItem[0]);
}
```

---

## 步骤 6: Tests（测试）

### 目的
编写测试验证实现的正确性。

### 要点
- 使用 JUnit 5.9.0 或更高版本
- 将示例转换为测试
- 测试典型情况和边界情况

### 实例：JUnit 测试

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class GameItemTest {
    
    private GameItem heavySword;
    
    @BeforeEach
    void setUp() {
        // Corresponds to example: GameItem("Heavy Sword", 250, 15, 10, -5, 0)
        heavySword = new GameItem("Heavy Sword", 250, 15, 10, -5, 0);
    }
    
    @Test
    @DisplayName("Test getName() returns correct name")
    void getName() {
        // From example: Counter(5) has value 5
        assertEquals("Heavy Sword", heavySword.getName());
    }
    
    @Test
    @DisplayName("Test getValue() returns correct value")
    void getValue() {
        assertEquals(250, heavySword.getValue());
    }
    
    @Test
    @DisplayName("Test negative agility bonus")
    void getAgilityBonus() {
        // Testing boundary case: negative bonus
        assertEquals(-5, heavySword.getAgilityBonus());
    }
    
    @Test
    @DisplayName("Test readItems() with valid file")
    void readItems(@TempDir Path tempDir) throws IOException {
        File testFile = tempDir.resolve("items.ini").toFile();
        
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("[Heavy Sword]\n");
            writer.write("Value=250\n");
            writer.write("Weight=15\n");
        }
        
        GameItem[] items = GameItem.readItems(testFile);
        
        assertEquals(1, items.length);
        assertEquals("Heavy Sword", items[0].getName());
        assertEquals(250, items[0].getValue());
    }
    
    @Test
    @DisplayName("Test readItems() handles missing file")
    void readItemsFileNotFound() {
        File nonExistent = new File("does_not_exist.ini");
        GameItem[] items = GameItem.readItems(nonExistent);
        
        // Boundary case: empty array for missing file
        assertEquals(0, items.length);
    }
}
```

---

## 总结：Design Recipe 完整流程

```
1. Data Design
   ↓ 定义类结构、字段、不变量
   
2. Purpose & Signature  
   ↓ 编写方法签名和目的说明
   
3. Examples
   ↓ 提供具体输入输出示例
   
4. Design Strategy
   ↓ 选择实现策略（表达式/区分/组合/迭代/递归）
   
5. Implementation
   ↓ 根据策略编写代码
   
6. Tests
   ↓ 将示例转换为 JUnit 测试
```

每个步骤都是必要的，它们共同确保代码的正确性、可维护性和可测试性。