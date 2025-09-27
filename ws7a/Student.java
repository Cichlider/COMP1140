import comp1110.lib.*;
import static comp1110.lib.Functions.*;

public class Student {
    // 实例变量
    private String name;
    private int id;
    private ConsList<Double> grades;
    
    // 构造方法
    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        this.grades = new Nil<>();
    }
    
    // 添加成绩
    public void addGrade(double grade) {
        this.grades = new Cons<>(grade, this.grades);
    }
    
    // 计算平均分
    public double computeAverageGrade() {
        double sum = sumGrades(this.grades);
        int count = countGrades(this.grades);
        return sum / count;
    }
    
    // 辅助方法：递归计算总和
    private double sumGrades(ConsList<Double> list) {
        return switch(list) {
            case Nil<Double>() -> 0.0;
            case Cons<Double>(var element, var rest) -> 
                element + sumGrades(rest);
        };
    }
    
    // 辅助方法：递归计算数量
    private int countGrades(ConsList<Double> list) {
        return switch(list) {
            case Nil<Double>() -> 0;
            case Cons<Double>(var element, var rest) -> 
                1 + countGrades(rest);
        };
    }
    
    // getter方法
    public String getName() {
        return this.name;
    }
    
    public int getId() {
        return this.id;
    }
    
    // ========== 测试辅助方法 ==========
    
    private static void testEqual(Object actual, Object expected, String message) {
        if (Equals(actual, expected)) {
            System.out.println("✓ PASS: " + message);
        } else {
            System.out.println("✗ FAIL: " + message);
            System.out.println("  Expected: " + expected);
            System.out.println("  Actual:   " + actual);
        }
    }
    
    private static void testEqualDouble(double actual, double expected, String message) {
        if (Math.abs(actual - expected) < 0.01) {
            System.out.println("✓ PASS: " + message);
        } else {
            System.out.println("✗ FAIL: " + message);
            System.out.println("  Expected: " + expected);
            System.out.println("  Actual:   " + actual);
        }
    }
    
    // ========== main方法 - 运行测试 ==========
    
    public static void main(String[] args) {
        System.out.println("=== 测试 Student 类 ===\n");
        
        // 测试1: 基本getter
        Student s1 = new Student("Alice", 101);
        testEqual(s1.getName(), "Alice", "getName() 返回正确的名字");
        testEqual(s1.getId(), 101, "getId() 返回正确的学号");
        
        // 测试2: 单个成绩
        Student s2 = new Student("Bob", 102);
        s2.addGrade(90.0);
        testEqualDouble(s2.computeAverageGrade(), 90.0, "单个成绩: 平均分 = 90.0");
        
        // 测试3: 两个成绩
        Student s3 = new Student("Charlie", 103);
        s3.addGrade(80.0);
        s3.addGrade(100.0);
        testEqualDouble(s3.computeAverageGrade(), 90.0, "两个成绩: (80+100)/2 = 90.0");
        
        // 测试4: 三个成绩
        Student s4 = new Student("Diana", 104);
        s4.addGrade(85.5);
        s4.addGrade(92.0);
        s4.addGrade(88.5);
        testEqualDouble(s4.computeAverageGrade(), 88.67, "三个成绩: 平均分约为 88.67");
        
        // 测试5: 五个成绩
        Student s5 = new Student("Eve", 105);
        s5.addGrade(70.0);
        s5.addGrade(75.0);
        s5.addGrade(80.0);
        s5.addGrade(85.0);
        s5.addGrade(90.0);
        testEqualDouble(s5.computeAverageGrade(), 80.0, "五个成绩: 平均分 = 80.0");
        
        // 测试6: 所有成绩相同
        Student s6 = new Student("Frank", 106);
        s6.addGrade(100.0);
        s6.addGrade(100.0);
        s6.addGrade(100.0);
        testEqualDouble(s6.computeAverageGrade(), 100.0, "所有成绩相同: 平均分 = 100.0");
        
        System.out.println("\n=== 测试完成 ===");
    }
}