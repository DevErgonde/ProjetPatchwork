package src.fr.umlv.patchwork;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Game{
  
  private final boolean mode; // Mode graphique (true) ascii (false)
  private final int phase;
  private final Player p1; // Joueur 1
  private final Player p2; // Joueur 2
  private Player actualPlayer; // Joueur à qui c'est le tour de jouer
  private final List<Piece> pieces; // Banque de morceaux de tissus disponibles pour le jeu
  private final Board chronoBoard; // Plateau principal du jeu
  
  //
  // Constructeurs
  //
  public Game(String name1, String name2, int phase, boolean mode) throws Exception{
    if (phase < 1 || phase > 4) {
      throw new IllegalArgumentException("The phase number has to be between 1 and 4");
    }
    Objects.requireNonNull(name1);
    Objects.requireNonNull(name2);
    this.mode = mode;
    this.phase = phase;
    p1 = new Player(name1);
    p2 = new Player(name2);
    actualPlayer = p1;
    pieces = this.generatePieces();
    chronoBoard = new Board(phase);
  }
  
  public Game(Player p1, Player p2, Player actualPlayer, List<Piece> pieces, Board chronoBoard, int phase, boolean mode) {
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
    this.actualPlayer = actualPlayer;
    this.pieces = pieces;
    this.chronoBoard = chronoBoard;
  }
  
  //
  // Méthodes aidants à l'implémentation dans le constructeur
  //
  
  private ArrayList<Piece> generatePieces () throws Exception{
    var pieces = new ArrayList<Piece>();
    switch(phase) {
      case 1:
        for(var i = 0; i < 20; i ++) {
          pieces.add(new Piece(3, 4, 1, new boolean[][]{{true, true}, {true, true}}));
          pieces.add(new Piece(2, 2, 0, new boolean[][]{{true, true}, {true, true}}));
        }
        return pieces;
      case 2:
        return generatePhase2Pieces();
      case 3:
        return null;
      case 4:
        return null;
      default:
        throw new IllegalArgumentException("The phase number has to be between 1 and 4");
    }
  }
  
  private static ArrayList<Piece> generatePhase2Pieces() throws IOException{
	var pieces = new ArrayList<Piece>();
	for (String line : Files.readAllLines(Paths.get("assets/textes/PieceList.txt"))) {
		var elems = line.split("\\|");
		var x = Integer.valueOf(elems[4]);
		var y = Integer.valueOf(elems[5]);
		boolean[][] form = new boolean[x][y];
		var temp = elems[3].split(",");
    for(var i = 0; i < x; i++) {
      for(var j = 0; j < y; j++) {
        form[i][j] = Boolean.parseBoolean(temp[i*y+j]);
      }
    }
    pieces.add(new Piece(Integer.valueOf(elems[0]),Integer.valueOf(elems[1]),Integer.valueOf(elems[2]),form));
	}
	return pieces;
}
  
  //
  // Méthodes
  //
  
  public void buyAndPlacePiece(Dialogue dial) {
    Objects.requireNonNull(dial);
    var choice = dial.askPieceChoice();
    //dial.askPieceOrientation();
    //dial.askPieceReverse();
    var piece = pieces.get(choice - 1);
    if (piece.price() > actualPlayer.ressourcesButtons()) {
      System.out.println("Attention, vous ne pouvez pas acheter cette pièce car vous n'avez pas les ressource nécessaires !");
      manageActionChoices(actualPlayer, dial);
    }
    else {
      actualPlayer.placePiece(piece, dial.askPieceCoord());
      movePawn(piece.time());
      p1.setRessourcesButtons(actualPlayer.ressourcesButtons() - piece.price());
    }
  }
  
  public Player changePlayer(Player actualPlayer) {
    if (actualPlayer.position > otherPlayer().position) {
      return otherPlayer();
    }
    return actualPlayer;
  }
  
  public void movePawn(int distance) {
    if (distance <= 0)
      throw new IllegalArgumentException("The distance can't be negativ.");
    Element end;
    switch(chronoBoard.boardElements().get(actualPlayer.position + distance)) {
      // Gain buttons
      case BUTTON:
        actualPlayer.gainButtons(1);
        break;
      // If it's a leather, the player have to play the Leather Piece.
      case LEATHER:
        //playLeatherPiece
        break;
      default:
        break;
    }
    actualPlayer.position += distance;
  }
  
  public void skipTour() {
    int distance;
    distance = otherPlayer().position - actualPlayer.position;
    movePawn(distance);
    //System.out.println("Position : " + actualPlayer.position);
    actualPlayer.setRessourcesButtons(actualPlayer.ressourcesButtons() + distance + actualPlayer.stableButtons());
  }
  
  public static void gains(Player player, Board board, Piece piece) {
    player.gainButtons(piece.buttons());
  }
  
  public boolean isOver() {
    return (p1.position == 53) && (p2.position == 53);
  }
  
  public void manageActionChoices(Player actualPlayer, Dialogue dial) {
    Display.displayActionsChoices();
    switch(dial.askActionChoices()) {
      case 1:
        buyAndPlacePiece(dial);
        break;
      case 2:
        skipTour();
        break;
      case 3:
        Display.displayPieces(this);
        dial.exitDisplay();
        break;
      case 4:
        Display.displayChronoBoard(this);
        dial.exitDisplay();
        break;
      case 5:
        Display.displayGrid(p1);
        dial.exitDisplay();
        break;
      case 6:
        Display.displayGrid(p2);
        dial.exitDisplay();
        break;
      case 7:
        Display.displayInfoPlayer(actualPlayer);
        dial.exitDisplay();
        break;
    }
  }
  
  public void play() {
    var actualPlayer = p1;
    var dial = new Dialogue(new Scanner(System.in));
    while(!isOver()) {
      manageActionChoices(actualPlayer, dial);
      actualPlayer = changePlayer(actualPlayer);
    }
    dial.closeDialogue();
    
    p1.endScore(); p2.endScore();
    
    if (p1.score() > p2.score()) {
      System.out.println("Le gagnant est " + p1.name());
    }
    else {
      System.out.println("Le gagnant est " + p2.name());
    }
  }
  
  public Player otherPlayer() {
    if (actualPlayer == p1)
      return p2;
    else
      return p1;
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
}