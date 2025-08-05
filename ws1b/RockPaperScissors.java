import comp1110.lib.*;
import static comp1110.lib.Functions.*;
import static comp1110.testing.Comp1110Unit.*; 
//java --enable-preview 
//java --enable-preview comp1110.testing.Test 
//RockPaperScissors.java

enum RPS {
    /** Player chosed rock */
    ROCK, 
    /** Player chosed paper */
    PAPER, 
    /** Player chosed scissors */
    SCISSORS;
}

enum WLD {
    /** A player wins **/
    WIN, 
    /** A player loses **/
    LOSE, 
    /** Neither of the two players wins or loses **/
    DRAW;
}

WLD rockPaperScissors(RPS left, RPS right) {
    return switch (left) {
        case ROCK -> switch (right) {
            case ROCK -> WLD.DRAW;
            case PAPER -> WLD.LOSE;
            case SCISSORS -> WLD.WIN;
        };
        case PAPER -> switch (right) {
            case ROCK -> WLD.WIN;
            case PAPER -> WLD.DRAW;
            case SCISSORS -> WLD.LOSE;
        };
        case SCISSORS -> switch (right) {
            case ROCK -> WLD.LOSE;
            case PAPER -> WLD.WIN;
            case SCISSORS -> WLD.DRAW;
        };
    };
}



void main(){
    println (rockPaperScissors (RPS.ROCK,RPS.PAPER));
}

void test() {
    runAsTest(this::testExercise8);
}

void testExercise8() {
    // The test cases for rockPaperScissors cover all 9 possible 
    // combinations 
    testEqual(rockPaperScissors(RPS.ROCK,RPS.ROCK), WLD.DRAW, "ROCK/ROCK");
    testEqual(rockPaperScissors(RPS.ROCK,RPS.PAPER), WLD.LOSE, "ROCK/PAPER");
    testEqual(rockPaperScissors(RPS.ROCK,RPS.SCISSORS), WLD.WIN, "ROCK/SCISSORS");
    testEqual(rockPaperScissors(RPS.PAPER,RPS.ROCK), WLD.WIN, "PAPER/ROCK");
    testEqual(rockPaperScissors(RPS.PAPER,RPS.PAPER), WLD.DRAW, "PAPER/PAPER");
    testEqual(WLD.LOSE, rockPaperScissors(RPS.PAPER,RPS.SCISSORS), "PAPER/SCISSORS");
    testEqual(rockPaperScissors(RPS.SCISSORS,RPS.ROCK), WLD.LOSE, "SCISSORS/ROCK");
    testEqual(rockPaperScissors(RPS.SCISSORS,RPS.PAPER), WLD.WIN, "SCISSORS/PAPER");
    testEqual(rockPaperScissors(RPS.SCISSORS,RPS.SCISSORS), WLD.DRAW, "SCISSORS/SCISSORS");
}





