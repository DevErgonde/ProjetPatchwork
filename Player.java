import java.util.HashMap;
import java.util.Objects;

/**
 * Class for the gestion of the information of a player
 */
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
	
    /**
     * Place a square on the quilt board
     * @param coord : Coordinates where the square must be placed
     * @return : 0 if the square was successfully placed, 1 if the space was already occupied
     */
	public int placeAtCoord(Coord coord) {
		if(grid[coord.x()][coord.y()] == false) {
			grid[coord.x()][coord.y()] = true;
			return 0;
		}
		else {
			return 1;
		}
	}
	
	/**
	 * Place a leather piece on the quilt board
	 * @param piece : The leather piece that will be placed
	 * @param origin : The top left corner of the leather piece
	 * @return ! 0 if the piece was successfully placed, 1 if the board was obstructed
	 */
	public int placePiece(Piece piece, Coord origin) {
		var coordlist = piece.pieceCoordinates(origin);
		for( var c : coordlist) {
			if(grid[c.x()][c.y()] == true) {
				return 1;
			}
		}
		for( var c : coordlist) {
			placeAtCoord(c);
		}
		return 0;
	}
	
	/**
	 * Calculate how many blank spaces there are on the quilt board
	 * @return : The number of blank spaces
	 */
	public int BlankSpaces() {
		var count = 0;
		for(var tab : grid) {
			for(var c : tab) {
				if(c == false) {count++;}
			}
		}
		return count;
	}
	
	public void endScore() {
		this.setScore(this.buttons() - 2* this.BlankSpaces());
	}
	
	public void gainButtons(int gain) {
		this.setButtons(buttons + gain);
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
	
  //
  // Setters
  //
  
  public void setButtons(int value) {
	  this.buttons = value;
  }
  
  public void setScore(int score) {
	  this.score = score;
  }
}


