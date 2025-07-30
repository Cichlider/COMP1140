import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 

sealed interface Shape permits Circle, Rectangle, RightTriangle {}

record Circle(double radius) implements Shape {}
record Rectangle(double width, double height) implements Shape {}
record RightTriangle(double leftleg, double rightleg) implements Shape {}

double shapeArea(Shape shape) {
    return switch(shape) {
        case Circle(double radius) -> radius * radius * PI;
        case Rectangle(double width, double height) -> width * height;
        case RightTriangle(double leftleg, double rightleg) -> leftleg * rightleg / 2.0;
    };
}

void main() {
    println(shapeArea(new Rectangle(2.0, 4.0)));
}

String addBang(String str) {
    return str + "!";
}


