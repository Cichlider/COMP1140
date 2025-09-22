class Rectangle{
    private double width;
    private double height;

    public Rectangle(double width,double height){
        this.width = width;
        this.height = height;
    }

    public double calculateArea(){
        return width * height;
    }

    public double calculatePerimeter(){
        return 2 * (width+height);
    }
}

class Circle{
    private double radius;

    public Circle(double radius){
        this.radius = radius;
    }

    public double calculateArea(){
        return Math.PI * radius * radius;
    }

    public double calculatePerimeter(){
        return 2 * Math.PI * radius;
    }
}

class RightTriangle{
    private double base;
    private double height;

    public RightTriangle(double base , double height){
        this.base = base;
        this.height = height;
    }

    public double calculateArea(){
        return 0.5 * base * height;
    }

    public double calculatePerimeter(){
        double hypotenuse = Math.sqrt(base *base + height *height);
        return base + height + hypotenuse;
    }
}

public class Shapes{
    public static void main(String[] args){
        Rectangle rectangle = new Rectangle(5.0,3.0);
        Circle circle = new Circle(4.0);
        RightTriangle triangle = new RightTriangle(3.0,4.0);

        System.out.println("Rectangle (5.0 x 3.0):");
        System.out.println("Area: " + rectangle.calculateArea());
        System.out.println("Perimeter: " + rectangle.calculatePerimeter());
        System.out.println();
        
        System.out.println("Circle (radius 4.0):");
        System.out.println("Area: " + circle.calculateArea());
        System.out.println("Perimeter: " + circle.calculatePerimeter());
        System.out.println();
        
        System.out.println("Right Triangle (base 3.0, height 4.0):");
        System.out.println("Area: " + triangle.calculateArea());
        System.out.println("Perimeter: " + triangle.calculatePerimeter());

    }
}