import comp1110.universe.*;
import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.universe.Colour.*;
import static comp1110.universe.Image.*;
import static comp1110.universe.Universe.*;
import static comp1110.testing.Comp1110Unit.*;


record Balloon(int x , int y , int speed , Colour color){}
record World(List<Balloon> balloons){}



int WORLD_WIDTH = 800;
int WORLD_HEIGHT = 800;
int MARBLE_RADIUS = 20;


/**
 * This function return a random color within RED, GREEN, BLUE, MAGENTA, or BLACK
 * @return Random Color
 */
Colour ranColor(){
    int ran = RandomNumber(0,5);
    return switch (ran){
        case 0 -> RED ;
        case 1 -> GREEN ;
        case 2 -> BLUE ;
        case 3 -> MAGENTA;
        case 4 -> BLACK;
        default -> RED;
    };
}

/**
 * This function return a World:(List of balloon)
 * @return a World
 */
World getStart(){
    return new World();
}


void main(){
    BigBang("world",getStart(),this::draw,this::step,this::mouseEvent);
}