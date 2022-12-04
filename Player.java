import java.util.HashMap;
import java.util.Objects;

public class Player{
	
  private final String name;
  private final HashMap<Piece, Coord> pieces;
  private final boolean[][] grid;
  private int buttons;
  private int score;
  
  //
  // Constructeurs
  //
  
  public Player(String name) {
    Objects.requireNonNull(name);
    this.name = name;
    this.pieces = new HashMap<Piece, Coord>();
    this.grid = new boolean[9][9];
    this.buttons = 0;
    this.score = 0;
  }
  
  //
  // Méthodes
  //
	
	public int placeAtCoord(Coord coord) {
		if(grid[coord.x()][coord.y()] == false) {
			grid[coord.x()][coord.y()] = true;
			return 0;
		}
		else {
			return 1;
		}
	}
	
	public int placePiece(Piece piece, Coord origin) {
		var coordlist = piece.pieceCoordinates(origin);
		for( var c : coordlist) {
			if(grid[c.x()][c.y()] == false) {
				placeAtCoord(c);
			}
			else {
				return 1;
			}
		}
		return 0;
	}
	
	public int BlankSpaces() {
		var count = 0;
		for(var tab : grid) {
			for(var c : tab) {
				if(c == false) {count++;}
			}
		}
		return count;
	}

  //
  // Accesseurs
  //
  
  public String name() {
    return name;
  }

  public HashMap<Piece, Coord> pieces(){
    return pieces;
  }
  
  public boolean[][] grid(){
    return grid;
  }
  
  public int buttons() {
    return buttons;
  }
  
  public int score() {
    return score;
  }
}

