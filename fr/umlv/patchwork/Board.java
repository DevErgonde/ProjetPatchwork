import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum Element {
  PAWN1, PAWN2, PAWNS, LEATHER, BUTTON
}

public class Board{
  
  private final Map<Integer, Element> boardElements;
  
  //
  // Constructeurs
  //
  
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
  }
  
  //
  // Méthodes aidants à l'implémentation dans le constructeur
  //
  
  //
  // Méthodes
  //
  
  //
  // Accesseurs
  //
  
  public Map<Integer, Element> boardElements(){
    return boardElements;
  }
}