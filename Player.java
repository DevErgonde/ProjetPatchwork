import java.util.HashMap;
import java.util.Objects;

public class Player{
	
  private final String name;
  private final int buttons;
  private final int score;
  private final HashMap<Piece, Coord> pieces;
  
  //
  // Constructeurs
  //
  
  public Player(String name) {
    Objects.requireNonNull(name);
    this.name = name;
    this.buttons = 0;
    this.score = 0;
    this.pieces = new HashMap<Piece, Coord>();
  }
  
  //
  // Méthodes
  //
  
  //
  // Accesseurs
  //
  
  public String name() {
    return name;
  }
  
  public int buttons() {
    return buttons;
  }
  
  public int score() {
    return score;
  }
  
  public HashMap<Piece, Coord> pieces(){
    return pieces;
  }
}

