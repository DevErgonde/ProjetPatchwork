package src.fr.umlv.patchwork;
import java.util.HashMap;
import java.util.Objects;

public class Player{
  
  private final String name;
  private final HashMap<Piece, Coord> pieces;
  private final boolean[][] grid;
  private int stableButtons;
  private int ressourcesButtons;
  private int score;
  int position;
  
  //
  // Constructeurs
  //
  
  public Player(String name) {
    Objects.requireNonNull(name);
    this.name = name;
    this.pieces = new HashMap<Piece, Coord>();
    this.grid = new boolean[9][9];
    this.stableButtons = 5;
    this.ressourcesButtons = 5;
    this.score = 0;
    this.position = 0;
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
  
  public void endScore() {
    this.setScore(this.stableButtons() - 2* this.BlankSpaces());
  }
  
  public void gainButtons(int gain) {
    this.setStableButtons(stableButtons + gain);
  }
  
  @Override
  
  public String toString() {
    return "Joueur : " + name + ", boutons stables : " + stableButtons + ", boutons ressources : " + ressourcesButtons + ", score : " + score;
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
  
  public int stableButtons() {
    return stableButtons;
  }
  
  public int ressourcesButtons() {
    return ressourcesButtons;
  }
  
  public int score() {
    return score;
  }
  
  //
  // Setters
  //
  
  public void setStableButtons(int value) {
    this.stableButtons = value;
  }
  
  public void setRessourcesButtons(int value) {
    this.ressourcesButtons = value;
  }
  
  public void setScore(int score) {
    this.score = score;
  }
}

