import java.util.ArrayList;
import java.util.Objects;

public class Game{
  
  private final Player p1;
  private final Player p2;
  private final ArrayList<Piece> pieces;
  private final Board chronoBoard;
  
  public Game(String name1, String name2, int phase) {
    Objects.requireNonNull(name1);
    Objects.requireNonNull(name2);
    if (phase < 1 || phase > 4) {
      throw new IllegalArgumentException("The phase number has to be between 1 and 4");
    }
    p1 = new Player(name1);
    p2 = new Player(name2);
    pieces = Game.generatePieces(phase); 
  }
  
  public static ArrayList<Piece> generatePieces (int phase){
    var pieces = new ArrayList<Piece>();
    switch(phase) {
      case 1:
        for(var i = 0; i < 20; i ++) {
          pieces.add(new Piece(3, 4, new int[][]{{1, 1}, {1, 1}}));
          pieces.add(new Piece(2, 2, new int[][]{{1, 1}, {1, 1}}));
        }
      case 2:
        return null;
      case 3:
        return null;
      case 4:
        return null;
      default:
        throw new IllegalArgumentException("The phase number has to be between 1 and 4");
    }
  }
  
}