import comp1110.universe.*;
import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.universe.Colour.*;
import static comp1110.universe.Image.*;
import static comp1110.universe.Universe.*;
import static comp1110.testing.Comp1110Unit.*;


enum Direction{
    up,
    down
}

record Balloon(int x , int y ,Direction dir, Colour color){}
record World(Balloon ball){}

int WORLD_WIDTH = 500;
int WORLD_HEIGHT = 500;
int MARBLE_RADIUS = 50;

Image draw(World world){
    Image background = Rectangle(WORLD_WIDTH,WORLD_HEIGHT,WHITE);
    Image ballImage = Circle (MARBLE_RADIUS,RED);
    Image ballOnTOP = PlaceXY(background,ballImage)
}

World getStart(){
    return new World(Balloon(250,250,Direction.down,RED));
}

void main() {
    BigBang("Bouncing Marbles", getStart(),this::draw,this::step);
}