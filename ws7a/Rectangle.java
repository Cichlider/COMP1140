public class Rectangle {
    // Step 1: Instance fields/attributes
    double width;
    double height;
    
    // Step 4: Default constructor (no arguments)
    public Rectangle() {
        this.width = 0.0;
        this.height = 0.0;
    }
    
    // Step 3: Constructor with parameters
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    // Step 5: Method to calculate area
    public double calculateArea() {
        return width * height;
    }
    
    // Step 6: Method to calculate perimeter
    public double calculatePerimeter() {
        return 2 * (width + height);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Step 2: Creating object without constructor ===");
        // 注意：这一步需要注释掉有参构造函数才能看到默认值
        // Rectangle rect1 = new Rectangle();
        // System.out.println("Default width: " + rect1.width);
        // System.out.println("Default height: " + rect1.height);
        // 答案：默认值是0.0，由JVM的默认初始化负责
        
        System.out.println("\n=== Step 3: Creating object with constructor ===");
        Rectangle rect2 = new Rectangle(2.5, 7.5);
        System.out.println("Width with constructor: " + rect2.width);
        System.out.println("Height with constructor: " + rect2.height);
        
        System.out.println("\n=== Step 4: Creating object with no arguments ===");
        Rectangle rect3 = new Rectangle(); // 现在可以编译了，因为我们添加了默认构造函数
        System.out.println("Default constructor - Width: " + rect3.width);
        System.out.println("Default constructor - Height: " + rect3.height);
        
        System.out.println("\n=== Step 5: Calculate and print areas ===");
        double area2 = rect2.calculateArea();
        double area3 = rect3.calculateArea();
        System.out.println("Area of rect2 (2.5 x 7.5): " + area2);
        System.out.println("Area of rect3 (0.0 x 0.0): " + area3);
        
        System.out.println("\n=== Step 6: Calculate and print perimeters ===");
        double perimeter2 = rect2.calculatePerimeter();
        double perimeter3 = rect3.calculatePerimeter();
        System.out.println("Perimeter of rect2 (2.5 x 7.5): " + perimeter2);
        System.out.println("Perimeter of rect3 (0.0 x 0.0): " + perimeter3);
    }
}