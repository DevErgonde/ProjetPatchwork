import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Enumeration for the different elements of the game
 */
enum Element {
  PAWN1, PAWN2, PAWNS, LEATHER, BUTTON
}

/**
 * Class reprensenting every element on the board, and the position of the players's pawns
 */
public class Board{
  
  private final Map<Integer, Element> boardElements;
  private int pawn1Position;
  private int pawn2Position;
  

  /**
   * Constructor
   * 
   * @param phase phase of the game, from 1 to 4
   */
  public Board(int phase){
    if (phase < 1 || phase > 4) {
      throw new IllegalArgumentException("The phase number has to be between 1 and 4");
    }
    
    boardElements = new HashMap<Integer, Element>();
    for (var i : List.of(26, 32, 38, 44, 50)) {
      boardElements.put(i, Element.LEATHER);
    }
    
    for (var i : List.of(5, 11, 17, 23, 29, 35, 41, 47, 53)) {
      boardElements.put(i, Element.BUTTON);
    }
    
    boardElements.put(0, Element.PAWNS);
    
    pawn1Position = 0;
    pawn2Position = 0;
  }
  
  //
  // Méthodes aidants à l'implémentation dans le constructeur
  //
  
  //
  // Méthodes
  //
  
  /**
   * Move a given pawn of a player based on the distance
   * 
   * @param distance Distance to move the pawn
   * @param pawn Given pawn
   * @param player Active player
   */
  public void movePawn(int distance, Element pawn, Player player) {
    if(pawn != Element.PAWN1 && pawn != Element.PAWN2) {
      throw new IllegalArgumentException("Only a pawn can be moved");
    }
    Element arrivée;
    switch(pawn) {
      case PAWN1:
        arrivée = boardElements.get(pawn1Position + distance);
        if(arrivée == Element.BUTTON) {
          player.gainButtons(1);
        }
        boardElements.remove(pawn1Position);
        if(pawn1Position == pawn2Position) {
          boardElements.put(pawn1Position,Element.PAWN2);
        }
        pawn1Position += distance;
        if(pawn1Position == pawn2Position) {
          boardElements.remove(pawn1Position);
          boardElements.put(pawn1Position, Element.PAWNS);
        }
        else {
          boardElements.put(pawn1Position, pawn);
        }
        break;
        
      case PAWN2:
        arrivée = boardElements.get(pawn2Position + distance);
        if(arrivée == Element.BUTTON) {
          player.gainButtons(1);
        }
        boardElements.remove(pawn2Position);
        
        if(pawn1Position == pawn2Position) {
          boardElements.put(pawn1Position,Element.PAWN1);
        }
        pawn2Position += distance;
        if(pawn1Position == pawn2Position) {
          boardElements.remove(pawn1Position);
          boardElements.put(pawn1Position, Element.PAWNS);
        }
        else {
          boardElements.put(pawn2Position, pawn);
        }
        break;
      default:
        break;
    }
  }
  
  //
  // Accesseurs
  //
  
  public Map<Integer, Element> boardElements(){
    return boardElements;
  }
  
  public int pawn1Position() {
    return pawn1Position;
  }
  
  public int pawn2Position() {
    return pawn2Position;
  }
}
