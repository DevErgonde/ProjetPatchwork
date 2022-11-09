import java.util.ArrayList;
import java.util.Objects;

public class Board{
  
  enum Elements{
    Player1Pawn, Player2Pawn, Button, LeatherPiece, Void
  }
  
  private final ArrayList<Elements> boardElements;
  
  public Board(int phase){
    if (phase < 1 || phase > 4) {
      throw new IllegalArgumentException("The phase number has to be between 1 and 4");
    }
    this.boardElements = Board.generateBoardElements(phase);
  }
  
  public static ArrayList<Elements> generateBoardElements(int phase){
    ArrayList<Elements> board = new ArrayList<Elements>();
    switch(phase) {
      case 1:
        for(var i = 0; i <= 53; i++) {
        }
      case 2:
        break;
      case 3:
        break;
      case 4:
        break;
      default:
        return null;
    }
    return board;
  }
}