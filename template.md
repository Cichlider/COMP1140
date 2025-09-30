# Template 写法速查（Java 21+）

## 核心概念

Template = 数据结构的访问模式，写在数据定义后作为注释

---

## 三种基本形式

### 1. 简单类 - 列字段

```java
class Point {
    double x;
    double y;
}
// Template: { ... point.x ... point.y ... }
```

**应用示例：**
```java
/**
 * Design Strategy: Apply Template - Point
 */
double distanceFromOrigin(Point p) {
    return Math.sqrt(p.x * p.x + p.y * p.y);
}
```

---

### 2. 接口/抽象类 - switch 模式匹配

```java
interface Shape { }
class Circle implements Shape { double radius; }
class Rectangle implements Shape { double width; double height; }

// Template:
// return switch(shape) {
//     case Circle c -> ... c.radius ...;
//     case Rectangle r -> ... r.width ... r.height ...;
// };
```

**应用示例：**
```java
/**
 * Design Strategy: Apply Template - Shape
 */
double getPerimeter(Shape shape) {
    return switch(shape) {
        case Circle c -> 2 * Math.PI * c.radius;
        case Rectangle r -> 2 * (r.width + r.height);
    };
}
```

---

### 3. Record - 方法调用

```java
record Student(String name, String uid, double gpa) {}

// Template: { ... student.name() ... student.uid() ... student.gpa() ... }
```

**应用示例：**
```java
/**
 * Design Strategy: Apply Template - Student
 */
boolean qualifiesForHonors(Student student) {
    return student.gpa() >= 3.5;
}
```

---

## 应用步骤

1. 写 `Design Strategy: Apply Template - TypeName`
2. 复制模板去掉注释符
3. 填充 `...` 占位符

---

## 关键规则

- 不必使用所有字段
- 可重复使用字段
- 一个函数只对一个参数应用模板
- 多参数需辅助函数