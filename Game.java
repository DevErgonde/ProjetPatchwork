import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Main class for the gestion of the current game
 */
public class Game{
  
  private final boolean mode; // Mode graphique (true) ascii (false)
  private final int phase;
  private final Player p1; // Joueur 1
  private final Player p2; // Joueur 2
  private final List<Piece> pieces; // Banque de morceaux de tissus disponibles pour le jeu
  private final Board chronoBoard; // Plateau principal du jeu
  
  //
  // Constructeurs
  //
  public Game(String name1, String name2, int phase, boolean mode) {
    if (phase < 1 || phase > 4) {
      throw new IllegalArgumentException("The phase number has to be between 1 and 4");
    }
    Objects.requireNonNull(name1);
    Objects.requireNonNull(name2);
    this.mode = mode;
    this.phase = phase;
    p1 = new Player(name1);
    p2 = new Player(name2);
    pieces = this.generatePieces();
    chronoBoard = new Board(phase);
  }
  
  public Game(Player p1, Player p2, List<Piece> pieces, Board chronoBoard, int phase, boolean mode) {
    if (phase < 1 || phase > 4) {
      throw new IllegalArgumentException("The phase number has to be between 1 and 4");
    }
    Objects.requireNonNull(p1);
    Objects.requireNonNull(p2);
    Objects.requireNonNull(pieces);
    Objects.requireNonNull(chronoBoard);
    this.mode = mode;
    this.phase = phase;
    this.p1 = p1;
    this.p2 = p2;
    this.pieces = pieces;
    this.chronoBoard = chronoBoard;
  }
  
  //
  // Méthodes aidants à l'implémentation dans le constructeur
  //
  
  /**
   * Generate the starting list of leather pieces
   * @return the list of pieces
   */
  private ArrayList<Piece> generatePieces (){
    var pieces = new ArrayList<Piece>();
    switch(phase) {
      case 1:
        for(var i = 0; i < 20; i ++) {
          pieces.add(new Piece(3, 4, 1, new boolean[][]{{true, true}, {true, true}}));
          pieces.add(new Piece(2, 2, 0, new boolean[][]{{true, true}, {true, true}}));
        }
      case 2:
        Game.generatePhase2Pieces(pieces);
      case 3:
        return null;
      case 4:
        return null;
      default:
        throw new IllegalArgumentException("The phase number has to be between 1 and 4");
    }
  }
  
  //
  // Méthodes
  //
  
  /**
   * Gives the amount of buttons displayed on the leather piece to the designated player
   * @param player : Designated player
   * @param piece : The leather piece from which the buttons are taken
   */
  public static void Gains(Player player, Piece piece) {
	  player.gainButtons(piece.buttons());
  }
  
  //
  // Accesseurs
  //
  
  public boolean mode() {
    return mode;
  }
  
  public int phase() {
    return phase;
  }
  
  public Player p1() {
    return p1;
  }
  
  public Player p2() {
    return p2;
  }
  
  public List<Piece> pieces(){
    return pieces;
  }
  
  public Board chronoBoard() {
    return chronoBoard;
  }
  
  //
  // Monstruosité
  //
  
  private static void generatePhase2Pieces(ArrayList<Piece> pieces) {
	  pieces.add(new Piece(2, 2, 0, new boolean[][]{{true,true,true}}));
      pieces.add(new Piece(2, 3, 0, new boolean[][]{{true,true,true},{false,true,false},{true,true,true}}));
      pieces.add(new Piece(2,1,0,new boolean[][]{{false,true,false},{true,true,false},{false,true,true},{false,true,false}}));
      pieces.add(new Piece(2,2,0,new boolean[][] {{true,true},{true,true},{false,true}}));
      pieces.add(new Piece(1,2,0,new boolean[][] {{true,true},{true,false},{true,true}}));
      pieces.add(new Piece(1,3,0,new boolean[][] {{true,true},{false,true}}));
      pieces.add(new Piece(1,2,0,new boolean[][] {{false,true,true},{false,true,false},{false,true,false},{true,true,false}}));
      pieces.add(new Piece(4, 2, 0, new boolean[][]{{true,true,true,false},{false,true,true,true}}));
      pieces.add(new Piece(2, 2, 0, new boolean[][]{{true,true,true},{false,true,false}}));
      pieces.add(new Piece(3, 1, 0, new boolean[][]{{false,true},{true,true}}));
      pieces.add(new Piece(3, 4, 1, new boolean[][]{{true,false},{true,false},{true,true},{true,false}}));
      pieces.add(new Piece(0, 3, 1, new boolean[][]{{false,false,true,false},{true,true,true,true},{false,false,true,false}}));
      pieces.add(new Piece(1, 4, 1, new boolean[][]{{false,false,true,false,false},{true,true,true,true,true},{false,false,true,false,false}}));
      pieces.add(new Piece(3,2,1,new boolean[][] {{false,true},{true,true},{true,false}}));
      pieces.add(new Piece(5,3,1,new boolean[][] {{false,true,false},{true,true,true},{true,true,true},{false,true,false}}));
      pieces.add(new Piece(4,2,1,new boolean[][] {{true,false},{true,false},{true,true}}));
      pieces.add(new Piece(1,5,1,new boolean[][] {{true,false,false,true},{true,true,true,true}}));
      pieces.add(new Piece(7,1,1,new boolean[][] {{true,true,true,true,true}}));
      pieces.add(new Piece(10,3,2,new boolean[][] {{true,false},{true,false},{true,false},{true,true}}));
      pieces.add(new Piece(7, 2, 2, new boolean[][]{{true,false,false,false},{true,true,true,true},{true,false,false,false}}));
      pieces.add(new Piece(3,3,1,new boolean[][] {{true,true,true,true}}));
      pieces.add(new Piece(2,3,1,new boolean[][] {{false,true,true,true},{true,true,false,false}}));
      pieces.add(new Piece(5,5,2,new boolean[][] {{false,false,true},{true,true,true},{false,false,true}}));
      pieces.add(new Piece(3,6,2,new boolean[][] {{true,true,false},{false,true,true},{true,true,false}}));
      pieces.add(new Piece(5,4,2,new boolean[][] {{false,true,false},{true,true,true},{false,true,false}}));
      pieces.add(new Piece(10,5,3,new boolean[][] {{true,true},{true,true},{false,true},{false,true}}));
      pieces.add(new Piece(7,4,2,new boolean[][] {{true,false},{true,true},{true,true},{true,false}}));
      pieces.add(new Piece(6,5,2,new boolean[][] {{true,true},{true,true}}));
      pieces.add(new Piece(7,6,3,new boolean[][] {{true,true,false},{false,true,true}}));
      pieces.add(new Piece(10,4,3,new boolean[][] {{false,false,true},{false,true,true},{true,true,false}}));
      pieces.add(new Piece(4,6,2,new boolean[][] {{false,false,true},{true,true,true}}));
      pieces.add(new Piece(8,6,3,new boolean[][] {{false,false,true},{true,true,true},{true,true,false}}));
      pieces.add(new Piece(2,1,0,new boolean[][] {{true,true}}));
      /* Ne vous inquietes pas, cette horreur ne sera plus là au rendu final */
  }
}
