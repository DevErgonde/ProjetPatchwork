import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
  
  private ArrayList<Piece> generatePieces (){
    var pieces = new ArrayList<Piece>();
    switch(phase) {
      case 1:
        for(var i = 0; i < 20; i ++) {
          pieces.add(new Piece(3, 4, new boolean[][]{{true, true}, {true, true}}));
          pieces.add(new Piece(2, 2, new boolean[][]{{true, true}, {true, true}}));
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
  
  //
  // Méthodes
  //
  
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
}